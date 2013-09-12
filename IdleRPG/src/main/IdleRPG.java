/*
 * Author : Pierre
 * Last Update : 12 sept. 2013 - 04:07:21
 */
package main;

import gui.HeroesStatus;
import gui.ScreenState;
import gui.ShopStatus;

import java.net.URL;
import java.util.ArrayList;

import location.Shop;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.util.ResourceLoader;

import util.NameGenerator;
import TWLSlick.TWLStateBasedGame;
import character.Hero;
import database.heroes.Warrior;

// TODO: Auto-generated Javadoc
/**
 * The Class IdleRPG.
 */
public class IdleRPG extends TWLStateBasedGame {

	/** The instance. */
	public static IdleRPG	instance;

	/**
	 * Gets the single instance of IdleRPG.
	 * 
	 * @return single instance of IdleRPG
	 */
	public static IdleRPG getInstance() {
		return IdleRPG.instance;
	}

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(final String[] args) {
		try {
			final AppGameContainer container = new AppGameContainer(new IdleRPG());
			container.setDisplayMode(800, 600, false);
			container.setTargetFrameRate(60);
			container.start();
		} catch( final SlickException e ) {
			e.printStackTrace();
		}
	}

	/** The game tick. */
	private long					gameTick;

	/** The hero list. */
	private final ArrayList<Hero>	heroList;

	/** The paused. */
	private boolean					paused;

	/** The shop. */
	private final Shop				shop;

	private final NameGenerator		nameGenerator;

	/**
	 * Instantiates a new idle rpg.
	 */
	public IdleRPG() {
		super("IdleRPG");
		IdleRPG.instance = this;

		this.heroList = new ArrayList<>();
		this.shop = Shop.getInstance();
		this.nameGenerator = new NameGenerator(this);

		this.addHero();
	}

	private void addHero() {
		final Hero h1 = new Warrior(this.nameGenerator.generate());
		this.heroList.add(h1);
	}

	/**
	 * Enter state.
	 * 
	 * @param state
	 *            the state
	 */
	public void enterState(final ScreenState state) {
		this.enterState(state.getID());
	}

	/**
	 * Gets the game tick.
	 * 
	 * @return the game tick
	 */
	public long getGameTick() {
		return this.gameTick;
	}

	/**
	 * Gets the hero list.
	 * 
	 * @return the hero list
	 */
	public ArrayList<Hero> getHeroList() {
		return this.heroList;
	}

	/**
	 * Gets the shop.
	 * 
	 * @return the shop
	 */
	public Shop getShop() {
		return this.shop;
	}

	/* (non-Javadoc)
	 * @see org.newdawn.slick.state.StateBasedGame#initStatesList(org.newdawn.slick.GameContainer)
	 */
	@Override
	public void initStatesList(final GameContainer container) throws SlickException {
		this.addState(new HeroesStatus());
		this.addState(new ShopStatus());
	}

	/**
	 * Checks if is paused.
	 * 
	 * @return true, if is paused
	 */
	public boolean isPaused() {
		return this.paused;
	}

	/**
	 * Sets the game tick.
	 * 
	 * @param gameTick
	 *            the new game tick
	 */
	public void setGameTick(final long gameTick) {
		this.gameTick = gameTick;
	}

	/**
	 * Sets the paused.
	 * 
	 * @param paused
	 *            the new paused
	 */
	public void setPaused(final boolean paused) {
		this.paused = paused;
	}

	/**
	 * Toggle paused.
	 */
	public void togglePaused() {
		this.paused = !this.paused;
	}

	/* (non-Javadoc)
	 * @see TWLSlick.TWLStateBasedGame#getThemeURL()
	 */
	@Override
	protected URL getThemeURL() {
		return ResourceLoader.getResource("/res/default/simple.xml");
	}

}