/*
 * Author : Pierre
 * Last Update : 12 sept. 2013 - 04:07:18
 */
package util;

import character.Attribute;
import character.Character;
import de.congrace.exp4j.Calculable;
import de.congrace.exp4j.ExpressionBuilder;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;

// TODO: Auto-generated Javadoc
/**
 * The Class Formula.
 */
public class Formula {

	/** The formula. */
	private Calculable	formula;

	/**
	 * Instantiates a new formula.
	 * 
	 * @param formula
	 *            the formula
	 */
	public Formula(final String formula) {
		try {
			this.formula = new ExpressionBuilder(formula).withVariableNames("CHA", "CON", "DEX", "INT", "STR", "WIS").build();
		} catch( UnknownFunctionException | UnparsableExpressionException e ) {
			e.printStackTrace();
		}
	}

	/**
	 * Calculate.
	 * 
	 * @return the int
	 */
	public int calculate() {
		return (int) Math.round(this.formula.calculate());
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
}
