package by.artempvn.les04.builder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.artempvn.les04.entity.Gem;
import by.artempvn.les04.entity.Gem.GemType;

public class GemStaxBuilder extends AbstractGemBuilder {
	private static final Logger logger = LogManager
			.getLogger(GemStaxBuilder.class);
	private static final String DEFAULT_ORIGIN = "Not defined";
	private static final String ELEMENT_GEMSTONE = "gemstone";
	private XMLInputFactory inputFactory;

	public GemStaxBuilder() {
		inputFactory = XMLInputFactory.newInstance();
	}

	public GemStaxBuilder(List<Gem> gems) {
		super(gems);
		inputFactory = XMLInputFactory.newInstance();
	}

	@Override
	public void buildListGems(String fileName) {
		FileInputStream inputStream = null;
		String name;
		try {
			inputStream = new FileInputStream(new File(fileName));
			XMLStreamReader reader = inputFactory
					.createXMLStreamReader(inputStream);
			while (reader.hasNext()) {
				int type = reader.next();
				if (type == XMLStreamConstants.START_ELEMENT) {
					name = reader.getLocalName();
					if (name.contains(ELEMENT_GEMSTONE)) {
						Gem gem = buildGem(reader);
						if (name.equals(
								GemXmlTag.PRECIOUS_GEMSTONE.getValue())) {
							gem.setPreciousness(GemType.PRECIOUS);
						} else if (name.equals(
								GemXmlTag.SEMIPRECIOUS_GEMSTONE.getValue())) {
							gem.setPreciousness(GemType.SEMIPRECIOUS);
						}
						gems.add(gem);
					}
				}
			}
		} catch (XMLStreamException e) {
			logger.error("XMLStream error: " + e);
		} catch (IOException e) {
			logger.error("IO error: " + e);
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (IOException e) {
				logger.error("Failed to close file: " + e);
			}
		}
	}

	private Gem buildGem(XMLStreamReader reader) throws XMLStreamException {
		Gem gem = new Gem();
		String namespaceUri = null;
		gem.setId(reader.getAttributeValue(namespaceUri,
				GemXmlTag.ID.getValue()));
		String origin = reader.getAttributeValue(namespaceUri,
				GemXmlTag.ORIGIN.getValue()) != null
						? reader.getAttributeValue(namespaceUri,
								GemXmlTag.ORIGIN.getValue())
						: DEFAULT_ORIGIN;
		gem.setOrigin(origin);
		CYCLE: while (reader.hasNext()) {
			int type = reader.next();
			switch (type) {
			case XMLStreamConstants.START_ELEMENT:
				Optional<GemXmlTag> xmlTag = Arrays
						.asList(GemXmlTag.values()).stream().filter(tag -> tag
								.getValue().equals(reader.getLocalName()))
						.findFirst();
				switch (xmlTag.get()) {
				case NAME:
					gem.setName(getXMLText(reader));
					break;
				case VALUE:
					gem.setValue(Integer.parseInt(getXMLText(reader)));
					break;
				case CUT_DATE:
					gem.setCutDate(LocalDateTime.parse(getXMLText(reader)));
					break;
				case PARAMETERS:
					gem.setParameters(getXmlParameters(reader));
					break;
				default:
					throw new XMLStreamException("Unknown element in tag Gem");
				}
				break;
			case XMLStreamConstants.END_ELEMENT:
				if (reader.getLocalName().contains(ELEMENT_GEMSTONE)) {
					break CYCLE;
				}
				break;
			}
		}
		return gem;
	}

	private Gem.Parameter getXmlParameters(XMLStreamReader reader)
			throws XMLStreamException {
		Gem.Parameter parameters = new Gem.Parameter();
		int type;
		CYCLE: while (reader.hasNext()) {
			type = reader.next();
			switch (type) {
			case XMLStreamConstants.START_ELEMENT:
				Optional<GemXmlTag> xmlTag = Arrays
						.asList(GemXmlTag.values()).stream().filter(tag -> tag
								.getValue().equals(reader.getLocalName()))
						.findFirst();
				switch (xmlTag.get()) {
				case COLOR:
					parameters.setColor(getXMLText(reader));
					break;
				case TRANSPARENCY:
					parameters.setTransparency(
							Integer.valueOf(getXMLText(reader)));
					break;
				case NUMBER_OF_FACES:
					parameters.setNumberOfFaces(
							Integer.valueOf(getXMLText(reader)));
					break;
				default:
					throw new XMLStreamException(
							"Unknown element in tag Parameters");
				}
				break;
			case XMLStreamConstants.END_ELEMENT:
				if (reader.getLocalName()
						.equals(GemXmlTag.PARAMETERS.getValue())) {
					break CYCLE;
				}
				break;
			}
		}
		return parameters;

	}

	private String getXMLText(XMLStreamReader reader)
			throws XMLStreamException {
		String text = null;
		if (reader.hasNext()) {
			reader.next();
			text = reader.getText();
		}
		return text;
	}
}
