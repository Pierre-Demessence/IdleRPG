/*
 * Author : Pierre
 * Last Update : 11 sept. 2013 - 23:23:42
 */
package character;

import item.Consumable;
import item.Equipment;
import item.Item;

import java.util.EnumMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.TreeSet;

import location.Shop;

import org.apache.commons.collections4.bag.HashBag;

import util.Logger;
import database.items.Slot;

// TODO: Auto-generated Javadoc
/**
 * Manage all the objects of an hero : the inventory, the equipments and the gold.
 */
public class Inventory {

	/** The hero. */
	private Hero						hero;
	/** The marked to sell. */
	private HashSet<Item>				markedForSale;
	/** The gold. */
	private int							gold;
	/** The equipment. */
	private EnumMap<Slot, Equipment>	equipment;
	/** The inventory. 15 Item slots. No more than 99 of the same item. */
	private HashBag<Item>				inventory;
	private boolean						shopChecked;
	private Iterator<Item>				shopIterator;

	/**
	 * Instantiates a new inventory.
	 * 
	 * @param hero
	 *            the hero
	 */
	public Inventory(Hero hero) {
		this.hero = hero;
		this.inventory = new HashBag<>();
		this.equipment = new EnumMap<>(Slot.class);
		this.markedForSale = new HashSet<>();
		this.gold = 100;
	}

	/**
	 * Checks if the inventory is full.
	 * 
	 * @return true, if the inventory is full
	 */
	public boolean isFull() {
		return this.inventory.size() == 15;
	}

	/**
	 * Gets the armor.
	 * 
	 * @return the armor
	 */
	public int getArmor() {
		int armor = 0;
		for( Entry<Slot, Equipment> e : this.equipment.entrySet() )
			armor += e.getValue().getArmorBonus();
		return armor;
	}

	/**
	 * Adds the gold.
	 * 
	 * @param gain
	 *            the gain
	 */
	public void addGold(final int gain) {
		this.gold += gain;
		this.gold = Math.max(0, this.gold);
	}

	/**
	 * Gets the gold.
	 * 
	 * @return the gold
	 */
	public int getGold() {
		return this.gold;
	}

	/**
	 * Inventory add item.
	 * 
	 * @param item
	 *            the i
	 * @param n
	 *            the quantity
	 */
	public void addItem(final Item item, final int n) {
		int count = this.inventory.getCount(item);
		// Check if we have reached the limit of 99 and discard items beyond that quantity.
		if( count + n > 99 ) {
			Logger.log(this.hero, "Trop de " + item.getName() + " dans l'inventaire.");
			if( count == 99 )
				Logger.log(this.hero, "Je ne pas en porter plus et dois donc jeter les " + n + " nouveaux.");
			else
				Logger.log(this.hero, "Je ne peux en porter que " + ( 99 - count ) + " de plus.");
			this.addItem(item, 99 - count);
		}

		this.inventory.add(item, n);
		// If the item is useless, mark it for sale.
		if( !this.hero.getAllowedItemTypes().contains(item.getType()) )
			this.markedForSale.add(item);
		// If the item can and should be equiped, equip it.
		else if( this.testEquip(item) )
			this.equip((Equipment) item);
	}

	/**
	 * Equip an equipment item, and remove the previous one if there was one.
	 * 
	 * @param equipment
	 *            the equipment
	 */
	private void equip(Equipment equipment) {
		Slot slot = equipment.getSlot();
		if( this.equipment.containsKey(slot) ) {
			Logger.log(this.hero, "Je déséquipe " + this.equipment.get(slot).getName() + ".");
			this.addItem(this.equipment.get(slot), 1);
		}
		Logger.log(this.hero, "Je m'équipe de " + equipment.getName() + ".");
		this.equipment.put(slot, equipment);
		this.removeItem(equipment, 1);
	}

	/**
	 * Test if equip.
	 * 
	 * @param equipement
	 *            the equipement
	 */
	private boolean testEquip(Item item) {
		// If it's not an equipment, return false.
		if( ! ( item instanceof Equipment ) )
			return false;
		Equipment equipment = (Equipment) item;

		// If the class cannot equip this type of item, return false.
		if( !this.hero.getAllowedItemTypes().contains(equipment.getType()) )
			return false;

		// TODO Check the level of the item (when items will have levels).

		// ----- ----- ----- ----- -----
		// Beyond that, the item CAN be equiped.

		// If the slot where the item goes is empty, equip it.
		Slot slot = equipment.getSlot();
		if( !this.equipment.containsKey(slot) )
			return true;

		// TODO Check if the item is better than the already equiped item.

		return false;
	}

	/**
	 * Inventory remove item.
	 * 
	 * @param item
	 *            the item
	 * @param n
	 *            the quantity
	 */
	public void removeItem(final Item item, final int n) {
		this.inventory.remove(item, n);
	}

