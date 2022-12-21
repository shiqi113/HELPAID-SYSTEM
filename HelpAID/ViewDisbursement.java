 /* Tan Shi QI
 * B1901264
 * 10/04/2022
 *  table model of All Org Applicant
 */
package HelpAID;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class ViewDisbursement extends AbstractTableModel {
	private HELPAid helpAid; // for storing items
	private List<Applicant> applicant;
	private List<Disbursement> disbursements;
	private String[] title = {"DisbursementDate", "CashAmount", 
			"Goods"};
	private Organization org;
	//only org all applicants
	public ViewDisbursement(Applicant user)
	{
		this.disbursements=user.getDisbursements();
	}

	/**
	 * To get the column count according to the length of title list
	 */
	public int getColumnCount()
	{
		return title.length;
	}
	/**
	 * To get the row count according to the number of 
	 * users stored in helpAid
	 */
	public int getRowCount()
	{
		return this.disbursements.size();
	}
	/**
	 * To get the object at a specified row and column
	 */
	public Object getValueAt(int row, int col)
	{
		//Appeal appeal = getHelpAid().currentAppealList().get(row);
				
		switch (col) {
		case 0: 
			return disbursements.get(row).getDisbursementDate();
			
		case 1:
			return disbursements.get(row).getCashAmount();
			
		case 2:
			return disbursements.get(row).getGoodsDisbursed();
			
		case 3:
			return applicant.get(row).getEmail();
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
	// return appeal based on index
	
	public Appeal getAppeal(int appealInd) {
		return helpAid.getAppeals().get(appealInd);
	}
}
