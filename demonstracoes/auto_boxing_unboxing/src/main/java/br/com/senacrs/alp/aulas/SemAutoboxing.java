package br.com.senacrs.alp.aulas;


public class SemAutoboxing {
	
	public static void main(String[] args) {
		
		
		MonitorMemoria monitor = null;
		Thread thread = null; 
		
		monitor = new MonitorMemoria();
		monitor.escreverOcupacaoSystemStdOut();
		thread = new Thread(monitor);
		thread.start();
		consumirMemoriaSemAutoBoxing();		
	}

	private static void consumirMemoriaSemAutoBoxing() {

		Double var = null;
		double varVal = 0.0;
		
		var = Double.valueOf(2.0);
		varVal = var.doubleValue();
		while (true) {
			varVal *= varVal;
		}
	}
}
