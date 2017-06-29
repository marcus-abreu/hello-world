import java.util.Scanner;

public class ExemploB {
	
	public static void main(String[] args) {

		//Declara��o de vari�vel
		int N1, N2, soma, diferenca, produto;
		double quadrado, divisao, raiz2, raiz3;

		// Entrada de dados
		Scanner entrada = new Scanner(System.in);
		
		System.out.println("Entrada de dados:");
		System.out.print("Entre com o primeiro valor:");
		N1 = entrada.nextInt();
		
		System.out.print("Entre com o segundo valor:");
		N2 = entrada.nextInt();

		// c�lculos
		soma = N1 + N2;
		diferenca = N1 - N2;
		produto = N1 * N2;
		divisao = (double) N1 / N2;
		quadrado = Math.pow(N1, 2);
		raiz2 = Math.sqrt(N1);
		raiz3 = Math.pow(N2, 1.0 / 3);

		// sa�da de dados
		System.out.println("\n Resultados: ");
		System.out.print("\nA soma � " + soma);
		System.out.print("\nA diferen�a � " + diferenca);
		System.out.print("\nO produto � " + produto);
		System.out.print("\nA divis�o � " + divisao);
		System.out.print("\nO quadrado de " + N1 + " �: " + quadrado);
		System.out.print("\nA raiz quadrada de " + N1 + " �: " + raiz2);
		System.out.print("\nA raiz c�bica de " + N2 + " �: " + raiz3 + "\n");
		
		entrada.close();

	}
	
}
