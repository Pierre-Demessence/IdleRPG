/*
 * Author : Pierre
 * Last Update : 12 sept. 2013 - 04:07:20
 */
package item;

import java.util.EnumMap;

import util.Formula;
import character.Character;
import database.factories.ModifierFactory.ModifierSlot;
import database.items.EquipmentSlot;
import database.items.ModifierQuality;
import database.items.equipements.weapons.ShortSword;

/**
 * The Class Weapon.
 */
public abstract class Weapon extends Equipment {

	public Weapon(boolean modifiers) {
		super(modifiers, ModifierSlot.WEAPON_MODIFIER);
	}

	public Weapon() {
		this(true);
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

	public void addModifier(WeaponModifier em) {
		super.addModifier(em);
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
	public int getValue() {
		return super.getValue() + 10 + 50 * this.getDammagesBonus();
	}

	/**
	 * Gets the dammages bonus.
	 * 
	 * @return the dammages bonus
	 */
	public int getDammagesBonus() {
		int d = 0;
		for( EquipmentModifier wm : this.modifiers )
			if( wm instanceof WeaponModifier )
				d += ( (WeaponModifier) wm ).getDamagesBonus();
		return d;
	}

	public static void main(String[] args) {

		for( int i = 0 ; i < 15 ; i++ ) {
			Weapon w = new ShortSword();
			System.out.println(i + ") " + w.getName());
		}

		EnumMap<ModifierQuality, Integer> table = new EnumMap<>(ModifierQuality.class);
		int N = 3000000;
		for( int i = 0 ; i < N ; i++ ) {
			ModifierQuality mv = ModifierQuality.getRandomModifierQuality();
			if( mv != null )
				table.put(mv, ( table.get(mv) != null ? table.get(mv) : 0 ) + 1);
		}

		int total = 0;
		for( java.util.Map.Entry<ModifierQuality, Integer> e : table.entrySet() ) {
			System.out.println(e.getKey().name() + " : " + e.getValue() + " / " + N + " (" + (float) e.getValue() / N * 100 + "%)");
			total += e.getValue();
		}
		System.out.println("----- ----- ----- ----- -----");
		System.out.println("Total : " + total + " / " + N + "(" + (float) total / N * 100 + "%)");
	}
}