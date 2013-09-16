package fr.idlerpg.item;

import fr.idlerpg.database.factories.ModifierFactory.ModifierSlot;

/**
 * The Class Equipment.
 */
public abstract class Armor extends Equipment {

	@Override
	protected ModifierSlot getModifierSlot() {
		return ModifierSlot.ARMOR_MODIFIER;
	}

	/**
	 * Gets the armor bonus.
	 * 
	 * @return the armor bonus
	 */
	public int getArmorBonus() {
		int a = this.getBaseArmorBonus();
		for( EquipmentModifier am : this.modifiers )
			if( am instanceof ArmorModifier )
				a += ( (ArmorModifier) am ).getArmorBonus();
		return a;
	}

	protected abstract int getBaseArmorBonus();

	@Override
	public int getValue() {
		int value = super.getValue();
		value = this.getArmorBonus() * 50;
		return value;
	}

	protected void addModifier(ArmorModifier em) {
		super.addModifier(em);
	}

}
