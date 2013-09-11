/*
 * Author : Pierre
 * Last Update : 11 sept. 2013 - 16:36:23
 */
package item;

import java.util.Comparator;

import database.items.Type;

// TODO: Auto-generated Javadoc
/**
 * The Class Item.
 */
public abstract class Item {

	/** The Constant VALUE_ORDER. */
	public static final Comparator<Item>	VALUE_ORDER	= new Comparator<Item>() {
															@Override
															public int compare(final Item o1, final Item o2) {
																return o1.getValue() - o2.getValue();
															}
														};

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		final Item item = (Item) obj;
		final boolean res = this.getName().equals(item.getName());
		return res;
	}

	/**
	 * Gets the name.
	 * 
	 * @return the name
	 */
	public abstract String getName();

	/**
	 * Gets the type.
	 * 
	 * @return the type
	 */
	public abstract Type getType();

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

}
