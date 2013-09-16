package fr.idlerpg.database.items.equipments.weapons;

import java.util.EnumMap;

import fr.idlerpg.database.characters.Attribute;

public class FighterStaff extends Staff {

	@Override
	protected String getBaseName() {
		return "Fighter Staff";
	}

	@Override
	public EnumMap<Attribute, Integer> getBaseAttributesBonus() {
		EnumMap<Attribute, Integer> bonuses = super.getAttributesBonus();
		bonuses.put(Attribute.WISDOM, 1);
		return bonuses;
	}

	@Override
	public int getBaseDammagesBonus() {
		return 1;
	}

}
