/**
 * 
 */
package HowCanIHelp;

import java.util.Calendar;

/**
 * @author dweintraub
 *
 */
@SuppressWarnings("unused")
public class RecipientItem extends Item {

	/**
	 * The RecipientItem class holds the attributes of an item that is being
	 * requested as a donation through the How Can I Help? app. It inherits from the
	 * Item superclass.
	 */

	/// Declare variables
	protected long numberNeeded;

	/**
	 * Constructor for requests of goods or services.
	 * <p>
	 * 
	 * @param userID
	 * @param String itemDescription
	 * @param long   numberNeeded
	 */

	public RecipientItem(long userID, String itemDescription, long numberNeeded) {

		// Call the superclass constructor
		super(userID, itemDescription);

		// Set the item needed, item amount, and if it is an ongoing request
		this.numberNeeded = numberNeeded;
	}

	/**
	 * The getNumberNeeded method returns the item or service being requested.
	 * <p>
	 * 
	 * @return the amount of the item requested
	 */
	public long getNumberNeeded() {
		return numberNeeded;
	}

	/**
	 * The setNumberNeeded method allows the user to change the item or service
	 * being requested.
	 * <p>
	 * 
	 * @param numberNeeded the amount of the item to set
	 */
	public void setNumberNeeded(long numberNeeded) {
		this.numberNeeded = numberNeeded;
	}

	/**
	 * The toString method returns a String reporting the item requested, amount
	 * requested, whether it is an ongoing or one-time need, and date of request.
	 */

	@Override
	public String toString() {
		return "Recipient Item [item description = " + itemDescription + "number needed = " + numberNeeded
				+ ", user ID = " + userID + ", item ID = " + itemID + "]";
	}

}
