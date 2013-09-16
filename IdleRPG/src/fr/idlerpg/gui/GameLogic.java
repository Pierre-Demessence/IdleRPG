package fr.idlerpg.gui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.idlerpg.TWLSlick.BasicTWLGameState;
import fr.idlerpg.TWLSlick.RootPane;
import fr.idlerpg.character.Hero;
import fr.idlerpg.gui.widget.BottomMenu;
import fr.idlerpg.main.IdleRPG;

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
		// On v�rifie la pause
		if( container.getInput().isKeyPressed(Input.KEY_SPACE) )
			this.game.togglePaused();
		if( this.game.isPaused() )
			return;

		if( container.getInput().isKeyDown(Input.KEY_ADD) )
			container.setTargetFrameRate(container.getFPS() + 50);
		else if( container.getInput().isKeyDown(Input.KEY_SUBTRACT) )
			container.setTargetFrameRate(container.getFPS() - 50);

		// On met � jour les ticks.
		this.game.setGameTick( ( this.game.getGameTick() + (long) ( ( 1.0f / 60 ) * 1000 ) )); // 60 est le nombre de FPS recherch�.

		// On met � jour les h�ros.
		for( final Hero h : this.game.getHeroList() )
			if( !h.isDead() && ( ( this.game.getGameTick() - h.getTimeSinceLastUpdate() ) > h.getDelayBeforeNewUpdate() ) )
				h.update(this.game.getGameTick());

		// On met � jour le magasin.
		if( ( this.game.getGameTick() - this.game.getShop().getTimeSinceLastRestock() ) > this.game.getShop().getDelayBeforeRestock() )
			this.game.getShop().restock(this.game.getGameTick());

		// On v�rifie qu'il y a le bon nombre de h�ros vivant minimum.
		while( this.game.countAliveHeroes() < IdleRPG.MIN_ALIVE_HEROES )
			this.game.addHero();
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
