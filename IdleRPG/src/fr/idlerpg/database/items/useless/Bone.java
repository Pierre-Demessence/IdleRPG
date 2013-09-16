package fr.idlerpg.database.items.useless;

import fr.idlerpg.item.Useless;

public class Bone extends Useless {

	@Override
	public int getValue() {
		return 2;
	}

	@Override
	protected String getBaseName() {
		return "Bone";
	}

}
