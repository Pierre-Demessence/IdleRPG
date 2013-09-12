/*
 * Author : Pierre
 * Last Update : 12 sept. 2013 - 04:07:19
 */
package TWLSlick;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Input;
import org.newdawn.slick.util.InputAdapter;

import de.matthiasmann.twl.GUI;

// TODO: Auto-generated Javadoc
/**
 * Forwards input events from Slick to TWL.
 * 
 * @author Matthias Mann
 */
class TWLInputForwarder extends InputAdapter {

	/** The gui. */
	private final GUI	gui;

	/** The input. */
	private final Input	input;

	/**
	 * Instantiates a new tWL input forwarder.
	 * 
	 * @param gui
	 *            the gui
	 * @param input
	 *            the input
	 */
	public TWLInputForwarder(final GUI gui, final Input input) {
		if( gui == null )
			throw new NullPointerException("gui");
		if( input == null )
			throw new NullPointerException("input");

		this.gui = gui;
		this.input = input;
	}

	/* (non-Javadoc)
	 * @see org.newdawn.slick.util.InputAdapter#inputEnded()
	 */
	@Override
	public void inputEnded() {
		this.gui.handleKeyRepeat();
	}

	/* (non-Javadoc)
	 * @see org.newdawn.slick.util.InputAdapter#inputStarted()
	 */
	@Override
	public void inputStarted() {
		this.gui.updateTime();
		if( !Display.isActive() ) {
			this.gui.clearKeyboardState();
			this.gui.clearMouseState();

			if( this.gui.getRootPane() instanceof RootPane )
				( (RootPane) this.gui.getRootPane() ).keyboardFocusLost();
		}
	}

	/* (non-Javadoc)
	 * @see org.newdawn.slick.util.InputAdapter#keyPressed(int, char)
	 */
	@Override
	public void keyPressed(final int key, final char c) {
		this.gui.handleKey(key, c, true);
		this.input.consumeEvent();
	}

	/* (non-Javadoc)
	 * @see org.newdawn.slick.util.InputAdapter#keyReleased(int, char)
	 */
	@Override
	public void keyReleased(final int key, final char c) {
		this.gui.handleKey(key, c, false);
		this.input.consumeEvent();
	}

	/* (non-Javadoc)
	 * @see org.newdawn.slick.util.InputAdapter#mouseClicked(int, int, int, int)
	 */
	@Override
	public void mouseClicked(final int button, final int x, final int y, final int clickCount) {
		this.input.consumeEvent();
	}

	/* (non-Javadoc)
	 * @see org.newdawn.slick.util.InputAdapter#mouseDragged(int, int, int, int)
	 */
	@Override
	public void mouseDragged(final int oldx, final int oldy, final int newX, final int newY) {
		this.mouseMoved(oldx, oldy, newX, newY);
	}

	/* (non-Javadoc)
	 * @see org.newdawn.slick.util.InputAdapter#mouseMoved(int, int, int, int)
	 */
	@Override
	public void mouseMoved(final int oldX, final int oldY, final int newX, final int newY) {
		this.gui.handleMouse(newX, newY, -1, false);
		this.input.consumeEvent();
	}

	/* (non-Javadoc)
	 * @see org.newdawn.slick.util.InputAdapter#mousePressed(int, int, int)
	 */
	@Override
	public void mousePressed(final int button, final int x, final int y) {
		this.gui.handleMouse(x, y, button, true);
		this.input.consumeEvent();
	}

	/* (non-Javadoc)
	 * @see org.newdawn.slick.util.InputAdapter#mouseReleased(int, int, int)
	 */
	@Override
	public void mouseReleased(final int button, final int x, final int y) {
		this.gui.handleMouse(x, y, button, false);
		this.input.consumeEvent();
	}

	/* (non-Javadoc)
	 * @see org.newdawn.slick.util.InputAdapter#mouseWheelMoved(int)
	 */
	@Override
	public void mouseWheelMoved(final int change) {
		this.gui.handleMouseWheel(change);
		this.input.consumeEvent();
	}
}
