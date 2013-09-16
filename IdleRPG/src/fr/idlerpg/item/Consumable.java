package fr.idlerpg.item;

import java.util.ArrayList;
import java.util.Comparator;

import org.apache.commons.lang3.StringUtils;

import fr.idlerpg.character.Hero;
import fr.idlerpg.database.factories.ItemFactory;
import fr.idlerpg.database.items.ItemType;
import fr.idlerpg.util.Logger;

/**
 * The Class Consumable.
 */
public abstract class Consumable extends Item {

	/** The Constant LIGE_GAIN_ORDER. */
	public static final Comparator<Consumable>	LIFE_GAIN_ORDER				= new Comparator<Consumable>() {
																				@Override
																				public int compare(final Consumable o1, final Consumable o2) {
																					return o1.getLifeGain() - o2.getLifeGain();
																				}
																			};

	/** The Constant LIFE_VALUE_RATIO_ORDER. */
	public static final Comparator<Consumable>	LIFE_VALUE_RATIO_ORDER		= new Comparator<Consumable>() {
																				@Override
																				public int compare(final Consumable o1, final Consumable o2) {
																					return ( o1.getLifeGain() / o1.getValue() ) - ( o2.getLifeGain() / o2.getValue() );
																				}
																			};

	/** The Constant MANA_GAIN_ORDER. */
	public static final Comparator<Consumable>	MANA_GAIN_ORDER				= new Comparator<Consumable>() {
																				@Override
																				public int compare(final Consumable o1, final Consumable o2) {
																					return o1.getManaGain() - o2.getManaGain();
																				}
																			};

	/** The Constant MANA_VALUE_RATIO_ORDER. */
	public static final Comparator<Consumable>	MANA_VALUE_RATIO_ORDER		= new Comparator<Consumable>() {
																				@Override
																				public int compare(final Consumable o1, final Consumable o2) {
																					return ( o1.getManaGain() / o1.getValue() ) - ( o2.getManaGain() / o2.getValue() );
																				}
																			};

	/** The Constant TOTAL_GAIN_ORDER. */
	public static final Comparator<Consumable>	TOTAL_GAIN_ORDER			= new Comparator<Consumable>() {
																				@Override
																				public int compare(final Consumable o1, final Consumable o2) {
																					return o1.getTotalGain() - o2.getTotalGain();
																				}
																			};

	/** The Constant TOTALGAIN_VALUE_RATIO_ORDER. */
	public static final Comparator<Consumable>	TOTALGAIN_VALUE_RATIO_ORDER	= new Comparator<Consumable>() {
																				@Override
																				public int compare(final Consumable o1, final Consumable o2) {
																					return ( o1.getTotalGain() / o1.getValue() ) - ( o2.getTotalGain() / o2.getValue() );
																				}
																			};

	@Override
	public Object clone() {
		return ItemFactory.getConsumable(this.getClass().getSimpleName());
	}

	/**
	 * Consume.
	 * 
	 * @param c
	 *            the c
	 */
	public void consume(final Hero c) {
		final ArrayList<String> gains = new ArrayList<>();
		if( this.getLifeGain() > 0 )
			gains.add(this.getLifeGain() + " PV");
		if( this.getManaGain() > 0 )
			gains.add(this.getManaGain() + " PM");
		Logger.log(c, "J'utilise " + this.getName() + " et gagne " + StringUtils.join(gains, " et ") + ".");
		c.addLife(this.getLifeGain());
		c.addMana(this.getManaGain());
		c.removeItem(this, 1);
	}

	/**
	 * Gets the life gain.
	 * 
	 * @return the life gain
	 */
	public abstract int getLifeGain();

	/**
	 * Gets the mana gain.
	 * 
	 * @return the mana gain
	 */
	public abstract int getManaGain();

	/**
	 * Gets the total gain.
	 * 
	 * @return the total gain
	 */
	public int getTotalGain() {
		return this.getLifeGain() + this.getManaGain();
	}

	/* (non-Javadoc)
	 * @see item.Item#getType()
	 */
	@Override
	public ItemType getType() {
		return ItemType.CONSUMMABLE;
	}

	/* (non-Javadoc)
	 * @see item.Item#getValue()
	 */
	@Override
	public int getValue() {
		return (int) ( this.getLifeGain() + ( this.getManaGain() * 1.5 ) );
	}

}
