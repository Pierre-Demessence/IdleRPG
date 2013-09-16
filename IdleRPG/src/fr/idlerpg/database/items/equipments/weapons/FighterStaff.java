package fr.idlerpg.database.items.equipments.weapons;

import java.util.EnumMap;

import fr.idlerpg.database.characters.Attribute;

/**
 * The Class FighterStaff.
 */
public class FighterStaff extends Staff {

	/* (non-Javadoc)
	 * @see fr.idlerpg.item.Equipment#getBaseAttributesBonus()
	 */
	@Override
	public EnumMap<Attribute, Integer> getBaseAttributesBonus() {
		final EnumMap<Attribute, Integer> bonuses = super.getAttributesBonus();
		bonuses.put(Attribute.WISDOM, 1);
		return bonuses;
	}

	/* (non-Javadoc)
	 * @see fr.idlerpg.item.Weapon#getBaseDammagesBonus()
	 */
	@Override
	public int getBaseDammagesBonus() {
		return 1;
	}

	/* (non-Javadoc)
	 * @see fr.idlerpg.item.Item#getBaseName()
	 */
	@Override
	protected String getBaseName() {
		return "Fighter Staff";
	}

}
