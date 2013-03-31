package br.com.senacrs.alp.aulas;

import java.util.Random;


public class DialogueLogicImpl implements DialogueLogic {
	
	private final Random rand = new Random(System.currentTimeMillis());
	private int max = -1;
	private StringBuilder text = new StringBuilder();
	
	public DialogueLogicImpl() {

		super();
	}

	@Override
	public int nextClickStartAtOne() {
		
		int result = 0;
		
		if (max < 0) {
			throw new IllegalStateException();
		}
		result = rand.nextInt(max) + 1;
		addClickToText(result);

		return result;
	}

	private void addClickToText(int value) {

		if (text.length() > 0) {
			text.append(DialogueLogicImpl.CLICK_SEP);
		}
		text.append(String.valueOf(value));
		
	}

	@Override
	public void setMax(int value) {

		if (value <= 0) {
			throw new IllegalArgumentException();
		}
		max = value;
	}

	@Override
	public String getText() {
		
		return text.toString();
	}

	@Override
	public void clearText() {
		
		text.setLength(0);		
	}
}
