/*
 * Author : Pierre
 * Last Update : 12 sept. 2013 - 23:21:15
 */
package fr.idlerpg.database.characters.monsters;

import java.util.ArrayList;
import java.util.EnumMap;

import fr.idlerpg.character.Monster;
import fr.idlerpg.database.characters.Attribute;
import fr.idlerpg.database.items.consumables.Fruit;
import fr.idlerpg.database.items.equipments.armors.Greaves;
import fr.idlerpg.database.items.equipments.weapons.Knife;
import fr.idlerpg.database.items.useless.Leather;
import fr.idlerpg.item.Loot;

/**
 * The Class Gobelin.
 */
public class Gobelin extends Monster {

	/* (non-Javadoc)
	 * @see character.Monster#getBaseArmor()
	 */
	@Override
	public int getBaseArmor() {
		return 2;
	}

	/* (non-Javadoc)
	 * @see character.Monster#getBaseAttributes()
	 */
	@Override
	public EnumMap<Attribute, Integer> getBaseAttributes() {
		final EnumMap<Attribute, Integer> attributes = new EnumMap<>(Attribute.class);
		attributes.put(Attribute.CHARISMA, 1);
		attributes.put(Attribute.CONSTITUTION, 4);
		attributes.put(Attribute.DEXTERITY, 4);
		attributes.put(Attribute.INTELLIGENCE, 2);
		attributes.put(Attribute.STRENGH, 3);
		attributes.put(Attribute.WISDOM, 1);
		return attributes;
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
		final ArrayList<Loot> loots = super.getLoots();
		loots.add(new Loot(new Leather(), 0.4f, 1));
		loots.add(new Loot(new Fruit(), 0.35f, 1));
		loots.add(new Loot(new Knife(), 0.3f, 1));
		loots.add(new Loot(new Greaves(), 0.1f, 1));
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
