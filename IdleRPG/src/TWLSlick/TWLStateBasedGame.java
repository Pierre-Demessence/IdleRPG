/*
 * Author : Pierre
 * Last Update : 11 sept. 2013 - 16:36:21
 */
package TWLSlick;

import java.io.IOException;
import java.net.URL;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.Transition;

import de.matthiasmann.twl.GUI;
import de.matthiasmann.twl.Widget;
import de.matthiasmann.twl.renderer.Renderer;
import de.matthiasmann.twl.renderer.lwjgl.LWJGLRenderer;
import de.matthiasmann.twl.theme.ThemeManager;

// TODO: Auto-generated Javadoc
/**
 * A StateBaseGame subclass with support for a TWL Ui per state.
 * 
 * @author Matthias Mann
 */
public abstract class TWLStateBasedGame extends StateBasedGame {

	/** The empty root widget. */
	private final Widget	emptyRootWidget;

	/** The gui. */
	private GUI				gui;
	
	/** The gui initialized. */
	private boolean			guiInitialized;

	/**
	 * Instantiates a new tWL state based game.
	 * 
	 * @param name
	 *            the name
	 */
	protected TWLStateBasedGame(final String name) {
		super(name);

		this.emptyRootWidget = new Widget();
		this.emptyRootWidget.setTheme("");
	}

	/**
	 * Adds a new game state.
	 * 
	 * @param state
	 *            the game state
	 * @see StateBasedGame#addState(org.newdawn.slick.state.GameState)
	 */
	public void addState(final BasicTWLGameState state) {
		super.addState(state);
	}

	/**
	 * Adds a new game state.
	 * 
	 * This method is overriden to ensure that only instances of BasicTWLGameState are added.
	 * 
	 * @param state
	 *            the game state. Must be an instance of BasicTWLGameState
	 * @see StateBasedGame#addState(org.newdawn.slick.state.GameState)
	 */
	@Override
	public void addState(final GameState state) {
		if( ! ( state instanceof BasicTWLGameState ) )
			throw new IllegalArgumentException("state must be a BasicTWLGameState");
		super.addState(state);
	}

	/**
	 * Transits to a the specified game state.
	 * This method hides the UI of the current state before starting the transition.
	 * 
	 * @param id
	 *            The ID of the state to enter
	 * @param leave
	 *            The transition to use when leaving the current state
	 * @param enter
	 *            The transition to use when entering the new state
	 * @see StateBasedGame#enterState(int, org.newdawn.slick.state.transition.Transition, org.newdawn.slick.state.transition.Transition)
	 */
	@Override
	public void enterState(final int id, final Transition leave, final Transition enter) {
		if( this.gui != null )
			this.gui.setRootPane(this.emptyRootWidget);
		super.enterState(id, leave, enter);
	}

	/**
	 * Implement this method and return the URL for the TWL theme.
	 * 
	 * @return the URL for the TWL theme. Must not be null.
	 */
	protected abstract URL getThemeURL();

	/**
	 * Inits the gui.
	 * 
	 * @throws SlickException
	 *             the slick exception
	 */
	protected void initGUI() throws SlickException {
		GL11.glPushAttrib(GL11.GL_ALL_ATTRIB_BITS);
		try {
			final Renderer renderer = new LWJGLRenderer();
			final ThemeManager theme = this.loadTheme(renderer);

			this.gui = new GUI(this.emptyRootWidget, renderer, null);
			this.gui.applyTheme(theme);

			final Input input = this.getContainer().getInput();
			final TWLInputForwarder inputForwarder = new TWLInputForwarder(this.gui, input);
			input.addPrimaryListener(inputForwarder);
		} catch( final Throwable e ) {
			throw new SlickException("Could not initialize TWL GUI", e);
		} finally {
			GL11.glPopAttrib();
		}
	}

	/**
	 * Load theme.
	 * 
	 * @param renderer
	 *            the renderer
	 * @return the theme manager
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	protected ThemeManager loadTheme(final Renderer renderer) throws IOException {
		final URL url = this.getThemeURL();
		assert url != null;
		return ThemeManager.createThemeManager(url, renderer);
	}

	/* (non-Javadoc)
	 * @see org.newdawn.slick.state.StateBasedGame#postRenderState(org.newdawn.slick.GameContainer, org.newdawn.slick.Graphics)
	 */
	@Override
	protected void postRenderState(final GameContainer container, final Graphics g) throws SlickException {
		if( this.gui != null )
			this.gui.draw();
	}

	/* (non-Javadoc)
	 * @see org.newdawn.slick.state.StateBasedGame#postUpdateState(org.newdawn.slick.GameContainer, int)
	 */
	@Override
	protected void postUpdateState(final GameContainer container, final int delta) throws SlickException {
		if( this.gui != null ) {
			this.gui.setSize();
			this.gui.handleTooltips();
			this.gui.updateTimers();
			this.gui.invokeRunables();
			this.gui.validateLayout();
			this.gui.setCursor();
		}
	}

	/**
	 * Sets the root pane.
	 * 
	 * @param rootPane
	 *            the new root pane
	 * @throws SlickException
	 *             the slick exception
	 */
	protected void setRootPane(final RootPane rootPane) throws SlickException {
		if( !this.guiInitialized ) {
			this.guiInitialized = true;
			this.initGUI();
		}
		if( this.gui != null )
			this.gui.setRootPane(rootPane);
	}
}
