package test.artempvn.les04.data;

import java.time.LocalDateTime;
import by.artempvn.les04.entity.Gem;
import by.artempvn.les04.entity.Gem.GemType;

public class StaticData {
	public static final Gem gem1;
	public static final Gem gem2;

	static {
		Gem.Parameter parameters1 = new Gem.Parameter("White", 0, 15);
		Gem.Parameter parameters2 = new Gem.Parameter("Blue", 95, 4);
		LocalDateTime cutDate1 = LocalDateTime.parse("2002-05-30T09:00:00");
		LocalDateTime cutDate2 = LocalDateTime.parse("2012-12-25T12:00:00");
		gem1 = new Gem("p1", "Not defined", "Diamond", 20, parameters1,
				cutDate1, GemType.PRECIOUS);
		gem2 = new Gem("s1", "Australia", "Opal", 15, parameters2, cutDate2,
				GemType.SEMIPRECIOUS);
	}
}
