 /* Tan Shi QI
 * B1901264
 * 10/04/2022
 * table model of all user original
 */
package HelpAID;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class AllUserOriginalTM extends AbstractTableModel {
	private HELPAid helpAid; // for storing items
	private List<User> users;
	private String[] title = {"Username", "Password", "Name", 
			"Phone","email", "ID No", "Address","Jobtitle","Income(RM)"
	};
	/**
	 * @param HelpAID for initialization of object of this class
	 */
	public AllUserOriginalTM(HELPAid helpAid)
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
		return helpAid.numOfUsers();
	}
	/**
	 * To get the object at a specified row and column
	 */
	public Object getValueAt(int row, int col)
	{
		
		User user = getHelpAid().getUsers().get(row);
		switch (col) {
		case 0: 
			return user.getUsername();
			
		case 1:
			return user.getPassword();
			
		case 2:
			return user.getFullname();
			
		case 3:
			return user.getMobileNo();
			
		case 4:
			return user.getEmail();
			
		case 5:
			if (user instanceof Applicant)
				return ((Applicant)user).getIDno();
			else
				return "n/a";
		case 6:
			if (user instanceof Applicant)
				return ((Applicant)user).getAddress();
			else
				return "n/a";
			
			
		case 7:
			if (user instanceof OrganizationRep)
				return ((OrganizationRep)user).getJobTitle();
			else
				return "n/a";
			
		case 8:
			if (user instanceof Applicant)
				return ((Applicant)user).getHouseholdIncome();
			else
				return "n/a";
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
