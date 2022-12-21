/**
 * Tan Shi QI
 * B1901264
 * 10/04/2022
 * Gui sign up
 */
package HelpAID;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class SignUpDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	
	private JTextField emailTF;
	private JTextField mobileTF;
	private JTextField idNoTF;
	private JTextField addressTF;
	private JTextField IncomeTF;
	private JTextField fullnameTF;
	private HELPAid helpAid;
	private static User loginUser;
	private AddDocumentDialog alldocdialog;
	private JTextField orgIDTF;
	private OrgTableModel orgTM;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			SignUpDialog dialog = new SignUpDialog(null,null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public SignUpDialog(HELPAid helpAid,Applicant applicant) {
		setTitle("Add Organization Representative");
		orgTM= new OrgTableModel(helpAid);
		setModal(true);
		setBounds(100, 100, 600, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(0, 2, 0, 0));
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane);
			{
				 /**
				 * Display all the Organizations 
				 * by setting the orgTableModel 
				 * to the JTable
				 */
				table = new JTable();
				table.setModel(orgTM);
				scrollPane.setViewportView(table);
				{
					JPanel panel = new JPanel();
					getContentPane().add(panel, BorderLayout.WEST);
					panel.setLayout(new GridLayout(7, 2, 0, 0));
					{
						JLabel orgID = new JLabel("OrgID:");
						panel.add(orgID);
					}
					{
						orgIDTF = new JTextField();
						panel.add(orgIDTF);
						orgIDTF.setColumns(10);
					}
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
				}
			}
			
		}
		
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			{
				JButton okButton = new JButton("SignUp");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						String orgID = orgIDTF.getText();
			            String fullname= fullnameTF.getText();
			            String email = emailTF.getText();
			            String mobile = mobileTF.getText();
			            String IDno= idNoTF.getText();
			            String address= addressTF.getText();
			            String strincome= IncomeTF.getText();
			            double income= 0;
			            
			            Organization foundOrganization = helpAid.findOrg(orgID);
			            if(fullname.isEmpty() || email.isEmpty()
					         || mobile.isEmpty() || IDno.isEmpty()
					         || address.isEmpty() || strincome.isEmpty()){
					            	
				            	JOptionPane.showMessageDialog(
				            			SignUpDialog.this, 
				            			"Input Cannot Empty");
					    }
			            else {
			            	if (helpAid.duplicateApplicant(IDno)) {
				            	JOptionPane.showMessageDialog(
				            			SignUpDialog.this, 
				            			"IDno already exists.");
				            	idNoTF.setText("");
				            	idNoTF.requestFocus();
				            	return;
				    		}else if(foundOrganization == null){
				    			JOptionPane.showMessageDialog(
				    					SignUpDialog.this, 
				            			"Invalid orgID");
				            	orgIDTF.setText("");
				            	orgIDTF.requestFocus();
				    		}
				    		else {
				    			try {
				    				income= Double.parseDouble(strincome);
									if(income < 0) {
										JOptionPane.showMessageDialog(
												SignUpDialog.this, 
						            			"Please Enter valid number");
									} 
									else {
										Applicant applicant = new Applicant
												(fullname,email,
							        			mobile,IDno,address
							        			,income,foundOrganization);
						    				foundOrganization.add(applicant);
							        		helpAid.addUser(applicant);
							        		alldocdialog = new AddDocumentDialog
							        				(helpAid,loginUser
							        						,applicant);
											alldocdialog.setVisible(true);
							        		JOptionPane.showMessageDialog(
							        				SignUpDialog.this, 
													"Add successfully!");	
									}
								} catch(NumberFormatException nfe) {
									JOptionPane.showMessageDialog(
											SignUpDialog.this, 
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
						SignUpDialog.this.dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		
		}
			            
			            
			           
			     
	}

}