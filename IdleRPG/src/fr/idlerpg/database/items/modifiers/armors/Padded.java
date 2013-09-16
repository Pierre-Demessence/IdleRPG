package fr.idlerpg.database.items.modifiers.armors;

import fr.idlerpg.database.items.ModifierQuality;
import fr.idlerpg.item.ArmorModifier;

/**
 * The Class Padded.
 */
public class Padded extends ArmorModifier {

	/* (non-Javadoc)
	 * @see fr.idlerpg.item.ArmorModifier#getArmorBonus()
	 */
	@Override
	public int getArmorBonus() {
		return 1;
	}

	/* (non-Javadoc)
	 * @see fr.idlerpg.item.EquipmentModifier#getModifierValue()
	 */
	@Override
	public ModifierQuality getModifierValue() {
		return ModifierQuality.UNCOMMON;
	}

	/* (non-Javadoc)
	 * @see fr.idlerpg.item.EquipmentModifier#getName()
	 */
	@Override
	protected String getName() {
		return "Padded";
	}

	/* (non-Javadoc)
	 * @see fr.idlerpg.item.EquipmentModifier#getNamePosition()
	 */
	@Override
	protected NamePosition getNamePosition() {
		return NamePosition.LEFT;
	}

}
