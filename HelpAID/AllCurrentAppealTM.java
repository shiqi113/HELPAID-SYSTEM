 /* Tan Shi QI
 * B1901264
 * 10/04/2022
 *  table model of All current appeal
 */
package HelpAID;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class AllCurrentAppealTM extends AbstractTableModel {
	private HELPAid helpAid; // for storing items
	private List<Appeal> appeals;
	private String[] title = {"AppealID", "Description", "From Date", 
			"To Date","Outcome","Organization Name"
			};
	private Organization org;
	/**
	 * @param HelpAID for initialization of object of this class
	 */
	public AllCurrentAppealTM(HELPAid helpAid)
	{
		this.appeals=helpAid.currentAppealList();
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
		return appeals.size();
	}
	/**
	 * To get the object at a specified row and column
	 */
	public Object getValueAt(int row, int col)
	{
		
				
		switch (col) {
		case 0: 
			return appeals.get(row).getAppealID();
			
		case 1:
			return appeals.get(row).getDescription();
			
		case 2:
			return appeals.get(row).getFromDate();
			
		case 3:
			return appeals.get(row).getToDate();
		case 4:
			return appeals.get(row).getOutcome();
		case 5:
			return appeals.get(row).getOrganizationName();
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
