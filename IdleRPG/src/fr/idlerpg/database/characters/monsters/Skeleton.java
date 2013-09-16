package fr.idlerpg.database.characters.monsters;

import java.util.ArrayList;
import java.util.EnumMap;

import fr.idlerpg.character.Monster;
import fr.idlerpg.database.characters.Attribute;
import fr.idlerpg.database.items.equipments.armors.Helm;
import fr.idlerpg.database.items.equipments.armors.Necklace;
import fr.idlerpg.database.items.equipments.weapons.LargeDagger;
import fr.idlerpg.database.items.useless.Bone;
import fr.idlerpg.item.Loot;

public class Skeleton extends Monster {

	@Override
	public int getBaseArmor() {
		return 2;
	}

	@Override
	public EnumMap<Attribute, Integer> getBaseAttributes() {
		final EnumMap<Attribute, Integer> attributes = new EnumMap<>(Attribute.class);
		attributes.put(Attribute.CHARISMA, 1);
		attributes.put(Attribute.CONSTITUTION, 7);
		attributes.put(Attribute.DEXTERITY, 3);
		attributes.put(Attribute.INTELLIGENCE, 1);
		attributes.put(Attribute.STRENGH, 2);
		attributes.put(Attribute.WISDOM, 1);
		return attributes;
	}

	@Override
	public String getName() {
		return "Skeleton";
	}

	@Override
	public ArrayList<Loot> getLoots() {
		ArrayList<Loot> loots = super.getLoots();
		loots.add(new Loot(new Bone(), 0.5f, 2));
		loots.add(new Loot(new LargeDagger(), 0.2f, 1));
		loots.add(new Loot(new Helm(), 0.1f, 1));
		loots.add(new Loot(new Necklace(), 0.1f, 1));
		return loots;
	}

	@Override
	public int getLevel() {
		return 2;
	}

}
