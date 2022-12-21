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
import java.util.stream.Collectors;

/**
 * Class to represent an organization that help to organize
 * or manage aid appeals. Contains three collections of
 * OrganiationRep, Applicant,and Appeal.
 * @author KokChyeHock
 * Last modified: 
 */
public class Organization implements Serializable {
	static int nextOrganizationID = 1000;
	private String orgID;
	private String orgName;
	private String address;
	private ArrayList<OrganizationRep> reps;
	private ArrayList<Applicant> applicants;
	private ArrayList<Appeal> appeals;
	
	public Organization(String orgName, String address) {
		setOrgID("ORG" + nextOrganizationID++);
		setOrgName(orgName);
		setAddress(address);
		setReps(new ArrayList<>());
		setApplicants(new ArrayList<>());
		setAppeals(new ArrayList<>());
	}

	/**
	 * @return the nextOrganizationID
	 */
	public static int getNextOrganizationID() {
		return nextOrganizationID;
	}

	/**
	 * @param nextOrganizationID the nextOrganizationID to set
	 */
	public static void setNextOrganizationID(int nextOrganizationID) {
		Organization.nextOrganizationID = nextOrganizationID;
	}

	/**
	 * @return the ordID
	 */
	public String getOrgID() {
		return orgID;
	}

	/**
	 * @param ordID the ordID to set
	 */
	public void setOrgID(String orgID) {
		this.orgID = orgID;
	}

	/**
	 * @return the orgName
	 */
	public String getOrgName() {
		return orgName;
	}

	/**
	 * @param orgName the orgName to set
	 */
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the reps
	 */
	public ArrayList<OrganizationRep> getReps() {
		return reps;
	}

	/**
	 * @param reps the reps to set
	 */
	public void setReps(ArrayList<OrganizationRep> reps) {
		this.reps = reps;
	}

	/**
	 * @return the applicants
	 */
	public ArrayList<Applicant> getApplicants() {
		return applicants;
	}

	/**
	 * @param applicants the applicants to set
	 */
	public void setApplicants(ArrayList<Applicant> applicants) {
		this.applicants = applicants;
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
	
	public boolean add(OrganizationRep rep) {
		return getReps().add(rep);
	}
	
	public boolean add(Applicant applicant) {
		return getApplicants().add(applicant);
	}
	
	public boolean add(Appeal appeal) {
		return getAppeals().add(appeal);
	}
	
	public OrganizationRep findOrganizationRep(String username) {
		return getReps().stream()
			.filter(r -> username.equalsIgnoreCase(r.getUsername()))
			.findFirst()
			.orElse(null);		
	}

	public Applicant findApplicantByUsername(String username) {
		return getApplicants().stream()
			.filter(r -> username.equalsIgnoreCase(r.getUsername()))
			.findFirst()
			.orElse(null);		
	}

	public Applicant findApplicantByID(String IDno) {
		return getApplicants().stream()
			.filter(app -> IDno.equalsIgnoreCase(app.getIDno()))
			.findFirst()
			.orElse(null);		
	}
	
	public Appeal findAppeal(String appealID) {
		return getAppeals().stream()
			.filter(a -> appealID.equalsIgnoreCase(a.getAppealID()))
			.findFirst()
			.orElse(null);
	}
	public ArrayList<Appeal>currentAppealList() {
		return (ArrayList<Appeal>) getAppeals().stream()
			.filter(Appeal::isCurrentAppeal)
			.collect(Collectors.toList());
	}
	// only organization's appeal
	public String listCurrentAppeals() {
		return getAppeals().stream()
			.filter(Appeal::isCurrentAppeal)
			.map(Appeal::summaryOfAppeal) 
			.collect(Collectors.joining("\n"));
	}
	
	public String allApplicants() {
		return getApplicants().stream()
			.map(Applicant::toString)
			.collect(Collectors.joining("\n"));
	}
	public ArrayList<Applicant> allOrgApplicants() {
		return (ArrayList<Applicant>)
				getApplicants().stream()
			.collect(Collectors.toList());
	}
	
	public int numOfAppeals() {
		return getAppeals().size();
	}
	
	public int numOfApplicants() {
		return getApplicants().size();
	}
	
	public int numOfOrganizationReps() {
		return getReps().size();
	}
	
	public String toString() {
		return String.format("%s (ID: %s) at %s", getOrgName(),
			getOrgID(), getAddress());
	}
	public boolean sameOrgname(String orgName) {
		return getOrgName().equals(orgName);
	}
	
}
