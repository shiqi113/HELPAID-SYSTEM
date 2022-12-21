/**
 * Tan Shi QI
 * B1901264
 * 10/04/2022
 * 
 */
package HelpAID;
/**
 * Concrete subclass of Contribution, with additional attributes
 * of description of goods, and its estimated value.
 * @author KokChyeHock
 * Last modified: 
 */
public class Goods extends Contribution {
	private String description;
	private double estimatedValue;
	
	public Goods(Appeal appeal, String description, 
		double estimatedValue) {
		super(appeal);
		setDescription(description);
		setEstimatedValue(estimatedValue);
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
	 * @return the estimatedValue
	 */
	public double getEstimatedValue() {
		return estimatedValue;
	}

	/**
	 * @param estimatedValue the estimatedValue to set
	 */
	public void setEstimatedValue(double estimatedValue) {
		this.estimatedValue = estimatedValue;
	}

	public String toString() {
		return String.format("%s\n  %s worth %4.2f", 
			super.toString(), getDescription(), 
			getEstimatedValue());
	}
}
