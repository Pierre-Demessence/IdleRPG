/*
 * Author : Pierre
 * Last Update : 12 sept. 2013 - 04:07:18
 */
package util;

import java.util.Random;

import character.Attribute;
import character.Character;
import database.heroes.Warrior;
import de.congrace.exp4j.Calculable;
import de.congrace.exp4j.CustomFunction;
import de.congrace.exp4j.ExpressionBuilder;
import de.congrace.exp4j.InvalidCustomFunctionException;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;

// TODO: Auto-generated Javadoc
/**
 * The Class Formula.
 */
public class Formula {

	/** The formula. */
	private Calculable				formula;

	private static CustomFunction	dice;
	private static boolean			init;
	private String					expression;

	@Override
	public String toString() {
		return this.expression;
	}

	private static void init() {
		try {
			dice = new CustomFunction("DICE", 2) {
				@Override
				public double applyFunction(double[] params) {
					int n = (int) params[0];
					int sides = (int) params[1];

					int value = 0;
					Random r = new Random();
					for( int i = 0 ; i < n ; i++ )
						value += r.nextInt(sides) + 1;

					return value;
				}
			};
		} catch( InvalidCustomFunctionException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		init = true;
	}

	/**
	 * Instantiates a new formula.
	 * 
	 * @param formula
	 *            the formula
	 */
	public Formula(final String formula) {
		if( !init )
			init();
		try {
			this.expression = formula;
			this.formula = new ExpressionBuilder(formula).withVariableNames("CHA", "CON", "DEX", "INT", "STR", "WIS").withCustomFunction(dice).build();
		} catch( UnknownFunctionException | UnparsableExpressionException e ) {
			e.printStackTrace();
		}
	}

	/**
	 * Calculate.
	 * 
	 * @return the int
	 */
	private int calculate() {
		return (int) Math.round(this.formula.calculate());
	}

	public int calculateMin(final Character c) {
		String newFormula = this.toString().replaceAll("DICE\\((\\d+), *\\d+\\)", "$1");
		Formula f2 = new Formula(newFormula);
		return f2.calculate(c);
	}

	public int calculateMax(final Character c) {
		String newFormula = this.toString().replaceAll("DICE\\((\\d+), *(\\d)+\\)", "$1 * $2");
		Formula f2 = new Formula(newFormula);
		return f2.calculate(c);
	}

	/**
	 * Calculate.
	 * 
	 * @param c
	 *            the c
	 * @return the int
	 */
	public int calculate(final Character c) {
		for( final Attribute attribute : Attribute.values() )
			this.formula.setVariable(attribute.getAbreviation(), c.getAttribute(attribute));
		return this.calculate();
	}

	public static void main(String[] args) {
		Warrior toto = new Warrior("toto");
		System.out.println(new Formula("2 + DICE(5,5)").calculateMax(toto));
	}
}
