 /* Tan Shi QI
 * B1901264
 * 10/04/2022
 *  Gui Add Applicant
 */
package HelpAID;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;

public class AddApplicantDialog extends JDialog {
	private JTextField emailTF;
	private JTextField mobileTF;
	private JTextField idNoTF;
	private JTextField addressTF;
	private JTextField IncomeTF;
	private JTextField fullnameTF;
	private HELPAid helpAid;
	private static User loginUser;
	private AddDocumentDialog alldocdialog;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AddApplicantDialog dialog = new AddApplicantDialog(null,null,null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * Create the dialog.
	 */
	public AddApplicantDialog(HELPAid helpAid,User loginUser, Applicant applicant){
		setTitle("Add Applicant");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JPanel panel = new JPanel();
				getContentPane().add(panel, BorderLayout.WEST);
				panel.setLayout(new GridLayout(6, 2, 0, 0));
				{
					JLabel lblNewLabel = new JLabel("Full Name:");
					panel.add(lblNewLabel);
				}
				{
					fullnameTF = new JTextField();
					panel.add(fullnameTF);
					fullnameTF.setColumns(10);
				}
				{
					JLabel lblNewLabel_3 = new JLabel("Email:");
					panel.add(lblNewLabel_3);
				}
				{
					emailTF = new JTextField();
					emailTF.setColumns(10);
					panel.add(emailTF);
				}
				{
					JLabel lblNewLabel_4 = new JLabel("Mobile number:");
					panel.add(lblNewLabel_4);
				}
				{
					mobileTF = new JTextField();
					mobileTF.setColumns(10);
					panel.add(mobileTF);
				}
				{
					JLabel lblNewLabel_5 = new JLabel("ID number:");
					panel.add(lblNewLabel_5);
				}
				{
					idNoTF = new JTextField();
					idNoTF.setColumns(10);
					panel.add(idNoTF);
				}
				{
					JLabel lblNewLabel_6 = new JLabel("Address:");
					panel.add(lblNewLabel_6);
				}
				{
					addressTF = new JTextField();
					addressTF.setColumns(10);
					panel.add(addressTF);
				}
				{
					JLabel lblNewLabel_7 = new JLabel("Household Income:");
					panel.add(lblNewLabel_7);
				}
				{
					IncomeTF = new JTextField();
					IncomeTF.setColumns(10);
					panel.add(IncomeTF);
				}
				JButton okButton = new JButton("Add");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Organization org=(((OrganizationRep)loginUser).getOrganization());
			            String fullname= fullnameTF.getText();
			            String email = emailTF.getText();
			            String mobile = mobileTF.getText();
			            String IDno= idNoTF.getText();
			            String address= addressTF.getText();
			            String strincome= IncomeTF.getText();
			            double income= 0;
			            //check input empty
			            if(fullname.isEmpty() || email.isEmpty()
					         || mobile.isEmpty() || IDno.isEmpty()
					         || address.isEmpty() || strincome.isEmpty()){
					            	
				            	JOptionPane.showMessageDialog(
				            			AddApplicantDialog.this, 
				            			"Input Cannot Empty");
					    }
			            //check duplicate ID no
			            else {
			            	if (helpAid.duplicateApplicant(IDno)) {
				            	JOptionPane.showMessageDialog(
				            			AddApplicantDialog.this, 
				            			"IDno already exists.");
				            	idNoTF.setText("");
				            	idNoTF.requestFocus();
				            	return;
				    		}else {
				    			try {
				    				//check valid number
				    				income= Double.parseDouble(strincome);
									if(income < 0) {
										JOptionPane.showMessageDialog(
												AddApplicantDialog.this, 
						            			"Please Enter valid number");
									} 
									else {
										Applicant applicant = new Applicant(
												fullname,email,
							        			mobile,IDno,address,
							        			income,org);
							        		org.add(applicant);
							        		helpAid.addUser(applicant);
							        		alldocdialog = new 
							        				AddDocumentDialog(
							        				helpAid,
							        				loginUser,applicant);
											alldocdialog.setVisible(true);
							        		AddApplicantDialog.this.dispose();
									}
								} catch(NumberFormatException nfe) {
									JOptionPane.showMessageDialog(
											AddApplicantDialog.this, 
					            			"Please Enter valid number");
											IncomeTF.setText("");
											IncomeTF.requestFocus();
									}
				    			
				    		}
					    }
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						AddApplicantDialog.this.dispose();
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
