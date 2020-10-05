package test.artempvn.les04.validator;

import org.testng.annotations.Test;
import by.artempvn.les04.validator.XmlValidator;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.AfterClass;

public class XmlValidatorTest {

	XmlValidator xmlValidator;

	@BeforeClass
	public void setUp() {
		xmlValidator = new XmlValidator();
	}

	@Test(dataProvider = "validateXmlTest")
	public void validateXmlTest(String xmlPath, String xsdPath,
			boolean expected) {
		boolean actual = xmlValidator.validateXml(xmlPath, xsdPath);
		assertEquals(actual, expected, " Test failed as...");
	}

	@DataProvider
	public Object[][] validateXmlTest() {
		return new Object[][] {
				{ "input/test_correct.xml", "input/gems.xsd", true },
				{ "input/test_incorrect.xml", "input/gems.xsd", false },
				{ null, "input/gems.xsd", false },
				{ "input/test_correct.xml", null, false } };
	}

	@AfterClass
	public void tierDown() {
		xmlValidator = null;
	}
}
