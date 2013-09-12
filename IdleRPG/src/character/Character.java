/*
 * Author : Pierre
 * Last Update : 12 sept. 2013 - 04:07:19
 */
package character;

import java.util.EnumMap;

import util.Logger;
import database.GlobalFormula;

// TODO: Auto-generated Javadoc
/**
 * The Class Character.
 */
public abstract class Character {

	/** The life. */
	private int		life;

	/** The mana. */
	private int		mana;

	/** The name. */
	private String	name;

	/**
	 * Instantiates a new character.
	 */
	public Character() {

	}

	/**
	 * To call at the end of the object creation process.
	 */
	protected void init() {
		this.life = this.getMaxLife();
		this.mana = this.getMaxMana();
	}

	/**
	 * Instantiates a new character.
	 * 
	 * @param name
	 *            the name
	 */
	public Character(final String name) {
		this();
		this.name = name;
	}

	/**
	 * Adds the life.
	 * 
	 * @param value
	 *            the value
	 */
	public void addLife(final int value) {
		this.life += value;
		this.life = Math.max(0, Math.min(this.life, this.getMaxLife()));
	}

	/**
	 * Adds the mana.
	 * 
	 * @param value
	 *            the value
	 */
	public void addMana(final int value) {
		this.mana += value;
		this.mana = Math.max(0, Math.min(this.mana, this.getMaxMana()));
	}

	/**
	 * Attack.
	 * 
	 * @param c
	 *            the c
	 */
	public abstract void attack(Character c);

	/**
	 * Attack.
	 * 
	 * @param c
	 *            the c
	 * @param dammages
	 *            the dammages
	 */
	public void attack(final Character c, final int dammages) {
		Logger.log(this, "J'attaque " + c.getName() + " et fait " + dammages + " dégâts.");
		c.defend(dammages);
	}

	/**
	 * Defend.
	 * 
	 * @param dammages
	 *            the dammages
	 */
	public void defend(final int dammages) {
		final int finaldammages = dammages - this.getArmor();
		Logger.log(this, "Je reçois " + finaldammages + "[+" + this.getArmor() + "].");
		Logger.log(this, "Ma vie passe de " + this.getLife() + " à " + ( this.getLife() - finaldammages ) + ".");
		this.addLife(-finaldammages);
	}

	/**
	 * Do fight.
	 * 
	 * @param opponent
	 *            the opponent
	 */
	public abstract void doFight(Character opponent);

	/**
	 * Gets the armor.
	 * 
	 * @return the armor
	 */
	public abstract int getArmor();

	/**
	 * Gets the attribute.
	 * 
	 * @param attribute
	 *            the attribute
	 * @return the attribute
	 */
	public int getAttribute(final Attribute attribute) {
		return this.getBaseAttributes().get(attribute);
	}

	/**
	 * Gets the base attributes.
	 * 
	 * @return the base attributes
	 */
	public abstract EnumMap<Attribute, Integer> getBaseAttributes();

	/**
	 * Gets the level.
	 * 
	 * @return the level
	 */
	public abstract int getLevel();

	/**
	 * Gets the life.
	 * 
	 * @return the life
	 */
	public int getLife() {
		return this.life;
	}

	/**
	 * Gets the mana.
	 * 
	 * @return the mana
	 */
	public int getMana() {
		return this.mana;
	}

	/**
	 * Gets the max life.
	 * 
	 * @return the max life
	 */
	public int getMaxLife() {
		return GlobalFormula.LIFE_MAX.getFormula().calculate(this);
	}

	/**
	 * Gets the max mana.
	 * 
	 * @return the max mana
	 */
	public int getMaxMana() {
		return GlobalFormula.MANA_MAX.getFormula().calculate(this);
	}

	/**
	 * Gets the name.
	 * 
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Checks if is ko.
	 * 
	 * @return true, if is ko
	 */
	public boolean isKO() {
		return this.life <= 0;
	}

}
