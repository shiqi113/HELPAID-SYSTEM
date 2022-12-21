 /* Tan Shi QI
 * B1901264
 * 10/04/2022
 *  table model of All appeal
 */
package HelpAID;

import javax.swing.table.AbstractTableModel;

public class AllAppealTableModel extends AbstractTableModel{
	
	private HELPAid helpAid; // for storing items
	private String[] title = {"AppealID", "Description", "From Date", 
			"To Date","Outcome","Organization Name"};
	/**
	 * @param HelpAID for initialization of object of this class
	 */
	public AllAppealTableModel(HELPAid helpAid)
	{
		setHelpAid(helpAid);
	}
	/**
	 * @param HelpAID to set the HelpAID in this class
	 */
	public void setHelpAid(HELPAid helpAid) {
		this.helpAid = helpAid;
	}
	/**
	 * 
	 * @return helpAid
	 */
	public HELPAid getHelpAid() {
		return helpAid;
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
		return helpAid.numOfAppeals();
	}
	/**
	 * To get the object at a specified row and column
	 */
	public Object getValueAt(int row, int col)
	{
		Appeal appeal = getHelpAid().getAppeals().get(row);
				
		switch (col) {
		case 0: 
			return appeal.getAppealID();
			
		case 1:
			return appeal.getDescription();
			
		case 2:
			return appeal.getFromDate();
			
		case 3:
			return appeal.getToDate();
		case 4:
			return appeal.getOutcome();
		case 5:
			return appeal.getOrganizationName();
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