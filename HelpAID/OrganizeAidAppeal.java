/**
 * Tan Shi QI
 * B1901264
 * 10/04/2022
 *  GUI Organized Aid Appeal
 */
package HelpAID;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import javax.swing.ButtonGroup;

public class OrganizeAidAppeal extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField fromDateTF;
	private JTextField toDateTF;
	private JTextField DescriptionTF;
	private HELPAid helpAid;
	private User loginUser;
	private Organization org;
	static DateTimeFormatter formatter = DateTimeFormatter.
			ofPattern("dd/MM/yyyy");
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			OrganizeAidAppeal dialog = new OrganizeAidAppeal
					(null, null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public OrganizeAidAppeal(HELPAid helpAid,User loginUser) {
		this.helpAid=helpAid;
		this.loginUser = loginUser;
		setTitle("Organizrd Aid Appeal");
		setModal(true);
		setBounds(100, 100, 570, 220);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(0, 2, 0, 0));
		{
			JLabel descLbl = new JLabel("From date (dd/mm/yyyy): ");
			contentPanel.add(descLbl);
		}
		{
			fromDateTF = new JTextField();
			contentPanel.add(fromDateTF);
			fromDateTF.setColumns(10);
		}
		{
			JLabel destinationLbl = new JLabel("To date (dd/mm/yyyy):");
			contentPanel.add(destinationLbl);
		}
		{
			toDateTF = new JTextField();
			contentPanel.add(toDateTF);
			toDateTF.setColumns(10);
		}
		{
			JLabel tripDateLbl = new JLabel("Description:");
			contentPanel.add(tripDateLbl);
		}
		{
			DescriptionTF = new JTextField();
			contentPanel.add(DescriptionTF);
			DescriptionTF.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			{
				JButton submitBtn = new JButton("Submit");
				submitBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						
						String fromDateStr = fromDateTF.getText();
						String toDateStr = toDateTF.getText();
						String decription = DescriptionTF.getText();
						Organization org=(((OrganizationRep)loginUser)
    							.getOrganization());
						
				    	if (fromDateStr.isEmpty() || toDateStr.isEmpty() 
				    			|| decription.isEmpty()) {
				    		
				    		JOptionPane.showMessageDialog(
				    				OrganizeAidAppeal.this, 
				    				"Please fill up everything!");
				    	}
				    	
				    	else {
				    		
				    		/**
				    		 * Try catch if the date entered matched 
				    		 * the correct format
				    		 */
				    		try{
				    			
				    			LocalDate fromDate = LocalDate.parse(
				    					fromDateStr, formatter);
				    			LocalDate toDate = LocalDate.parse(
				    					toDateStr, formatter);
				    			/**
				    			 * Create a new Appeal
				    			 */
				    			
				    			
				    			Appeal appeal = new Appeal(fromDate,toDate
				    					,decription,org);
				    			helpAid.addAppeal(appeal);
				    			org.add(appeal);
								JOptionPane.showMessageDialog(
										OrganizeAidAppeal.this, 
										"Add successfully!");
								OrganizeAidAppeal.this.dispose();
								
				    		}
				    		
				    		catch (DateTimeParseException dtpe) {
				    			
				    			JOptionPane.showMessageDialog(
				    					OrganizeAidAppeal.this,
				    					"Invalid date format!");
				    			 fromDateTF.setText("");
				    			 fromDateTF.requestFocus();
				    			 toDateTF.setText("");
				    			 toDateTF.requestFocus();
				   
				    			
				    		}
				    		
				    	}
						
						
						
					}
				}

				);
				submitBtn.setActionCommand("OK");
				buttonPane.add(submitBtn);
				getRootPane().setDefaultButton(submitBtn);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						OrganizeAidAppeal.this.dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		{
			JPanel panel = new JPanel();
			getContentPane().add(panel, BorderLayout.NORTH);
			{
				JLabel organizationlb = new JLabel("Organization:"
						+(((OrganizationRep)loginUser).getOrganization()));
				panel.add(organizationlb);
			}
		}
	}

}