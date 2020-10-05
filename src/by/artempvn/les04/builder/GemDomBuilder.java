package by.artempvn.les04.builder;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import by.artempvn.les04.entity.Gem;
import by.artempvn.les04.entity.Gem.GemType;

public class GemDomBuilder extends AbstractGemBuilder {
	private static final String DEFAULT_ORIGIN = "Not defined";
	private static final Logger logger = LogManager
			.getLogger(GemDomBuilder.class);
	private DocumentBuilder docBuilder;

	public GemDomBuilder() {
		this.gems = new ArrayList<>();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			docBuilder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			logger.error("DOM parser error: " + e);
		}
	}

	public GemDomBuilder(List<Gem> gems) {
		super(gems);
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			docBuilder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			logger.error("DOM parser error: " + e);
		}
	}

	@Override
	public void buildListGems(String fileName) {
		Document doc = null;
		try {
			doc = docBuilder.parse(fileName);
			Element root = doc.getDocumentElement();
			NodeList preciousGemsList = root.getElementsByTagName(
					GemXmlTag.PRECIOUS_GEMSTONE.getValue());
			for (int i = 0; i < preciousGemsList.getLength(); i++) {
				Element gemElement = (Element) preciousGemsList.item(i);
				Gem gem = buildGem(gemElement);
				gem.setPreciousness(GemType.PRECIOUS);
				gems.add(gem);
			}
			NodeList semipreciousGemsList = root.getElementsByTagName(
					GemXmlTag.SEMIPRECIOUS_GEMSTONE.getValue());
			for (int i = 0; i < semipreciousGemsList.getLength(); i++) {
				Element gemElement = (Element) semipreciousGemsList.item(i);
				Gem gem = buildGem(gemElement);
				gem.setPreciousness(GemType.SEMIPRECIOUS);
				gems.add(gem);
			}
		} catch (SAXException e) {
			logger.error("DOM parser error: " + e);
		} catch (IOException e) {
			logger.error("IO error: " + e);
		}
	}

	private Gem buildGem(Element gemElement) {
		Gem gem = new Gem();
		gem.setId(gemElement.getAttribute(GemXmlTag.ID.getValue()));
		String origin = !gemElement.getAttribute(GemXmlTag.ORIGIN.getValue())
				.isBlank()
						? gemElement.getAttribute(GemXmlTag.ORIGIN.getValue())
						: DEFAULT_ORIGIN;
		gem.setOrigin(origin);
		gem.setName(
				getElementTextContent(gemElement, GemXmlTag.NAME.getValue()));
		gem.setValue(Integer.parseInt(
				getElementTextContent(gemElement, GemXmlTag.VALUE.getValue())));
		gem.setCutDate(LocalDateTime.parse(getElementTextContent(gemElement,
				GemXmlTag.CUT_DATE.getValue())));
		Gem.Parameter parameters = gem.getParameters();
		Element parametersElement = (Element) gemElement
				.getElementsByTagName(GemXmlTag.PARAMETERS.getValue()).item(0);
		parameters.setColor(getElementTextContent(parametersElement,
				GemXmlTag.COLOR.getValue()));
		parameters.setTransparency(Integer.parseInt(getElementTextContent(
				parametersElement, GemXmlTag.TRANSPARENCY.getValue())));
		parameters.setNumberOfFaces(Integer.parseInt(getElementTextContent(
				parametersElement, GemXmlTag.NUMBER_OF_FACES.getValue())));
		return gem;
	}

	private static String getElementTextContent(Element element,
			String elementName) {
		NodeList nList = element.getElementsByTagName(elementName);
		Node node = nList.item(0);
		String text = node.getTextContent();
		return text;
	}

}
