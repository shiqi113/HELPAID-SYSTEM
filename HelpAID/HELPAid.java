/**
 * Tan Shi QI
 * B1901264
 * 10/04/2022
 * 
 */
package HelpAID;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The controller class for the HELPAid system. It contains
 * collections of Organization, Appeal, and User. 
 * @author KokChyeHock
 * Last modified:
 * 
 */
public class HELPAid implements Serializable {
	private ArrayList<Organization> organizations;
	private ArrayList<Appeal> appeals;
	private ArrayList<User> users;
	
	public HELPAid() {
		setOrganizations(new ArrayList<>());
		setAppeals(new ArrayList<>());
		setUsers(new ArrayList<>());
	}

	public boolean addUser(User user) {
		return getUsers().add(user);
	}
	
	public boolean addOrganization(Organization organization) {
		return getOrganizations().add(organization);
	}
	
	public boolean addAppeal(Appeal appeal) {
		return getAppeals().add(appeal);
	}
	
	public User findUser(String username) {
		return getUsers().stream()
			.filter(u -> username.equalsIgnoreCase(u.getUsername()))
			.findFirst()
			.orElse(null);
	}
	
	public boolean validateOrg(String orgName) {
		for (int i = 0; getOrganizations().size() > i; i++) {
			Organization org = getOrganizations().get(i);
			if (orgName.equals(org.getOrgName())) {
				return true;
			}
		}
		return false;
	}
	//check duplicate username
		public boolean validateUser(String username) {
			for (int i = 0; getUsers().size() > i; i++) {
				User user = getUsers().get(i);
				if (username.equals(user.getUsername())) {
					return true;
				}
			}
			return false;
		}
	public Organization findOrg(String orgID) {
		return getOrganizations().stream()
			.filter(o -> orgID.equalsIgnoreCase(o.getOrgID()))
			.findFirst()
			.orElse(null);
	}
	
	public Appeal findAppeal(String appealID) {
		return getAppeals().stream()
			.filter(a -> appealID.equalsIgnoreCase(a.getAppealID()))
			.findFirst()
			.orElse(null);
	}
	
	public String allUsers() {
		return getUsers().stream()
			.map(User::toString)
			.collect(Collectors.joining("\n"));
	}
	
	public String allAppeals() {
		return getAppeals().stream()
			.map(Appeal::toString)
			.collect(Collectors.joining("\n"));	
	}
	
	public String allOrganizations() {
		return getOrganizations().stream()
			.map(Organization::toString)
			.collect(Collectors.joining("\n"));
	}
	
	public List<User> listUsersSortedByFullname() {
		return getUsers().stream()
				.sorted((u1, u2) -> u1.compareTo(u2))
				.collect(Collectors.toList());
	}
	
	public String listSortedAppeals() {
		return getAppeals().stream()
			.sorted(Comparator.comparing(
				Appeal::getOrganizationName)
				.thenComparing(Appeal::getFromDate))
			.map(Appeal::toString)
			.collect(Collectors.joining("\n"));
	}
	public ArrayList<Appeal> listSortedAppealsWithOrg() {
		return (ArrayList<Appeal>) getAppeals().stream()
				.sorted(Comparator.comparing(
						Appeal::getOrganizationName)
						.thenComparing(Appeal::getFromDate))
			.collect(Collectors.toList());
	}
	
	/**
	 * @return the organizations
	 */
	public ArrayList<Organization> getOrganizations() {
		return organizations;
	}

	/**
	 * @param organizations the organizations to set
	 */
	public void setOrganizations(ArrayList<Organization> organizations) {
		this.organizations = organizations;
	}

	/**
	 * @return the appeals
	 */
	public ArrayList<Appeal> getAppeals() {
		return appeals;
	}

	/**
	 * @param appeals the appeals to set
	 */
	public void setAppeals(ArrayList<Appeal> appeals) {
		this.appeals = appeals;
	}

	/**
	 * @return the users
	 */
	public ArrayList<User> getUsers() {
		return users;
	}

	/**
	 * @param users the users to set
	 */
	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}
	
	public int numOfUsers() {
		return getUsers().size();
	}
	
	public int numOfAppeals() {
		return getAppeals().size();
	}
	
	public int numOfOrganizations() {
		return getOrganizations().size();
	}
	
	public ArrayList<Appeal> currentAppealList() {
		return (ArrayList<Appeal>) getAppeals().stream()
			.filter(Appeal::isCurrentAppeal)
			.collect(Collectors.toList());
	}

	public ArrayList<Appeal> pastAppealList() {
		return (ArrayList<Appeal>) getAppeals().stream()
			.filter(Appeal::isPastAppeal)
			.collect(Collectors.toList());
	}
	
	// PRE-CONDITION applicant exists
	public boolean duplicateApplicant(String IDno) {
		Applicant app = getUsers().stream()
			.filter(Applicant.class::isInstance)
			.map(a -> (Applicant) a)
			.filter(a -> a.getIDno().equalsIgnoreCase(IDno))
			.findAny()
			.orElse(null);
		return app != null; 
	}
}