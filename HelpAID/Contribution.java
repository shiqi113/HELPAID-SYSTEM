 /* Tan Shi QI
 * B1901264
 * 10/04/2022
 *
 */
package HelpAID;
import java.io.Serializable;
import java.time.LocalDate;
/**
 * Abstract class to represent a contribution for aid appeal.
 * Every contribution will have date received, and ID.
 * @author KokChyeHock
 *
 */
public abstract class Contribution implements Serializable {
	static int nextContributionID = 1000;
	private LocalDate receivedDate;
	private String contributionID;
	
	private Appeal appeal;
	
	public Contribution(Appeal appeal) {
		setContributionID("C" + nextContributionID++);
		setReceivedDate(LocalDate.now());
		
		setAppeal(appeal);
	}
	
	/**
	 * @return the nextContributionID
	 */
	public static int getNextContributionID() {
		return nextContributionID;
	}
	/**
	 * @param nextContributionID the nextContributionID to set
	 */
	public static void setNextContributionID(int nextContributionID) {
		Contribution.nextContributionID = nextContributionID;
	}
	/**
	 * @return the receivedDate
	 */
	public LocalDate getReceivedDate() {
		return receivedDate;
	}
	/**
	 * @param receivedDate the receivedDate to set
	 */
	public void setReceivedDate(LocalDate receivedDate) {
		this.receivedDate = receivedDate;
	}
	/**
	 * @return the contributionID
	 */
	public String getContributionID() {
		return contributionID;
	}
	/**
	 * @param contributionID the contributionID to set
	 */
	public void setContributionID(String contributionID) {
		this.contributionID = contributionID;
	}

	/**
	 * @return the appeal
	 */
	public Appeal getAppeal() {
		return appeal;
	}
	public String getAppealID() {
		return getAppeal().getAppealID();
	}
	/**
	 * @param appeal the appeal to set
	 */
	public void setAppeal(Appeal appeal) {
		this.appeal = appeal;
	}
	
	public String toString() {
		return String.format("%s %s on %s for Appeal %s", 
			getContributionID(), getClass().getSimpleName(), 
			getReceivedDate().toString(), getAppeal().getAppealID());
	}
}
