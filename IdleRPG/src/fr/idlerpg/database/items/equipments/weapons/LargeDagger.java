package fr.idlerpg.database.items.equipments.weapons;

/**
 * The Class LargeDagger.
 */
public class LargeDagger extends Dagger {

	/* (non-Javadoc)
	 * @see fr.idlerpg.item.Weapon#getBaseDammagesBonus()
	 */
	@Override
	protected int getBaseDammagesBonus() {
		return 3;
	}

	/* (non-Javadoc)
	 * @see fr.idlerpg.item.Item#getBaseName()
	 */
	@Override
	protected String getBaseName() {
		return "Large Dagger";
	}

}
