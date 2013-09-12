/*
 * Author : Pierre
 * Last Update : 12 sept. 2013 - 04:07:20
 */
package gui;

import gui.widget.HeroesList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import TWLSlick.RootPane;
import character.Hero;

// TODO: Auto-generated Javadoc
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
		g.drawRect(100, 200, 300, 20);
		g.setColor(Color.blue);
		final float w = ( ( this.game.getGameTick() - (float) hero.getTimeSinceLastUpdate() ) / hero.getDelayBeforeNewUpdate() ) * 300;
		g.fillRect(101, 201, w - 1, 19);

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
