package br.com.senacrs.alp.aulas;

public class Factory {
	
	private static final Factory instancia = new Factory();
	
	private Factory() {
	}
	
	public ObterRequisicaoGet criarValidacao(ArquivoConfiguracao config) {
	
		ObterRequisicaoGet resultado = null;
		
		resultado = new MeuObterRequisicaoGet(config);
		
		return resultado;
	}
	
	public static Factory getInstancia() {
		return instancia;
	}
}
