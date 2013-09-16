package fr.idlerpg.database.factories;

import java.util.HashSet;
import java.util.Random;

import fr.idlerpg.database.items.ModifierQuality;
import fr.idlerpg.database.items.modifiers.Address;
import fr.idlerpg.database.items.modifiers.Charm;
import fr.idlerpg.database.items.modifiers.Cunning;
import fr.idlerpg.database.items.modifiers.Golden;
import fr.idlerpg.database.items.modifiers.Knowledge;
import fr.idlerpg.database.items.modifiers.Power;
import fr.idlerpg.database.items.modifiers.Precious;
import fr.idlerpg.database.items.modifiers.Shiny;
import fr.idlerpg.database.items.modifiers.Toughness;
import fr.idlerpg.database.items.modifiers.armors.Padded;
import fr.idlerpg.database.items.modifiers.armors.Reinforced;
import fr.idlerpg.database.items.modifiers.weapons.Dangerous;
import fr.idlerpg.database.items.modifiers.weapons.Deadly;
import fr.idlerpg.database.items.modifiers.weapons.Sharp;
import fr.idlerpg.item.ArmorModifier;
import fr.idlerpg.item.EquipmentModifier;
import fr.idlerpg.item.WeaponModifier;

public class ModifierFactory {

	public enum ModifierSlot {
		ARMOR_MODIFIER,
		WEAPON_MODIFIER;
	}

	private static HashSet<EquipmentModifier>	modifiers;

	private static void init() {
		modifiers = new HashSet<>();
		modifiers.add(new Sharp());
		modifiers.add(new Dangerous());
		modifiers.add(new Deadly());

		modifiers.add(new Shiny());
		modifiers.add(new Precious());
		modifiers.add(new Golden());

		modifiers.add(new Padded());
		modifiers.add(new Reinforced());
		// modifiers.add(new Golden()); ??

		modifiers.add(new Power());
		modifiers.add(new Toughness());
		modifiers.add(new Knowledge());
		modifiers.add(new Cunning());
		modifiers.add(new Charm());
		modifiers.add(new Address());

	}

	private static HashSet<EquipmentModifier> getModifiers(HashSet<EquipmentModifier> set, ModifierSlot slot) {
		HashSet<EquipmentModifier> res = new HashSet<>();
		for( EquipmentModifier em : set )
			if( slot == null || ( slot == ModifierSlot.ARMOR_MODIFIER && ! ( em instanceof WeaponModifier ) ) || ( slot == ModifierSlot.WEAPON_MODIFIER && ! ( em instanceof ArmorModifier ) ) )
				res.add(em);
		return res;
	}

	private static HashSet<EquipmentModifier> getModifiers(HashSet<EquipmentModifier> set, ModifierQuality type) {
		HashSet<EquipmentModifier> res = new HashSet<>();
		for( EquipmentModifier em : set )
			if( type == null || em.getModifierValue() == type )
				res.add(em);
		return res;
	}

	public static EquipmentModifier getModifier(ModifierSlot slot, ModifierQuality type) {
		if( modifiers == null )
			init();

		HashSet<EquipmentModifier> set = getModifiers(modifiers, slot);
		set = getModifiers(set, type);
		Random r = new Random();
		if( set.size() < 1 )
			return null;
		return (EquipmentModifier) set.toArray()[r.nextInt(set.size())];
	}
}
