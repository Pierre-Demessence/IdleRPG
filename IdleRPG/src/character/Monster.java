/*
 * Author : Pierre
 * Last Update : 11 sept. 2013 - 16:36:22
 */
package character;

import item.Loot;

import java.util.ArrayList;
import java.util.EnumMap;

import util.Formula;
import database.GlobalFormula;

// TODO: Auto-generated Javadoc
/**
 * The Class Monster.
 */
public abstract class Monster extends Character implements Cloneable {

	/* (non-Javadoc)
	 * @see character.Character#attack(character.Character)
	 */
	@Override
	public void attack(final Character c) {
		super.attack(c, this.getFormula().calculate(this));
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	public Object clone() {
		Monster clone = null;
		try {
			clone = (Monster) super.clone();
		} catch( final CloneNotSupportedException e ) {
			e.printStackTrace();
		}
		return clone;
	}

	/* (non-Javadoc)
	 * @see character.Character#doFight(character.Character)
	 */
	@Override
	public void doFight(final Character opponent) {
		this.attack(opponent);
	}

	/**
	 * The total of all attributes points must be 10 + 5 per level.
	 * 
	 * @see character.Character#getBaseAttributes()
	 */
	@Override
	public abstract EnumMap<Attribute, Integer> getBaseAttributes();

	/* (non-Javadoc)
	 * @see character.Character#getArmor()
	 */
	@Override
	public int getArmor() {
		return this.getBaseArmor();
	}

	/**
	 * Gets the base armor.
	 * 
	 * @return the base armor
	 */
	public abstract int getBaseArmor();

	/**
	 * Gets the experience loot.
	 * 
	 * @return the experience loot
	 */
	public abstract int getExperienceLoot();

	/**
	 * Gets the formula.
	 * 
	 * @return the formula
	 */
	public Formula getFormula() {
		return GlobalFormula.COMBAT_UNARMED.getFormula();
	}

	/**
	 * Gets the gold loot.
	 * 
	 * @return the gold loot
	 */
	public abstract int getGoldLoot();

	/**
	 * Gets the loots.
	 * 
	 * @return the loots
	 */
	public abstract ArrayList<Loot> getLoots();

	/* (non-Javadoc)
	 * @see character.Character#getName()
	 */
	@Override
	public abstract String getName();

}
