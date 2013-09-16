package fr.idlerpg.gui;

import fr.idlerpg.TWLSlick.BasicTWLGameState;
import fr.idlerpg.TWLSlick.RootPane;
import fr.idlerpg.character.Hero;
import fr.idlerpg.gui.widget.BottomMenu;
import fr.idlerpg.main.IdleRPG;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

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
		// On vérifie la pause
		if( container.getInput().isKeyPressed(Input.KEY_SPACE) )
			this.game.togglePaused();
		if( this.game.isPaused() )
			return;

		// On met à jour les ticks.
		this.game.setGameTick( ( this.game.getGameTick() + (long) ( ( 1.0f / 60 ) * 1000 ) )); // 60 est le nombre de FPS recherché.

		// On met à jour les héros.
		for( final Hero h : this.game.getHeroList() )
			if( !h.isDead() && ( this.game.getGameTick() - h.getTimeSinceLastUpdate() ) > h.getDelayBeforeNewUpdate() )
				h.update(this.game.getGameTick());

		// On met à jour le magasin.
		if( ( this.game.getGameTick() - this.game.getShop().getTimeSinceLastRestock() ) > this.game.getShop().getDelayBeforeRestock() )
			this.game.getShop().restock(this.game.getGameTick());

		// On vérifie qu'il y a le bon nombre de héros vivant minimum.
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
