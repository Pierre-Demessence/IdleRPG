package fr.idlerpg.database.items.equipments.weapons;

public class Knife extends Dagger {

	@Override
	protected String getBaseName() {
		return "Knife";
	}

	@Override
	protected int getBaseDammagesBonus() {
		return 1;
	}

}
