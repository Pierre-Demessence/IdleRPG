/*
 * Author : Pierre
 * Last Update : 12 sept. 2013 - 04:07:18
 */
package gui;

import gui.widget.BottomMenu;
import main.IdleRPG;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import TWLSlick.BasicTWLGameState;
import TWLSlick.RootPane;
import character.Hero;

// TODO: Auto-generated Javadoc
/**
 * The Class GameLogic.
 */
public abstract class GameLogic extends BasicTWLGameState {

	/** The bottom menu. */
	private BottomMenu	bottomMenu;

	/** The game. */
	protected IdleRPG	game;

	/* (non-Javadoc)
	 * @see org.newdawn.slick.state.GameState#init(org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame)
	 */
	@Override
	public void init(final GameContainer container, final StateBasedGame game) throws SlickException {
		this.game = (IdleRPG) game;
	}

	/* (non-Javadoc)
	 * @see org.newdawn.slick.state.GameState#update(org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame, int)
	 */
	@Override
	public void update(final GameContainer container, final StateBasedGame game, final int delta) throws SlickException {
		if( container.getInput().isKeyPressed(Input.KEY_SPACE) )
			this.game.togglePaused();
		if( this.game.isPaused() )
			return;

		this.game.setGameTick( ( this.game.getGameTick() + (long) ( ( 1.0f / 60 ) * 1000 ) )); // 60 est le nombre de FPS recherché.

		for( final Hero h : this.game.getHeroList() )
			if( ( this.game.getGameTick() - h.getTimeSinceLastUpdate() ) > h.getDelayBeforeNewUpdate() )
				h.update(this.game.getGameTick());
		if( ( this.game.getGameTick() - this.game.getShop().getTimeSinceLastRestock() ) > this.game.getShop().getDelayBeforeRestock() )
			this.game.getShop().restock(this.game.getGameTick());
	}

	/* (non-Javadoc)
	 * @see TWLSlick.BasicTWLGameState#createRootPane()
	 */
	@Override
	protected RootPane createRootPane() {
		final RootPane rp = super.createRootPane();

		this.bottomMenu = new BottomMenu();
		rp.add(this.bottomMenu);

		return rp;
	}

	/* (non-Javadoc)
	 * @see TWLSlick.BasicTWLGameState#layoutRootPane()
	 */
	@Override
	protected void layoutRootPane() {
		super.layoutRootPane();
		this.bottomMenu.adjustSize();
		this.bottomMenu.setPosition(0, this.getRootPane().getHeight() - this.bottomMenu.getHeight());
	}

}
