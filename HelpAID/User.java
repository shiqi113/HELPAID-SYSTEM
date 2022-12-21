package HelpAID;

import java.io.Serializable;

/**
 * Abstract class for representing a user, with username,
 * full name, password, email, and mobileno
 * @author KokChyeHock
 * Last modified: 
 *
 */
public abstract class User implements Comparable<User>, Serializable {
	private String username;
	private String password;
	private String fullname;
	private String email;
	private String mobileNo;
	
	/**
	 * Constructor for User.
	 * @param username User's unique username
	 * @param password User's login password
	 * @param fullname User's full name
	 * @param email User's email address
	 * @param mobileNo User's mobile number
	 */
	public User(String username, String fullname, String email, 
		String mobileNo) {
		setUsername(username);
		setFullname(fullname);
		setPassword(getDefaultPassword());
		setEmail(email);
		setMobileNo(mobileNo);
	}
	
	private String getDefaultPassword() {
		return "Welcome123" + getFullname().charAt(0);
	}
	
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the fullname
	 */
	public String getFullname() {
		return fullname;
	}
	/**
	 * @param fullname the fullname to set
	 */
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the mobileNo
	 */
	public String getMobileNo() {
		return mobileNo;
	}
	/**
	 * @param mobileNo the mobileNo to set
	 */
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	@Override
	/**
	 * Checking for equality of two User objects.
	 * Two users are deemed to be equal if their usernames
	 * are the same
	 * @param obj User object to check for equality
	 * #return true if both users are the same, false otherwise
	 */
	public boolean equals(Object obj) {
		if (obj instanceof User) {
			return getUsername().equalsIgnoreCase(
				((User) obj).getUsername());
		}
		return false;
	}
	

	@Override
	/**
	 * Returns a string representation of a user
	 * @return A string representing details of a user
	 */
	public String toString() {
		return String.format("%s: %s (username: %s, password: %s)" +
			"\n  email: %s mobile no: %s", getClass().getSimpleName(), 
			getFullname(), getUsername(), getPassword(), getEmail(), 
			getMobileNo());
	}

	/**
	 * Compare two uses according to their full names, lexicographically.
	 * This is different from the equals method, which check username
	 * for equality.
	 * @return the value 0 if name of parametric user is equal to this 
	 * user's name; a value greater than 0 if name of parametric 
	 * user is lexicographically less than the name of this user; and a 
	 * value less than 0 if name of parametric user is lexicographically
	 * greater than name of this user.
	 */
	public int compareTo(User rhs) {
		return getFullname().compareToIgnoreCase(rhs.getFullname());
	}
}
