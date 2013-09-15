/*
 * Author : Pierre
 * Last Update : 12 sept. 2013 - 04:07:20
 */
package database.characters.monsters;

import item.Loot;

import java.util.ArrayList;
import java.util.EnumMap;

import character.Monster;
import database.characters.Attribute;

/**
 * The Class Orc.
 */
public class Orc extends Monster {

	/* (non-Javadoc)
	 * @see character.Monster#getBaseArmor()
	 */
	@Override
	public int getBaseArmor() {
		return 3;
	}

	/* (non-Javadoc)
	 * @see character.Monster#getBaseAttributes()
	 */
	@Override
	public EnumMap<Attribute, Integer> getBaseAttributes() {
		final EnumMap<Attribute, Integer> attributes = new EnumMap<>(Attribute.class);
		attributes.put(Attribute.CHARISMA, 1);
		attributes.put(Attribute.CONSTITUTION, 5);
		attributes.put(Attribute.DEXTERITY, 3);
		attributes.put(Attribute.INTELLIGENCE, 1);
		attributes.put(Attribute.STRENGH, 4);
		attributes.put(Attribute.WISDOM, 1);
		return attributes;
	}

	/* (non-Javadoc)
	 * @see character.Monster#getExperienceLoot()
	 */
	@Override
	public int getExperienceLoot() {
		return 20;
	}

	/* (non-Javadoc)
	 * @see character.Monster#getGoldLoot()
	 */
	@Override
	public int getGoldLoot() {
		return 10;
	}

	/* (non-Javadoc)
	 * @see character.Character#getLevel()
	 */
	@Override
	public int getLevel() {
		return 2;
	}

	/* (non-Javadoc)
	 * @see character.Monster#getLoots()
	 */
	@Override
	public ArrayList<Loot> getLoots() {
		return null;
	}

	/* (non-Javadoc)
	 * @see character.Monster#getName()
	 */
	@Override
	public String getName() {
		return "Orc";
	}

}
