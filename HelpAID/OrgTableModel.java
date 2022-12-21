/**
 * Tan Shi QI
 * B1901264
 * 10/04/2022
 * table model of organization
 */
package HelpAID;

import javax.swing.table.AbstractTableModel;



public class OrgTableModel extends AbstractTableModel {
	private HELPAid helpAid;
	private String[] title = {"Org ID", "Org Name", 
			"Org Address"};
	
	/**
	 * @param crs for initialization of object of this class
	 */
	public OrgTableModel(HELPAid helpAid)
	{
		setHelpAid(helpAid);
	}
	
	/**
	 * @param helpAid to set the helpaid in this class
	 */
	public void setHelpAid(HELPAid helpAid) {
		this.helpAid = helpAid;
	}
	/**
	 * 
	 * @return HelpAid
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
		return helpAid.numOfOrganizations();
	}
	public Object getValueAt(int row, int col)
	{
		Organization org = getHelpAid().getOrganizations().get(row);
		switch (col) {
		case 0:
			return org.getOrgID();
			
		case 1: 
			return org.getOrgName();
			
		case 2:
			return org.getAddress();
		default:
			return "";
		}
	}
	public String getColumnName(int col)
	{
		return title[col];
	}
}
