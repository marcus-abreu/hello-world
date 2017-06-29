import java.util.Scanner;

public class Exercicio1 {
	
	public static void main(String[] args) {
		
		//Declaração das variáveis de entrada
		int a;
		int b;
		//String c;
		int c;
		//String d;
		int d;
		char e;
		boolean f;
		
		
		//Entrada de dados pelo usuário
		Scanner entrada = new Scanner(System.in);
		
		System.out.print("Digite um valor para a variável A: ");
		a = entrada.nextInt();
		
		System.out.print("Digite um valor para a variável B: ");
		b = entrada.nextInt();
		
		System.out.print("Digite um valor para a variável C: ");
		//c = entrada.next();
		c = entrada.nextInt();
		
		System.out.print("Digite um valor para a variável D: ");
		//d = entrada.next();
		d = entrada.nextInt();
		
		System.out.print("Digite um valor para a variável E: ");
		e = entrada.next().charAt(0);
		
		System.out.print("Digite um valor para a variável F (true ou false): ");
		f = entrada.nextBoolean();
		
		
		//Declaração das variáveis dos resultados
		double respostaA;
		double respostaB;
		boolean respostaC;
		int respostaD;
		boolean respostaE;
		boolean respostaF;
		boolean respostaG;
		String respostaH;
		
		
		//Cálculos dos resultados
		
		//int cConvertido;
		//cConvertido = Integer.parseInt(c);
		//respostaA = 2 * (b % cConvertido) + 3;
		
		respostaA = 2 * (b % c) + 3;
		System.out.println("O resultado de [[[ 2 * (b % c) + 3 ]]] é " + respostaA);
		
		respostaB = a * Math.pow(b, 1.0 / 3) * c - 1;
		System.out.println("O resultado de [[[ a * Math.pow(b, 1.0 / 3) * c - 1 ]]] é " + respostaB);
		
		respostaC = a == b;
		System.out.println("O resultado de [[[ a == b ]]] é " + respostaC);
		
		respostaD = c + d;
		System.out.println("O resultado de [[[ c + d ]]] é " + respostaD);
		
		respostaE = f = true;
		System.out.println("O resultado de [[[ f = true ]]] é " + respostaE);
		
		respostaF = f == true;
		System.out.println("O resultado de [[[ f == true ]]] é " + respostaF);
		
		respostaG = b >= c * 2;
		System.out.println("O resultado de [[[ b >= c * 2 ]]] é " + respostaG);
		
		respostaH = entrada.next();
		System.out.println("A primeira letra de " + respostaH + " é " + respostaH.charAt(0) );
		
	}

}