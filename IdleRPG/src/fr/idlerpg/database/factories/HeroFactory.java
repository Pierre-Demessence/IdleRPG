package fr.idlerpg.database.factories;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Random;

import fr.idlerpg.character.Hero;
import fr.idlerpg.database.characters.heroes.Rogue;
import fr.idlerpg.database.characters.heroes.Warrior;
import fr.idlerpg.database.characters.heroes.Wizard;

/**
 * A factory for creating Hero objects.
 */
public class HeroFactory {

	/** The heroes classes. */
	private static ArrayList<Class<? extends Hero>>	heroesClasses;

	/**
	 * Gets the random hero.
	 * 
	 * @param name
	 *            the name
	 * @return the random hero
	 */
	public static Hero getRandomHero(final String name) {
		if( HeroFactory.heroesClasses == null )
			HeroFactory.init();
		try {
			return HeroFactory.heroesClasses.get(new Random().nextInt(HeroFactory.heroesClasses.size())).getConstructor(String.class).newInstance(name);
		} catch( InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e ) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(final String[] args) {
		System.out.println(HeroFactory.getRandomHero("toto").getClass());
	}

	/**
	 * Inits the.
	 */
	private static void init() {
		HeroFactory.heroesClasses = new ArrayList<>();
		HeroFactory.heroesClasses.add(Warrior.class);
		HeroFactory.heroesClasses.add(Rogue.class);
		HeroFactory.heroesClasses.add(Wizard.class);
	}
}
