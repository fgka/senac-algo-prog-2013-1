package br.com.senacrs.alp.aulas;

public class MapaFactory {
	
	private static final MapaFactory instancia = new MapaFactory();
	
	private MapaFactory() {
	}
	
	public <C, V> Mapa<C, V> criarMapa() {
		
		Mapa<C, V> resultado = null;
		
		//implementar
		
		return resultado;
	}
	
	public static MapaFactory getInstancia() {
		return instancia;
	}
}
