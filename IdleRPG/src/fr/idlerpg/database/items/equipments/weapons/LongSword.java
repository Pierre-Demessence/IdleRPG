package fr.idlerpg.database.items.equipments.weapons;

/**
 * The Class LongSword.
 */
public class LongSword extends Sword {

	/* (non-Javadoc)
	 * @see item.Item#getName()
	 */
	@Override
	public String getBaseName() {
		return "Long Sword";
	}

	@Override
	public int getBaseDammagesBonus() {
		return 2;
	}

}
