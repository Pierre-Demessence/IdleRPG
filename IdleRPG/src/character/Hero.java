/*
 * Author : Pierre
 * Last Update : 12 sept. 2013 - 04:07:21
 */
package character;

import item.Consumable;
import item.Equipment;
import item.Item;
import item.Weapon;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Map.Entry;
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
// TODO: Impl�menter un system de leveling.
/**
 * The Class Hero.
 */
public abstract class Hero extends Character {

	/** The delay before new update. */
	private DelayType		delayBeforeNewUpdate;

	/** The experience. */
	private int				experience;

	/** The fight. */
	private Fight			fight;

	/** The fight before go to shop. */
	private int				fightBeforeGoToShop;

	/** The goal. */
	private Goal			goal;

	/** The inventory. */
	private final Inventory	inventory;

	/** The KO counter. */
	private int				KOCounter;

	/** The level. */
	private final int		level;

	/** The location. */
	private Location		location;

	/** The time since last update. */
	private long			timeSinceLastUpdate	= Integer.MIN_VALUE;

	/**
	 * Instantiates a new hero.
	 * 
	 * @param name
	 *            the name
	 */
	public Hero(final String name) {
		super(name);
		this.level = 1;
		this.experience = 0;
		this.location = Exploration.getInstance();
		this.delayBeforeNewUpdate = DelayType.EXPLORATION;
		this.inventory = new Inventory(this);
		this.init();
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
		this.inventory.addGold(gain);
	}

	/**
	 * Adds the item.
	 * 
	 * @param item
	 *            the item
	 * @param n
	 *            the n
	 */
	public void addItem(final Item item, final int n) {
		this.inventory.addItem(item, n);
	}

	/* (non-Javadoc)
	 * @see character.Character#attack(character.Character)
	 */
	@Override
	public void attack(final Character c) {
		super.attack(c, this.getDammagesFormula().calculate(this));
	}

	/**
	 * Decrease fight before go to shop.
	 */
	public void decreaseFightBeforeGoToShop() {
		if( this.fightBeforeGoToShop > 0 )
			this.fightBeforeGoToShop--;
	}

	/* (non-Javadoc)
	 * @see character.Character#doFight(character.Character)
	 */
	@Override
	public void doFight(final Character opponent) {
		if( this.goal == Goal.DUNGEON ) {
			if( this.shouldUseLifeConsumable() )
				this.fightUseLifeConsumable();
			else if( this.shouldUseManaConsumable() )
				this.fightUseManaConsumable();
			else
				this.attack(opponent);
		} else if( this.goal == Goal.FLEE ) {
			Logger.log(this, "Je fuis le combat !");
			this.fight.flee();
		}
	};

	/**
	 * Gets the allowed item types.
	 * 
	 * @return the allowed item types
	 */
	public ArrayList<Type> getAllowedItemTypes() {
		final ArrayList<Type> types = new ArrayList<>();
		types.add(Type.CONSUMMABLE);
		return types;
	}

	/* (non-Javadoc)
	 * @see character.Character#getArmor()
	 */
	@Override
	public int getArmor() {
		return this.inventory.getArmor();
	}

	/* (non-Javadoc)
	 * @see character.Character#getAttribute(character.Attribute)
	 */
	@Override
	public int getAttribute(final Attribute attribute) {
		int value = super.getAttribute(attribute);
		for( final Entry<Slot, Equipment> e : this.inventory.getEquipment().entrySet() )
			if( e.getValue().getAttributesBonus().containsKey(attribute) )
				value += e.getValue().getAttributesBonus().get(attribute);
		return value;
	}

	/**
	 * The total of all attributes points must be 10.
	 * 
	 * @return the base attributes
	 * @see character.Character#getBaseAttributes()
	 */
	@Override
	public abstract EnumMap<Attribute, Integer> getBaseAttributes();

	/**
	 * Gets the class name.
	 * 
	 * @return the class name
	 */
	public String getClassName() {
		return this.getClass().getSimpleName();
	}

	/**
	 * Gets the dammages formula.
	 * 
	 * @return the dammages formula
	 */
	@Override
	public Formula getDammagesFormula() {
		if( this.inventory.hasEquipment(Slot.MAINHAND) ) {
			final Weapon w = (Weapon) this.inventory.getEquipment(Slot.MAINHAND);
			return w.getFormula();
		} else
			return GlobalFormula.COMBAT_UNARMED.getFormula();
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
		return this.inventory.getGold();
	}

