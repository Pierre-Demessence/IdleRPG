/*
 * Author : Pierre
 * Last Update : 11 sept. 2013 - 16:36:20
 */
package TWLSlick;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import de.matthiasmann.twl.ActionMap;
import de.matthiasmann.twl.Widget;

// TODO: Auto-generated Javadoc
/**
 * The base class for all game states when using TWLStateBasedGame.
 * 
 * <p>
 * To create your UI you can override {@link #createRootPane()} or {@link #init(org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame)}.
 * </p>
 * 
 * <p>
 * Set the position and size of the widgets inside {@link #layoutRootPane()}.
 * </p>
 * 
 * @author Matthias Mann
 */
public abstract class BasicTWLGameState extends BasicGameState {

	/** The root pane. */
	private RootPane	rootPane;

	/**
	 * Installs the rootPane of this state as the active root pane.
	 * Calls createRootPane() on first run.
	 * 
	 * @param container
	 *            the GameContainer instance
	 * @param game
	 *            the StateBasedGame instance
	 * @throws SlickException
	 *             the slick exception
	 * @see #createRootPane()
	 */
	@Override
	public void enter(final GameContainer container, final StateBasedGame game) throws SlickException {
		( (TWLStateBasedGame) game ).setRootPane(this.getRootPane());
	}

	/**
	 * Returns the root pane for this game state.
	 * Calls {@link #createRootPane()} if it has not yet been created.
	 * 
	 * @return the root pane
	 */
	public RootPane getRootPane() {
		if( this.rootPane == null ) {
			this.rootPane = this.createRootPane();
			if( this.rootPane.getState() != this )
				throw new IllegalStateException("rootPane.getState() != this");
		}
		return this.rootPane;
	}

	/**
	 * Override this method to customize the root pane for your UI for this state.
	 * 
	 * <p>
	 * The theme name of the RootPane created by this method is "state"+getID(). It will also register all action methods to the rootPane.
	 * </p>
	 * 
	 * <p>
	 * Do not call this method. Call {@link #getRootPane()} instead
	 * </p>
	 * 
	 * <p>
	 * When overriding this method don't call {@link #getRootPane()} from within this method or it will lead to an endless loop.
	 * </p>
	 * 
	 * @return the created root pane
	 * @see ActionMap.Action
	 * @see ActionMap#addMapping(java.lang.Object)
	 * @see Widget#setActionMap(de.matthiasmann.twl.ActionMap)
	 * @see Widget#setTheme(java.lang.String)
	 */
	protected RootPane createRootPane() {
		assert this.rootPane == null : "RootPane already created";

		final RootPane rp = new RootPane(this);
		rp.setTheme("state" + this.getID());
		rp.getOrCreateActionMap().addMapping(this);
		return rp;
	}

	/**
	 * This method is called when keyboard focus is transfered to a UI widget
	 * or to another application.
	 */
	protected void keyboardFocusLost() {
	}

	/**
	 * This method is called when the layout of the root pane needs to be updated.
	 * 
	 * Widget position and size should only be changed within this method.
	 * 
	 * @see Widget#setPosition(int, int)
	 * @see Widget#setSize(int, int)
	 * @see Widget#adjustSize()
	 */
	protected void layoutRootPane() {
	}
}
