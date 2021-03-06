/*
 * Author : Pierre
 * Last Update : 12 sept. 2013 - 04:07:20
 */
package fr.idlerpg.gui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.idlerpg.TWLSlick.RootPane;
import fr.idlerpg.gui.widget.ShopInventory;

/**
 * The Class ShopStatus.
 */
public class ShopStatus extends GameLogic {

	/** The text area. */
	private ShopInventory	textArea;

	/* (non-Javadoc)
	 * @see org.newdawn.slick.state.BasicGameState#getID()
	 */
	@Override
	public int getID() {
		return ScreenState.SHOP_STATUS.getID();
	}

	/* (non-Javadoc)
	 * @see org.newdawn.slick.state.GameState#render(org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame, org.newdawn.slick.Graphics)
	 */
	@Override
	public void render(final GameContainer container, final StateBasedGame game, final Graphics g) throws SlickException {
		this.textArea.render(container, game, g);
	}

	/* (non-Javadoc)
	 * @see gui.GameLogic#createRootPane()
	 */
	@Override
	protected RootPane createRootPane() {
		final RootPane rp = super.createRootPane();

		this.textArea = new ShopInventory();
		this.textArea.setSize(200, 200);
		this.textArea.setPosition(10, 50);

		rp.add(this.textArea);
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
