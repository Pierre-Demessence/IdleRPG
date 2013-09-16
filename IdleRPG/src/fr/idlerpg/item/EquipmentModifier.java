package fr.idlerpg.item;

import java.util.EnumMap;

import fr.idlerpg.database.characters.Attribute;
import fr.idlerpg.database.items.ModifierQuality;

public abstract class EquipmentModifier implements Comparable<EquipmentModifier> {

	public static final float	MODIFIER_CHANCE	= 0.30f;

	public enum NamePosition {
		LEFT,
		RIGHT;
	}

	@Override
	public int compareTo(EquipmentModifier o) {
		return this.getName().compareTo(o.getName());
	}

	// TODO En fonction de la valeur du modifier ? (Donc ajouter un getBaseValue() );
	public abstract ModifierQuality getModifierValue();

	protected abstract String getName();

	protected abstract NamePosition getNamePosition();

	public String modifyName(String name) {
		switch( this.getNamePosition() ) {
		case RIGHT:
			return name + " " + this.getName();
		case LEFT:
		default:
			return this.getName() + " " + name;
		}

	}

	public int getValueBonus() {
		return 0;
	}

	/**
	 * Gets the attributes bonus.
	 * 
	 * @return the attributes bonus
	 */
	public EnumMap<Attribute, Integer> getAttributesBonus() {
		final EnumMap<Attribute, Integer> attributesBonus = new EnumMap<>(Attribute.class);
		return attributesBonus;
	}

}