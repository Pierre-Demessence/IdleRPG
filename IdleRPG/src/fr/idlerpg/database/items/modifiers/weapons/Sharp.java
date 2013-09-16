package fr.idlerpg.database.items.modifiers.weapons;

import fr.idlerpg.database.items.ModifierQuality;
import fr.idlerpg.item.WeaponModifier;

/**
 * The Class Sharp.
 */
public class Sharp extends WeaponModifier {

	/* (non-Javadoc)
	 * @see fr.idlerpg.item.WeaponModifier#getDamagesBonus()
	 */
	@Override
	public int getDamagesBonus() {
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
		return "Sharp";
	}

	/* (non-Javadoc)
	 * @see fr.idlerpg.item.EquipmentModifier#getNamePosition()
	 */
	@Override
	protected NamePosition getNamePosition() {
		return NamePosition.LEFT;
	}

}
