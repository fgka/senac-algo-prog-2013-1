package br.com.senacrs.alp.aulas;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class MapaTest {
	
	private static MapaFactory factory = MapaFactory.getInstancia();
	private Mapa<String, Integer> obj = factory.criarMapa();
	
	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Before
	public void setUp() throws Exception {
		
		obj = factory.criarMapa();
		Assert.assertNotNull(obj);
	}

	@After
	public void tearDown() throws Exception {
		
		obj = null;
	}

	@Test
	public void testPut() {
		
		String chave = null;
		Integer valor = null;
		Integer obtido = null;
		Integer inicial = null;
		
		chave = "nada";
		valor = Integer.valueOf(0);
		inicial = obj.put(chave, valor);
		obtido = obj.get(chave);
		Assert.assertEquals(valor, obtido);
		Assert.assertNull(inicial);
	}

	@Test
	public void testPutChaveJaExistente() {
		
		String chave = null;
		Integer valorA = null;
		Integer valorB = null;
		Integer obtido = null;
		Integer substituido = null;
		
		chave = "nada";
		valorA = Integer.valueOf(0);
		valorB = Integer.valueOf(1);
		obj.put(chave, valorA);
		substituido = obj.put(chave, valorB);		
		obtido = obj.get(chave);
		Assert.assertEquals(valorB, obtido);
		Assert.assertEquals(valorA, substituido);
	}

	@Test
	public void testPutChaveNula() {
		
		Integer valor = null;
		
		valor = Integer.valueOf(0);
		exception.expect(IllegalArgumentException.class);
		obj.put(null, valor);
	}

	@Test
	public void testGet() {
		
		String chave = null;
		Integer obtido = null;
		Integer esperado = null;
		int tam = 0;
		
		tam = 5;
		popularMapa(tam);
		for (int i = 0; i < tam; i++) {
			chave = String.valueOf(i);
			esperado = Integer.valueOf(i);
			obtido = obj.get(chave);
			Assert.assertEquals(esperado, obtido);			
		}
	}

	private void popularMapa(int tam) {
		
		String chave = null;
		Integer valor = null;
		
		for (int i = 0; i < tam; i++) {
			chave = String.valueOf(i);
			valor = Integer.valueOf(i);
			obj.put(chave, valor);
		}
	}

	@Test
	public void testGetChaveNula() {
		
		exception.expect(IllegalArgumentException.class);
		obj.get(null);
	}

	@Test
	public void testGetChaveInexistente() {
		
		exception.expect(IllegalArgumentException.class);
		obj.get("inexistente");
	}
	
	@Test
	public void testDel() {
		
		String chave = null;
		Integer obtido = null;
		Integer esperado = null;
		int tam = 0;		
		
		tam = 7;
		popularMapa(tam);
		for (int i = 0; i < tam; i++) {
			chave = String.valueOf(i);
			esperado = Integer.valueOf(i);
			obtido = obj.del(chave);
			Assert.assertEquals(esperado, obtido);
			try {
				obj.get(chave);
				Assert.fail();
			} catch (IllegalArgumentException e) {
				// tudo ok
			}
		}
	}

	@Test
	public void testDelChaveNula() {
		
		exception.expect(IllegalArgumentException.class);
		obj.del(null);
	}

	@Test
	public void testDelChaveInexistente() {
		
		exception.expect(IllegalArgumentException.class);
		obj.del("inexistente");
	}
}
