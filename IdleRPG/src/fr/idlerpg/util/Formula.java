/*
 * Author : Pierre
 * Last Update : 12 sept. 2013 - 23:14:55
 */
package fr.idlerpg.util;

import java.util.Random;

import de.congrace.exp4j.Calculable;
import de.congrace.exp4j.CustomFunction;
import de.congrace.exp4j.ExpressionBuilder;
import de.congrace.exp4j.InvalidCustomFunctionException;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;
import fr.idlerpg.character.Character;
import fr.idlerpg.database.characters.Attribute;
import fr.idlerpg.database.characters.heroes.Warrior;

/**
 * The Class Formula.
 */
public class Formula {

	/** The dice. */
	private static CustomFunction	dice;

	/** The init. */
	private static boolean			init;

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(final String[] args) {
		final Warrior hero = new Warrior("toto");
		hero.addAttribute(Attribute.STRENGH, 0);
		hero.addAttribute(Attribute.CONSTITUTION, 0);
		final Formula formula = new Formula("(STR * 2 + CON/2) / (DICE(2, CON) + DICE(1, STR)) + DICE(2, CON) + DICE(1, STR)");
		System.out.println("Min: " + formula.calculateMin(hero));
		System.out.println("Max: " + formula.calculateMax(hero));
	}

	/**
	 * Inits the formula class.
	 */
	private static void init() {
		try {
			Formula.dice = new CustomFunction("DICE", 2) {
				@Override
				public double applyFunction(final double[] params) {
					final int n = (int) params[0];
					final int sides = (int) params[1];

					int value = 0;
					final Random r = new Random();
					for( int i = 0 ; i < n ; i++ )
						value += r.nextInt(sides) + 1;

					return value;
				}
			};
		} catch( final InvalidCustomFunctionException e ) {
			e.printStackTrace();
		}
		Formula.init = true;
	}

	/** The expression. */
	private String		expression;

	/** The formula. */
	private Calculable	formula;

	/**
	 * Instantiates a new formula.
	 * 
	 * @param formula
	 *            the formula
	 */
	public Formula(final String formula) {
		if( !Formula.init )
			Formula.init();
		try {
			this.expression = formula;
			this.formula = new ExpressionBuilder(formula).withVariableNames("CHA", "CON", "DEX", "INT", "STR", "WIS").withCustomFunction(Formula.dice).build();
		} catch( UnknownFunctionException | UnparsableExpressionException e ) {
			e.printStackTrace();
		}
	}

	/**
	 * Calculate a formula for a specific character.
	 * 
	 * @param c
	 *            the character to use to get the attribute values.
	 * @return the int
	 */
	public int calculate(final Character c) {
		for( final Attribute attribute : Attribute.values() )
			this.formula.setVariable(attribute.getAbreviation(), c.getAttribute(attribute));
		return this.calculate();
	}

	/**
	 * Calculate the max value a formula can return.
	 * 
	 * @param c
	 *            the c
	 * @return the int
	 * @see Formula#calculate(Character)
	 */
	public int calculateMax(final Character c) {
		final String newFormula = this.toString().replaceAll("DICE\\((\\d+|\\w{3}), *(\\d+|\\w{3})\\)", "$1 * $2");
		final Formula f2 = new Formula(newFormula);
		return f2.calculate(c);
	}

	/**
	 * Calculate the min value a formula can return.
	 * 
	 * @param c
	 *            the c
	 * @return the int
	 * @see Formula#calculate(Character)
	 */
	public int calculateMin(final Character c) {
		final String newFormula = this.toString().replaceAll("DICE\\((\\d+|\\w{3}), *(\\d+|\\w{3})\\)", "$1");
		final Formula f2 = new Formula(newFormula);
		return f2.calculate(c);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.expression;
	}

	/**
	 * Calculate the formula.
	 * 
	 * @return the int
	 */
	private int calculate() {
		return (int) Math.round(this.formula.calculate());
	}
}
