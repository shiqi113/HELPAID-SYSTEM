 /* Tan Shi QI
 * B1901264
 * 10/04/2022
 * 
 */
package HelpAID;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Concrete subclass of User, with additional attributes of IDno, 
 * address, household income, organization, and two collections
 * for Document, and Disbursement.
 * @author KokChyeHock
 * Last modified: 
 *
 */
public class Applicant extends User {
	static int nextApplicantID = 1000;
	private String IDno;
	private String address;
	private double householdIncome;
	
	private Organization organization;
	private ArrayList<Document> documents;
	private ArrayList<Disbursement> disbursements;
	
	public Applicant(String username, String fullname,
		String email, String mobileNo, String IDno, String address,
		double householdIncome, Organization organization) {
		super(username, fullname, email, mobileNo);
		setIDno(IDno);
		setAddress(address);
		setHouseholdIncome(householdIncome);
		
		setOrganization(organization);
		setDocuments(new ArrayList<>());
		setDisbursements(new ArrayList<>());
	}

	public Applicant(String fullname,
		String email, String mobileNo, String IDno, String address,
		double householdIncome, Organization organization) {
		this("AP" + nextApplicantID++, fullname, email, mobileNo, 
			IDno, address, householdIncome, organization);
	}
	
	public void addDocument(Document document) {
		getDocuments().add(document);
	}
	
	public void addDisbursement(Disbursement disbursement) {
		getDisbursements().add(disbursement);
	}
	/**
	 * @return the iDno
	 */
	public String getIDno() {
		return IDno;
	}

	/**
	 * @param iDno the iDno to set
	 */
	public void setIDno(String iDno) {
		IDno = iDno;
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
	 * @return the householdIncome
	 */
	public double getHouseholdIncome() {
		return householdIncome;
	}

	/**
	 * @param householdIncome the householdIncome to set
	 */
	public void setHouseholdIncome(double householdIncome) {
		this.householdIncome = householdIncome;
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

	/**
	 * @return the documents
	 */
	public ArrayList<Document> getDocuments() {
		return documents;
	}

	/**
	 * @param documents the documents to set
	 */
	public void setDocuments(ArrayList<Document> documents) {
		this.documents = documents;
	}

	/**
	 * @return the disbursements
	 */
	public ArrayList<Disbursement> getDisbursements() {
		return disbursements;
	}

	/**
	 * @param disbursements the disbursements to set
	 */
	public void setDisbursements(ArrayList<Disbursement> disbursements) {
		this.disbursements = disbursements;
	}
	
	public String toString() {
		String msg = String.format("%s\n  ID no: %s at %s, with income %.2f", 
			super.toString(), getIDno(), getAddress(), getHouseholdIncome());
		if (numOfDisbursements() != 0)
			msg += "\n  has received " + numOfDisbursements() + 
				" disbursements.";
		return msg;
	}
	
	public String allDocuments() {
		return getDocuments().stream()
			.map(Document::toString)
			.collect(Collectors.joining("\n"));
	}
	
	public String allDisbursements() {
		return getDisbursements().stream()
			.map(Disbursement::toString)
			.collect(Collectors.joining("\n"));
	}
	
	public int numOfDisbursements() {
		return getDisbursements().size();
	}
}
