package fr.idlerpg.database.characters.heroes;

import java.util.ArrayList;
import java.util.EnumMap;

import fr.idlerpg.character.Hero;
import fr.idlerpg.database.characters.Attribute;
import fr.idlerpg.database.characters.AttributePriority;
import fr.idlerpg.database.factories.ItemFactory;
import fr.idlerpg.database.items.ItemType;

/**
 * The Class Rogue.
 */
public class Rogue extends Hero {

	/**
	 * Instantiates a new rogue.
	 * 
	 * @param name
	 *            the name
	 */
	public Rogue(final String name) {
		super(name);
	}

	/* (non-Javadoc)
	 * @see fr.idlerpg.character.Hero#getAllowedItemTypes()
	 */
	@Override
	public ArrayList<ItemType> getAllowedItemTypes() {
		final ArrayList<ItemType> types = super.getAllowedItemTypes();
		types.add(ItemType.DAGGER);
		types.add(ItemType.MEDIUM_ARMOR);
		types.add(ItemType.LIGHT_SHIELD);
		return types;
	}

	/* (non-Javadoc)
	 * @see fr.idlerpg.character.Hero#getBaseAttributes()
	 */
	@Override
	public EnumMap<Attribute, Integer> getBaseAttributes() {
		final EnumMap<Attribute, Integer> attributes = new EnumMap<>(Attribute.class);
		attributes.put(Attribute.CHARISMA, 1);
		attributes.put(Attribute.CONSTITUTION, 1);
		attributes.put(Attribute.DEXTERITY, 3);
		attributes.put(Attribute.INTELLIGENCE, 2);
		attributes.put(Attribute.STRENGH, 2);
		attributes.put(Attribute.WISDOM, 1);
		return attributes;
	}

	/* (non-Javadoc)
	 * @see fr.idlerpg.character.Hero#getAttributePriority()
	 */
	@Override
	protected EnumMap<Attribute, AttributePriority> getAttributePriority() {
		final EnumMap<Attribute, AttributePriority> res = super.getAttributePriority();
		res.put(Attribute.STRENGH, AttributePriority.HIGH);
		res.put(Attribute.DEXTERITY, AttributePriority.HIGH);
		res.put(Attribute.INTELLIGENCE, AttributePriority.NORMAL);
		res.put(Attribute.CONSTITUTION, AttributePriority.LOW);
		res.put(Attribute.WISDOM, AttributePriority.LOW);
		res.put(Attribute.CHARISMA, AttributePriority.LOW);
		return res;
	}

	/* (non-Javadoc)
	 * @see fr.idlerpg.character.Hero#init()
	 */
	@Override
	protected void init() {
		super.init();
		this.addItem(ItemFactory.getWeapon("Knife"), 1);
	}

}
