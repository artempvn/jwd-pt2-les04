package by.artempvn.les04.factory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.artempvn.les04.builder.AbstractGemBuilder;
import by.artempvn.les04.builder.GemDomBuilder;
import by.artempvn.les04.builder.GemSaxBuilder;
import by.artempvn.les04.builder.GemStaxBuilder;
import by.artempvn.les04.exception.CustomException;

public class GemBuilderFactory {
	private static final Logger logger = LogManager
			.getLogger(GemBuilderFactory.class);

	private enum TypeParser {
		SAX, DOM, STAX;
	}

	public AbstractGemBuilder createGemBuilder(String typeParser)
			throws CustomException {
		TypeParser type = TypeParser.valueOf(typeParser.toUpperCase());
		logger.info("Parsing type is: " + typeParser);
		switch (type) {
		case SAX:
			return new GemSaxBuilder();
		case DOM:
			return new GemDomBuilder();
		case STAX:
			return new GemStaxBuilder();
		default:
			throw new CustomException("Wrong parsing type");
		}
	}

}
