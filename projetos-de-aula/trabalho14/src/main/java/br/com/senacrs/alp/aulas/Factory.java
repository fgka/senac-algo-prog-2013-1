package br.com.senacrs.alp.aulas;

public class Factory {
	
	private static final Factory instancia = new Factory();
	
	private Factory() {
	}
	
	public ObterPathRequisicaoGet criarValidacao() {
	
		ObterPathRequisicaoGet resultado = null;
		
		//implementar
		
		return resultado;
	}
	
	public static Factory getInstancia() {
		return instancia;
	}
}
