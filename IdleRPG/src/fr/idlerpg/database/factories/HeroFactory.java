package fr.idlerpg.database.factories;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Random;

import fr.idlerpg.character.Hero;
import fr.idlerpg.database.characters.heroes.Rogue;
import fr.idlerpg.database.characters.heroes.Warrior;
import fr.idlerpg.database.characters.heroes.Wizard;

public class HeroFactory {

	private static ArrayList<Class<? extends Hero>>	heroesClasses;

	private static void init() {
		heroesClasses = new ArrayList<>();
		heroesClasses.add(Warrior.class);
		heroesClasses.add(Rogue.class);
		heroesClasses.add(Wizard.class);
	}

	public static Hero getRandomHero(String name) {
		if( heroesClasses == null )
			init();
		try {
			return heroesClasses.get(new Random().nextInt(heroesClasses.size())).getConstructor(String.class).newInstance(name);
		} catch( InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e ) {
			e.printStackTrace();
		}

		return null;
	}

	public static void main(String[] args) {
		System.out.println(getRandomHero("toto").getClass());
	}
}
