package by.artempvn.les04.builder;

import java.util.ArrayList;
import java.util.List;
import by.artempvn.les04.entity.Gem;

public abstract class AbstractGemBuilder {
	protected List<Gem> gems;

	public AbstractGemBuilder() {
		gems = new ArrayList<>();
	}

	public AbstractGemBuilder(List<Gem> gems) {
		this.gems = gems;
	}

	public List<Gem> getGems() {
		return gems;
	}

	abstract public void buildListGems(String fileName);

}
