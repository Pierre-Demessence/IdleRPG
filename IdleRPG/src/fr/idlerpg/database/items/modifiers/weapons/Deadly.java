package fr.idlerpg.database.items.modifiers.weapons;

import fr.idlerpg.database.items.ModifierQuality;
import fr.idlerpg.item.WeaponModifier;

/**
 * The Class Deadly.
 */
public class Deadly extends WeaponModifier {

	/* (non-Javadoc)
	 * @see fr.idlerpg.item.WeaponModifier#getDamagesBonus()
	 */
	@Override
	public int getDamagesBonus() {
		return 3;
	}

	/* (non-Javadoc)
	 * @see fr.idlerpg.item.EquipmentModifier#getModifierValue()
	 */
	@Override
	public ModifierQuality getModifierValue() {
		return ModifierQuality.EPIC;
	}

	/* (non-Javadoc)
	 * @see fr.idlerpg.item.EquipmentModifier#getName()
	 */
	@Override
	protected String getName() {
		return "Deadly";
	}

	/* (non-Javadoc)
	 * @see fr.idlerpg.item.EquipmentModifier#getNamePosition()
	 */
	@Override
	protected NamePosition getNamePosition() {
		return NamePosition.LEFT;
	}

}
