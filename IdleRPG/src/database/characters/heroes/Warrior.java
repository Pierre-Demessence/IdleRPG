package database.characters.heroes;

import java.util.ArrayList;
import java.util.EnumMap;

import character.Hero;
import database.characters.Attribute;
import database.characters.AttributePriority;
import database.items.ItemType;

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
	public ArrayList<ItemType> getAllowedItemTypes() {
		final ArrayList<ItemType> types = super.getAllowedItemTypes();
		types.add(ItemType.SWORD);
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

	@Override
	protected EnumMap<Attribute, AttributePriority> getAttributePriority() {
		EnumMap<Attribute, AttributePriority> res = super.getAttributePriority();
		res.put(Attribute.STRENGH, AttributePriority.HIGH);
		res.put(Attribute.CONSTITUTION, AttributePriority.HIGH);
		res.put(Attribute.INTELLIGENCE, AttributePriority.LOW);
		res.put(Attribute.WISDOM, AttributePriority.LOW);
		res.put(Attribute.CHARISMA, AttributePriority.LOW);
		return res;
	}

}