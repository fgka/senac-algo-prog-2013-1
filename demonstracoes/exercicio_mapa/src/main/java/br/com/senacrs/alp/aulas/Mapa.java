package br.com.senacrs.alp.aulas;

public interface Mapa<C, V> {
	
	V put(C chave, V valor);
	
	V get(C chave);
	
	V del(C chave);
}
