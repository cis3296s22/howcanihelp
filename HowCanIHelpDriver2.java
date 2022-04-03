package HowCanIHelp;

/**
 * The HowCanIHelpDriver program runs the How Can I Help? app, collecting donor 
 * and recipient information.
 * <p>
 * @author dweintraub
 *
 */

//Needed for the ArrayList class
import java.util.ArrayList;

public class HowCanIHelpDriver2 {

	/**
	 * The main method calls the AppUser, DonorItem, RecipientItem, and Claims
	 * classes
	 * 
	 * <p>
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// Create and instantiate new AppUser objects
		AppUser user1 = new AppUser("dweintra@gmail.com", "dwein1", "Kohelet", "Diane", "Weintraub", 223,
				"Highland Avenue", "Merion Station", "PA", 19066, "2674718000");
		AppUser user2 = new AppUser("pmoran8994@students.mc3.edu", "pmoran", null, "Patrick", "Moran", 160,
				"N. Gulph Road", "King of Prussia", "PA", 19406, "2157453000");
		AppUser user3 = new AppUser("hshaik6725@students.mc3.edu", "hamsa1", "Montco", "Hamsa", "Shaik", 101,
				"Sibley Ave", "Ardmore", "PA", 19003, "4103567654");

		// Create ArrayList and add the car objects to the list
		ArrayList<AppUser> userList = new ArrayList<AppUser>();
		userList.add(user1);
		userList.add(user2);
		userList.add(user3);

		for (int i = 0; i < userList.size(); i++) {
			System.out.println(userList.get(i).toString());
			System.out.println(); // Insert blank line for readability
		}
	}

}
