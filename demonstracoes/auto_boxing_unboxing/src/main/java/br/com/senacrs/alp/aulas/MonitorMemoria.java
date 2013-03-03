package br.com.senacrs.alp.aulas;

public class MonitorMemoria implements Runnable {

	private static final long UM_SEGUNDO_EM_MILISECS_1000L = 1000l;
	private static final long VALOR_1024 = 1024;
	private Runtime runtime = Runtime.getRuntime();

	@Override
	public void run() {

		while (true) {
			try {
				Thread.sleep(UM_SEGUNDO_EM_MILISECS_1000L);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			escreverOcupacaoSystemStdOut();
		}
	}

	public void escreverOcupacaoSystemStdOut() {

		String msg = null;
		long[] ocupacao = null;

		ocupacao = obterMemoriaOcupadaBytesMegabytes();
		msg = String.format("Memoria ocupada %d MB, %d B", ocupacao[1],
				ocupacao[0]);
		System.out.println(msg);
	}

	private long[] obterMemoriaOcupadaBytesMegabytes() {

		long[] resultado = null;
		long total = 0l;
		long livre = 0l;

		total = this.runtime.totalMemory();
		livre = this.runtime.freeMemory();
		resultado = new long[2];
		// bytes
		resultado[0] = total - livre;
		// mega bytes
		resultado[1] = resultado[0] / (VALOR_1024 * VALOR_1024);

		return resultado;
	}
}
