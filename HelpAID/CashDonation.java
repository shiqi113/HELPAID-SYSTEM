 /* Tan Shi QI
 * B1901264
 * 10/04/2022
 * 
 */
package HelpAID;

/**
 * Concrete subclass of Contribution, with addition attributes
 * of amount, payment channel, and reference number.
 * @author KokChyeHock
 * Last modified: 
 */
public class CashDonation extends Contribution {
	private double amount;
	private String paymentChannel;
	private String referenceNo;
	
	public CashDonation(Appeal appeal, double amount, String paymentChannel,
		String referenceNo) {
		super(appeal);
		setAmount(amount);
		setPaymentChannel(paymentChannel);
		setReferenceNo(referenceNo);
	}
	
	/**
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}
	/**
	 * @return the paymentChannel
	 */
	public String getPaymentChannel() {
		return paymentChannel;
	}
	/**
	 * @param paymentChannel the paymentChannel to set
	 */
	public void setPaymentChannel(String paymentChannel) {
		this.paymentChannel = paymentChannel;
	}
	/**
	 * @return the referenceNo
	 */
	public String getReferenceNo() {
		return referenceNo;
	}
	/**
	 * @param referenceNo the referenceNo to set
	 */
	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}
	
	public String toString() {
		return String.format("%s\n  Amount: %.2f paid via %s " + 
			"(Ref. no: %s)", super.toString(), getAmount(), 
			getPaymentChannel(), getReferenceNo());
	}
}
