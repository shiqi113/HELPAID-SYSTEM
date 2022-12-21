/**
 * Tan Shi QI
 * B1901264
 * 10/04/2022
 * 
 */
package HelpAID;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Class that represents disbursement of aid contribution to applicant.
 * Information include date aid is disbursed, the amount, and any goods
 * disbursed, plus applicant that received the disbursement.
 * @author KokChyeHock
 * Last modified:
 * 
 */
public class Disbursement implements Serializable {
	private LocalDate disbursementDate;
	private double cashAmount;
	private String goodsDisbursed;
	
	private Applicant applicant;
	
	public Disbursement(LocalDate disbursementDate, double cashAmount,
		String goodsDisbursed, Applicant applicant) {
		setDisbursementDate(disbursementDate);
		setCashAmount(cashAmount);
		setGoodsDisbursed(goodsDisbursed);
		setApplicant(applicant);
	}
	
	/**
	 * @return the disbursementDate
	 */
	public LocalDate getDisbursementDate() {
		return disbursementDate;
	}
	/**
	 * @param disbursementDate the disbursementDate to set
	 */
	public void setDisbursementDate(LocalDate disbursementDate) {
		this.disbursementDate = disbursementDate;
	}
	/**
	 * @return the cashAmount
	 */
	public double getCashAmount() {
		return cashAmount;
	}
	/**
	 * @param cashAmount the cashAmount to set
	 */
	public void setCashAmount(double cashAmount) {
		this.cashAmount = cashAmount;
	}
	/**
	 * @return the goodsDisbursed
	 */
	public String getGoodsDisbursed() {
		return goodsDisbursed;
	}
	/**
	 * @param goodsDisbursed the goodsDisbursed to set
	 */
	public void setGoodsDisbursed(String goodsDisbursed) {
		this.goodsDisbursed = goodsDisbursed;
	}

	/**
	 * @return the applicant
	 */
	public Applicant getApplicant() {
		return applicant;
	}

	/**
	 * @param applicant the applicant to set
	 */
	public void setApplicant(Applicant applicant) {
		this.applicant = applicant;
	}
	
	public String toString() {
		return String.format("Disbursement on %s on %s, worth %.2f", 
			getDisbursementDate(), getGoodsDisbursed(), getCashAmount());
	}
}
