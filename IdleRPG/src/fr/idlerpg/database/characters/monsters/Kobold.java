package fr.idlerpg.database.characters.monsters;

import java.util.ArrayList;
import java.util.EnumMap;

import fr.idlerpg.character.Monster;
import fr.idlerpg.database.characters.Attribute;
import fr.idlerpg.database.items.consumables.Fruit;
import fr.idlerpg.database.items.equipments.armors.Buckler;
import fr.idlerpg.database.items.equipments.armors.Hood;
import fr.idlerpg.database.items.equipments.armors.Necklace;
import fr.idlerpg.database.items.equipments.weapons.Knife;
import fr.idlerpg.database.items.useless.Bone;
import fr.idlerpg.item.Loot;

public class Kobold extends Monster {

	@Override
	public int getBaseArmor() {
		return 1;
	}

	@Override
	public ArrayList<Loot> getLoots() {
		ArrayList<Loot> loots = super.getLoots();
		loots.add(new Loot(new Bone(), 0.10f, 1));
		loots.add(new Loot(new Knife(), 0.20f, 1));
		loots.add(new Loot(new Hood(), 0.10f, 1));
		loots.add(new Loot(new Necklace(), 0.10f, 1));
		loots.add(new Loot(new Buckler(), 0.05f, 1));
		loots.add(new Loot(new Fruit(), 0.15f, 1));
		return loots;
	}

	@Override
	public EnumMap<Attribute, Integer> getBaseAttributes() {
		final EnumMap<Attribute, Integer> attributes = new EnumMap<>(Attribute.class);
		attributes.put(Attribute.CHARISMA, 1);
		attributes.put(Attribute.CONSTITUTION, 1);
		attributes.put(Attribute.DEXTERITY, 3);
		attributes.put(Attribute.INTELLIGENCE, 1);
		attributes.put(Attribute.STRENGH, 2);
		attributes.put(Attribute.WISDOM, 1);
		return attributes;
	}

	@Override
	public String getName() {
		return "Kobold";
	}

	@Override
	public int getLevel() {
		return 1;
	}

}
