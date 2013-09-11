/*
 * Author : Pierre
 * Last Update : 11 sept. 2013 - 16:41:12
 */
package character;

import item.Consumable;
import item.Equipment;
import item.Item;
import item.Weapon;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

import location.Dungeon;
import location.Exploration;
import location.Location;
import location.Shop;
import util.Formula;
import util.Logger;
import database.GlobalFormula;
import database.items.Slot;
import database.items.Type;

// TODO: Auto-generated Javadoc
/**
 * The Class Hero.
 */
public abstract class Hero extends Character {

	/**
	 * The Enum DelayType.
	 */
	private enum DelayType {

		/** The dungeon. */
		DUNGEON(2500),

		/** The exploration. */
		EXPLORATION(5000),

		/** The fighting. */
		FIGHTING(1000),

		/** The shop buying. */
		SHOP_BUYING(1000),

		/** The shop selling. */
		SHOP_SELLING(1000);

		/** The delay. */
		private long	delay;

		/**
		 * Instantiates a new delay type.
		 * 
		 * @param ms
		 *            the ms
		 */
		DelayType(final long ms) {
			this.delay = ms;
		}

		/**
		 * Gets the delay.
		 * 
		 * @return the delay
		 */
		public long getDelay() {
			return this.delay;
		}
	}

	/** The delay before new update. */
	private DelayType					delayBeforeNewUpdate;

	/** The equipment. */
	private EnumMap<Slot, Equipment>	equipment;

	/** The experience. */
	private int							experience;

	/** The fight. */
	private Fight						fight;

	/** The goal. */
	private Goal						goal;

	/** The gold. */
	private int							gold;

	/** The inventory. */
	private final Inventory				inventory;

	/** The level. */
	private final int					level;

	/** The location. */
	private Location					location;

	/** The marked to sell. */
	private final HashSet<Item>			markedToSell;

	/** The time since last update. */
	private long						timeSinceLastUpdate	= Integer.MIN_VALUE;

	/* (non-Javadoc)
	 * @see character.Character#getArmor()
	 */
	@Override
	public int getArmor() {
		int armor = 0;
		for( Entry<Slot, Equipment> e : this.equipment.entrySet() )
			armor += e.getValue().getArmorBonus();
		return armor;
	}

	/**
	 * Instantiates a new hero.
	 * 
	 * @param name
	 *            the name
	 */
	public Hero(final String name) {
		super(name);
		this.gold = 100;
		this.level = 1;
		this.experience = 0;
		this.location = Exploration.getInstance();
		this.delayBeforeNewUpdate = DelayType.EXPLORATION;
		this.inventory = new Inventory();
		this.markedToSell = new HashSet<>();
		this.equipment = new EnumMap<>(Slot.class);
	}

