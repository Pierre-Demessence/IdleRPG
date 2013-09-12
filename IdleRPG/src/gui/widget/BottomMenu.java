/*
 * Author : Pierre
 * Last Update : 12 sept. 2013 - 04:07:19
 */
package gui.widget;

import gui.ScreenState;
import main.IdleRPG;
import de.matthiasmann.twl.BoxLayout;
import de.matthiasmann.twl.Button;

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
	 * Creates a button.
	 * 
	 * @param text
	 *            the button text
	 * @param cb
	 *            the button callback
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
