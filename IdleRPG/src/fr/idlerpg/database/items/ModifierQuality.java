package fr.idlerpg.database.items;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * The Enum ModifierQuality.
 */
public enum ModifierQuality {

	// A ajouter sur certains items pour les transformer totalement.
	/** The artefact. */
	ARTEFACT(0),
	// Epic bonus !
	/** The epic. */
	EPIC(3),
	// Bonus aux attributs.
	/** The magical. */
	MAGICAL(2),
	// Bonus tout sauf attribut.
	/** The uncommon. */
	UNCOMMON(1);

	/**
	 * Gets the random modifier quality.
	 * 
	 * @return the random modifier quality
	 */
	public static ModifierQuality getRandomModifierQuality() {
		ModifierQuality res = null;
		final float randomFloat = new Random().nextFloat();
		float curFactor = 0;
		// On test chaque valeur de modifier pour savoir laquelle on met.
		final ArrayList<ModifierQuality> modifiers = new ArrayList<>(Arrays.asList(ModifierQuality.values()));
		modifiers.remove(ModifierQuality.ARTEFACT);
		final int totalValue = ModifierQuality.getSumValue(modifiers.toArray(new ModifierQuality[modifiers.size()]));
		for( final ModifierQuality mt : modifiers ) {
			curFactor += ( ( totalValue - ( mt.getValue() * 1.95 ) ) / ( 2.0f * totalValue ) ) * .50;
			if( randomFloat <= curFactor ) {
				res = mt;
				break;
			}
		}
		return res;
	}

	/**
	 * Gets the sum value.
	 * 
	 * @param array
	 *            the array
	 * @return the sum value
	 */
	public static int getSumValue(final ModifierQuality[] array) {
		int sum = 0;
		for( final ModifierQuality mv : array )
			sum += mv.getValue();
		return sum;
	}

	/** The value. */
	private int	value;

	/**
	 * Instantiates a new modifier quality.
	 * 
	 * @param value
	 *            the value
	 */
	private ModifierQuality(final int value) {
		this.value = value;
	}

	/**
	 * Gets the value.
	 * 
	 * @return the value
	 */
	public int getValue() {
		return this.value;
	}
}