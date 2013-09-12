/*
 * Author : Pierre
 * Last Update : 12 sept. 2013 - 04:07:21
 */
package character;

import item.Loot;
import util.Logger;

// TODO: Auto-generated Javadoc
/**
 * The Class Fight.
 */
public class Fight {

	/** The has hero fleed. */
	private boolean			hasHeroFleed;

	/** The hero. */
	private final Hero		hero;

	/** The hero turn. */
	private boolean			heroTurn;

	/** The monster. */
	private final Monster	monster;

	/**
	 * Instantiates a new fight.
	 * 
	 * @param hero
	 *            the hero
	 * @param monster
	 *            the monster
	 */
	public Fight(final Hero hero, final Monster monster) {
		this.hero = hero;
		this.monster = monster;
		this.heroTurn = true;

		this.hero.setFight(this);
		Logger.log("Début d'un nouveau combat entre " + this.hero.getName() + " et " + monster.getName() + ".");
	}

	/**
	 * Do turn.
	 */
	public void doTurn() {
		if( this.heroTurn )
			this.doTurn(this.hero, this.monster);
		else
			this.doTurn(this.monster, this.hero);
		this.heroTurn = !this.heroTurn;
		if( this.hasHeroFleed() )
			this.endFight();
	}

	/**
	 * Do turn.
	 * 
	 * @param attacker
	 *            the attacker
	 * @param defenser
	 *            the defenser
	 */
	public void doTurn(final Character attacker, final Character defenser) {
		if( !this.isEnded() ) {
			attacker.doFight(defenser);
			this.verifyResult();
		}
	}

	/**
	 * End fight.
	 */
	public void endFight() {
		Logger.log("Le combat est terminé.");
		if( this.monster.isKO() )
			this.win();
		else if( this.hero.isKO() )
			this.loose();
		else
			Logger.log(this.hero.getName() + " a fuit le combat !");
		this.hero.setFight(null);
	}

	/**
	 * Flee.
	 */
	public void flee() {
		Logger.log("Le monstre ennemi a une attaque d'opportunité !");
		this.hasHeroFleed = true;
	}

	/**
	 * Gets the monster.
	 * 
	 * @return the monster
	 */
	public Monster getMonster() {
		return this.monster;
	}

	/**
	 * Checks for hero fleed.
	 * 
	 * @return true, if successful
	 */
	private boolean hasHeroFleed() {
		return this.hasHeroFleed;
	}

	/**
	 * Checks if is the fight ended.
	 * 
	 * @return true, if the fight is ended
	 */
	private boolean isEnded() {
		return ( ( this.hero.isKO() ) || ( this.monster.isKO() ) );
	}

	/**
	 * If the hero loose.
	 */
	private void loose() {
		Logger.log(this.hero.getName() + " est mis KO.");
		final int goldLost = Math.round(this.hero.getGold() * ( (float) this.monster.getLevel() / 100 ));
		Logger.log("Il perd " + goldLost + "po que le " + this.monster.getName() + " lui vole !");
		this.hero.addGold(goldLost);
	}

	/**
	 * Verify result.
	 */
	private void verifyResult() {
		if( this.isEnded() )
			this.endFight();
	}

	/**
	 * If the hero win.
	 */
	private void win() {
		Logger.log(this.hero.getName() + " a gagné son combat !");
		Logger.log("Il gagne " + this.monster.getGoldLoot() + "po ainsi que " + this.monster.getExperienceLoot() + "xp !");
		this.hero.addGold(this.monster.getGoldLoot());
		this.hero.addExperience(this.monster.getExperienceLoot());
		if( this.monster.getLoots() != null )
			for( final Loot l : this.monster.getLoots() )
				if( l.test() ) {
					Logger.log("Il trouve " + l.getQuantity() + " " + l.getItem().getName() + " !");
					this.hero.addItem(l.getItem(), l.getQuantity());
				}
		this.hero.decreaseFightBeforeGoToShop();
	}
}
