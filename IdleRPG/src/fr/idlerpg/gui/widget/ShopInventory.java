package fr.idlerpg.gui.widget;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import de.matthiasmann.twl.BorderLayout;
import de.matthiasmann.twl.BorderLayout.Location;
import de.matthiasmann.twl.ProgressBar;
import de.matthiasmann.twl.ResizableFrame;
import de.matthiasmann.twl.ScrollPane;
import de.matthiasmann.twl.TextArea;
import de.matthiasmann.twl.textarea.SimpleTextAreaModel;
import fr.idlerpg.item.Item;
import fr.idlerpg.location.Shop;
import fr.idlerpg.main.IdleRPG;

/**
 * The Class ShopInventory.
 */
public class ShopInventory extends ResizableFrame {

	/** The bar. */
	private final ProgressBar			bar;

	/** The tam. */
	private final SimpleTextAreaModel	tam;

	/**
	 * Instantiates a new shop inventory.
	 */
	public ShopInventory() {
		super();

		final BorderLayout dl = new BorderLayout();

		this.tam = new SimpleTextAreaModel();

		final TextArea textArea = new TextArea(this.tam);
		textArea.setTheme("textarea");

		final ScrollPane scrollPane = new ScrollPane(textArea);
		scrollPane.setTheme("scrollpane");
		scrollPane.setFixed(ScrollPane.Fixed.HORIZONTAL);

		this.bar = new ProgressBar();

		dl.add(this.bar, Location.NORTH);
		dl.add(scrollPane, Location.CENTER);

		this.add(dl);
		this.setTitle("Shop Stock");
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
		final Shop shop = Shop.getInstance();
		String text = "";
		for( final Item i : shop.getItems() )
			text += shop.getCount(i) + " " + i.getName() + " ; " + i.getValue() + "po\n";
		this.tam.setText(text);

		final float value = ( IdleRPG.getInstance().getGameTick() - (float) shop.getTimeSinceLastRestock() ) / shop.getDelayBeforeRestock();
		this.bar.setValue(value);
	}

}
