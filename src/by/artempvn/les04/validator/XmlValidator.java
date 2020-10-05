package by.artempvn.les04.validator;

import java.io.File;
import java.io.IOException;
import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import by.artempvn.les04.handler.XmlErrorHandler;

public class XmlValidator {
	private static final Logger logger = LogManager
			.getLogger(XmlValidator.class);

	public boolean validateXml(String xmlPath, String xsdPath) {
		boolean isCorrect = false;
		if (xmlPath != null && xsdPath != null) {
			String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
			SchemaFactory factory = SchemaFactory.newInstance(language);
			File xsdFile = new File(xsdPath);
			try {
				Schema schema = factory.newSchema(xsdFile);
				Validator validator = schema.newValidator();
				Source source = new StreamSource(xmlPath);
				XmlErrorHandler errorHandler = new XmlErrorHandler();
				validator.setErrorHandler(errorHandler);
				validator.validate(source);
				logger.info(xmlPath + " is valid.");
				isCorrect = true;
			} catch (SAXException | IOException e) {
				logger.error(xmlPath + " is not correct or valid because "
						+ e.getMessage());
			}
		}
		return isCorrect;
	}
}
