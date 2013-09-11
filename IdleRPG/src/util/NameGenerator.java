/*
 * Author : Pierre
 * Last Update : 11 sept. 2013 - 16:36:20
 */
package util;

import java.util.ArrayList;
import java.util.Random;

import main.IdleRPG;

import org.apache.commons.collections4.CollectionUtils;

import character.Hero;

// TODO: Auto-generated Javadoc
/**
 * The Class NameGenerator.
 */
public class NameGenerator {

	/** The name list. */
	private static ArrayList<String>	nameList;

	/**
	 * Inits the name list.
	 */
	public static void initNameList() {
		NameGenerator.nameList = new ArrayList<>();
		NameGenerator.nameList.add("Elrond");
		NameGenerator.nameList.add("Saruman");
		NameGenerator.nameList.add("Torn");
		NameGenerator.nameList.add("Dragor");
		NameGenerator.nameList.add("Kailo");
		NameGenerator.nameList.add("Kyra");
		NameGenerator.nameList.add("Arthos");
		NameGenerator.nameList.add("Goolahg");
		NameGenerator.nameList.add("Fahgot");
		NameGenerator.nameList.add("Key Vine");
		NameGenerator.nameList.add("Chun Ki");
		NameGenerator.nameList.add("Son Goku");
	}

	/** The game. */
	private final IdleRPG			game;

	/** The used names. */
	private final ArrayList<String>	usedNames;

	/**
	 * Instantiates a new name generator.
	 * 
	 * @param game
	 *            the game
	 */
	public NameGenerator(final IdleRPG game) {
		this.game = game;
		this.usedNames = new ArrayList<>();
		NameGenerator.initNameList();
	}

	/**
	 * Count name.
	 * 
	 * @param name
	 *            the name
	 * @return the int
	 */
	public int countName(final String name) {
		int n = 0;
		for( final Hero h : this.game.getHeroList() )
			if( h.getName().matches("^" + name + "\\d*$") )
				n++;
		return n;
	}

	/**
	 * Generate.
	 * 
	 * @return the string
	 */
	public String generate() {
		String name;
		ArrayList<String> names;
		if( this.usedNames.containsAll(NameGenerator.nameList) )
			names = NameGenerator.nameList;
		else
			names = (ArrayList<String>) CollectionUtils.subtract(NameGenerator.nameList, this.usedNames);

		final Random r = new Random();
		name = names.get(r.nextInt(names.size()));

		final int countName = this.countName(name);
		if( countName > 0 )
			name += countName;

		this.usedNames.add(name);
		return name;
	}

}
