 /* Tan Shi QI
 * B1901264
 * 10/04/2022
 *  table model of All current appeal
 */
package HelpAID;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class AllContributionTM extends AbstractTableModel {
	private HELPAid helpAid; // for storing items
	private List<Appeal> appeals;
	private List<Contribution> contributions;
	private Contribution c;
	private String[] title = {"AppealID", "Contribution ID", 
			"Contribution Type","Receive Date","Payement Channel",
			"Amount","Ref No","Estimated Value","Description"
			};
	private Organization org;
	//only appeal contribution
	/**
	 * @param HelpAID for initialization of object of this class
	 */
	public AllContributionTM(Appeal appeal)
	{
		this.contributions=appeal.allContributionsWithAppeal();
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
	 * users stored in contribution
	 */
	public int getRowCount()
	{
		return this.contributions.size();
	}
	/**
	 * To get the object at a specified row and column
	 */
	public Object getValueAt(int row, int col)
	{
		Contribution c = this.contributions.get(row);
				
		switch (col) {
		case 0: 
			return contributions.get(row).getAppealID();
			
		case 1:
			return contributions.get(row).getContributionID();
			
		case 2:
			return contributions.get(row).getClass().getSimpleName();
			
		case 3:
			return contributions.get(row).getReceivedDate();
		case 4:
			if (c instanceof CashDonation)
				return ((CashDonation)c).getPaymentChannel();
			else
				return "n/a";
		case 5:
			if (c instanceof CashDonation)
				return ((CashDonation)c).getAmount();
			else
				return "n/a";
		case 6:
			if (c instanceof CashDonation)
				return ((CashDonation)c).getReferenceNo();
			else
				return "n/a";
		case 7:
			if (c instanceof Goods)
				return ((Goods)c).getEstimatedValue();
			else
				return "n/a";
		case 8:
			if (c instanceof Goods)
				return ((Goods)c).getDescription();
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
