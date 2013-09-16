package fr.idlerpg.database.characters.monsters;

import java.util.ArrayList;
import java.util.EnumMap;

import fr.idlerpg.character.Monster;
import fr.idlerpg.database.characters.Attribute;
import fr.idlerpg.database.items.equipments.armors.Amulet;
import fr.idlerpg.database.items.equipments.armors.Shoes;
import fr.idlerpg.database.items.useless.Leather;
import fr.idlerpg.item.Loot;

public class Zombie extends Monster {

	@Override
	public int getBaseArmor() {
		return 0;
	}

	@Override
	public EnumMap<Attribute, Integer> getBaseAttributes() {
		final EnumMap<Attribute, Integer> attributes = new EnumMap<>(Attribute.class);
		attributes.put(Attribute.CHARISMA, 1);
		attributes.put(Attribute.CONSTITUTION, 5);
		attributes.put(Attribute.DEXTERITY, 1);
		attributes.put(Attribute.INTELLIGENCE, 1);
		attributes.put(Attribute.STRENGH, 1);
		attributes.put(Attribute.WISDOM, 1);
		return attributes;
	}

	@Override
	public String getName() {
		return "Zombie";
	}

	@Override
	public ArrayList<Loot> getLoots() {
		ArrayList<Loot> loots = super.getLoots();
		loots.add(new Loot(new Leather(), 0.40f, 2));
		loots.add(new Loot(new Amulet(), 0.10f, 1));
		loots.add(new Loot(new Shoes(), 0.05f, 1));
		return loots;
	}

	@Override
	public int getLevel() {
		return 1;
	}

}
