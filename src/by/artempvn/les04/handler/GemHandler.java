package by.artempvn.les04.handler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import by.artempvn.les04.builder.GemXmlTag;
import by.artempvn.les04.entity.Gem;
import by.artempvn.les04.entity.Gem.GemType;

public class GemHandler extends DefaultHandler {
	private static final String ATTRIBUTE_ORIGIN = "ORIGIN";
	private static final String ATTRIBUTE_ID = "ID";
	private static final String ELEMENT_GEMSTONE = "gemstone";
	private static final String DEFAULT_ORIGIN = "Not defined";
	private static final Logger logger = LogManager.getLogger(GemHandler.class);
	private List<Gem> gems;
	private Gem current;
	private GemXmlTag currentTag;
	private EnumSet<GemXmlTag> withText;

	public GemHandler() {
		gems = new ArrayList<>();
		withText = EnumSet.range(GemXmlTag.NAME, GemXmlTag.NUMBER_OF_FACES);
	}

	public List<Gem> getGems() {
		return gems;
	}

	public void startElement(String uri, String localName, String qName,
			Attributes attrs) {
		if (qName.contains(ELEMENT_GEMSTONE)) {
			current = new Gem();
			if (qName.equals(GemXmlTag.PRECIOUS_GEMSTONE.getValue())) {
				current.setPreciousness(GemType.PRECIOUS);
			} else if (qName
					.equals(GemXmlTag.SEMIPRECIOUS_GEMSTONE.getValue())) {
				current.setPreciousness(GemType.SEMIPRECIOUS);
			}
			for (int i = 0; i < attrs.getLength(); i++) {
				String attributeName = attrs.getQName(i).toUpperCase();
				switch (attributeName) {
				case ATTRIBUTE_ID:
					current.setId(attrs.getValue(i));
					break;
				case ATTRIBUTE_ORIGIN:
					current.setOrigin(attrs.getValue(i));
					break;
				default:
					logger.error("Wrong attribute: " + attributeName);
				}
			}
			if (current.getOrigin() == null) {
				current.setOrigin(DEFAULT_ORIGIN);
			}
		} else {
			Optional<GemXmlTag> temp = Arrays.asList(GemXmlTag.values())
					.stream().filter(tag -> tag.getValue().equals(qName))
					.findFirst();
			if (temp.isPresent()) {
				if (withText.contains(temp.get())) {
					currentTag = temp.get();
				}
			} else {
				logger.error("Wrong tag: " + qName);
			}
		}
	}

	public void endElement(String uri, String localName, String qName) {
		if (qName.contains(ELEMENT_GEMSTONE)) {
			gems.add(current);
		}
	}

	public void characters(char[] ch, int start, int length) {
		String data = new String(ch, start, length).strip();
		if (currentTag != null) {
			switch (currentTag) {
			case NAME:
				current.setName(data);
				break;
			case VALUE:
				current.setValue(Integer.parseInt(data));
				break;
			case CUT_DATE:
				current.setCutDate(LocalDateTime.parse(data));
				break;
			case COLOR:
				current.getParameters().setColor(data);
				break;
			case TRANSPARENCY:
				current.getParameters().setTransparency(Integer.parseInt(data));
				break;
			case NUMBER_OF_FACES:
				current.getParameters()
						.setNumberOfFaces(Integer.parseInt(data));
				break;
			default:
				throw new EnumConstantNotPresentException(
						currentTag.getDeclaringClass(), currentTag.name());
			}
		}
		currentTag = null;
	}
}
