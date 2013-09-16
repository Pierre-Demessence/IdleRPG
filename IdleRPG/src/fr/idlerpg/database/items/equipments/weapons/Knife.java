package fr.idlerpg.database.items.equipments.weapons;

/**
 * The Class Knife.
 */
public class Knife extends Dagger {

	/* (non-Javadoc)
	 * @see fr.idlerpg.item.Weapon#getBaseDammagesBonus()
	 */
	@Override
	protected int getBaseDammagesBonus() {
		return 1;
	}

	/* (non-Javadoc)
	 * @see fr.idlerpg.item.Item#getBaseName()
	 */
	@Override
	protected String getBaseName() {
		return "Knife";
	}

}
