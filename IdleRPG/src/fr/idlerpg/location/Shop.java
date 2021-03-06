package fr.idlerpg.location;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.collections4.bag.HashBag;

import fr.idlerpg.character.Hero;
import fr.idlerpg.database.items.EquipmentSlot;
import fr.idlerpg.database.items.consumables.BigLifePotion;
import fr.idlerpg.database.items.consumables.MediumLifePotion;
import fr.idlerpg.database.items.consumables.SmallLifePotion;
import fr.idlerpg.item.Armor;
import fr.idlerpg.item.Consumable;
import fr.idlerpg.item.Item;
import fr.idlerpg.main.IdleRPG;
import fr.idlerpg.util.Logger;

/**
 * The Class Shop.
 */
public class Shop implements Location {

	/** The delay before restock. */
	private final static long	DELAY_BEFORE_RESTOCK	= 60 * 1000;

	/** The instance. */
	private static Shop			INSTANCE;

	/**
	 * Gets the single instance of Shop.
	 * 
	 * @return single instance of Shop
	 */
	public static final Shop getInstance() {
		if( Shop.INSTANCE == null )
			Shop.init();
		return Shop.INSTANCE;
	}

	/**
	 * Inits the.
	 */
	private static final void init() {
		Shop.INSTANCE = new Shop();
	}

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
		final int totalValue = ( i.getValue() / 2 ) * n;
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
		return Shop.DELAY_BEFORE_RESTOCK;
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
	public TreeSet<Armor> getEquipments(final EquipmentSlot slot, final int maxlevel) {
		final TreeSet<Armor> equipments = new TreeSet<>(Collections.reverseOrder(Item.VALUE_ORDER));

		for( final Item i : this.items )
			if( i instanceof Armor ) {
				final Armor e = (Armor) i;
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

	/* (non-Javadoc)
	 * @see fr.idlerpg.location.Location#getName()
	 */
	@Override
	public String getName() {
		return "Shop";
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
		if( this.timeSinceLastRestock == Integer.MIN_VALUE ) {
			this.items.add(new SmallLifePotion(), 10);
			this.items.add(new MediumLifePotion(), 5);
			this.items.add(new BigLifePotion(), 1);
		}
		this.timeSinceLastRestock = time;
		final int countAliveHeroes = IdleRPG.getInstance().countAliveHeroes();
		this.items.add(new SmallLifePotion(), countAliveHeroes * 2);
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
		Logger.log(hero, "J'ach�te " + n + " " + i.getName() + " pour un total de " + totalValue + "po.");
		hero.addItem(i, n);
		this.items.remove(i, n);
	}

}
