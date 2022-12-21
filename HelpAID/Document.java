/**
 * Tan Shi QI
 * B1901264
 * 10/04/2022
 * 
 */
package HelpAID;

import java.io.Serializable;

/**
 * Class for representing documents submitted by applicant
 * to prove that one is eligible for aid.
 * @author KokChyeHock
 * Last modified:
 *
 */
public class Document implements Serializable {
	static int nextDocumentID = 1000;
	private String documentID;
	private String filename;
	private String description;
	
	public Document(String filename, String description) {
		setDocumentID("D" + nextDocumentID++);
		setFilename(filename);
		setDescription(description);
	}
	
	/**
	 * @return the nextDocumentID
	 */
	public static int getNextDocumentID() {
		return nextDocumentID;
	}
	/**
	 * @param nextDocumentID the nextDocumentID to set
	 */
	public static void setNextDocumentID(int nextDocumentID) {
		Document.nextDocumentID = nextDocumentID;
	}
	/**
	 * @return the documentID
	 */
	public String getDocumentID() {
		return documentID;
	}
	/**
	 * @param documentID the documentID to set
	 */
	public void setDocumentID(String documentID) {
		this.documentID = documentID;
	}
	/**
	 * @return the filename
	 */
	public String getFilename() {
		return filename;
	}
	/**
	 * @param filename the filename to set
	 */
	public void setFilename(String filename) {
		this.filename = filename;
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
	
	public String toString() {
		return String.format("%s: %s: %s", getDocumentID(), 
			getFilename(), getDescription());
	}
}
