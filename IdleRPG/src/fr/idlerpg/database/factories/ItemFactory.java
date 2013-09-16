package fr.idlerpg.database.factories;

import fr.idlerpg.item.Armor;
import fr.idlerpg.item.Consumable;
import fr.idlerpg.item.Equipment;
import fr.idlerpg.item.Item;
import fr.idlerpg.item.Weapon;

public class ItemFactory {

	private static Item getItem(String className) {
		className = "fr.idlerpg.database.items." + className;
		try {
			Item item = (Item) Class.forName(className).newInstance();
			return item;
		} catch( InstantiationException | IllegalAccessException | ClassNotFoundException e ) {
			e.printStackTrace();
		}
		return null;

	}

	private static Equipment getEquipment(String className, boolean modifier) {
		Equipment equipment = (Equipment) getItem("equipments." + className);
		if( modifier )
			equipment.addRandomModifiers(equipment.getMaxModifiers());
		return equipment;
	}

	public static Weapon getWeapon(String className, boolean modifier) {
		return (Weapon) getEquipment("weapons." + className, modifier);
	}

	public static Weapon getWeapon(String className) {
		return getWeapon(className, false);
	}

	public static Armor getArmor(String className, boolean modifier) {
		return (Armor) getEquipment("armors." + className, modifier);
	}

	public static Armor getArmor(String className) {
		return getArmor(className, false);
	}

	public static Consumable getConsumable(String className) {
		return (Consumable) getItem("consumables." + className);
	}

}
