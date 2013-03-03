package br.com.senacrs.alp.aulas;

public class OperadorLogico {
	
	private static class ClasseVazia {
		
	}

	public static void main(String[] args) {

		testarIgualdadeImutaveis();
		testarIgualdadeObjetos();
	}

	private static void testarIgualdadeImutaveis() {

		Boolean val1Wrapper = null;
		Integer val2Wrapper = null;
		Double val3Wrapper = null;
		String val4Wrapper = null;

		//Boolean
		val1Wrapper = Boolean.TRUE;
		if (val1Wrapper == true) {
			System.out.println("Auto unboxing para Boolean");
		} else {
			System.out.println("Algo saiu errado");
		}
		//Integer
		val2Wrapper = Integer.valueOf(10);
		if (val2Wrapper == 10) {
			System.out.println("Auto unboxing para Integer");
		} else {
			System.out.println("Algo saiu errado");
		}
		//Double
		val3Wrapper = Double.valueOf(3.14);
		if (val3Wrapper == 3.14) {
			System.out.println("Auto unboxing para Double");
		} else {
			System.out.println("Algo saiu errado");
		}
		//String
		val4Wrapper = String.valueOf("str");
		if (val4Wrapper == "str") {
			System.out.println("Strings têm um tratamento especial em Java");
		} else {
			System.out.println("Algo saiu errado");
		}
	}

	private static void testarIgualdadeObjetos() {
		
		ClasseVazia obj1 = null;
		ClasseVazia obj2 = null;
		
		obj1 = new ClasseVazia();
		obj2 = new ClasseVazia();
		if (obj1 == obj2) {
			System.out.println("Não deve executar, pois não são o mesmo objeto");
		} else {
			System.out.println("Apesar de equivalentes, não são o mesmo objeto");			
		}
	}
}
