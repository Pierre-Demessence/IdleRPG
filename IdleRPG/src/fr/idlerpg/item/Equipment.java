package fr.idlerpg.item;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.Map.Entry;

import fr.idlerpg.database.characters.Attribute;
import fr.idlerpg.database.factories.ModifierFactory;
import fr.idlerpg.database.factories.ModifierFactory.ModifierSlot;
import fr.idlerpg.database.items.EquipmentSlot;
import fr.idlerpg.database.items.ModifierQuality;

/**
 * The Class Equipment.
 */
public abstract class Equipment extends Item {

	/** The Constant VALUE_ORDER. */
	public static final Comparator<Equipment>	CALCULATED_VALUE_ORDER	= new Comparator<Equipment>() {
																			@Override
																			public int compare(final Equipment o1, final Equipment o2) {
																				return o1.getValue() - o2.getValue();
																			}
																		};

	/** The modifiers. */
	protected ArrayList<EquipmentModifier>		modifiers;

	/**
	 * Instantiates a new equipment.
	 */
	public Equipment() {
		this.modifiers = new ArrayList<>();
	}

	public abstract Equipment clone(boolean modifiers);

	/**
	 * Adds the random modifiers.
	 * 
	 * @param n
	 *            the n
	 */
	public void addRandomModifiers(final int n) {
		for( int i = 0 ; i < n ; i++ ) {
			final ModifierQuality mq = ModifierQuality.getRandomModifierQuality();
			if( mq != null )
				this.addModifier(ModifierFactory.getModifier(this.getModifierSlot(), mq));
		}
	}

	/**
	 * Gets the attributes bonus.
	 * 
	 * @return the attributes bonus
	 */
	public EnumMap<Attribute, Integer> getAttributesBonus() {
		final EnumMap<Attribute, Integer> attributesBonus = this.getBaseAttributesBonus();
		for( final EquipmentModifier em : this.modifiers )
			for( final Entry<Attribute, Integer> e : em.getAttributesBonus().entrySet() )
				attributesBonus.put(e.getKey(), ( attributesBonus.get(e.getKey()) != null ? attributesBonus.get(e.getKey()) : 0 ) + e.getValue());
		return attributesBonus;
	}

	/**
	 * The required level to equip this object.
	 * 
	 * @return the level
	 */
	public int getLevel() {
		return (int) Math.ceil(this.getValue() / 200.0f);
	}

	/**
	 * Gets the max modifiers.
	 * 
	 * @return the max modifiers
	 */
	public int getMaxModifiers() {
		return 1;
	}

	/* (non-Javadoc)
	 * @see fr.idlerpg.item.Item#getName()
	 */
	@Override
	public String getName() {
		String finalName = this.getBaseName();
		for( final EquipmentModifier em : this.modifiers )
			finalName = em.modifyName(finalName);
		return finalName;
	}

	/**
	 * Gets the slot.
	 * 
	 * @return the slot
	 */
	public abstract EquipmentSlot getSlot();

	public int getCalculatedValue() {
		int value = 0;
		for( final Entry<Attribute, Integer> e : this.getAttributesBonus().entrySet() )
			value += e.getValue() * 200;
		return value;
	}

	@Override
	public int getValue() {
		int value = this.getCalculatedValue();
		for( final EquipmentModifier em : this.modifiers )
			value += em.getValueBonus();
		return Math.max(2, value);
	}

	/**
	 * Adds the modifier.
	 * 
	 * @param em
	 *            the em
	 */
	protected void addModifier(final EquipmentModifier em) {
		if( em != null ) {
			this.modifiers.add(em);
			Collections.sort(this.modifiers);
		}
	}

	/**
	 * Gets the base attributes bonus.
	 * 
	 * @return the base attributes bonus
	 */
	protected EnumMap<Attribute, Integer> getBaseAttributesBonus() {
		final EnumMap<Attribute, Integer> attributesBonus = new EnumMap<>(Attribute.class);
		return attributesBonus;
	}

	/**
	 * Gets the modifier slot.
	 * 
	 * @return the modifier slot
	 */
	protected abstract ModifierSlot getModifierSlot();

}