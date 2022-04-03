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
public class DonorItem extends Item {
	/**
	 * The DonorItem class holds the attributes of an item that is being offered as
	 * a donation through the How Can I Help? app. It inherits from the Item
	 * superclass.
	 */

	// Declare variables
	protected long numberRemaining;

	/**
	 * Constructor for donations of goods or services.
	 * <p>
	 * 
	 * @param userID
	 * @param String itemDescription
	 * @param long   numberRemaining
	 */

	public DonorItem(long userID, String itemDescription, long numberRemaining) {

		// Call the superclass constructor
		super(userID, itemDescription);

		// Set the item needed, item amount, and if it is an ongoing request
		this.numberRemaining = numberRemaining;
	}

	/**
	 * The getnumberRemaining method returns the amount of the item or service being
	 * offered.
	 * <p>
	 * 
	 * @return the donation amount
	 */
	public long getNumberRemaining() {
		return numberRemaining;
	}

	/**
	 * the setNumberRemaining method allows the user to change the amount of the
	 * item or service being offered.
	 * <p>
	 * 
	 * @param donationAmount the donation amount to set
	 */
	public void setNumberRemaining(long numberRemaining) {
		this.numberRemaining = numberRemaining;
	}

	/**
	 * The toString method returns a String reporting the item offered, amount
	 * offered, whether it is an ongoing or one-time offer, and date of offer.
	 * <p>
	 * 
	 * @return String
	 */

	@Override
	public String toString() {
		return "Donor Item [item description = " + itemDescription + "number offered/remaining = " + numberRemaining
				+ ", user ID = " + userID + ", item ID = " + itemID + "]";
	}

	/**
	 * The stillAble method generates a message to the user a set amount of time
	 * after their initial offer of goods or services to ask whether the offer is
	 * still good. If the user responds that the item is no longer available, the
	 * method removes the item from the How Can I Help? database.
	 * <p>
	 * 
	 * @param donationDate the date the offer was made
	 */

	/**
	 * The itemClaimed method adjusts the number saved in the numberRemaining
	 * variable in order to update the database with the correct number of items
	 * remaining after each donation.
	 * <p>
	 * 
	 * @param claimAmount
	 */

	public long itemClaimed(long claimAmount) {

		// Subtract the number of items claimed from the number remaining
		numberRemaining -= claimAmount;

		return numberRemaining;
	}

}
