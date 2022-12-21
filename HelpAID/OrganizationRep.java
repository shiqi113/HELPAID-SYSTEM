/**
 * Tan Shi QI
 * B1901264
 * 10/04/2022
 * 
 */
package HelpAID;

/**
 * Concrete subclass for User, with additional attributes
 * job title, and organization.
 * @author KokChyeHock
 * Last modified:
 *
 */
public class OrganizationRep extends User {
	private String jobTitle;
	private Organization organization;
	
	public OrganizationRep(String username,  
		String fullname, String email, String mobileNo, 
		String jobTitle, Organization organization) {
		super(username, fullname, email, mobileNo);
		setJobTitle(jobTitle);
		setOrganization(organization);
	}

	/**
	 * @return the jobTitle
	 */
	public String getJobTitle() {
		return jobTitle;
	}

	/**
	 * @param jobTitle the jobTitle to set
	 */
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	/**
	 * @return the organization
	 */
	public Organization getOrganization() {
		return organization;
	}

	/**
	 * @param organization the organization to set
	 */
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public String toString() {
		return String.format("%s\n  Job title: %s", 
			super.toString(), getJobTitle());
	}
}
