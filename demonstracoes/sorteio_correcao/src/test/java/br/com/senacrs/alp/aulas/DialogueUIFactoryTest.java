package br.com.senacrs.alp.aulas;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Properties;
import java.util.Random;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

public class DialogueUIFactoryTest {

	private static final Random rand = new Random(System.currentTimeMillis());
	private static final int MAX_VALUE = rand.nextInt(90) + 10;
	private static final String TEXT_AREA_CONTENT_TEXT = "TEXT";

	@Rule
	public ExpectedException exception = ExpectedException.none();

	private final DialogueUIFactory factory = DialogueUIFactory.getInstance();
	private DialogueLogic logic = Mockito.mock(DialogueLogic.class);
	private Properties properties = Mockito.mock(Properties.class);

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFormBuilderNullDialogueLogic() {

		exception.expect(IllegalArgumentException.class);
		factory.createDialogueUI(null, properties);
	}

	@Test
	public void testFormBuilderNullProperties() {

		exception.expect(IllegalArgumentException.class);
		factory.createDialogueUI(logic, null);
	}

	@Test
	public void testFormBuilderRegular() {

		DialogueUI obj = null;

		obj = factory.createDialogueUI(logic, properties);
		Assert.assertNotNull(obj);
	}

	@Test
	public void testBuildContentProperties() {

		DialogueUI obj = null;

		obj = factory.createDialogueUI(logic, properties);
		perparePropertiesMock(Labels.TITLE);
		obj.getJFrame();
		verifyPropertiesMock(Labels.TITLE);
	}

	private void perparePropertiesMock(Labels... excludeList) {

		sort(excludeList);		
		for (Labels l : Labels.values()) {
			if (Arrays.binarySearch(excludeList, l) < 0) {
				Mockito.when(properties.getProperty(l.getTag())).thenReturn(
						l.name());
			}
		}
	}

	private void sort(Labels[] excludeList) {

		Comparator<Labels> lblsComp = null;
		
		lblsComp = getLabelsComparator();
		Arrays.sort(excludeList, lblsComp);
	}

	private Comparator<Labels> getLabelsComparator() {
		
		Comparator<Labels> result = null;
		
		result = new Comparator<Labels>() {
			
			@Override
			public int compare(Labels o1, Labels o2) {
				
				int result = 0;
				
				if ((o1 == null) && (o2 == null)) {
					result = 0;
				} else if ((o1 == null) && (o2 != null)) {
					result = -1;
				} else if ((o1 != null) && (o2 == null)) {
					result = 1;
				} else {
					result = o1.ordinal() - o2.ordinal();
				}
				
				return result;
			}
		};
		
		return result;
	}

	private void verifyPropertiesMock(Labels... excludeList) {

		sort(excludeList);
		for (Labels l : Labels.values()) {
			if (Arrays.binarySearch(excludeList, l) < 0) {
				Mockito.verify(properties).getProperty(l.getTag());
			}
		}
	}

	@Test
	public void testClickNext() {

		DialogueUI obj = null;

		perpareLogicMock();
		perparePropertiesMock();
		obj = factory.createDialogueUI(logic, properties);
		obj.getMax().setText(String.valueOf(MAX_VALUE));
		obj.getNext().doClick();
		verifyLogicMock(1);
		Assert.assertEquals(TEXT_AREA_CONTENT_TEXT, obj.getPicked().getText());
	}

	private void perpareLogicMock() {
		
		Mockito.when(logic.getText()).thenReturn(TEXT_AREA_CONTENT_TEXT);		
	}

	private void verifyLogicMock(int getTextAmount) {
		
		Mockito.verify(logic).setMax(MAX_VALUE);
		Mockito.verify(logic).nextClickStartAtOne();
		Mockito.verify(logic, Mockito.times(getTextAmount)).getText();
	}


	@Test
	public void testClickClear() {

		DialogueUI obj = null;

		perpareLogicMock();
		perparePropertiesMock();
		obj = factory.createDialogueUI(logic, properties);
		obj.getMax().setText(String.valueOf(MAX_VALUE));
		obj.getNext().doClick();
		obj.getClear().doClick();
		verifyLogicMock(2);
	}
}
