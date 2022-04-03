/**
 * 
 */
package HowCanIHelp;

import java.util.Calendar;

/**
 * @author dweintraub
 *
 */
public abstract class Item {

	/**
	 * The Item class is an abstract class that holds general data about goods or
	 * services that have been inputed into the How Can I Help? database. Classes
	 * representing specific types of items should inherit from this class.
	 */

	// Declare variables
	protected long userID;
	protected static long itemCount = 0;
	protected long itemID;
	protected String itemDescription;
	protected Calendar inputDate;

	/**
	 * Constructor sets the user ID and item description and increments the static
	 * field itemCount in order to create a unique item ID for each item created. It
	 * also creates a time stamp to record the date of the item's creation.
	 * <p>
	 * 
	 * @param userID
	 * @param itemDescription
	 */
	protected Item(long userID, String itemDescription) {
		// Increment static field itemCount each time an item object is created, then
		// set that as the item ID
		itemCount++;
		itemID = itemCount;

		this.userID = userID;
		this.itemDescription = itemDescription;

		// Set the date of the offer to the current date
		inputDate = Calendar.getInstance();
	}

	/**
	 * The getUserID method returns the user ID.
	 * <p>
	 * 
	 * @return the user ID
	 */
	public long getUserID() {
		return userID;
	}

	/**
	 * The setUserID method allows the user to change the user ID.
	 * <p>
	 * 
	 * @param userID the userID to set
	 */
	public void setUserID(long userID) {
		this.userID = userID;
	}

	/**
	 * The getItemID method returns the item ID number.
	 * <p>
	 * 
	 * @return the itemID
	 */
	public long getItemID() {
		return itemID;
	}

	/**
	 * The getItemDescription method returns the item description.
	 * <p>
	 * 
	 * @return the itemDescription
	 */
	public String getItemDescription() {
		return itemDescription;
	}

	/**
	 * The setItemDescription allows the user to change the item description.
	 * <p>
	 * 
	 * @param itemDescription the itemDescription to set
	 */
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	/**
	 * The getRequestDate method returns the date the request was made.
	 * <p>
	 * 
	 * @return the requestDate
	 */
	public Calendar getRequestDate() {
		return inputDate;
	}

	@Override
	public String toString() {
		return "Item [user ID = " + userID + ", item ID = " + itemID + ", item description = " + itemDescription + "]";
	}

}
