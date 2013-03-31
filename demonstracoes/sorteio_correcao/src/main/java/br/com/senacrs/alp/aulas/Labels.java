package br.com.senacrs.alp.aulas;

public enum Labels {
	
	NEXT("btNext"),
	CLEAR("btClear"),
	MAX("lblMax"),
	PICKED("lblPicked"),
	TITLE("lblTitle"),
	;
	
	private final String tag;
	
	private Labels(String tag) {

		this.tag = tag;
	}

	public String getTag() {
		
		return this.tag;
	}
	
	@Override
	public String toString() {

		return getTag();
	}
}
