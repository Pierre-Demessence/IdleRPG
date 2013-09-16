package fr.idlerpg.database.items.modifiers.weapons;

import fr.idlerpg.database.items.ModifierQuality;
import fr.idlerpg.item.WeaponModifier;

/**
 * The Class Dangerous.
 */
public class Dangerous extends WeaponModifier {

	/* (non-Javadoc)
	 * @see fr.idlerpg.item.WeaponModifier#getDamagesBonus()
	 */
	@Override
	public int getDamagesBonus() {
		return 2;
	}

	/* (non-Javadoc)
	 * @see fr.idlerpg.item.EquipmentModifier#getModifierValue()
	 */
	@Override
	public ModifierQuality getModifierValue() {
		return ModifierQuality.MAGICAL;
	}

	/* (non-Javadoc)
	 * @see fr.idlerpg.item.EquipmentModifier#getName()
	 */
	@Override
	protected String getName() {
		return "Dangerous";
	}

	/* (non-Javadoc)
	 * @see fr.idlerpg.item.EquipmentModifier#getNamePosition()
	 */
	@Override
	protected NamePosition getNamePosition() {
		return NamePosition.LEFT;
	}

}
