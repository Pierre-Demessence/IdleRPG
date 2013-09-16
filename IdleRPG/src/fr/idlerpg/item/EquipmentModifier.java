package fr.idlerpg.item;

import java.util.EnumMap;

import fr.idlerpg.database.characters.Attribute;
import fr.idlerpg.database.items.ModifierQuality;

/**
 * The Class EquipmentModifier.
 */
public abstract class EquipmentModifier implements Comparable<EquipmentModifier> {

	/**
	 * The Enum NamePosition.
	 */
	public enum NamePosition {

		/** The left. */
		LEFT,

		/** The right. */
		RIGHT;
	}

	/** The Constant MODIFIER_CHANCE. */
	public static final float	MODIFIER_CHANCE	= 0.30f;

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(final EquipmentModifier o) {
		return this.getName().compareTo(o.getName());
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

	// TODO En fonction de la valeur du modifier ? (Donc ajouter un getBaseValue() );
	/**
	 * Gets the modifier value.
	 * 
	 * @return the modifier value
	 */
	public abstract ModifierQuality getModifierValue();

	/**
	 * Gets the value bonus.
	 * 
	 * @return the value bonus
	 */
	public int getValueBonus() {
		return 0;
	}

	/**
	 * Modify name.
	 * 
	 * @param name
	 *            the name
	 * @return the string
	 */
	public String modifyName(final String name) {
		switch( this.getNamePosition() ) {
		case RIGHT:
			return name + " " + this.getName();
		case LEFT:
		default:
			return this.getName() + " " + name;
		}

	}

	/**
	 * Gets the name.
	 * 
	 * @return the name
	 */
	protected abstract String getName();

	/**
	 * Gets the name position.
	 * 
	 * @return the name position
	 */
	protected abstract NamePosition getNamePosition();

}