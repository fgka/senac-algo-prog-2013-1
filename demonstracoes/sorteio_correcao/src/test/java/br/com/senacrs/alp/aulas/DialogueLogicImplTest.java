package br.com.senacrs.alp.aulas;

import java.util.Random;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class DialogueLogicImplTest {
	
	private final Random rand = new Random(System.currentTimeMillis());
	private int MAX = rand.nextInt(90) + 10;
	private int AMOUNT_NEXT = rand.nextInt(90) + 10;
	private DialogueLogic logic = null;

	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Before
	public void setUp() throws Exception {
		
		logic = new DialogueLogicImpl();
	}

	@After
	public void tearDown() throws Exception {

		logic = null;
	}

	@Test
	public void testNextClickNoMaxSet() {		
	
		exception.expect(IllegalStateException.class);
		logic.nextClickStartAtOne();
	}

	@Test
	public void testSetMaxNegative() {
				
		exception.expect(IllegalArgumentException.class);
		logic.setMax(-MAX);		
	}

	@Test
	public void testSetMaxZero() {
				
		exception.expect(IllegalArgumentException.class);
		logic.setMax(0);		
	}

	@Test
	public void testNextClickMaxOne() {		

		int max = 0;
		int result = 0;
		
		max = 1;
		logic.setMax(max);
		result = logic.nextClickStartAtOne();
		Assert.assertEquals(1, result);
	}

	@Test
	public void testGetTextNothing() {
		
		String result = null;
		
		result = logic.getText();
		Assert.assertNotNull(result);
		Assert.assertTrue(result.isEmpty());
	}

	@Test
	public void testGetText() {
		
		checkGetText();
	}

	private void checkGetText() {
		String result = null;
		StringBuilder expected = null;
		int click = 0;
		
		logic.setMax(MAX);		
		expected = new StringBuilder();
		click = logic.nextClickStartAtOne();
		expected.append(String.valueOf(click));
		for (int i = 1; i < AMOUNT_NEXT; i++) {
			click = logic.nextClickStartAtOne();
			expected.append(DialogueLogic.CLICK_SEP);			
			expected.append(String.valueOf(click));			
		}
		result = logic.getText();
		Assert.assertNotNull(result);
		Assert.assertEquals(expected.toString(), result);
	}

	@Test
	public void testClear() {
		
		String result = null;
		
		logic.setMax(MAX);
		for (int i = 0; i < AMOUNT_NEXT; i++) {
			logic.nextClickStartAtOne();
		}
		logic.clearText();
		result = logic.getText();		
		Assert.assertNotNull(result);
		Assert.assertTrue(result, result.isEmpty());
	}

	@Test
	public void testClearPlusMoreClicks() {
		
		logic.clearText();
		checkGetText();
	}
}
