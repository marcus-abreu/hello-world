import java.util.Scanner;

public class Exercicio1 {
	
	public static void main(String[] args) {
		
		//Declara��o das vari�veis de entrada
		int a;
		int b;
		//String c;
		int c;
		//String d;
		int d;
		char e;
		boolean f;
		
		
		//Entrada de dados pelo usu�rio
		Scanner entrada = new Scanner(System.in);
		
		System.out.print("Digite um valor para a vari�vel A: ");
		a = entrada.nextInt();
		
		System.out.print("Digite um valor para a vari�vel B: ");
		b = entrada.nextInt();
		
		System.out.print("Digite um valor para a vari�vel C: ");
		//c = entrada.next();
		c = entrada.nextInt();
		
		System.out.print("Digite um valor para a vari�vel D: ");
		//d = entrada.next();
		d = entrada.nextInt();
		
		System.out.print("Digite um valor para a vari�vel E: ");
		e = entrada.next().charAt(0);
		
		System.out.print("Digite um valor para a vari�vel F (true ou false): ");
		f = entrada.nextBoolean();
		
		
		//Declara��o das vari�veis dos resultados
		double respostaA;
		double respostaB;
		boolean respostaC;
		int respostaD;
		boolean respostaE;
		boolean respostaF;
		boolean respostaG;
		String respostaH;
		
		
		//C�lculos dos resultados
		
		//int cConvertido;
		//cConvertido = Integer.parseInt(c);
		//respostaA = 2 * (b % cConvertido) + 3;
		
		respostaA = 2 * (b % c) + 3;
		System.out.println("O resultado de [[[ 2 * (b % c) + 3 ]]] � " + respostaA);
		
		respostaB = a * Math.pow(b, 1.0 / 3) * c - 1;
		System.out.println("O resultado de [[[ a * Math.pow(b, 1.0 / 3) * c - 1 ]]] � " + respostaB);
		
		respostaC = a == b;
		System.out.println("O resultado de [[[ a == b ]]] � " + respostaC);
		
		respostaD = c + d;
		System.out.println("O resultado de [[[ c + d ]]] � " + respostaD);
		
		respostaE = f = true;
		System.out.println("O resultado de [[[ f = true ]]] � " + respostaE);
		
		respostaF = f == true;
		System.out.println("O resultado de [[[ f == true ]]] � " + respostaF);
		
		respostaG = b >= c * 2;
		System.out.println("O resultado de [[[ b >= c * 2 ]]] � " + respostaG);
		
		respostaH = entrada.next();
		System.out.println("A primeira letra de " + respostaH + " � " + respostaH.charAt(0) );
		
	}

}