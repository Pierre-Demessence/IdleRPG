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

	/** The combat with sword formula. */
	DAMAGE_SWORD(makeDamageFormula(Attribute.STRENGH, Attribute.DEXTERITY)),

	/** The combat unarmed formula. */
	DAMAGE_UNARMED(makeDamageFormula(Attribute.CONSTITUTION, Attribute.STRENGH)),
	DAMAGE_DAGGER(makeDamageFormula(Attribute.DEXTERITY, Attribute.STRENGH)),
	DAMAGE_STAFF(makeDamageFormula(Attribute.WISDOM, Attribute.DEXTERITY)),
	// COMBAT_UNARMED("2 * STR + CON"),

	ACCURACY("2 * DEX + INT"),

	DODGE("DEX + INT"),

	/** The life max formula. */
	LIFE_MAX("3 * CON + 2 * STR"),

	/** The mana max formula. */
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

	private static String makeDamageFormula(Attribute primary, Attribute secondary) {
		String primaryAbr = primary.getAbreviation();
		String secondaryAbr = secondary.getAbreviation();
		return new String("(" + secondaryAbr + " * 2 + " + primaryAbr + "/2) / (DICE(2, " + primaryAbr + ") + DICE(1, " + secondaryAbr + ")) + DICE(2, " + primaryAbr + ") + DICE(1, " + secondaryAbr + ")");
	}
}
