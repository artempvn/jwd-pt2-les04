package by.artempvn.les04.handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXParseException;

public class XmlErrorHandler implements ErrorHandler {
	private static final String LINE_COLUMN_SEPARATOR = " : ";
	private static final String MESSAGE_SEPARATOR = " - ";
	private static final Logger logger = LogManager
			.getLogger(XmlErrorHandler.class);

	public void warning(SAXParseException e) {
		logger.warn(getLineAddress(e) + MESSAGE_SEPARATOR + e.getMessage());
	}

	public void error(SAXParseException e) {
		logger.error(getLineAddress(e) + MESSAGE_SEPARATOR + e.getMessage());
	}

	public void fatalError(SAXParseException e) {
		logger.fatal(getLineAddress(e) + MESSAGE_SEPARATOR + e.getMessage());
	}

	private String getLineAddress(SAXParseException e) {
		return e.getLineNumber() + LINE_COLUMN_SEPARATOR + e.getColumnNumber();
	}

}
