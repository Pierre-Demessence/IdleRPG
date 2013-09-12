/*
 * Author : Pierre
 * Last Update : 11 sept. 2013 - 23:55:13
 */
package character;

import item.Consumable;
import item.Item;
import item.Weapon;

import java.util.ArrayList;
import java.util.EnumMap;
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

	/** The delay before new update. */
	private DelayType		delayBeforeNewUpdate;

	/** The experience. */
	private int				experience;

	/** The fight. */
	private Fight			fight;

	/** The goal. */
	private Goal			goal;

	/** The inventory. */
	private final Inventory	inventory;

	/** The level. */
	private final int		level;

	/** The location. */
	private Location		location;

	/** The time since last update. */
	private long			timeSinceLastUpdate	= Integer.MIN_VALUE;

	private int				fightBeforeGoToShop;

	public void setFightBeforeGoToShop() {
		this.fightBeforeGoToShop = 1;
	}

	public void decreaseFightBeforeGoToShop() {
		if( this.fightBeforeGoToShop > 0 )
			this.fightBeforeGoToShop--;
	}

	/* (non-Javadoc)
	 * @see character.Character#getArmor()
	 */
	@Override
	public int getArmor() {
		return this.inventory.getArmor();
	}

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

	/* (non-Javadoc)
	 * @see character.Character#attack(character.Character)
	 */
	@Override
	public void attack(final Character c) {
		super.attack(c, this.getDammagesFormula().calculate(this));
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
		if( this.inventory.hasEquipment(Slot.MAINHAND) ) {
			Weapon w = (Weapon) this.inventory.getEquipment(Slot.MAINHAND);
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
	 * Gets the time since last update.
	 * 
	 * @return the time since last update
	 */
	public long getTimeSinceLastUpdate() {
		return this.timeSinceLastUpdate;
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

	private int	KOCounter;

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

	public void addGold(int gain) {
		this.inventory.addGold(gain);
	}

	public void addItem(Item item, int n) {
		this.inventory.addItem(item, n);
	}

	/**
	 * Fight use life consumable.
	 * 
	 * @param urge
	 *            the urge
	 */
	private void fightUseLifeConsumable(final boolean urge) {
		final TreeSet<Consumable> consumables = this.inventory.getLifeConsumables();

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
	 * The percent of life the hero should have in total life gain over all his consummables.
	 * 
	 * @return the min life gain to keep
	 */
	public float getMinLifeGainToKeep() {
		return 2;
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
	 * Fight use mana consumable.
	 * 
	 * @param urge
	 *            the urge
	 */
	private void fightUseManaConsumable(final boolean urge) {
		final TreeSet<Consumable> consumables = this.inventory.getManaConsumables();

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
		// TODO Faire des vérifications afin que l'on ne puisse pas sauter d'étapes. Sauf en cas de mort !
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
		if( this.inventory.getLifeConsumables().size() == 0 )
			return false;

		return ( (float) this.getLife() / this.getMaxLife() ) < 0.5;
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
		else if( !this.isFighting() && this.fightBeforeGoToShop == 0 && ( this.inventory.isFull() || this.inventory.getTotalLifeGain() < this.getMaxLife() * this.getMinLifeGainToKeep() ) )
			this.goal = Goal.SHOP;
		else if( this.isShopping() && ( this.inventory.hasSomethingToSell() || !this.inventory.isShopChecked() ) )
			this.goal = Goal.SHOP;
		else if( this.isShopping() && this.inventory.isShopChecked() )
			this.goal = Goal.DUNGEON;
		else
			this.goal = Goal.DUNGEON;
	}

	public void removeItem(Item item, int n) {
		this.inventory.removeItem(item, n);
	}
}