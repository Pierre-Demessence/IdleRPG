/*
 * Author : Pierre
 * Last Update : 12 sept. 2013 - 04:07:19
 */
package util;

import character.Character;

// TODO: Auto-generated Javadoc
/**
 * The Class Logger.
 */
public final class Logger {

	/** The last character. */
	private static Character	lastCharacter;

	/**
	 * Check character.
	 * 
	 * @param c
	 *            the c
	 */
	public static final void checkCharacter(final Character c) {
		if( Logger.lastCharacter != c ) {
			Logger.lastCharacter = c;

			String name;
			if( c == null )
				name = "MJ";
			else
				name = c.getName();

			System.out.println("\n" + name + " :");
		}
	}

	/**
	 * Log.
	 * 
	 * @param c
	 *            the c
	 * @param text
	 *            the text
	 */
	public static final void log(final Character c, final String text) {
		Logger.checkCharacter(c);
		System.out.println("\t\"" + text + "\"");
	}

	/**
	 * Log.
	 * 
	 * @param text
	 *            the text
	 */
	public static final void log(final String text) {
		Logger.log(null, text);
	}

	/**
	 * Instantiates a new logger.
	 */
	private Logger() {
	}
}
