package by.artempvn.les04.builder;

public enum GemXmlTag {

	GEMS("gems"), ID("id"), PRECIOUS_GEMSTONE("precious-gemstone"),
	SEMIPRECIOUS_GEMSTONE("semiprecious-gemstone"), ORIGIN("origin"),
	PARAMETERS("parameters"), NAME("name"), VALUE("value"),
	CUT_DATE("cut-date"), COLOR("color"), TRANSPARENCY("transparency"),
	NUMBER_OF_FACES("number-of-faces");

	private String value;

	private GemXmlTag(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
