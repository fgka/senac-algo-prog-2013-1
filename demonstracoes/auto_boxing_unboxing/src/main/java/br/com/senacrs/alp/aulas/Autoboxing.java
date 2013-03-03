package br.com.senacrs.alp.aulas;


public class Autoboxing {
	
	public static void main(String[] args) {
		
		MonitorMemoria monitor = null;
		Thread thread = null; 
		
		monitor = new MonitorMemoria();
		monitor.escreverOcupacaoSystemStdOut();
		thread = new Thread(monitor);
		thread.start();
		consumirMemoriaComAutoBoxing();		
	}

	private static void consumirMemoriaComAutoBoxing() {

		Double var = null;
		
		var = 2.0;
		while (true) {
			var *= var;
		}
	}
}
