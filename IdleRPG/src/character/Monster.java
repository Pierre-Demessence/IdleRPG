/*
 * Author : Pierre
 * Last Update : 12 sept. 2013 - 04:07:20
 */
package character;

import item.Loot;

import java.util.ArrayList;
import java.util.EnumMap;

import util.Formula;
import database.GlobalFormula;
import database.characters.Attribute;

/**
 * The Class Monster.
 */
public abstract class Monster extends Character implements Cloneable {

	/**
	 * Instantiates a new monster.
	 */
	public Monster() {
		this.init();
	}

	/* (non-Javadoc)
	 * @see character.Character#attack(character.Character)
	 */
	@Override
	public void attack(final Character c) {
		super.attack(c, this.getDammagesFormula().calculate(this));
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
	 * The total of all attributes points must be 10 + 5 per level.
	 * 
	 * @return the base attributes
	 * @see character.Character#getBaseAttributes()
	 */
	@Override
	public abstract EnumMap<Attribute, Integer> getBaseAttributes();

	/**
	 * Gets the formula.
	 * 
	 * @return the formula
	 */
	@Override
	public Formula getDammagesFormula() {
		return GlobalFormula.DAMAGE_UNARMED.getFormula();
	}

	/**
	 * Gets the experience loot.
	 * 
	 * @return the experience loot
	 */
	public abstract int getExperienceLoot();

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
