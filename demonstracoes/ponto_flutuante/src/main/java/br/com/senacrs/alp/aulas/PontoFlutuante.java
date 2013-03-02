package br.com.senacrs.alp.aulas;


public class PontoFlutuante {
	
	public static void main(String[] args) {
		
		precisaoFloat();
		precisaoDouble();
	}

	private static void precisaoFloat() {
		
		float acumulado = 0.0f;
		int i = 0;
		int inc = 0;
		int dif = 0;
		String msg = null;
		
		inc = 1000000;
		for (; i < Integer.MAX_VALUE - inc; i += inc) {
			acumulado += i;
		}
		dif = i - (int)acumulado;
		msg = String.format("Valor esperado (int) %d, (float) obtido %g, (int) diferenca %d", i, acumulado, dif);
		System.out.println(msg);
	}

	private static void precisaoDouble() {
		
		double acumulado = 0.0f;
		long i = 0;
		long inc = 0;
		long dif = 0;
		String msg = null;
		
		inc = 100000000000L;
		for (; i < Long.MAX_VALUE - inc; i += inc) {
			acumulado += i;
		}
		dif = i - (long)acumulado;
		msg = String.format("Valor esperado (long) %d, (double) obtido %g, (long) diferenca %d", i, acumulado, dif);
		System.out.println(msg);
	}
}
