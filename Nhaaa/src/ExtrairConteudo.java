

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.nextframework.controller.resource.Resource;

import com.sliic.framework.office.ExcelBuilder;
import com.sliic.framework.types.MimeTypesEnum;
import com.sliic.framework.util.SliicUtil;
import com.sliic.framework.util.XMLExtractor;

public class ExtrairConteudo {
	
	private static Map<String, String> categoriasMap = SliicUtil.collections.createFastMap(
			"Geral", "0",			//GERAL			http://www.publishnews.com.br/ranking/mensal/0/2010/9/0/0
			"Ficção", "9",			//FICCAO		http://www.publishnews.com.br/ranking/mensal/9/2010/9/0/0
			"Não Ficção", "13",		//N FICCAO		http://www.publishnews.com.br/ranking/mensal/13/2010/9/0/0
			"Autoajuda", "5",		//AUTOAJUDA		http://www.publishnews.com.br/ranking/mensal/5/2010/9/0/0
			"Infantil", "11",		//INFANTO		http://www.publishnews.com.br/ranking/mensal/11/2010/9/0/0
			"Negócios", "8" );		//NEGOCIOS		http://www.publishnews.com.br/ranking/mensal/8/2010/9/0/0
	
	private static Calendar dataInicio = new GregorianCalendar(2010, Calendar.SEPTEMBER, 1);	//INCIO	http://www.publishnews.com.br/ranking/mensal/8/2010/9/0/0
	private static Calendar dataFim = new GregorianCalendar(2017, Calendar.SEPTEMBER, 1);		//FIM	http://www.publishnews.com.br/ranking/mensal/8/2017/9/0/0
	//private static Calendar dataFim = new GregorianCalendar(2010, Calendar.OCTOBER, 1);			//FIM	http://www.publishnews.com.br/ranking/mensal/8/2017/9/0/0
	
	public static void main(String[] args) throws Exception {
		
		ExcelBuilder excelBuilder = new ExcelBuilder();
		excelBuilder.addDefaultHeaderStyle(null,true,false,null,Color.WHITE,Color.BLACK);
		
		for (String categoria : categoriasMap.keySet()) {
			
			List<Editora> editoasCategoria = new ArrayList<Editora>();
			
			Calendar data = dataInicio;
			do{
			
				System.out.println("Categoria: " + categoria + " Mês: " + SliicUtil.calendar.getWithMask(data, "yyyy/MM"));
				String response = sendRequest(categoria, data);
				
				List<Editora> editorasMes = obtemEditorasMes(response, data);
				addListaTotal(editoasCategoria, editorasMes);
				
				data = SliicUtil.calendar.cloneAddMonth(data, 1);
			}while(data.before(dataFim) || SliicUtil.calendar.equals(data, dataFim));
			
			editoasCategoria = SliicUtil.collections.sortCollections("qtd", editoasCategoria, true);
			
			excelBuilder
				.addSheet(categoria, editoasCategoria, Editora.class)
				.addColumn("grupo", "Grupo")
				.addColumn("nome", "Nome")
				.addColumn("qtd", "Qtd");
		}
		
		byte[] rendered = excelBuilder.renderAsByteArray();
		Resource resource = new Resource(MimeTypesEnum.XLSAPPLICATION_EXCEL.getContentType(), "Editoras.xls", rendered);
		SliicUtil.file.gravaArquivo("D:/", resource);
	}

	private static void addListaTotal(List<Editora> editoasCategoria, List<Editora> editorasMes) {
		for (Editora editoraMes : editorasMes) {
			Editora editoraExistente = null;
			for (Editora editora : editoasCategoria) {
				if (editora.getNome().equals(editoraMes.getNome())) {
					editoraExistente = editora;
					break;
				}
			}
			if (editoraExistente != null) {
				editoraExistente.add(editoraMes.getQtd());
			}else{
				editoasCategoria.add(editoraMes);
			}
		}
	}
	
	private static String sendRequest(String categoria, Calendar data) throws Exception{
		URL url = new URL("http://www.publishnews.com.br/ranking/mensal/" + categoriasMap.get(categoria) + "/" + SliicUtil.calendar.getWithMask(data, "yyyy/MM") + "/0/0");
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.connect();
		InputStream inputStream = connection.getInputStream();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		
		try {
			byte[] buf = new byte[4096];
			int len;
			while ((len = inputStream.read(buf)) > 0){
				outputStream.write(buf, 0, len);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			inputStream.close();
			outputStream.close();
		}  
		
		byte[] bytes = outputStream.toByteArray();
		return new String(bytes);
	}

	private static List<Editora> obtemEditorasMes(String response, Calendar data) throws Exception {
		
		List<Editora> editorasTodas = new ArrayList<Editora>();
		
		int indexIni = response.indexOf("<div class=\"pn-ranking-grupos-corpo clearfix\">");
		if (indexIni == -1) {
			return editorasTodas;
		}
		int indexFim = response.indexOf("<div class=\"pn-colunas-06x07\">", indexIni);
		if (indexIni == -1) {
			return editorasTodas;
		}
		String response2 = response.substring(indexIni, indexFim-22);
		response2 = SliicUtil.web.unescapeHTML(response2);
		response2 = response2.replace("&", "e");
		SliicUtil.file.gravaArquivo("D:/teste.xml", response2.getBytes());
		
		XMLExtractor extractor = new XMLExtractor(response2, "", Locale.ENGLISH);
		List<XMLExtractor> extractorsItens = extractor.obtemList("//div[@class='pn-ranking-grupo clearfix']");
		for (XMLExtractor extractorItem : extractorsItens) {
			List<Editora> editoras = obtemEditoras(data, extractorItem);
			editorasTodas.addAll(editoras);
		}
		
		return editorasTodas;
	}

	private static List<Editora> obtemEditoras(Calendar data, XMLExtractor extractorItem) throws Exception {
		
		String nomeGrupo = extractorItem.obtemString(".//div[@class='pn-ranking-grupo-nome']/a");
		Integer qtdGrupo = extractorItem.obtemInteger(".//div[@class='pn-ranking-grupo-volume']");
		List<XMLExtractor> subEditoras = extractorItem.obtemList(".//div[@class='pn-ranking-editora clearfix']");
		
		if (SliicUtil.collections.isEmpty(subEditoras)){
			return SliicUtil.collections.createFastList(new Editora(nomeGrupo, nomeGrupo, qtdGrupo));
		}
		
		List<Editora> editoras = new ArrayList<Editora>();
		for (XMLExtractor subEditora : subEditoras) {
			String nome= subEditora.obtemString(".//div[@class='pn-ranking-editora-nome']/a");
			Integer qtd = subEditora.obtemInteger(".//div[@class='pn-ranking-editora-volume']");
			editoras.add(new Editora(nome, nomeGrupo, qtd));
		}
		
		return editoras;
	}
	
	public static class Editora{
		String nome;
		String grupo;
		Integer qtd;
		public Editora(String nome, String grupo, Integer qtd) {
			this.nome = nome;
			this.grupo = grupo;
			this.qtd = qtd;
		}
		public String getNome() {
			return nome;
		}
		public String getGrupo() {
			return grupo;
		}
		public Integer getQtd() {
			return qtd;
		}
		public void add(Integer qtd2) {
			this.qtd += qtd2;
		}
	}

}
