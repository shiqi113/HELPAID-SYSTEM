/**
 * Tan Shi QI
 * B1901264
 * 10/04/2022
 *  GUI Register Organization
 */
package HelpAID;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegisterOrganizationDialog extends JDialog {
	private JTextField orgNameTF;
	private JTextField orgAddressTF;
	private HELPAid helpAid;
	private User loginUser;
	private Organization org;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegisterOrganizationDialog dialog = new 
					RegisterOrganizationDialog(null,null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegisterOrganizationDialog(HELPAid helpAid,User loginUser) {
		
		this.loginUser = loginUser;
		setTitle("Register Organization");
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton AddButton = new JButton("Add");
				AddButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String orgName = orgNameTF.getText();
			            String orgAddress = orgAddressTF.getText();
			            if(orgName.isEmpty() || orgAddress.isEmpty()) {
			            	
			            	JOptionPane.showMessageDialog(
			            			RegisterOrganizationDialog.this, 
			            			"Input Cannot Empty");
			            }
			            else {
			            	
				    		if (helpAid.validateOrg(orgName)) {
				            	JOptionPane.showMessageDialog(
				            			 RegisterOrganizationDialog.this, 
				            			"OrgName already exists.");
				            	orgNameTF.setText("");
				            	orgNameTF.requestFocus();
				            	return;
				    		}
				    		else {
				    			Organization organization= 
				    					new Organization(orgName, orgAddress);
				    			helpAid.addOrganization(organization);
				    			
				    			JOptionPane.showMessageDialog(
				    					RegisterOrganizationDialog.this, 
										"Add successfully!");
				    			RegisterOrganizationDialog.this.dispose();
				    			
				    		}
				          	
			            }
			            
					}
				});
				AddButton.setActionCommand("OK");
				buttonPane.add(AddButton);
				getRootPane().setDefaultButton(AddButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						RegisterOrganizationDialog.this.dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		{
			JPanel panel = new JPanel();
			getContentPane().add(panel, BorderLayout.NORTH);
			panel.setLayout(new GridLayout(0, 2, 0, 0));
			{
				JLabel lblNewLabel = new JLabel("Organization Name :");
				panel.add(lblNewLabel);
			}
			{
				orgNameTF = new JTextField();
				panel.add(orgNameTF);
				orgNameTF.setColumns(10);
			}
			{
				JLabel lblNewLabel_1 = new JLabel("Address :");
				panel.add(lblNewLabel_1);
			}
			{
				orgAddressTF = new JTextField();
				panel.add(orgAddressTF);
				orgAddressTF.setColumns(10);
			}
		}
	}

	

}
