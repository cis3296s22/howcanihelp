/**
 * 
 */
package HowCanIHelp;

/**
 * Project: How Can I Help? application Authors: Patrick Moran, Hamsa Nandana
 * Shaik, Diane Weintraub
 * 
 * Class purpose: The AppUser class stores the identifying information for
 * individuals and/or organizations that are registered with the How Can I Help?
 * app. Date created: 14 April 2020 Programmer(s): Patrick Moran & Diane
 * Weintraub
 * 
 * Revision: 14 Apr 2020, Moran, example explanation of what was changed in the
 * code Revision: 20 Apr 2020, Weintraub, remove abstract class and add fields
 * Revision: N/A
 * 
 */
public class AppUser {

	// Declare variables
	protected String email;
	protected String password;
	protected static long userCount = 0;
	protected long userIDNumber;
	protected String firstName;
	protected String lastName;
	protected String organizationName;
	protected long houseNumber;
	protected String streetName;
	protected String city;
	protected String state;
	protected long zipCode;
	protected String phoneNumber;

	/**
	 * Constructor for organizational users sets the organization's identifying
	 * information: email of a contact, a password, ID number, name of the
	 * organization, first name of contact, last name of contact, address of the
	 * organization, and contact's phone number.
	 * <p>
	 * 
	 * @param email
	 * @param password
	 * @param organizationName
	 * @param firstName
	 * @param lastName
	 * @param houseNumber
	 * @param streetName
	 * @param city
	 * @param state
	 * @param zipCode
	 * @param phoneNumber
	 */

	public AppUser(String email, String password, String organizationName, String firstName, String lastName,
			long houseNumber, String streetName, String city, String state, long zipCode, String phoneNumber) {
		this.email = email;
		this.password = password;
		this.organizationName = organizationName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.houseNumber = houseNumber;
		this.streetName = streetName;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.phoneNumber = phoneNumber;

		// Increment static field userCount each time an AppUser object is created, then
		// set that as the user ID
		userCount++;
		userIDNumber = userCount;
	}

	//////////////////////////////////////////// get Methods
	//////////////////////////////////////////// ////////////////////////////////////////////

	/**
	 * The getEmail method returns the user's email.
	 * <p>
	 * 
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * The getPassword method returns the password.
	 * <p>
	 * 
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * The getidNumber method returns the user's ID number.
	 * <p>
	 * 
	 * @return the ID number
	 */
	public long getIDNumber() {
		return userIDNumber;
	}

	/**
	 * The getOrganizationName method returns the password.
	 * <p>
	 * 
	 * @return the name of the organization
	 */
	public String getOrganizationName() {
		return organizationName;
	}

	/**
	 * The getFirstName method returns the password.
	 * <p>
	 * 
	 * @return the first name of the user or contact
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * The getLastName method returns the password.
	 * <p>
	 * 
	 * @return the last name of the user or contact
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * The getHouseNumber method returns the user's house number.
	 * <p>
	 * 
	 * @return the house number
	 */
	public long getHouseNumber() {
		return houseNumber;
	}

	/**
	 * The getStreetName method returns the user's street name.
	 * <p>
	 * 
	 * @return the street name
	 */
	public String getStreetName() {
		return streetName;
	}

	/**
	 * The getCity method returns the user's city.
	 * <p>
	 * 
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * The getState method returns the user's state.
	 * <p>
	 * 
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * The getZipCode method returns the user's zip code.
	 * <p>
	 * 
	 * @return the zip code
	 */
	public long getZipCode() {
		return zipCode;
	}

	/**
	 * The getPhoneNumber method returns the user's phone number.
	 * <p>
	 * 
	 * @return the phone number
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	//////////////////////////////////////////// set Methods
	//////////////////////////////////////////// ////////////////////////////////////////////

	/**
	 * The setEmail method allows the user to change the email.
	 * <p>
	 * 
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * The setPassword method allows the user to change the password.
	 * <p>
	 * 
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * The setIDNumber method allows the user to change the user ID number.
	 * <p>
	 * 
	 * @param idNumber the ID number to set
	 */
	public void setIDNumber(long idNumber) {
		this.userIDNumber = idNumber;
	}

	/**
	 * The setOrganizationName method allows the user to change the name of the
	 * organization.
	 * <p>
	 * 
	 * @param organizationName the name of the organization
	 */
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	/**
	 * The setFirstName method allows the user to change the first name.
	 * <p>
	 * 
	 * @param firstName the first name of the user or contact
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * The setLastName method allows the user to change the last name.
	 * <p>
	 * 
	 * @param lastName the last name of the user or contact
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * The setHouseNumber method allows the user to change the house number.
	 * <p>
	 * 
	 * @param the house number
	 */
	public void getHouseNumber(long houseNumber) {
		this.houseNumber = houseNumber;
	}

	/**
	 * The setStreetName method allows the user to change the street name.
	 * <p>
	 * 
	 * @param the street name
	 */
	public void getStreetName(String streetName) {
		this.streetName = streetName;
	}

	/**
	 * The setCity method allows the the user to change the city.
	 * <p>
	 * 
	 * @param the city
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * The setState method allows the user to change the state.
	 * <p>
	 * 
	 * @param the state
	 */
	public void getState(String state) {
		this.state = state;
	}

	/**
	 * The setZipCode method allows the user to change the zip code.
	 * <p>
	 * 
	 * @param the zip code
	 */
	public void getZipCode(long zipCode) {
		this.zipCode = zipCode;
	}

	/**
	 * The setPhoneNumber method allows the user to set or change the phone number.
	 * <p>
	 * 
	 * @param phoneNumber the phone number to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	//////////////////////////////////////// Additional Methods
	//////////////////////////////////////// /////////////////////////////////////////

	@Override
	public String toString() {
		return "AppUser [email = " + email + ", password = " + password + ", user ID Number = " + userIDNumber
				+ ", first name = " + firstName + ", last name = " + lastName + ", organization name = "
				+ organizationName + ", house number = " + houseNumber + ", street name = " + streetName + ", city = "
				+ city + ", state = " + state + ", zip code = " + zipCode + ", phone number = " + phoneNumber + "]";
	}

}
