/*
 * Author : Pierre
 * Last Update : 12 sept. 2013 - 04:07:20
 */
package fr.idlerpg.character;

import java.util.Collections;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.TreeSet;

import org.apache.commons.collections4.bag.HashBag;

import fr.idlerpg.database.items.EquipmentSlot;
import fr.idlerpg.item.Armor;
import fr.idlerpg.item.Consumable;
import fr.idlerpg.item.Equipment;
import fr.idlerpg.item.Item;
import fr.idlerpg.location.Shop;
import fr.idlerpg.util.Logger;

/**
 * Manage all the objects of an hero : the inventory, the equipments and the gold.
 */
public class Inventory {

	/** The equipment. */
	private final EnumMap<EquipmentSlot, Equipment>	equipment;
	/** The gold. */
	private int										gold;
	/** The hero. */
	private final Hero								hero;
	/** The inventory. 15 Item slots. No more than 99 of the same item. */
	private final HashBag<Item>						inventory;
	/** The marked to sell. */
	private final HashSet<Item>						markedForSale;

	/** The shop checked. */
	private boolean									shopChecked;

	/**
	 * Instantiates a new inventory.
	 * 
	 * @param hero
	 *            the hero
	 */
	public Inventory(final Hero hero) {
		this.hero = hero;
		this.inventory = new HashBag<>();
		this.equipment = new EnumMap<>(EquipmentSlot.class);
		this.markedForSale = new HashSet<>();
		this.gold = 100;
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
	 * Inventory add item.
	 * 
	 * @param item
	 *            the i
	 * @param n
	 *            the quantity
	 */
	public void addItem(final Item item, final int n) {
		if( !this.inventory.contains(item) && this.isFull() ) {
			Logger.log(this.hero, "Je n'ai plus de place dans mon inventaire.");

			final TreeSet<Item> markedForSale = new TreeSet<>(Item.VALUE_ORDER);
			markedForSale.addAll(this.markedForSale);

			final Item firstItemForSale = markedForSale.first();
			final int countFirstItemForSale = this.inventory.getCount(firstItemForSale);

			if( ( firstItemForSale.getValue() * countFirstItemForSale ) <= ( item.getValue() * n ) ) {
				Logger.log(this.hero, "Je jette mes " + countFirstItemForSale + " " + firstItemForSale.getName() + " pour faire de la place.");
				this.removeItem(firstItemForSale, countFirstItemForSale);
			} else {
				Logger.log(this.hero, "Je dois jeter " + n + " nouveaux " + item.getName() + ".");
				return;
			}
		}

		final int count = this.inventory.getCount(item);
		// Check if we have reached the limit of 99 and discard items beyond that quantity.
		if( ( count + n ) > 99 ) {
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
		else if( this.testEquip(item) ) {
			this.equip((Equipment) item);
			if( n > 1 )
				this.markedForSale.add(item);
		} else if( item instanceof Equipment )
			this.markedForSale.add(item);
	}

	/**
	 * Buy.
	 */
	public void buy() {
		final Shop shop = Shop.getInstance();

		// On achète des consommable tant que l'on en a besoin.
		if( this.getTotalLifeGain() < ( this.hero.getMaxLife() * this.hero.getMaxLifeGainToKeep() ) ) {

			final int lifeGainNeed = Math.round( ( this.hero.getMaxLife() * this.hero.getMaxLifeGainToKeep() ) - this.getTotalLifeGain());

			final TreeSet<Consumable> consumables = new TreeSet<>(Consumable.LIFE_VALUE_RATIO_ORDER);
			consumables.addAll(shop.getConsumables());
			for( final Consumable c : consumables )
				if( c.getValue() <= this.getGold() ) {
					final int maxCanBuy = this.getGold() / c.getValue();
					final double maxNeeded = Math.ceil((float) lifeGainNeed / c.getLifeGain());
					shop.sell(this.hero, c, (int) Math.min(Math.min(maxCanBuy, maxNeeded), shop.getCount(c)));
					return;
				}
			this.setShopChecked(true, true);
			Logger.log(this.hero, "J'ai besoin de plus de consommables, mais je n'ai pas assez d'argent.");
		} else {

			final TreeSet<Equipment> equipments = new TreeSet<>(Collections.reverseOrder(Item.VALUE_ORDER));
			equipments.addAll(shop.getEquipments(null, this.hero.getLevel()));
			for( final Equipment e : equipments )
				if( ( e.getValue() <= this.getGold() ) && this.testEquip(e) ) {
					shop.sell(this.hero, e, 1);
					return;
				}
			// On a parcouru tous les items, on peut partir tranquille.
			this.setShopChecked(true, false);
			Logger.log(this.hero, "Je n'ai rien d'autre à acheter.");
		}
	}

	/**
	 * Gets the armor.
	 * 
	 * @return the armor
	 */
	public int getArmor() {
		int armor = 0;
		for( final Entry<EquipmentSlot, Equipment> e : this.equipment.entrySet() )
			if( e.getValue() instanceof Armor )
				armor += ( (Armor) e.getValue() ).getArmorBonus();
		return armor;
	}

	/**
	 * Gets the equipment.
	 * 
	 * @return the equipment
	 */
	public EnumMap<EquipmentSlot, Equipment> getEquipment() {
		return this.equipment;
	}

	/**
	 * Gets the equipment.
	 * 
	 * @param slot
	 *            the slot
	 * @return the equipment
	 */
	public Equipment getEquipment(final EquipmentSlot slot) {
		return this.equipment.get(slot);
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
	 * Gets the total life gain.
	 * 
	 * @return the total life gain
	 */
	public int getTotalLifeGain() {
		int res = 0;
		for( final Consumable c : this.getLifeConsumables() )
			res += c.getLifeGain() * this.inventory.getCount(c);
		return res;
	}

	/**
	 * Checks for equipment.
	 * 
	 * @param slot
	 *            the slot
	 * @return true, if successful
	 */
	public boolean hasEquipment(final EquipmentSlot slot) {
		return this.equipment.containsKey(slot);
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
	 * Checks if the inventory is full.
	 * 
	 * @return true, if the inventory is full
	 */
	public boolean isFull() {
		return this.inventory.uniqueSet().size() == 5;
	}

	/**
	 * Checks if is shop checked.
	 * 
	 * @return true, if is shop checked
	 */
	public boolean isShopChecked() {
		return this.shopChecked;
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
	 * Shop sell.
	 */
	public void sell() {
		final Iterator<Item> it = this.markedForSale.iterator();
		final Item i = it.next();
		Shop.getInstance().buy(this.hero, i, this.inventory.getCount(i));
		it.remove();
	}

	/**
	 * Sets the shop checked.
	 * 
	 * @param shopChecked
	 *            the shop checked
	 * @param notEnough
	 *            the not enough
	 */
	public void setShopChecked(final boolean shopChecked, final boolean notEnough) {
		this.shopChecked = shopChecked;
		if( notEnough )
			this.hero.setFightBeforeGoToShop();
	}

	/**
	 * Equip an equipment item, and remove the previous one if there was one.
	 * 
	 * @param item
	 *            the equipment
	 */
	private void equip(final Equipment item) {
		final EquipmentSlot slot = item.getSlot();
		if( this.equipment.containsKey(slot) ) {
			Logger.log(this.hero, "Je déséquipe " + this.equipment.get(slot).getName() + ".");
			this.addItem(this.equipment.get(slot), 1);
		}
		Logger.log(this.hero, "Je m'équipe de " + item.getName() + ".");
		this.equipment.put(slot, item);
		this.removeItem(item, 1);
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
	 * Test if equip.
	 * 
	 * @param item
	 *            the item
	 * @return true, if successful
	 */
	private boolean testEquip(final Item item) {
		// If it's not an equipment, return false.
		if( ! ( item instanceof Equipment ) )
			return false;
		final Equipment equipment = (Equipment) item;

		// If the item has no bonuses, return false;
		if( equipment.getCalculatedValue() == 0 )
			return false;

		// If the class cannot equip this type of item, return false.
		if( !this.hero.getAllowedItemTypes().contains(equipment.getType()) )
			return false;

		// Check the level of the item.
		if( equipment.getLevel() > this.hero.getLevel() )
			return false;

		// ----- ----- ----- ----- -----
		// Beyond that, the item CAN be equiped.

		// If the slot where the item goes is empty, equip it.
		final EquipmentSlot slot = equipment.getSlot();
		if( !this.equipment.containsKey(slot) )
			return true;

		// Beyond that, there is already an item in the corresponding slot.
		// Check if the item is better than the already equiped item.
		if( Equipment.CALCULATED_VALUE_ORDER.compare(equipment, this.equipment.get(slot)) > 0 )
			return true;

		return false;
	}

}
