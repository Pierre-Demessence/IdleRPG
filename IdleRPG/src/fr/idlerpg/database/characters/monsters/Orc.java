/*
 * Author : Pierre
 * Last Update : 12 sept. 2013 - 04:07:20
 */
package fr.idlerpg.database.characters.monsters;

import java.util.ArrayList;
import java.util.EnumMap;

import fr.idlerpg.character.Monster;
import fr.idlerpg.database.characters.Attribute;
import fr.idlerpg.database.items.consumables.Fruit;
import fr.idlerpg.database.items.equipments.armors.Breastplate;
import fr.idlerpg.database.items.equipments.armors.Ring;
import fr.idlerpg.database.items.equipments.weapons.ShortSword;
import fr.idlerpg.database.items.useless.Bone;
import fr.idlerpg.item.Loot;

/**
 * The Class Orc.
 */
public class Orc extends Monster {

	/* (non-Javadoc)
	 * @see character.Monster#getBaseArmor()
	 */
	@Override
	public int getBaseArmor() {
		return 5;
	}

	/* (non-Javadoc)
	 * @see character.Monster#getBaseAttributes()
	 */
	@Override
	public EnumMap<Attribute, Integer> getBaseAttributes() {
		final EnumMap<Attribute, Integer> attributes = new EnumMap<>(Attribute.class);
		attributes.put(Attribute.CHARISMA, 1);
		attributes.put(Attribute.CONSTITUTION, 6);
		attributes.put(Attribute.DEXTERITY, 4);
		attributes.put(Attribute.INTELLIGENCE, 1);
		attributes.put(Attribute.STRENGH, 6);
		attributes.put(Attribute.WISDOM, 2);
		return attributes;
	}

	/* (non-Javadoc)
	 * @see character.Character#getLevel()
	 */
	@Override
	public int getLevel() {
		return 3;
	}

	/* (non-Javadoc)
	 * @see character.Monster#getLoots()
	 */
	@Override
	public ArrayList<Loot> getLoots() {
		ArrayList<Loot> loots = super.getLoots();
		loots.add(new Loot(new Bone(), 0.15f, 1));
		loots.add(new Loot(new ShortSword(), 0.1f, 1));
		loots.add(new Loot(new Fruit(), 0.3f, 2));
		loots.add(new Loot(new Breastplate(), 0.2f, 1));
		loots.add(new Loot(new Ring(), 0.05f, 1));
		return loots;
	}

	/* (non-Javadoc)
	 * @see character.Monster#getName()
	 */
	@Override
	public String getName() {
		return "Orc";
	}

}
