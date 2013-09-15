/*
 * Author : Pierre
 * Last Update : 12 sept. 2013 - 22:31:15
 */
package character;

import java.util.EnumMap;
import java.util.Random;

import util.Formula;
import util.Logger;
import database.GlobalFormula;
import database.characters.Attribute;

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
	 * Instantiates a new character.
	 * 
	 * @param name
	 *            the name of the character
	 */
	public Character(final String name) {
		this();
		this.name = name;
	}

	/**
	 * Add some life.
	 * 
	 * @param value
	 *            the value of life to add, which can be negative
	 */
	public void addLife(final int value) {
		this.life += value;
		this.life = Math.max(0, Math.min(this.life, this.getMaxLife()));
	}

	/**
	 * Add some mana.
	 * 
	 * @param value
	 *            the value of mana to add, which can be negative
	 */
	public void addMana(final int value) {
		this.mana += value;
		this.mana = Math.max(0, Math.min(this.mana, this.getMaxMana()));
	}

	/**
	 * Attack a character.
	 * 
	 * @param c
	 *            the character to attack
	 */
	public abstract void attack(Character c);

	/**
	 * Attack a character and deal a certain amount of dammages.
	 * 
	 * @param c
	 *            the character to attack
	 * @param dammages
	 *            the dammages to deal to the opponent
	 */
	public void attack(final Character c, final int dammages) {
		if( this.touch(c) ) {
			Logger.log(this, "J'attaque " + c.getName() + " et fait " + dammages + " dégâts.");
			c.defend(dammages);
		} else
			Logger.log(this, "J'attaque " + c.getName() + " mais l'attaque échoue.");
	}

	private boolean touch(Character c) {
		int x = GlobalFormula.ACCURACY.getFormula().calculate(this) - GlobalFormula.DODGE.getFormula().calculate(this);
		float y = (float) ( 1 / ( 1 + Math.exp(-x) ) );
		Random r = new Random();
		return r.nextFloat() <= y;
	}

	/**
	 * Chance to kill a character, based on the current min and max damage we can do and the character current life.
	 * 
	 * @param c
	 *            the character to kill
	 * @return the % of chance to kill the character, between 0.0f and 1.0f
	 */
	public float chanceToKill(final Character c) {
		final Formula dammagesFormula = this.getDammagesFormula();
		final int dammagesMin = dammagesFormula.calculateMin(this);
		final int dammagesMax = dammagesFormula.calculateMax(this);
		final int life = c.getLife();
		if( dammagesMin >= life )
			return 1.0f;
		else if( dammagesMax < life )
			return 0.0f;
		else {
			int kill = 0;
			for( int i = dammagesMin ; i <= dammagesMax ; i++ )
				if( i >= life )
					kill++;
			return (float) ( ( dammagesMax - dammagesMin ) + 1 ) / kill;
		}
	}

	/**
	 * Defend some dammages taken.
	 * 
	 * @param dammages
	 *            the dammages taken
	 */
	private void defend(final int dammages) {
		final int finaldammages = dammages - this.getArmor();
		Logger.log(this, "Je reçois " + finaldammages + "[+" + this.getArmor() + "].");
		Logger.log(this, "Ma vie passe de " + this.getLife() + " à " + Math.max(0, this.getLife() - finaldammages) + ".");
		this.addLife(-finaldammages);
	}

	/**
	 * Do fight. Must be implemented to do the rights actions per turn properly.
	 * 
	 * @param opponent
	 *            the opponent
	 */
	public abstract void doFight(Character opponent);

	/**
	 * Gets the armor. Dammages taken will be reduced by the armor.
	 * 
	 * @return the armor
	 */
	public abstract int getArmor();

	/**
	 * Gets the attribute.
	 * 
	 * @param attribute
	 *            the attribute to get
	 * @return the attribute value
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
	 * Gets the dammages formula.
	 * 
	 * @return the dammages formula
	 */
	public abstract Formula getDammagesFormula();

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
	 * Checks if the character is ko.
	 * 
	 * @return true, if is ko
	 */
	public boolean isKO() {
		return this.life <= 0;
	}

	/**
	 * To call at the end of the object creation process.
	 */
	protected void init() {
		this.life = this.getMaxLife();
		this.mana = this.getMaxMana();
	}

}
