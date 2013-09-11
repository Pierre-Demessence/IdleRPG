/*
 * Author : Pierre
 * Last Update : 11 sept. 2013 - 16:36:21
 */
package character;

import item.Item;

import org.apache.commons.collections4.BoundedCollection;
import org.apache.commons.collections4.bag.HashBag;

// TODO: Auto-generated Javadoc
/**
 * The Class Inventory.
 */
public class Inventory extends HashBag<Item> implements BoundedCollection<Item> {

	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= 1L;

	/**
	 * Instantiates a new inventory.
	 */
	public Inventory() {
		super();
	}

	/* (non-Javadoc)
	 * @see org.apache.commons.collections4.BoundedCollection#isFull()
	 */
	@Override
	public boolean isFull() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.apache.commons.collections4.BoundedCollection#maxSize()
	 */
	@Override
	public int maxSize() {
		// TODO Auto-generated method stub
		return 0;
	}

}
