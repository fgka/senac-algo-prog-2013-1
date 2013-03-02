package br.com.senacrs.alp.aulas;

public class OperadorLogico {

	public static void main(String[] args) {

		int numerador = 0;
		int denominador = 0;

		numerador = 10;
		// seguro: &&
		if ((denominador != 0) && (numerador / denominador > 0)) {
			System.out.println("Não deve ser executado");
		} else {
			System.out
					.println("Primeiro teste bastou para avaliar toda a expressao");
		}
		// inseguro: &
		try {
			if ((denominador != 0) & (numerador / denominador > 0)) {
				System.out.println("Não deve ser executado");
			} else {
				System.out.println("Não deve ser executado");
			}
		} catch (ArithmeticException e) {
			System.out
					.println("Primeiro teste NAO bastou para avaliar toda a expressao e uma excecao foi lancada");
			e.printStackTrace();
		}
	}
}
