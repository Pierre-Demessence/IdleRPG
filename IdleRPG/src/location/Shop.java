/*
 * Author : Pierre
 * Last Update : 12 sept. 2013 - 04:07:18
 */
package location;

import item.Consumable;
import item.Equipment;
import item.Item;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.collections4.bag.HashBag;

import util.Logger;
import character.Hero;
import database.items.Slot;
import database.items.consumables.SmallLifePotion;
import database.items.equipements.weapons.LongSword;
import database.items.equipements.weapons.ShortSword;

// TODO: Auto-generated Javadoc
/**
 * The Class Shop.
 */
public class Shop implements Location {

	/** The instance. */
	private static Shop		instance;

	/** The is initialised. */
	private static boolean	isInitialised;

	/**
	 * Gets the single instance of Shop.
	 * 
	 * @return single instance of Shop
	 */
	public static final Shop getInstance() {
		if( !Shop.isInitialised )
			Shop.init();
		return Shop.instance;
	}

	/**
	 * Inits the.
	 */
	private static final void init() {

		Shop.instance = new Shop();

		Shop.isInitialised = true;
	}

	/** The delay before restock. */
	private final long			delayBeforeRestock		= 60 * 1000;

	/** The items. */
	private final HashBag<Item>	items;

	/** The time since last restock. */
	private long				timeSinceLastRestock	= Integer.MIN_VALUE;

	/**
	 * Instantiates a new shop.
	 */
	private Shop() {
		this.items = new HashBag<>();
	}

	/**
	 * Buy.
	 * 
	 * @param hero
	 *            the hero
	 * @param i
	 *            the i
	 * @param n
	 *            the n
	 */
	public void buy(final Hero hero, final Item i, final int n) {
		final int totalValue = (int) Math.floor(i.getValue() / 2) * n;
		hero.addGold(+totalValue);
		Logger.log(hero, "Je vend " + n + " " + i.getName() + " pour un total de " + totalValue + "po.");
		hero.removeItem(i, n);
		this.items.add(i, n);
	}

	/**
	 * Gets the consumables.
	 * 
	 * @return the consumables
	 */
	public HashSet<Consumable> getConsumables() {
		final HashSet<Consumable> consos = new HashSet<>();
		for( final Item i : this.items )
			if( i instanceof Consumable )
				consos.add((Consumable) i);
		return consos;
	}

	/**
	 * Gets the count.
	 * 
	 * @param i
	 *            the i
	 * @return the count
	 */
	public int getCount(final Item i) {
		return this.items.getCount(i);
	}

	/**
	 * Gets the delay before restock.
	 * 
	 * @return the delay before restock
	 */
	public long getDelayBeforeRestock() {
		return this.delayBeforeRestock;
	}

	/**
	 * Gets the equipments.
	 * 
	 * @param slot
	 *            the slot
	 * @param maxlevel
	 *            the maxlevel
	 * @return the equipments
	 */
	public TreeSet<Equipment> getEquipments(final Slot slot, final int maxlevel) {
		final TreeSet<Equipment> equipments = new TreeSet<>(Collections.reverseOrder(Item.VALUE_ORDER));

		for( final Item i : this.items )
			if( i instanceof Equipment ) {
				final Equipment e = (Equipment) i;
				if( ( ( slot == null ) || e.getSlot().equals(slot) ) && ( ( maxlevel == 0 ) || ( e.getLevel() <= maxlevel ) ) )
					equipments.add(e);
			}
		return equipments;
	}

	/**
	 * Gets the items.
	 * 
	 * @return the items
	 */
	public Set<Item> getItems() {
		return this.items.uniqueSet();
	}

	/**
	 * Gets the time since last restock.
	 * 
	 * @return the time since last restock
	 */
	public long getTimeSinceLastRestock() {
		return this.timeSinceLastRestock;
	}

	/**
	 * Restock.
	 * 
	 * @param time
	 *            the time
	 */
	public void restock(final long time) {
		this.timeSinceLastRestock = time;
		this.items.add(new SmallLifePotion(), 10);
		this.items.add(new ShortSword());
		this.items.add(new LongSword());
	}

	/**
	 * Sell.
	 * 
	 * @param hero
	 *            the hero
	 * @param i
	 *            the i
	 * @param n
	 *            the n
	 */
	public void sell(final Hero hero, final Item i, final int n) {
		final int totalValue = i.getValue() * n;
		hero.addGold(-totalValue);
		Logger.log(hero, "J'achète " + n + " " + i.getName() + " pour un total de " + totalValue + "po.");
		hero.addItem(i, n);
		this.items.remove(i, n);
	}

}
