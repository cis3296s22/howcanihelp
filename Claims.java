/**
 * 
 */
package HowCanIHelp;

import java.util.Calendar;

/**
 * @author dweintraub
 *
 */
public class Claims {

	/**
	 * The Claims class holds information about donation-request matches that have
	 * been made in the How Can I Help? app.
	 */

	// Declare variables
	private long donorID;
	private long recipientID;
	private long donatedItemID;
	private long receivedItemID;
	private long claimQuantity;

	/**
	 * Constructor accepts the donor and recipient's ID numbers, as well as the
	 * donation item ID and recipient item ID; it also generates a time stamp when
	 * it is called.
	 * <p>
	 * 
	 * @param donorID
	 * @param recipientID
	 * @param donatedItemID
	 * @param receivedItemID
	 * @param claimQuantity
	 */

	Claims(long donorID, long recipientID, long donatedItemID, long receivedItemID, long claimQuantity) {
		this.donorID = donorID;
		this.recipientID = recipientID;
		this.donatedItemID = donatedItemID;
		this.receivedItemID = receivedItemID;
		this.claimQuantity = claimQuantity;

		// Set the date of the offer to the current date
		@SuppressWarnings("unused")
		Calendar matchDate = Calendar.getInstance();
	}

	/**
	 * The getDonorID method returns the donor ID number.
	 * <p>
	 * 
	 * @return the donorID
	 */
	public long getDonorID() {
		return donorID;
	}

	/**
	 * The setDonorID method allows the user to change the donor ID saved in the
	 * claim.
	 * <p>
	 * 
	 * @param donorID the donorID to set
	 */
	public void setDonorID(long donorID) {
		this.donorID = donorID;
	}

	/**
	 * The getRecipientID returns the recipient ID number.
	 * <p>
	 * 
	 * @return the recipientID
	 */
	public long getRecipientID() {
		return recipientID;
	}

	/**
	 * The setRecipientID allows the user to change the recipient ID number saved in
	 * the claim.
	 * <p>
	 * 
	 * @param recipientID the recipientID to set
	 */
	public void setRecipientID(long recipientID) {
		this.recipientID = recipientID;
	}

	/**
	 * The getDonatedItemID method returns the item ID
	 * <p>
	 * 
	 * @return the donatedItemID
	 */
	public long getDonatedItemID() {
		return donatedItemID;
	}

	/**
	 * The setDonatedItemID method allows the user to change the item ID saved in
	 * the claims object.
	 * <p>
	 * 
	 * @param donatedItemID the donatedItemID to set
	 */
	public void setDonatedItemID(long donatedItemID) {
		this.donatedItemID = donatedItemID;
	}

	/**
	 * The getReceivedItemID method returns the item ID for the
	 * <p>
	 * 
	 * @return the receivedItemID
	 */
	public long getReceivedItemID() {
		return receivedItemID;
	}

	/**
	 * The setReceivedItemID method allows the user to change the received item's ID
	 * saved in the claim.
	 * <p>
	 * 
	 * @param receivedItemID the received item's ID to set
	 */
	public void setReceivedItemID(long receivedItemID) {
		this.receivedItemID = receivedItemID;
	}

	/**
	 * The getClaimQuantity method returns the quantity of the item claimed
	 * <p>
	 * 
	 * @return the claim quantity
	 */
	public long claimQuantity() {
		return claimQuantity;
	}

	/**
	 * The setClaimQuantity method allows the user to set the quantity of the item
	 * claimed.
	 * <p>
	 * 
	 * @param claimQuantity the quantity of item claimed to set
	 */
	public void setClaimQuantity(long claimQuantity) {
		this.claimQuantity = claimQuantity;
	}

	/**
	 * The toString method returns the claim object as a String.
	 * <p>
	 * 
	 * @return String
	 */

	@Override
	public String toString() {
		return "Claims [donor ID = " + donorID + ", recipient ID = " + recipientID + ", donated item ID = "
				+ donatedItemID + ", received item ID = " + receivedItemID + ", claim quantity = " + claimQuantity
				+ "]";
	}

}
