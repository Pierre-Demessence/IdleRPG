package database.factories;

import item.ArmorModifier;
import item.EquipmentModifier;
import item.WeaponModifier;

import java.util.HashSet;
import java.util.Random;

import database.items.ModifierQuality;
import database.items.modifiers.Golden;
import database.items.modifiers.Precious;
import database.items.modifiers.Shiny;
import database.items.modifiers.weapons.Sharp;

public class ModifierFactory {

	public enum ModifierSlot {
		ARMOR_MODIFIER,
		WEAPON_MODIFIER;
	}

	private static HashSet<EquipmentModifier>	modifiers;

	private static void init() {
		modifiers = new HashSet<>();
		modifiers.add(new Sharp());

		modifiers.add(new Shiny());
		modifiers.add(new Precious());
		modifiers.add(new Golden());
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
