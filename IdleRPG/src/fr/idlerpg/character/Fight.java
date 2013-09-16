package fr.idlerpg.character;

import fr.idlerpg.item.Item;
import fr.idlerpg.item.Loot;
import fr.idlerpg.util.Logger;

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
		if( this.isSomeoneKO() || this.hasHeroFleed() )
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
		attacker.doFight(defenser);
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
		this.monster.doFight(this.hero);
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
	private boolean isSomeoneKO() {
		return ( ( this.hero.isKO() ) || ( this.monster.isKO() ) );
	}

	/**
	 * If the hero loose.
	 */
	private void loose() {
		Logger.log(this.hero.getName() + " est mis KO.");
		final int goldLost = Math.round(this.hero.getGold() * ( (float) this.monster.getLevel() / 100 ));
		Logger.log("Il perd " + goldLost + "po que le " + this.monster.getName() + " lui vole !");
		this.hero.addGold(-goldLost);
	}

	/**
	 * If the hero win.
	 */
	private void win() {
		Logger.log(this.hero.getName() + " a gagné son combat !");
		Logger.log(this.hero, "Je gagne " + this.monster.getGoldLoot() + "po ainsi que " + this.monster.getExperienceLoot() + "xp !");
		this.hero.addGold(this.monster.getGoldLoot());
		this.hero.addExperience(this.monster.getExperienceLoot());
		if( this.monster.getLoots() != null )
			for( final Loot l : this.monster.getLoots() )
				if( l.test() ) {
					Item item = l.getItem();
					Logger.log(this.hero, "Je trouve " + l.getQuantity() + " " + item.getName() + " !");
					this.hero.addItem(item, l.getQuantity());
				}
		this.hero.decreaseFightBeforeGoToShop();
	}
}