	/* (non-Javadoc)
	 * @see character.Character#getLevel()
	 */
	@Override
	public int getLevel() {
		return this.level;
	}

	/**
	 * The percent of life the hero should have in total life gain over all his consummables.
	 * 
	 * @return the min life gain to keep
	 */
	public float getMaxLifeGainToKeep() {
		return 3;
	}

	/**
	 * The percent of life the hero should have in total life gain over all his consummables.
	 * 
	 * @return the min life gain to keep
	 */
	public float getMinLifeGainToKeep() {
		return 2;
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
	 * Removes the item.
	 * 
	 * @param item
	 *            the item
	 * @param n
	 *            the n
	 */
	public void removeItem(final Item item, final int n) {
		this.inventory.removeItem(item, n);
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
	 * Sets the fight before go to shop.
	 */
	public void setFightBeforeGoToShop() {
		this.fightBeforeGoToShop = 1;
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
		if( this.isKO() ) {
			this.move(Exploration.getInstance());
			this.KOCounter++;
			this.addLife(this.getMaxLife());
			this.delayBeforeNewUpdate = DelayType.RESURRECTION;
		} else if( this.isFighting() ) {
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
		if( this.shouldUseLifeConsumable() )
			this.fightUseLifeConsumable();
		if( this.goal == Goal.DUNGEON ) {
			Logger.log(this, "Je recherche un nouvel ennemi � combattre !");
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
			Logger.log(this, "Je recherche un donjon � explorer.");
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
		if( this.goal == Goal.SHOP )
			if( this.inventory.hasSomethingToSell() )
				this.inventory.sell();
			else
				this.inventory.buy();
		else {
			Logger.log(this, "Je sors du magasin.");
			this.move(Exploration.getInstance());
			this.inventory.setShopChecked(false, false);
		}
	}

	/**
	 * Fight use life consumable.
	 * 
	 */
	private void fightUseLifeConsumable() {
		final TreeSet<Consumable> consumables = this.inventory.getLifeConsumables();

		Consumable toUse = null;
		float toUseCoef = 0;
		final int toGain = this.getMaxLife() - this.getLife();

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
	 */
	private void fightUseManaConsumable() {
		final TreeSet<Consumable> consumables = this.inventory.getManaConsumables();

		Consumable toUse = null;
		float toUseCoef = 0;
		final int toGain = this.getMaxMana() - this.getMana();

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
		// TODO Faire des v�rifications afin que l'on ne puisse pas sauter d'�tapes. Sauf en cas de r�surrection.
		this.location = location;
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
		// On a toute notre vie, on ne prend pas de conso.
		if( this.getLife() == this.getMaxLife() )
			return false;
		// On a pas de consommable, on ne prend pas de conso.
		if( this.inventory.getLifeConsumables().size() == 0 )
			return false;

		if( this.isFighting() ) {
			final Monster monster = this.fight.getMonster();
			// Si on est pas s�r de tuer l'ennemi mais que s'il survit il est s�r de nous tuer, on prend une potion.
			if( ( this.chanceToKill(monster) < 0.9f ) && ( monster.chanceToKill(this) == 1 ) )
				return true;
			// Si on est plut�t s�r de tuer l'ennemi, on ne prend pas de potion.
			else if( this.chanceToKill(monster) >= 0.8f )
				return false;
		}
		// Si on a on moins qu'un certain % de notre vie, on prend une potion.
		if( ( (float) this.getLife() / this.getMaxLife() ) < 0.5f )
			return true;

		return false;
	}

	/**
	 * Should use mana consumable.
	 * 
	 * @return true, if successful
	 */
	private boolean shouldUseManaConsumable() {
		// TODO shouldUseManaConsumable() - Des conditions plus simple que pour la vie.
		return false;
	}

	/**
	 * Update goal.
	 */
	private void updateGoal() {
		if( this.isFighting() && this.shouldFlee() )
			this.goal = Goal.FLEE;
		else if( !this.isFighting() && ( this.fightBeforeGoToShop == 0 ) && ( this.inventory.isFull() || ( this.inventory.getTotalLifeGain() < ( this.getMaxLife() * this.getMinLifeGainToKeep() ) ) ) )
			this.goal = Goal.SHOP;
		else if( this.isShopping() && ( this.inventory.hasSomethingToSell() || !this.inventory.isShopChecked() ) )
			this.goal = Goal.SHOP;
		else if( this.isShopping() && this.inventory.isShopChecked() )
			this.goal = Goal.DUNGEON;
		else
			this.goal = Goal.DUNGEON;
	}
}