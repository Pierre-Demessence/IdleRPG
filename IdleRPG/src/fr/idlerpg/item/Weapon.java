/*
 * Author : Pierre
 * Last Update : 12 sept. 2013 - 04:07:20
 */
package fr.idlerpg.item;

import java.util.EnumMap;

import fr.idlerpg.character.Character;
import fr.idlerpg.database.factories.ItemFactory;
import fr.idlerpg.database.factories.ModifierFactory.ModifierSlot;
import fr.idlerpg.database.items.EquipmentSlot;
import fr.idlerpg.database.items.ModifierQuality;
import fr.idlerpg.util.Formula;

/**
 * The Class Weapon.
 */
public abstract class Weapon extends Equipment {

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(final String[] args) {

		for( int i = 0 ; i < 15 ; i++ ) {
			final Weapon w = ItemFactory.getWeapon("ShortSword", true);
			System.out.println(i + ") " + w.getName());
		}

		final EnumMap<ModifierQuality, Integer> table = new EnumMap<>(ModifierQuality.class);
		final int N = 3000000;
		for( int i = 0 ; i < N ; i++ ) {
			final ModifierQuality mv = ModifierQuality.getRandomModifierQuality();
			if( mv != null )
				table.put(mv, ( table.get(mv) != null ? table.get(mv) : 0 ) + 1);
		}

		int total = 0;
		for( final java.util.Map.Entry<ModifierQuality, Integer> e : table.entrySet() ) {
			System.out.println(e.getKey().name() + " : " + e.getValue() + " / " + N + " (" + ( ( (float) e.getValue() / N ) * 100 ) + "%)");
			total += e.getValue();
		}
		System.out.println("----- ----- ----- ----- -----");
		System.out.println("Total : " + total + " / " + N + "(" + ( ( (float) total / N ) * 100 ) + "%)");
	}

	@Override
	public Object clone() {
		return this.clone(false);
	}

	@Override
	public Weapon clone(boolean modifiers) {
		return ItemFactory.getWeapon(this.getClass().getSimpleName(), modifiers);
	}

	/**
	 * Adds the modifier.
	 * 
	 * @param em
	 *            the em
	 */
	public void addModifier(final WeaponModifier em) {
		super.addModifier(em);
	}

	/**
	 * Gets the dammages.
	 * 
	 * @param c
	 *            the c
	 * @return the dammages
	 */
	public int getDammages(final Character c) {
		return this.getFormula().calculate(c) + this.getDammagesBonus();
	}

	/**
	 * Gets the formula.
	 * 
	 * @return the formula
	 */
	public abstract Formula getFormula();

	/* (non-Javadoc)
	 * @see item.Equipment#getSlot()
	 */
	@Override
	public EquipmentSlot getSlot() {
		return EquipmentSlot.MAINHAND;
	}

	/* (non-Javadoc)
	 * @see item.Equipment#getValue()
	 */
	@Override
	public int getCalculatedValue() {
		return super.getCalculatedValue() + 50 + 100 * this.getDammagesBonus();
	}

	/**
	 * Gets the dammages bonus.
	 * 
	 * @return the dammages bonus
	 */
	private int getDammagesBonus() {
		int d = this.getBaseDammagesBonus();
		for( final EquipmentModifier wm : this.modifiers )
			if( wm instanceof WeaponModifier )
				d += ( (WeaponModifier) wm ).getDamagesBonus();
		return d;
	}

	/**
	 * Gets the base dammages bonus.
	 * 
	 * @return the base dammages bonus
	 */
	protected int getBaseDammagesBonus() {
		return 0;
	}

	/* (non-Javadoc)
	 * @see fr.idlerpg.item.Equipment#getModifierSlot()
	 */
	@Override
	protected ModifierSlot getModifierSlot() {
		return ModifierSlot.WEAPON_MODIFIER;
	}
}