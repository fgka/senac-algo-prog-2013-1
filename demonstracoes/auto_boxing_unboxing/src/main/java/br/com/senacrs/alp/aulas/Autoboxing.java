package br.com.senacrs.alp.aulas;


public class Autoboxing {
	
	public static void main(String[] args) {
		
		consumirMemoriaComAutoBoxing();		
	}

	private static void consumirMemoriaComAutoBoxing() {

		Double var = null;
		
		System.out.println("Abra o gerenciador de tarefas ou outro monitor de memoria");
		var = 2.0;
		while (true) {
			var *= var;
		}
	}
}
