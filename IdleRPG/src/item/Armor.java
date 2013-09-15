package item;

import database.factories.ModifierFactory.ModifierSlot;

/**
 * The Class Equipment.
 */
public abstract class Armor extends Equipment {

	public Armor(boolean modifiers) {
		super(modifiers, ModifierSlot.ARMOR_MODIFIER);
	}

	public Armor() {
		this(true);
	}

	/**
	 * Gets the armor bonus.
	 * 
	 * @return the armor bonus
	 */
	public int getArmorBonus() {
		int a = 0;
		for( EquipmentModifier am : this.modifiers )
			if( am instanceof ArmorModifier )
				a += ( (ArmorModifier) am ).getArmorBonus();
		return a;
	}

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
