package database.items;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public enum ModifierQuality {

	// A ajouter sur certains items pour les transformer totalement.
	ARTEFACT(0),
	// Epic bonus !
	EPIC(3),
	// Bonus aux attributs.
	MAGICAL(2),
	// Bonus tout sauf attribut.
	UNCOMMON(1);

	private int	value;

	private ModifierQuality(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}

	public static int getSumValue(ModifierQuality[] array) {
		int sum = 0;
		for( ModifierQuality mv : array )
			sum += mv.getValue();
		return sum;
	}

	public static ModifierQuality getRandomModifierQuality() {
		ModifierQuality res = null;
		float randomFloat = new Random().nextFloat();
		float curFactor = 0;
		// On test chaque valeur de modifier pour savoir laquelle on met.
		ArrayList<ModifierQuality> modifiers = new ArrayList<>(Arrays.asList(ModifierQuality.values()));
		modifiers.remove(ModifierQuality.ARTEFACT);
		int totalValue = getSumValue(modifiers.toArray(new ModifierQuality[modifiers.size()]));
		for( ModifierQuality mt : modifiers ) {
			curFactor += ( ( totalValue - mt.getValue() * 1.95 ) / ( 2.0f * totalValue ) ) * .50;
			if( randomFloat <= curFactor ) {
				res = mt;
				break;
			}
		}
		return res;
	}
}