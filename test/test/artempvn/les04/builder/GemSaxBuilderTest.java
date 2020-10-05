package test.artempvn.les04.builder;

import static org.testng.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import by.artempvn.les04.builder.GemSaxBuilder;
import by.artempvn.les04.entity.Gem;
import test.artempvn.les04.data.StaticData;

public class GemSaxBuilderTest {
	GemSaxBuilder builder;
	List<Gem> gems;

	@BeforeClass
	public void setUp() {
		builder = new GemSaxBuilder();
		gems = new ArrayList<>();
		gems.add(StaticData.gem1);
		gems.add(StaticData.gem2);
	}

	@Test
	public void buildListGemsTest() {
		builder.buildListGems("input/test_correct.xml");
		List<Gem> actual = builder.getGems();
		List<Gem> expected = gems;
		assertEquals(actual, expected, " Test failed as...");
	}

	@AfterClass
	public void tierDown() {
		builder = null;
		gems = null;
	}
}
