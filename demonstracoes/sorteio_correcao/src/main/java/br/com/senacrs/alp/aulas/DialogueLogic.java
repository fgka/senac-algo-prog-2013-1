package br.com.senacrs.alp.aulas;

public interface DialogueLogic {
	
	public static final String CLICK_SEP = ", ";
	
	int nextClickStartAtOne();
	
	void setMax(int value);
	
	String getText();
	
	void clearText();
}
