package fr.idlerpg.main;

import java.net.URL;
import java.util.ArrayList;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.util.ResourceLoader;

import fr.idlerpg.TWLSlick.TWLStateBasedGame;
import fr.idlerpg.character.Hero;
import fr.idlerpg.database.factories.HeroFactory;
import fr.idlerpg.gui.HeroesStatus;
import fr.idlerpg.gui.ScreenState;
import fr.idlerpg.gui.ShopStatus;
import fr.idlerpg.location.Shop;
import fr.idlerpg.util.NameGenerator;

/**
 * The Class IdleRPG.
 */
public class IdleRPG extends TWLStateBasedGame {

	/** The instance. */
	private static IdleRPG	INSTANCE;

	public static final int	KO_NUMBER_FOR_DEAD	= 3;

	public static final int	MIN_ALIVE_HEROES	= 1;

	public static final int	GAME_SPEED			= 1;

	public static final int	LEVEL_CAP			= 3;

	private long			beginTime;

	private static long[]	EXPERIENCE_NEED		= new long[LEVEL_CAP - 1];

	/**
	 * Gets the single instance of IdleRPG.
	 * 
	 * @return single instance of IdleRPG
	 */
	public static IdleRPG getInstance() {
		if( INSTANCE == null )
			IdleRPG.init();
		return IdleRPG.INSTANCE;
	}

	private static void init() {
		INSTANCE = new IdleRPG();
		IdleRPG.EXPERIENCE_NEED[0] = 100; // Niveau 2
		IdleRPG.EXPERIENCE_NEED[1] = 500; // Niveau 3
	}

	public static long getExperienceNeed(int level) {
		if( level < 2 )
			return 0;
		return IdleRPG.EXPERIENCE_NEED[level - 2];
	}

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(final String[] args) {
		try {
			IdleRPG game = IdleRPG.getInstance();
			final AppGameContainer container = new AppGameContainer(game);
			container.setDisplayMode(800, 600, false);
			container.setTargetFrameRate(60 * IdleRPG.GAME_SPEED);
			container.setShowFPS(false);
			container.start();
		} catch( final SlickException e ) {
			e.printStackTrace();
		}
	}

	/** The game tick. */
	private long					gameTick;

	/** The hero list. */
	private final ArrayList<Hero>	heroList;

	/** The name generator. */
	private final NameGenerator		nameGenerator;

	/** The paused. */
	private boolean					paused;

	/** The shop. */
	private final Shop				shop;

	/**
	 * Instantiates a new idle rpg.
	 */
	private IdleRPG() {
		super("IdleRPG");

		this.heroList = new ArrayList<>();
		this.shop = Shop.getInstance();
		this.nameGenerator = new NameGenerator(this);
		this.beginTime = System.currentTimeMillis();
	}

	public long getBeginTime() {
		return this.beginTime;
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

	public int countAliveHeroes() {
		int res = 0;
		for( Hero h : this.heroList )
			if( !h.isDead() )
				res++;
		return res;
	}

	public ArrayList<Hero> getAliveHeroes() {
		ArrayList<Hero> res = new ArrayList<>();
		for( Hero h : this.heroList )
			if( !h.isDead() )
				res.add(h);
		return res;
	}

	public ArrayList<Hero> getDeadHeroes() {
		ArrayList<Hero> res = new ArrayList<>();
		for( Hero h : this.heroList )
			if( h.isDead() )
				res.add(h);
		return res;
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

	/**
	 * Adds the hero.
	 */
	public void addHero() {
		this.heroList.add(HeroFactory.getRandomHero(this.nameGenerator.generate()));
	}

	/* (non-Javadoc)
	 * @see TWLSlick.TWLStateBasedGame#getThemeURL()
	 */
	@Override
	protected URL getThemeURL() {
		return ResourceLoader.getResource("/res/default/simple.xml");
	}

}