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

/**
 * A factory for creating Modifier objects.
 */
public class ModifierFactory {

	/**
	 * The Enum ModifierSlot.
	 */
	public enum ModifierSlot {

		/** The armor modifier. */
		ARMOR_MODIFIER,

		/** The weapon modifier. */
		WEAPON_MODIFIER;
	}

	/** The modifiers. */
	private static HashSet<EquipmentModifier>	modifiers;

	/**
	 * Gets the modifier.
	 * 
	 * @param slot
	 *            the slot
	 * @param type
	 *            the type
	 * @return the modifier
	 */
	public static EquipmentModifier getModifier(final ModifierSlot slot, final ModifierQuality type) {
		if( ModifierFactory.modifiers == null )
			ModifierFactory.init();

		HashSet<EquipmentModifier> set = ModifierFactory.getModifiers(ModifierFactory.modifiers, slot);
		set = ModifierFactory.getModifiers(set, type);
		final Random r = new Random();
		if( set.size() < 1 )
			return null;
		return (EquipmentModifier) set.toArray()[r.nextInt(set.size())];
	}

	/**
	 * Gets the modifiers.
	 * 
	 * @param set
	 *            the set
	 * @param type
	 *            the type
	 * @return the modifiers
	 */
	private static HashSet<EquipmentModifier> getModifiers(final HashSet<EquipmentModifier> set, final ModifierQuality type) {
		final HashSet<EquipmentModifier> res = new HashSet<>();
		for( final EquipmentModifier em : set )
			if( ( type == null ) || ( em.getModifierValue() == type ) )
				res.add(em);
		return res;
	}

	/**
	 * Gets the modifiers.
	 * 
	 * @param set
	 *            the set
	 * @param slot
	 *            the slot
	 * @return the modifiers
	 */
	private static HashSet<EquipmentModifier> getModifiers(final HashSet<EquipmentModifier> set, final ModifierSlot slot) {
		final HashSet<EquipmentModifier> res = new HashSet<>();
		for( final EquipmentModifier em : set )
			if( ( slot == null ) || ( ( slot == ModifierSlot.ARMOR_MODIFIER ) && ! ( em instanceof WeaponModifier ) ) || ( ( slot == ModifierSlot.WEAPON_MODIFIER ) && ! ( em instanceof ArmorModifier ) ) )
				res.add(em);
		return res;
	}

	/**
	 * Inits the.
	 */
	private static void init() {
		ModifierFactory.modifiers = new HashSet<>();
		ModifierFactory.modifiers.add(new Sharp());
		ModifierFactory.modifiers.add(new Dangerous());
		ModifierFactory.modifiers.add(new Deadly());

		ModifierFactory.modifiers.add(new Shiny());
		ModifierFactory.modifiers.add(new Precious());
		ModifierFactory.modifiers.add(new Golden());

		ModifierFactory.modifiers.add(new Padded());
		ModifierFactory.modifiers.add(new Reinforced());
		// modifiers.add(new Golden()); ??

		ModifierFactory.modifiers.add(new Power());
		ModifierFactory.modifiers.add(new Toughness());
		ModifierFactory.modifiers.add(new Knowledge());
		ModifierFactory.modifiers.add(new Cunning());
		ModifierFactory.modifiers.add(new Charm());
		ModifierFactory.modifiers.add(new Address());

	}
}
