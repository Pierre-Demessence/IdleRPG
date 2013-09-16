package fr.idlerpg.item;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map.Entry;

import fr.idlerpg.database.characters.Attribute;
import fr.idlerpg.database.factories.ModifierFactory;
import fr.idlerpg.database.factories.ModifierFactory.ModifierSlot;
import fr.idlerpg.database.items.EquipmentSlot;
import fr.idlerpg.database.items.ModifierQuality;

public abstract class Equipment extends Item {

	protected ArrayList<EquipmentModifier>	modifiers;

	public Equipment() {
		this.modifiers = new ArrayList<>();
	}

	public int getMaxModifiers() {
		return 1;
	}

	protected abstract ModifierSlot getModifierSlot();

	public void addRandomModifiers(int n) {
		for( int i = 0 ; i < n ; i++ ) {
			ModifierQuality mq = ModifierQuality.getRandomModifierQuality();
			if( mq != null )
				this.addModifier(ModifierFactory.getModifier(this.getModifierSlot(), mq));
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
		final EnumMap<Attribute, Integer> attributesBonus = this.getBaseAttributesBonus();
		for( EquipmentModifier em : this.modifiers )
			for( Entry<Attribute, Integer> e : em.getAttributesBonus().entrySet() )
				attributesBonus.put(e.getKey(), ( attributesBonus.get(e.getKey()) != null ? attributesBonus.get(e.getKey()) : 0 ) + e.getValue());
		return attributesBonus;
	}

	protected EnumMap<Attribute, Integer> getBaseAttributesBonus() {
		final EnumMap<Attribute, Integer> attributesBonus = new EnumMap<>(Attribute.class);
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