	/**
	 * Checks if there are objects to sell.
	 * 
	 * @return true, if there are objects in inventory marked for sale.
	 */
	public boolean hasSomethingToSell() {
		return !this.markedForSale.isEmpty();
	}

	/**
	 * Checks for equipment.
	 * 
	 * @param slot
	 *            the slot
	 * @return true, if successful
	 */
	public boolean hasEquipment(Slot slot) {
		return this.equipment.containsKey(slot);
	}

	/**
	 * Gets the equipment.
	 * 
	 * @param slot
	 *            the slot
	 * @return the equipment
	 */
	public Equipment getEquipment(Slot slot) {
		return this.equipment.get(slot);
	}

	/**
	 * Shop can afford.
	 * 
	 * @param i
	 *            the i
	 * @param j
	 *            the j
	 * @return true, if successful
	 */
	private boolean shopCanAfford(final Item i, final int j) {
		return this.gold >= i.getValue();
	}

	/**
	 * Inventory get consumables.
	 * 
	 * @return the hash set
	 */
	private HashSet<Consumable> getConsumables() {
		final HashSet<Consumable> consos = new HashSet<>();
		for( final Item i : this.inventory )
			if( i instanceof Consumable )
				consos.add((Consumable) i);
		return consos;
	}

	/**
	 * Inventory get life consumables.
	 * 
	 * @return the tree set
	 */
	public TreeSet<Consumable> getLifeConsumables() {
		final TreeSet<Consumable> consos = new TreeSet<>(Consumable.LIFE_GAIN_ORDER);
		for( final Consumable c : this.getConsumables() )
			if( c.getLifeGain() > 0 )
				consos.add(c);
		return consos;
	}

	/**
	 * Inventory get mana consumables.
	 * 
	 * @return the tree set
	 */
	public TreeSet<Consumable> getManaConsumables() {
		final TreeSet<Consumable> consos = new TreeSet<>(Consumable.MANA_GAIN_ORDER);
		for( final Consumable c : this.getConsumables() )
			if( c.getManaGain() > 0 )
				consos.add(c);
		return consos;
	}

	/**
	 * Shop sell.
	 */
	public void sell() {
		final Iterator<Item> it = this.markedForSale.iterator();
		final Item i = it.next();
		Shop.getInstance().buy(this.hero, i, this.inventory.getCount(i));
		it.remove();
	}

	public void buy() {
		final Shop shop = Shop.getInstance();

		// On achète des consommable tant que l'on en a besoin.
		if( this.getTotalLifeGain() < this.hero.getMaxLife() * this.hero.getMaxLifeGainToKeep() ) {

			int lifeGainNeed = Math.round(this.hero.getMaxLife() * this.hero.getMaxLifeGainToKeep() - this.getTotalLifeGain());

			TreeSet<Consumable> consumables = new TreeSet<>(Consumable.LIFE_VALUE_RATIO_ORDER);
			consumables.addAll(shop.getConsumables());
			for( Consumable c : consumables )
				if( c.getValue() <= this.getGold() ) {
					shop.sell(this.hero, c, (int) Math.min(Math.min(this.getGold() / c.getValue(), Math.ceil((float) lifeGainNeed / c.getLifeGain())), shop.getCount(c)));
					return;
				}
			this.setShopChecked(true, true);
			System.out.println("Je ne peux rien acheter d'autre.");
		} else
			this.setShopChecked(true, false);

		// Puis on vérifie les autres items un par un les achetant si on doit les acheter.
		// --> Pour plus d'efficacité : Donner la liste des objets du magasin par ordre décroissant de valeur ?
		/*
				final Set<Item> shopItems = shop.getItems();
				if( shopItems.isEmpty() )
					Logger.log("Nous sommes désolé mais le magasin est vide.");
				else {
					Logger.log("Le magasin dispose des objets suivants :");
					for( final Item i : shopItems ) {
						Logger.log("\t" + i.getName() + " au nombre de " + shop.getCount(i) + ".");
						if( this.shopCanAfford(i, 1) ) {
							shop.sell(this.hero, i, 1);
							Logger.log(this.hero, "J'en achète 1.");
						} else
							Logger.log(this.hero, "Je n'ai pas assez d'argent.");
					}
				}*/

	}

	public boolean isShopChecked() {
		return this.shopChecked;
	}

	public void setShopChecked(boolean shopChecked, boolean notEnough) {
		this.shopChecked = shopChecked;
		if( notEnough )
			this.hero.setFightBeforeGoToShop();

		this.shopIterator = null;
	}

	public int getTotalLifeGain() {
		int res = 0;
		for( Consumable c : this.getLifeConsumables() )
			res += c.getLifeGain() * this.inventory.getCount(c);
		return res;
	}

}
