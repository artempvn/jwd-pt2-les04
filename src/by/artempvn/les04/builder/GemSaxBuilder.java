package by.artempvn.les04.builder;

import java.io.IOException;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import by.artempvn.les04.entity.Gem;
import by.artempvn.les04.handler.GemHandler;
import by.artempvn.les04.handler.XmlErrorHandler;

public class GemSaxBuilder extends AbstractGemBuilder {
	private static final Logger logger = LogManager
			.getLogger(GemSaxBuilder.class);
	private GemHandler handler = new GemHandler();
	private XMLReader reader;

	public GemSaxBuilder() {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			SAXParser parser = factory.newSAXParser();
			reader = parser.getXMLReader();
		} catch (SAXException | ParserConfigurationException e) {
			logger.error("SAX parser error: " + e);
		}
		reader.setErrorHandler(new XmlErrorHandler());
		reader.setContentHandler(handler);
	}

	public GemSaxBuilder(List<Gem> gems) {
		super(gems);
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			SAXParser parser = factory.newSAXParser();
			reader = parser.getXMLReader();
		} catch (SAXException | ParserConfigurationException e) {
			logger.error("SAX parser error: " + e);
		}
		reader.setErrorHandler(new XmlErrorHandler());
		reader.setContentHandler(handler);
	}

	@Override
	public void buildListGems(String fileName) {
		try {
			reader.parse(fileName);
		} catch (SAXException e) {
			logger.error("SAX parser error: " + e);
		} catch (IOException e) {
			logger.error("IO error: " + e);
		}
		gems = handler.getGems();
	}

}
