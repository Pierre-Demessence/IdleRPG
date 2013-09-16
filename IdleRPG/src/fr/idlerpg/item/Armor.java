package fr.idlerpg.item;

import fr.idlerpg.database.factories.ItemFactory;
import fr.idlerpg.database.factories.ModifierFactory.ModifierSlot;

/**
 * The Class Equipment.
 */
public abstract class Armor extends Equipment {

	/**
	 * Gets the armor bonus.
	 * 
	 * @return the armor bonus
	 */
	public int getArmorBonus() {
		int a = this.getBaseArmorBonus();
		for( final EquipmentModifier am : this.modifiers )
			if( am instanceof ArmorModifier )
				a += ( (ArmorModifier) am ).getArmorBonus();
		return a;
	}

	@Override
	public Object clone() {
		return this.clone(false);
	}

	@Override
	public Armor clone(boolean modifiers) {
		return ItemFactory.getArmor(this.getClass().getSimpleName(), modifiers);
	}

	/* (non-Javadoc)
	 * @see fr.idlerpg.item.Equipment#getValue()
	 */
	@Override
	public int getCalculatedValue() {
		return super.getCalculatedValue() + this.getArmorBonus() * 100;
	}

	/**
	 * Adds the modifier.
	 * 
	 * @param em
	 *            the em
	 */
	protected void addModifier(final ArmorModifier em) {
		super.addModifier(em);
	}

	/**
	 * Gets the base armor bonus.
	 * 
	 * @return the base armor bonus
	 */
	protected abstract int getBaseArmorBonus();

	/* (non-Javadoc)
	 * @see fr.idlerpg.item.Equipment#getModifierSlot()
	 */
	@Override
	protected ModifierSlot getModifierSlot() {
		return ModifierSlot.ARMOR_MODIFIER;
	}

}
