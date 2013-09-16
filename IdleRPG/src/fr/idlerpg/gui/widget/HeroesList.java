package fr.idlerpg.gui.widget;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import de.matthiasmann.twl.ColumnLayout;
import de.matthiasmann.twl.ResizableFrame;

/**
 * The Class HeroesList.
 */
public class HeroesList extends ResizableFrame {

	/** The column layout. */
	private ColumnLayout	columnLayout;

	/**
	 * Instantiates a new heroes list.
	 */
	public HeroesList() {
		super();

		this.setTitle("Heroes List");
		this.setTheme("resizableframe-title");

	}

	/**
	 * Render.
	 * 
	 * @param container
	 *            the container
	 * @param game
	 *            the game
	 * @param g
	 *            the g
	 */
	public void render(final GameContainer container, final StateBasedGame game, final Graphics g) {

	}
}
