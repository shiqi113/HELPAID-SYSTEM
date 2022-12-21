 /* Tan Shi QI
 * B1901264
 * 10/04/2022
 *  
 */
package HelpAID;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * Class that represents appeal for help, with information
 * appealID,from and to date, description of the appeal,
 * organization, and outcome. There are two collections for 
 * Contribution and Disbursement.
 * @author KokChyeHock
 * Last modified: 
 *
 */
public class Appeal implements Serializable {
	static int nextAppealID = 1000;
	private String appealID;
	private LocalDate fromDate;
	private LocalDate toDate;
	private String description;
	private String outcome; // PENDING, PARTIAL, COMPLETED, OVERDRAFT
	
	private Organization organization;
	private ArrayList<Contribution> contributions;
	private ArrayList<Disbursement> disbursements;
	
	public Appeal(LocalDate fromDate, LocalDate toDate, String 
		description, Organization organization) {
		setAppealID("A" + nextAppealID++);
		setFromDate(fromDate);
		setToDate(toDate);
		setDescription(description);
		setOrganization(organization);
		
		setContributions(new ArrayList<>());
		setDisbursements(new ArrayList<>());
		setOutcome("PENDING");
	}
	
	public Appeal() {
		this(null, null, null, null);
	}

	/**
	 * @return the nextAppealID
	 */
	public static int getNextAppealID() {
		return nextAppealID;
	}

	/**
	 * @param nextAppealID the nextAppealID to set
	 */
	public static void setNextAppealID(int nextAppealID) {
		Appeal.nextAppealID = nextAppealID;
	}

	/**
	 * @return the appealID
	 */
	public String getAppealID() {
		return appealID;
	}

	/**
	 * @param appealID the appealID to set
	 */
	public void setAppealID(String appealID) {
		this.appealID = appealID;
	}

	/**
	 * @return the fromDate
	 */
	public LocalDate getFromDate() {
		return fromDate;
	}

	/**
	 * @param fromDate the fromDate to set
	 */
	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}

	/**
	 * @return the toDate
	 */
	public LocalDate getToDate() {
		return toDate;
	}

	/**
	 * @param toDate the toDate to set
	 */
	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the outcome
	 */
	public String getOutcome() {
		return outcome;
	}

	/**
	 * @param outcome the outcome to set
	 */
	public void setOutcome(String outcome) {
		this.outcome = outcome;
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

	public String getOrganizationName() {
		return getOrganization().getOrgName();
	}
	/**
	 * @return the contributions
	 */
	public ArrayList<Contribution> getContributions() {
		return contributions;
	}

	/**
	 * @param contributions the contributions to set
	 */
	public void setContributions(ArrayList<Contribution> contributions) {
		this.contributions = contributions;
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

	public boolean addDisbursement(Disbursement disbursement) {
		return getDisbursements().add(disbursement);
		
	}
	
	public boolean addContribution(Contribution contribution) {
		return getContributions().add(contribution);
	}
	
	public double totalContributions() {
		return getContributions().stream()
			.filter(c -> c instanceof CashDonation)
			.map(c -> (CashDonation) c)
			.mapToDouble(CashDonation::getAmount)
			.sum() +
			getContributions().stream()
			.filter(c -> c instanceof Goods)
			.map(c -> (Goods) c)
			.mapToDouble(Goods::getEstimatedValue)
			.sum();
	}
	
	public double totalDisbursements() {
		return getDisbursements().stream()
				.mapToDouble(Disbursement::getCashAmount)
				.sum();
	}
	
	public boolean hasContributions() {
		return getContributions().size() != 0;
	}
	
	public boolean hasDisbursements() {
		return getDisbursements().size() != 0;
	}
	
	public String allContributions() {
		return getContributions().stream()
			.map(Contribution::toString)
			.collect(Collectors.joining("\n"));
	}
	public ArrayList<Contribution> allContributionsWithAppeal() {
		return (ArrayList<Contribution>)
				getContributions().stream()
			.collect(Collectors.toList());
	}
	public String allDisbursements() {
		return getDisbursements().stream()
			.map(Disbursement::toString)
			.collect(Collectors.joining("\n"));
	}
	
	public String summaryOfAppeal() {
		return String.format("Appeal [%s] for %s, from %s to %s",
			getAppealID(), getDescription(), getFromDate(), getToDate());
	}
	
	public String toString() {
		String msg = String.format("Appeal [%s] for %s from %s to %s, " +
			"with status [%s]", getAppealID(), getDescription(), 
			getFromDate(), getToDate(), getOutcome());
		if (hasContributions())
			msg += String.format("\n  has contributions, total %.2f",
				totalContributions());
		if (hasDisbursements())
			msg += String.format("\n  and has already disbursed %.2f",
				totalDisbursements());
		return msg;
	}
	
	public int numOfContributions() {
		return getContributions().size();
	}
	
	public int numOfDisbursements() {
		return getDisbursements().size();
	}
	
	public boolean isPastAppeal() {
		return getToDate().isBefore(LocalDate.now());
	}
	
	public boolean isCurrentAppeal() {
		return !isPastAppeal();
	}
}