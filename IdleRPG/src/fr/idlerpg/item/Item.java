package fr.idlerpg.item;

import java.util.Comparator;

import fr.idlerpg.database.items.ItemType;

/**
 * The Class Item.
 */
public abstract class Item implements Comparable<Item>, Cloneable {

	/** The Constant VALUE_ORDER. */
	public static final Comparator<Item>	VALUE_ORDER	= new Comparator<Item>() {
															@Override
															public int compare(final Item o1, final Item o2) {
																return o1.getValue() - o2.getValue();
															}
														};

	@Override
	protected abstract Object clone();

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(final Item o) {
		return this.getName().compareTo(o.getName());
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if( ( obj == null ) || ! ( obj instanceof Item ) )
			return false;
		final Item item = (Item) obj;
		final boolean res = this.getName().equals(item.getName());
		return res;
	}

	/**
	 * Gets the name.
	 * 
	 * @return the name
	 */
	public String getName() {
		return this.getBaseName();
	}

	/**
	 * Gets the type.
	 * 
	 * @return the type
	 */
	public abstract ItemType getType();

	/**
	 * Gets the value.
	 * 
	 * @return the value
	 */
	public abstract int getValue();

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return this.getName().hashCode();
	}

	/**
	 * Gets the base name.
	 * 
	 * @return the base name
	 */
	protected abstract String getBaseName();

}
