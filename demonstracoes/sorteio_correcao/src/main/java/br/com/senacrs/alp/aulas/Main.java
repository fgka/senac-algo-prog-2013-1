package br.com.senacrs.alp.aulas;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Main {
	

	private static final String LABELS_PROPERTIES = "/labels.properties";

	public static void main(String[] args) {
		
		DialogueLogic logic = null;
		Properties properties = null;
		DialogueUI diag = null;
		
		logic = getDialogueLogic();
		try {
			properties = getProperties();
			diag = DialogueUIFactory.getInstance().createDialogueUI(logic, properties);
			diag.getJFrame();
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

	private static DialogueLogic getDialogueLogic() {

		return new DialogueLogicImpl();
	}

	private static Properties getProperties() throws IOException {
		
		Properties result = null;
		InputStream input = null;
		
		result = new Properties(); 
		input = getPropertiesInputStream();
		result.load(input);

		return result;
	}

	private static InputStream getPropertiesInputStream() {
		
		InputStream result = null;
		
		result = Main.class.getResourceAsStream(LABELS_PROPERTIES);

		return result;
	}
}
