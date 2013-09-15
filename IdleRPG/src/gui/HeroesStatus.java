package gui;

import gui.widget.HeroesList;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import TWLSlick.RootPane;
import character.Hero;
import database.characters.Attribute;

/**
 * The Class HeroesStatus.
 */
public class HeroesStatus extends GameLogic {

	/** The heroes list. */
	private HeroesList	heroesList;

	/* (non-Javadoc)
	 * @see org.newdawn.slick.state.BasicGameState#getID()
	 */
	@Override
	public int getID() {
		return ScreenState.HEROES_LIST.getID();
	}

	/* (non-Javadoc)
	 * @see org.newdawn.slick.state.GameState#render(org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame, org.newdawn.slick.Graphics)
	 */
	@Override
	public void render(final GameContainer container, final StateBasedGame game, final Graphics g) throws SlickException {
		g.setColor(Color.white);
		final Hero hero = this.game.getHeroList().get(0);
		g.drawString("Name : " + hero.getName(), 100, 100);
		g.drawString("Gold : " + hero.getGold(), 100, 125);
		g.drawString("Experience : " + hero.getExperience(), 100, 150);
		g.drawString("Life : " + hero.getLife() + " / " + hero.getMaxLife(), 100, 175);

		int y = 0;
		for( Attribute a : Attribute.values() ) {

			g.drawString(a.name() + " : " + hero.getAttribute(a), 100, 250 + y);
			y += 25;
		}

		g.drawRect(100, 200, 300, 20);
		g.setColor(Color.blue);
		final float w = ( ( this.game.getGameTick() - (float) hero.getTimeSinceLastUpdate() ) / hero.getDelayBeforeNewUpdate() ) * 300;
		g.fillRect(101, 201, w - 1, 19);

		g.setColor(Color.white);
		int deads = this.game.getHeroList().size() - this.game.countAliveHeroes();
		g.drawString("Dead : " + deads, 400, 400);

		long timeSpent = System.currentTimeMillis() - this.game.getBeginTime();
		g.drawString("Dead/s : " + deads / ( (float) timeSpent / 1000 ), 400, 450);

		ArrayList<Hero> aliveHeroes = this.game.getAliveHeroes();
		float meanLevel = 0;
		for( Hero h : aliveHeroes )
			meanLevel += h.getLevel();
		meanLevel /= aliveHeroes.size();

		g.drawString("Mean Level : " + meanLevel, 400, 500);

		this.heroesList.render(container, game, g);
	}

	/* (non-Javadoc)
	 * @see gui.GameLogic#createRootPane()
	 */
	@Override
	protected RootPane createRootPane() {
		final RootPane rp = super.createRootPane();

		this.heroesList = new HeroesList();
		rp.add(this.heroesList);

		return rp;
	}

	/* (non-Javadoc)
	 * @see gui.GameLogic#layoutRootPane()
	 */
	@Override
	protected void layoutRootPane() {
		super.layoutRootPane();
	}

}
