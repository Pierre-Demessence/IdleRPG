/*
 * Author : Pierre
 * Last Update : 11 sept. 2013 - 16:36:23
 */
package database;

import util.Formula;

// TODO: Auto-generated Javadoc
/**
 * The Enum GlobalFormula.
 */
public enum GlobalFormula {

	/** The combat unarmed. */
	COMBAT_UNARMED("2 * STR + CON"),

	/** The life max. */
	LIFE_MAX("3 * CON + 2 * STR"),

	/** The mana max. */
	MANA_MAX("3 * WIS + 2 * INT"),

	COMBAT_SWORD("2 * STR + 2 * DEX + CON");

	/** The formula. */
	private Formula	formula;

	/**
	 * Instantiates a new global formula.
	 * 
	 * @param formula
	 *            the formula
	 */
	GlobalFormula(final String formula) {
		this.formula = new Formula(formula);
	}

	/**
	 * Gets the formula.
	 * 
	 * @return the formula
	 */
	public Formula getFormula() {
		return this.formula;
	}

}
