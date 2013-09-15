package item;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map.Entry;

import database.characters.Attribute;
import database.factories.ModifierFactory;
import database.factories.ModifierFactory.ModifierSlot;
import database.items.EquipmentSlot;
import database.items.ModifierQuality;

public abstract class Equipment extends Item {

	protected ArrayList<EquipmentModifier>	modifiers;

	public Equipment(boolean modifiers, ModifierSlot slot) {
		this.modifiers = new ArrayList<>();
		if( modifiers )
			this.addRandomModifiers(this.getMaxModifiers(), slot);
	}

	private int getMaxModifiers() {
		return 1;
	}

	protected void addRandomModifiers(int n, ModifierSlot slot) {

		for( int i = 0 ; i < n ; i++ ) {
			ModifierQuality mq = ModifierQuality.getRandomModifierQuality();
			if( mq != null )
				this.addModifier(ModifierFactory.getModifier(slot, mq));
		}
	}

	protected void addModifier(EquipmentModifier em) {
		if( em != null ) {
			this.modifiers.add(em);
			Collections.sort(this.modifiers);
		}
	}

	/**
	 * Gets the attributes bonus.
	 * 
	 * @return the attributes bonus
	 */
	public EnumMap<Attribute, Integer> getAttributesBonus() {
		final EnumMap<Attribute, Integer> attributesBonus = new EnumMap<>(Attribute.class);
		for( EquipmentModifier em : this.modifiers )
			for( Entry<Attribute, Integer> e : em.getAttributesBonus().entrySet() )
				attributesBonus.put(e.getKey(), attributesBonus.get(e.getKey()) + e.getValue());
		return attributesBonus;
	}

	/**
	 * The required level to equip this object.
	 * 
	 * @return the level
	 */
	public int getLevel() {
		return (int) Math.ceil(this.getValue() / 750.0f);
	}

	@Override
	public String getName() {
		String finalName = this.getBaseName();
		for( EquipmentModifier em : this.modifiers )
			finalName = em.modifyName(finalName);
		return finalName;
	}

	/**
	 * Gets the slot.
	 * 
	 * @return the slot
	 */
	public abstract EquipmentSlot getSlot();

	@Override
	public int getValue() {
		int value = 0;
		for( EquipmentModifier em : this.modifiers )
			value += em.getValueBonus();
		for( final Entry<Attribute, Integer> e : this.getAttributesBonus().entrySet() )
			value += e.getValue() * 50;
		return value;
	}

}