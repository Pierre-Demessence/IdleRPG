/*
 * Author : Pierre
 * Last Update : 11 sept. 2013 - 16:36:20
 */
package gui.widget;

import gui.ScreenState;
import main.IdleRPG;
import de.matthiasmann.twl.BoxLayout;
import de.matthiasmann.twl.Button;

// TODO: Auto-generated Javadoc
/**
 * The Class BottomMenu.
 */
public class BottomMenu extends BoxLayout {

	/**
	 * Instantiates a new bottom menu.
	 */
	public BottomMenu() {
		super();
		this.createButton("Heroes List", new Runnable() {
			@Override
			public void run() {
				IdleRPG.getInstance().enterState(ScreenState.HEROES_LIST);
			}
		});
		this.createButton("Shop", new Runnable() {
			@Override
			public void run() {
				IdleRPG.getInstance().enterState(ScreenState.SHOP_STATUS);
			}
		});
	}

	/**
	 * Creates the button.
	 * 
	 * @param text
	 *            the text
	 * @param cb
	 *            the cb
	 * @return the button
	 */
	private Button createButton(final String text, final Runnable cb) {
		final Button button = new Button(text);
		button.addCallback(cb);
		this.invalidateLayout();
		this.add(button);
		return button;
	}

}
