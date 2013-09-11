/*
 * Author : Pierre
 * Last Update : 11 sept. 2013 - 16:36:21
 */
package database.monsters;

import item.Loot;

import java.util.ArrayList;
import java.util.EnumMap;

import character.Attribute;
import character.Monster;
import database.items.Leather;

// TODO: Auto-generated Javadoc
/**
 * The Class Gobelin.
 */
public class Gobelin extends Monster {

	/* (non-Javadoc)
	 * @see character.Monster#getBaseArmor()
	 */
	@Override
	public int getBaseArmor() {
		return 1;
	}

	/* (non-Javadoc)
	 * @see character.Monster#getBaseAttributes()
	 */
	@Override
	public EnumMap<Attribute, Integer> getBaseAttributes() {

		EnumMap<Attribute, Integer> attributes = new EnumMap<>(Attribute.class);
		attributes.put(Attribute.CHARISMA, 1);
		attributes.put(Attribute.CONSTITUTION, 2);
		attributes.put(Attribute.DEXTERITY, 1);
		attributes.put(Attribute.INTELLIGENCE, 1);
		attributes.put(Attribute.STRENGH, 3);
		attributes.put(Attribute.WISDOM, 1);
		return attributes;

	}

	/* (non-Javadoc)
	 * @see character.Monster#getExperienceLoot()
	 */
	@Override
	public int getExperienceLoot() {
		return 10;
	}

	/* (non-Javadoc)
	 * @see character.Monster#getGoldLoot()
	 */
	@Override
	public int getGoldLoot() {
		return 2;
	}

	/* (non-Javadoc)
	 * @see character.Character#getLevel()
	 */
	@Override
	public int getLevel() {
		return 1;
	}

	/* (non-Javadoc)
	 * @see character.Monster#getLoots()
	 */
	@Override
	public ArrayList<Loot> getLoots() {
		ArrayList<Loot> loots = new ArrayList<>();
		loots.add(new Loot(new Leather(), 0.5f, 1));
		return loots;
	}

	/* (non-Javadoc)
	 * @see character.Monster#getName()
	 */
	@Override
	public String getName() {
		return "Gobelin";
	}

}
