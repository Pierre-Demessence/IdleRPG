/*
 * Author : Pierre
 * Last Update : 12 sept. 2013 - 04:07:21
 */
package database.heroes;

import java.util.ArrayList;
import java.util.EnumMap;

import character.Attribute;
import character.Hero;
import database.items.Type;
import database.items.equipements.weapons.ShortSword;

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
		this.addGold(200);
		this.addItem(new ShortSword(), 2);
	}

	/* (non-Javadoc)
	 * @see character.Hero#getAllowedItemTypes()
	 */
	@Override
	public ArrayList<Type> getAllowedItemTypes() {
		final ArrayList<Type> types = super.getAllowedItemTypes();
		types.add(Type.SWORD);
		return types;
	}

	/* (non-Javadoc)
	 * @see character.Character#getBaseAttributes()
	 */
	@Override
	public EnumMap<Attribute, Integer> getBaseAttributes() {
		final EnumMap<Attribute, Integer> attributes = new EnumMap<>(Attribute.class);
		attributes.put(Attribute.CHARISMA, 1);
		attributes.put(Attribute.CONSTITUTION, 2);
		attributes.put(Attribute.DEXTERITY, 2);
		attributes.put(Attribute.INTELLIGENCE, 1);
		attributes.put(Attribute.STRENGH, 3);
		attributes.put(Attribute.WISDOM, 1);
		return attributes;
	}

}