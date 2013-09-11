/*
 * Author : Pierre
 * Last Update : 11 sept. 2013 - 16:36:21
 */
package TWLSlick;

import de.matthiasmann.twl.DesktopArea;
import de.matthiasmann.twl.Event;
import de.matthiasmann.twl.Widget;

// TODO: Auto-generated Javadoc
/**
 * RootPane for all game states.
 * It forwards input events which where not handled by the UI to the game state.
 * 
 * @author Matthias Mann
 */
public class RootPane extends DesktopArea {

	/** The old mouse x. */
	protected int				oldMouseX;
	
	/** The old mouse y. */
	protected int				oldMouseY;
	
	/** The state. */
	protected BasicTWLGameState	state;

	/**
	 * Instantiates a new root pane.
	 * 
	 * @param state
	 *            the state
	 */
	public RootPane(final BasicTWLGameState state) {
		if( state == null )
			throw new NullPointerException("state");
		this.state = state;

		this.setCanAcceptKeyboardFocus(true);
	}

	/**
	 * When subclassing this class it's strongly suggested to provide
	 * a default constructor to allow previewing in the Theme Editor.
	 */
	protected RootPane() {
		this.state = null;

		this.setCanAcceptKeyboardFocus(true);

		System.err.println("This constructor is only intended to by called to preview subclass in the TWL Theme Editor");
	}

	/**
	 * Returns the game state to which this root pane is associated with.
	 * 
	 * @return the game state or null when in preview mode (Theme Editor).
	 * @see #isPreviewMode()
	 */
	public final BasicTWLGameState getState() {
		return this.state;
	}

	/**
	 * Returns true when the root pane is in preview mode (Theme Editor).
	 * 
	 * @return true when the root pane is in preview mode (Theme Editor).
	 */
	public final boolean isPreviewMode() {
		return this.state == null;
	}

	/* (non-Javadoc)
	 * @see de.matthiasmann.twl.Widget#handleEvent(de.matthiasmann.twl.Event)
	 */
	@Override
	protected boolean handleEvent(final Event evt) {
		if( super.handleEvent(evt) )
			return true;

		if( this.state != null )
			switch( evt.getType() ) {
			case KEY_PRESSED:
				this.state.keyPressed(evt.getKeyCode(), evt.getKeyChar());
				break;
			case KEY_RELEASED:
				this.state.keyReleased(evt.getKeyCode(), evt.getKeyChar());
				break;
			case MOUSE_BTNDOWN:
				this.state.mousePressed(evt.getMouseButton(), evt.getMouseX(), evt.getMouseY());
				break;
			case MOUSE_BTNUP:
				this.state.mouseReleased(evt.getMouseButton(), evt.getMouseX(), evt.getMouseY());
				break;
			case MOUSE_CLICKED:
				this.state.mouseClicked(evt.getMouseButton(), evt.getMouseX(), evt.getMouseY(), evt.getMouseClickCount());
				break;
			case MOUSE_ENTERED:
			case MOUSE_MOVED:
				this.state.mouseMoved(this.oldMouseX, this.oldMouseY, evt.getMouseX(), evt.getMouseY());
				break;
			case MOUSE_DRAGGED:
				this.state.mouseDragged(this.oldMouseX, this.oldMouseY, evt.getMouseX(), evt.getMouseY());
				break;
			case MOUSE_WHEEL:
				this.state.mouseWheelMoved(evt.getMouseWheelDelta());
				break;
			}

		if( evt.isMouseEvent() ) {
			this.oldMouseX = evt.getMouseX();
			this.oldMouseY = evt.getMouseY();
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see de.matthiasmann.twl.Widget#keyboardFocusLost()
	 */
	@Override
	protected void keyboardFocusLost() {
		if( this.state != null )
			this.state.keyboardFocusLost();
	}

	/* (non-Javadoc)
	 * @see de.matthiasmann.twl.DesktopArea#layout()
	 */
	@Override
	protected void layout() {
		super.layout();
		this.state.layoutRootPane();
	}

	/* (non-Javadoc)
	 * @see de.matthiasmann.twl.Widget#requestKeyboardFocus(de.matthiasmann.twl.Widget)
	 */
	@Override
	protected boolean requestKeyboardFocus(final Widget child) {
		if( ( child != null ) && ( this.state != null ) )
			this.state.keyboardFocusLost();
		return super.requestKeyboardFocus(child);
	}
}
