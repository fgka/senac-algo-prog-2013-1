package br.com.senacrs.alp.aulas;

import java.util.Random;


public class PassagemArgumentos {
	
	private static final Random rand = new Random(System.currentTimeMillis());
	
	private static class MinhaClasse {
		
		public int valorA = 0;
		public int valorB = 0;
	}
	
	public static void main(String[] args) {
				
		demonstrarPassagemPorValor();		
		demonstrarPassagemPorReferencia();
	}

	private static void demonstrarPassagemPorValor() {

		String escopo = null;
		int valorA = 0;
		int valorB = 0;
		
		System.out.println("Demostracao de passagem de argumentos por valor");
		escopo = "demonstrarPassagemPorValor";
		//valores quaisquer
		valorA = rand.nextInt();
		valorB = rand.nextInt();		
		imprimirValores(escopo, "inicial", valorA, valorB);
		//modificar argumentos
		modificarArgumentos(valorA, valorB);
		//não devem ser modificados
		imprimirValores(escopo, "final", valorA, valorB);
		System.out.println();
	}

	private static void imprimirValores(
			String escopo, 
			String prefixo,
			int valorA, 
			int valorB) {
		
		String msg = null;
		
		msg = String.format("[%s] : %s -- valores: %d e %d", escopo, prefixo, valorA, valorB);
		System.out.println(msg);
	}

	private static void modificarArgumentos(int valorA, int valorB) {

		String escopo = null;
		
		escopo = "modificarArgumentos";
		imprimirValores(escopo, "inicial", valorA, valorB);
		//modificar os valores
		valorA = rand.nextInt();
		valorB = rand.nextInt();
		
		imprimirValores(escopo, "final", valorA, valorB);
	}

	private static void demonstrarPassagemPorReferencia() {

		String escopo = null;
		MinhaClasse objeto = null;
		
		System.out.println("Demostracao de passagem de argumentos por referencia");
		escopo = "demonstrarPassagemPorReferencia";
		//valores quaisquer
		objeto = new MinhaClasse();
		objeto.valorA = rand.nextInt();
		objeto.valorB = rand.nextInt();		
		imprimirValores(escopo, "inicial", objeto.valorA, objeto.valorB);
		//modificar argumentos
		modificarArgumentos(objeto);
		//não devem ser modificados
		imprimirValores(escopo, "final", objeto.valorA, objeto.valorB);
		System.out.println();
	}

	private static void modificarArgumentos(MinhaClasse objeto) {
		String escopo = null;
		
		escopo = "modificarArgumentos";
		imprimirValores(escopo, "inicial", objeto.valorA, objeto.valorB);
		//modificar os valores
		objeto.valorA = rand.nextInt();
		objeto.valorB = rand.nextInt();		
		
		imprimirValores(escopo, "final", objeto.valorA, objeto.valorB);
	}
}
