package fr.idlerpg.database.factories;

import fr.idlerpg.item.Armor;
import fr.idlerpg.item.Consumable;
import fr.idlerpg.item.Equipment;
import fr.idlerpg.item.Item;
import fr.idlerpg.item.Weapon;

/**
 * A factory for creating Item objects.
 */
public class ItemFactory {

	/**
	 * Gets the armor.
	 * 
	 * @param className
	 *            the class name
	 * @return the armor
	 */
	public static Armor getArmor(final String className) {
		return ItemFactory.getArmor(className, false);
	}

	public static Item getUseless(final String className) {
		return getItem("useless." + className);
	}

	/**
	 * Gets the armor.
	 * 
	 * @param className
	 *            the class name
	 * @param modifier
	 *            the modifier
	 * @return the armor
	 */
	public static Armor getArmor(final String className, final boolean modifiers) {
		return (Armor) ItemFactory.getEquipment("armors." + className, modifiers);
	}

	/**
	 * Gets the consumable.
	 * 
	 * @param className
	 *            the class name
	 * @return the consumable
	 */
	public static Consumable getConsumable(final String className) {
		return (Consumable) ItemFactory.getItem("consumables." + className);
	}

	/**
	 * Gets the weapon.
	 * 
	 * @param className
	 *            the class name
	 * @return the weapon
	 */
	public static Weapon getWeapon(final String className) {
		return ItemFactory.getWeapon(className, false);
	}

	/**
	 * Gets the weapon.
	 * 
	 * @param className
	 *            the class name
	 * @param modifier
	 *            the modifier
	 * @return the weapon
	 */
	public static Weapon getWeapon(final String className, final boolean modifiers) {
		return (Weapon) ItemFactory.getEquipment("weapons." + className, modifiers);
	}

	/**
	 * Gets the equipment.
	 * 
	 * @param className
	 *            the class name
	 * @param modifier
	 *            the modifier
	 * @return the equipment
	 */
	private static Equipment getEquipment(final String className, final boolean modifiers) {
		final Equipment equipment = (Equipment) ItemFactory.getItem("equipments." + className);
		if( modifiers )
			equipment.addRandomModifiers(equipment.getMaxModifiers());
		return equipment;
	}

	/**
	 * Gets the item.
	 * 
	 * @param className
	 *            the class name
	 * @return the item
	 */
	private static Item getItem(String className) {
		className = "fr.idlerpg.database.items." + className;
		try {
			final Item item = (Item) Class.forName(className).newInstance();
			return item;
		} catch( InstantiationException | IllegalAccessException | ClassNotFoundException e ) {
			e.printStackTrace();
		}
		return null;

	}

}
