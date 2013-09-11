/*
 * Author : Pierre
 * Last Update : 11 sept. 2013 - 16:36:24
 */
package database.heroes;

import item.Type;

import java.util.ArrayList;
import java.util.EnumMap;

import character.Attribute;
import character.Hero;

// TODO: Auto-generated Javadoc
/**
 * The Class Warrior.
 */
public class Warrior extends Hero {

	/**
	 * Instantiates a new warrior.
	 * 
	 * @param name
	 *            the name
	 */
	public Warrior(final String name) {
		super(name);
	}

	/* (non-Javadoc)
	 * @see character.Hero#getAllowedItemTypes()
	 */
	@Override
	public ArrayList<Type> getAllowedItemTypes() {
		ArrayList<Type> types = super.getAllowedItemTypes();
		types.add(Type.SWORD);
		return types;
	}

	/* (non-Javadoc)
	 * @see character.Character#getBaseAttributes()
	 */
	@Override
	public EnumMap<Attribute, Integer> getBaseAttributes() {
		EnumMap<Attribute, Integer> attributes = new EnumMap<>(Attribute.class);
		attributes.put(Attribute.CHARISMA, 1);
		attributes.put(Attribute.CONSTITUTION, 2);
		attributes.put(Attribute.DEXTERITY, 2);
		attributes.put(Attribute.INTELLIGENCE, 1);
		attributes.put(Attribute.STRENGH, 3);
		attributes.put(Attribute.WISDOM, 1);
		return attributes;
	}

}