	/**
	 * Adds the experience.
	 * 
	 * @param gain
	 *            the gain
	 */
	public void addExperience(final int gain) {
		this.experience += gain;
		this.experience = Math.max(0, this.experience);
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

	/* (non-Javadoc)
	 * @see character.Character#attack(character.Character)
	 */
	@Override
	public void attack(final Character c) {
		if( this.hasWeapon() )
			super.attack(c, this.equipmentGetWeapon().getDammages(this));
		else
			super.attack(c, GlobalFormula.COMBAT_UNARMED.getFormula().calculate(this));
	}

	/* (non-Javadoc)
	 * @see character.Character#doFight(character.Character)
	 */
	@Override
	public void doFight(final Character opponent) {
		if( this.goal == Goal.DUNGEON ) {
			if( this.shouldUseLifeConsumable() )
				this.fightUseLifeConsumable(false);
			else if( this.shouldUseManaConsumable() )
				this.fightUseManaConsumable(false);
			else
				this.attack(opponent);
		} else if( this.goal == Goal.FLEE ) {
			Logger.log(this, "Je fuis le combat !");
			this.fight.flee();
		}
	}

	/**
	 * Gets the allowed item types.
	 * 
	 * @return the allowed item types
	 */
	public ArrayList<Type> getAllowedItemTypes() {
		ArrayList<Type> types = new ArrayList<>();
		types.add(Type.CONSUMMABLE);
		return types;
	};

	/**
	 * Gets the class name.
	 * 
	 * @return the class name
	 */
	public String getClassName() {
		return this.getClass().getSimpleName();
	}

	/**
	 * The total of all attributes points must be 10.
	 * 
	 * @see character.Character#getBaseAttributes()
	 */
	@Override
	public abstract EnumMap<Attribute, Integer> getBaseAttributes();

	/**
	 * Gets the dammages formula.
	 * 
	 * @return the dammages formula
	 */
	public Formula getDammagesFormula() {
		return ( this.hasWeapon() ? this.equipmentGetWeapon().getFormula() : GlobalFormula.COMBAT_UNARMED.getFormula() );
	}

	/**
	 * Gets the delay before new update.
	 * 
	 * @return the delay before new update
	 */
	public long getDelayBeforeNewUpdate() {
		return this.delayBeforeNewUpdate.getDelay();
	}

	/**
	 * Gets the experience.
	 * 
	 * @return the experience
	 */
	public int getExperience() {
		return this.experience;
	}

	/**
	 * Gets the gold.
	 * 
	 * @return the gold
	 */
	public int getGold() {
		return this.gold;
	}

	/* (non-Javadoc)
	 * @see character.Character#getLevel()
	 */
	@Override
	public int getLevel() {
		return this.level;
	}

	/**
	 * Gets the time since last update.
	 * 
	 * @return the time since last update
	 */
	public long getTimeSinceLastUpdate() {
		return this.timeSinceLastUpdate;
	}

	/**
	 * Inventory add item.
	 * 
	 * @param item
	 *            the i
	 * @param n
	 *            the quantity
	 */
	public void inventoryAddItem(final Item item, final int n) {
		this.inventory.add(item, n);
		if( !this.getAllowedItemTypes().contains(item.getType()) )
			this.markedToSell.add(item);
		else if( item instanceof Equipment )
			this.testEquip((Equipment) item);
	}

	private void equip(Equipment equipment) {
		Slot slot = equipment.getSlot();
		if( this.equipment.containsKey(slot) ) {
			Logger.log(this, "Je déséquipe " + this.equipment.get(slot).getName() + ".");
			this.inventoryAddItem(this.equipment.get(slot), 1);
		}
		Logger.log(this, "Je m'équipe de " + equipment.getName() + ".");
		this.equipment.put(slot, equipment);
		this.inventoryRemoveItem(equipment, 1);
	}

	private void testEquip(Equipment equipement) {
		Slot slot = equipement.getSlot();
		if( !this.equipment.containsKey(slot) )
			this.equip(equipement);
	}

	/**
	 * Inventory remove item.
	 * 
	 * @param item
	 *            the item
	 * @param n
	 *            the quantity
	 */
	public void inventoryRemoveItem(final Item item, final int n) {
		this.inventory.remove(item, n);
	}

	/**
	 * Sets the fight.
	 * 
	 * @param fight
	 *            the new fight
	 */
	public void setFight(final Fight fight) {
		this.fight = fight;
	}

	/**
	 * Shop buy.
	 */
	public void shopBuy() {
		// TODO
	}

	/**
	 * Shop sell.
	 */
	public void shopSell() {
		final Iterator<Item> it = this.markedToSell.iterator();
		while( it.hasNext() ) {
			final Item i = it.next();
			Shop.getInstance().buy(this, i, this.inventory.getCount(i));
			it.remove();
		}
	}

	/**
	 * Update.
	 * 
	 * @param time
	 *            the time
	 */
	public void update(final long time) {
		this.timeSinceLastUpdate = time;
		this.updateGoal();
		if( this.isFighting() ) {
			this.delayBeforeNewUpdate = DelayType.FIGHTING;
			this.fight.doTurn();
		} else if( this.isInDungeon() ) {
			this.delayBeforeNewUpdate = DelayType.DUNGEON;
			this.doDungeon();
		} else if( this.isExploring() ) {
			this.delayBeforeNewUpdate = DelayType.EXPLORATION;
			this.doExploration();
		} else if( this.isShopping() ) {
			this.delayBeforeNewUpdate = DelayType.SHOP_BUYING;
			this.doShop();
		}
	}

	/**
	 * Do dungeon.
	 */
	private void doDungeon() {
		if( this.goal == Goal.DUNGEON ) {
			Logger.log(this, "Je recherche un nouvel ennemi à combattre !");
			final Dungeon dungeon = (Dungeon) this.location;
			this.fight = new Fight(this, dungeon.getMonster());
		} else {
			Logger.log(this, "Je sors du donjon.");
			this.move(Exploration.getInstance());
		}
	}

	/**
	 * Do exploration.
	 */
	private void doExploration() {
		if( this.goal == Goal.DUNGEON ) {
			Logger.log(this, "Je recherche un donjon à explorer.");
			final Exploration exploration = (Exploration) this.location;
			this.move(exploration.getDungeon());
		} else {
			Logger.log(this, "Je me dirige vers le magasin.");
			this.move(Shop.getInstance());
		}
	}

	/**
	 * Do shop.
	 */
	private void doShop() {
		if( this.goal == Goal.SHOP ) {
			if( !this.markedToSell.isEmpty() )
				this.shopSell();

			final Shop shop = (Shop) this.location;
			Logger.log("Bonjour " + this.getName() + " !");
			Logger.log(this, "Je dispose de " + this.getGold() + " po.");

			final Set<Item> shopItems = shop.getItems();
			if( shopItems.isEmpty() )
				Logger.log("Nous sommes désolé mais le magasin est vide.");
			else {
				Logger.log("Le magasin dispose des objets suivants :");
				for( final Item i : shopItems ) {
					Logger.log("\t" + i.getName() + " au nombre de " + shop.getCount(i) + ".");
					if( this.shopCanAfford(i, 1) ) {
						shop.sell(this, i, 1);
						Logger.log(this, "J'en achète 1.");
					} else
						Logger.log(this, "Je n'ai pas assez d'argent.");
				}
			}
		} else {
			Logger.log(this, "Je sors du magasin.");
			this.move(Exploration.getInstance());
		}
	}

	/**
	 * Equipment get weapon.
	 * 
	 * @return the weapon
	 */
	private Weapon equipmentGetWeapon() {
		return (Weapon) this.equipment.get(Slot.MAINHAND);
	}

	/**
	 * Fight use life consumable.
	 * 
	 * @param urge
	 *            the urge
	 */
	private void fightUseLifeConsumable(final boolean urge) {
		final TreeSet<Consumable> consumables = this.inventoryGetLifeConsumables();

		Consumable toUse = null;
		float toUseCoef = 0;
		final int toGain = this.getMaxLife() - this.getLife();

		// TODO use the urge boolean.
		for( final Consumable c : consumables ) {
			final float coef = Math.min((float) c.getLifeGain() / toGain, 1.0f) - Math.max( ( (float) c.getLifeGain() - toGain ) / c.getLifeGain(), 0.0f);
			if( coef > toUseCoef ) {
				toUse = c;
				toUseCoef = coef;
			} else
				break;
		}

		toUse.consume(this);
	}

	/**
	 * Fight use mana consumable.
	 * 
	 * @param urge
	 *            the urge
	 */
	private void fightUseManaConsumable(final boolean urge) {
		final TreeSet<Consumable> consumables = this.inventoryGetManaConsumables();

		Consumable toUse = null;
		float toUseCoef = 0;
		final int toGain = this.getMaxMana() - this.getMana();

		// TODO use the urge boolean.
		for( final Consumable c : consumables ) {
			final float coef = Math.min((float) c.getManaGain() / toGain, 1.0f) - Math.max( ( (float) c.getManaGain() - toGain ) / c.getManaGain(), 0.0f);
			if( coef > toUseCoef ) {
				toUse = c;
				toUseCoef = coef;
			} else
				break;
		}

		toUse.consume(this);
	}

	/**
	 * Checks for weapon.
	 * 
	 * @return true, if successful
	 */
	private boolean hasWeapon() {
		return ( this.equipmentGetWeapon() != null );
	}

	/**
	 * Inventory get consumables.
	 * 
	 * @return the hash set
	 */
	private HashSet<Consumable> inventoryGetConsumables() {
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
	private TreeSet<Consumable> inventoryGetLifeConsumables() {
		final TreeSet<Consumable> consos = new TreeSet<>(Consumable.LIGE_GAIN_ORDER);
		for( final Consumable c : this.inventoryGetConsumables() )
			if( c.getLifeGain() > 0 )
				consos.add(c);
		return consos;
	}

	/**
	 * Inventory get mana consumables.
	 * 
	 * @return the tree set
	 */
	private TreeSet<Consumable> inventoryGetManaConsumables() {
		final TreeSet<Consumable> consos = new TreeSet<>(Consumable.MANA_GAIN_ORDER);
		for( final Consumable c : this.inventoryGetConsumables() )
			if( c.getManaGain() > 0 )
				consos.add(c);
		return consos;
	}

	/**
	 * Checks if is exploring.
	 * 
	 * @return true, if is exploring
	 */
	private boolean isExploring() {
		return this.location instanceof Exploration;
	}

	/**
	 * Checks if is fighting.
	 * 
	 * @return true, if is fighting
	 */
	private boolean isFighting() {
		return ( this.fight != null );
	}

	/**
	 * Checks if is in dungeon.
	 * 
	 * @return true, if is in dungeon
	 */
	private boolean isInDungeon() {
		return this.location instanceof Dungeon;
	}

	/**
	 * Checks if is shopping.
	 * 
	 * @return true, if is shopping
	 */
	private boolean isShopping() {
		return this.location instanceof Shop;
	}

	/**
	 * Move.
	 * 
	 * @param location
	 *            the location
	 */
	private void move(final Location location) {
		// TODO Faire des vérifications afin que l'on ne puisse pas sauter d'étapes.
		this.location = location;
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
	 * Should flee.
	 * 
	 * @return true, if successful
	 */
	private boolean shouldFlee() {
		if( !this.isFighting() )
			return false;
		final Monster m = this.fight.getMonster();
		if( ( (float) this.getLife() / m.getLife() ) < 0.2 )
			return true;
		return false;
	}

	/**
	 * Should use life consumable.
	 * 
	 * @return true, if successful
	 */
	private boolean shouldUseLifeConsumable() {
		return ( this.inventoryGetLifeConsumables().size() > 0 ) && ( ( (float) this.getLife() / this.getMaxLife() ) < 0.5 );
	}

	/**
	 * Should use mana consumable.
	 * 
	 * @return true, if successful
	 */
	private boolean shouldUseManaConsumable() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Update goal.
	 */
	private void updateGoal() {
		if( this.isFighting() && this.shouldFlee() )
			this.goal = Goal.FLEE;
		else if( !this.isFighting() && ( this.inventoryGetLifeConsumables().size() == 0 ) )
			this.goal = Goal.SHOP;
		else
			this.goal = Goal.DUNGEON;
	}
}