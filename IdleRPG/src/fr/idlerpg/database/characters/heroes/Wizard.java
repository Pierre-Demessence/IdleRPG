package fr.idlerpg.database.characters.heroes;

import java.util.ArrayList;
import java.util.EnumMap;

import fr.idlerpg.character.Hero;
import fr.idlerpg.database.characters.Attribute;
import fr.idlerpg.database.characters.AttributePriority;
import fr.idlerpg.database.factories.ItemFactory;
import fr.idlerpg.database.items.ItemType;

/**
 * The Class Wizard.
 */
public class Wizard extends Hero {

	/**
	 * Instantiates a new wizard.
	 * 
	 * @param name
	 *            the name
	 */
	public Wizard(final String name) {
		super(name);
	}

	/* (non-Javadoc)
	 * @see fr.idlerpg.character.Hero#getAllowedItemTypes()
	 */
	@Override
	protected ArrayList<ItemType> getAllowedItemTypes() {
		final ArrayList<ItemType> types = super.getAllowedItemTypes();
		types.add(ItemType.STAFF);
		return types;
	}

	/* (non-Javadoc)
	 * @see fr.idlerpg.character.Hero#getAttributePriority()
	 */
	@Override
	protected EnumMap<Attribute, AttributePriority> getAttributePriority() {
		final EnumMap<Attribute, AttributePriority> res = super.getAttributePriority();
		res.put(Attribute.INTELLIGENCE, AttributePriority.HIGH);
		res.put(Attribute.WISDOM, AttributePriority.HIGH);
		res.put(Attribute.DEXTERITY, AttributePriority.NORMAL);
		res.put(Attribute.CONSTITUTION, AttributePriority.LOW);
		res.put(Attribute.STRENGH, AttributePriority.LOW);
		res.put(Attribute.CHARISMA, AttributePriority.LOW);
		return res;
	}

	/* (non-Javadoc)
	 * @see fr.idlerpg.character.Hero#getBaseAttributes()
	 */
	@Override
	protected EnumMap<Attribute, Integer> getBaseAttributes() {
		final EnumMap<Attribute, Integer> attributes = new EnumMap<>(Attribute.class);
		attributes.put(Attribute.CHARISMA, 1);
		attributes.put(Attribute.CONSTITUTION, 1);
		attributes.put(Attribute.DEXTERITY, 2);
		attributes.put(Attribute.INTELLIGENCE, 2);
		attributes.put(Attribute.STRENGH, 1);
		attributes.put(Attribute.WISDOM, 3);
		return attributes;
	}

	/* (non-Javadoc)
	 * @see fr.idlerpg.character.Hero#init()
	 */
	@Override
	protected void init() {
		super.init();
		this.addItem(ItemFactory.getWeapon("NoviceStaff"), 1);
	}

}
