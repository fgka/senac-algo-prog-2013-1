package br.com.senacrs.alp.aulas;


public class PontoFlutuanteRepresentacao {
	
	public static void main(String[] args) {
		
		representacaoFloat();
		representacaoDouble();
	}

	private static void representacaoFloat() {
		
		float valorInicial = 0.0f;
		float valorFinal = 0.0f;
		float diferenca = 0.0f;
		float esperado = 0.0f;
		int total = 0;
		String msg = null;		
		
		total = Integer.MAX_VALUE;
		valorInicial = total;
		for (int i = 0; i < total; i++) {
			valorFinal = valorInicial + 1;			
		}
		diferenca = valorFinal - valorInicial;
		esperado = total;
		msg = String.format("Valor inicial (float) %g, (float) final %g, (float) diferenca %g, (float) esperado %g", valorInicial, valorFinal, diferenca, esperado);
		System.out.println(msg);
	}

	private static void representacaoDouble() {
		
		double valorInicial = 0.0;
		double valorFinal = 0.0;
		double diferenca = 0.0;
		double esperado = 0.0;
		long total = 0L;
		String msg = null;		
		
		total = Integer.MAX_VALUE;
		valorInicial = Long.MAX_VALUE;
		for (int i = 0; i < total; i++) {
			valorFinal = valorInicial + 1;			
		}
		diferenca = valorFinal - valorInicial;
		esperado = total;
		msg = String.format("Valor inicial (double) %g, (double) final %g, (double) diferenca %g, (float) esperado %g", valorInicial, valorFinal, diferenca, esperado);
		System.out.println(msg);
	}
}
