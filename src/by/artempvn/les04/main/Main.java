package by.artempvn.les04.main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.artempvn.les04.builder.AbstractGemBuilder;
import by.artempvn.les04.exception.CustomException;
import by.artempvn.les04.factory.GemBuilderFactory;

public class Main {
	private static final String XML_PATH = "input/gems.xml";
	private static final String PARSE_STAX = "STAX";
	private static final String PARSE_SAX = "SAX";
	private static final String PARSE_DOM = "DOM";
	private static final Logger logger = LogManager.getLogger(Main.class);

	public static void main(String... args) {
		GemBuilderFactory gemBuilderFactory = new GemBuilderFactory();
		AbstractGemBuilder builder;
		try {
			builder = gemBuilderFactory.createGemBuilder(PARSE_DOM);
			builder.buildListGems(XML_PATH);
			logger.info(builder.getGems().toString());
		} catch (CustomException ex) {
			logger.error("Wrong parsing type");
		}
	}

}
