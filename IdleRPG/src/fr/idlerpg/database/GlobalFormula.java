/*
 * Author : Pierre
 * Last Update : 12 sept. 2013 - 04:07:21
 */
package fr.idlerpg.database;

import fr.idlerpg.database.characters.Attribute;
import fr.idlerpg.util.Formula;

/**
 * The Enum GlobalFormula.
 */
public enum GlobalFormula {

	/** The accuracy. */
	ACCURACY("2 * DEX + INT"),

	/** The damage dagger. */
	DAMAGE_DAGGER(GlobalFormula.makeDamageFormula(Attribute.DEXTERITY, Attribute.STRENGH)),

	/** The damage staff. */
	DAMAGE_STAFF(GlobalFormula.makeDamageFormula(Attribute.WISDOM, Attribute.DEXTERITY)),

	/** The combat with sword formula. */
	DAMAGE_SWORD(GlobalFormula.makeDamageFormula(Attribute.STRENGH, Attribute.DEXTERITY)),
	// COMBAT_UNARMED("2 * STR + CON"),

	/** The combat unarmed formula. */
	DAMAGE_UNARMED(GlobalFormula.makeDamageFormula(Attribute.CONSTITUTION, Attribute.STRENGH)),

	/** The dodge. */
	DODGE("DEX + INT"),

	/** The life max formula. */
	LIFE_MAX("3 * CON + 2 * STR"),

	/** The mana max formula. */
	MANA_MAX("3 * WIS + 2 * INT");

	/**
	 * Make damage formula.
	 * 
	 * @param primary
	 *            the primary
	 * @param secondary
	 *            the secondary
	 * @return the string
	 */
	private static String makeDamageFormula(final Attribute primary, final Attribute secondary) {
		final String primaryAbr = primary.getAbreviation();
		final String secondaryAbr = secondary.getAbreviation();
		return "(" + secondaryAbr + " * 2 + " + primaryAbr + "/2) / (DICE(2, " + primaryAbr + ") + DICE(1, " + secondaryAbr + ")) + DICE(2, " + primaryAbr + ") + DICE(1, " + secondaryAbr + ")";
	}

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
