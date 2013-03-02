package br.com.senacrs.alp.aulas;


public class SemAutoboxing {
	
	public static void main(String[] args) {
		
		consumirMemoriaComAutoBoxing();		
	}

	private static void consumirMemoriaComAutoBoxing() {

		Double var = null;
		double varVal = 0.0;
		
		System.out.println("Abra o gerenciador de tarefas ou outro monitor de memoria");
		var = Double.valueOf(2.0);
		varVal = var.doubleValue();
		while (true) {
			varVal *= varVal;
		}
	}
}
