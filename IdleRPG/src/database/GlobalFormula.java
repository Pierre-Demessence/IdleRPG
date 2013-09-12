/*
 * Author : Pierre
 * Last Update : 12 sept. 2013 - 04:07:21
 */
package database;

import util.Formula;

// TODO: Auto-generated Javadoc
/**
 * The Enum GlobalFormula.
 */
public enum GlobalFormula {

	/** The combat sword. */
	COMBAT_SWORD("2 * STR + 2 * DEX + CON"),

	/** The combat unarmed. */
	COMBAT_UNARMED("2 * STR + CON"),

	/** The life max. */
	LIFE_MAX("3 * CON + 2 * STR"),

	/** The mana max. */
	MANA_MAX("3 * WIS + 2 * INT");

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
