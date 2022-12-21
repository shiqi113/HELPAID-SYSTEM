 /* Tan Shi QI
 * B1901264
 * 10/04/2022
 *  table model of All Org Applicant
 */
package HelpAID;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class AllApplicantWithOrgTM extends AbstractTableModel {
	private HELPAid helpAid; // for storing items
	private List<Applicant> applicant;
	private String[] title = {"Username", "Name", 
			"Phone","email", "ID No", "Address","Income(RM)"};
	private Organization org;
	//only org all applicants
	public AllApplicantWithOrgTM(Organization org)
	{
		this.applicant=org.allOrgApplicants();
	}

	/**
	 * To get the column count 
	 * according to the length of title list
	 */
	public int getColumnCount()
	{
		return title.length;
	}
	/**
	 * To get the row count according to the number of 
	 * users stored in applicant
	 */
	public int getRowCount()
	{
		return this.applicant.size();
	}
	/**
	 * To get the object at a specified row and column
	 */
	public Object getValueAt(int row, int col)
	{
		
				
		switch (col) {
		case 0: 
			return applicant.get(row).getUsername();
			
		case 1:
			return applicant.get(row).getFullname();
			
		case 2:
			return applicant.get(row).getMobileNo();
			
		case 3:
			return applicant.get(row).getEmail();
		case 4:
			return applicant.get(row).getIDno();
		case 5:
			return applicant.get(row).getAddress();
		case 6:
			return applicant.get(row).getHouseholdIncome();
		default:
			return "";
		}
	}
	/**
	 * Display the titles
	 */
	public String getColumnName(int col)
	{
		return title[col];
	}
	
}
