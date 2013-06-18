package br.com.senacrs.alp.aulas;

public class Factory {
	
	private static final Factory instancia = new Factory();
	
	private Factory() {
	}
	
	public ObterCabecalhoRequisicaoGet criarValidacao(ArquivoConfiguracao config) {
	
		ObterCabecalhoRequisicaoGet resultado = null;
		
		//implementar
		
		return resultado;
	}
	
	public static Factory getInstancia() {
		return instancia;
	}
}
