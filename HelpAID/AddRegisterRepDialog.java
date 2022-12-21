 /* Tan Shi QI
 * B1901264
 * 10/04/2022
 *  GUI Add Org Rep;
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

public class AddRegisterRepDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	
	private OrgTableModel orgTM;
	private JTextField usernameTF;
	private JTextField FullnameTF;
	private JTextField EmailTF;
	private JTextField mobileTF;
	private JTextField jobtitleTF;
	private JTextField orgIDTF;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AddRegisterRepDialog dialog = new AddRegisterRepDialog(null,null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AddRegisterRepDialog(HELPAid helpAid,User loginUser) {
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
					panel.setLayout(new GridLayout(6, 2, 0, 0));
					{
						JLabel lblNewLabel = new JLabel("Org ID:");
						panel.add(lblNewLabel);
					}
					{
						orgIDTF = new JTextField();
						panel.add(orgIDTF);
						orgIDTF.setColumns(10);
					}
					{
						JLabel lblNewLabel_3 = new JLabel("Username:");
						panel.add(lblNewLabel_3);
					}
					{
						usernameTF = new JTextField();
						usernameTF.setColumns(10);
						panel.add(usernameTF);
					}
					{
						JLabel lblNewLabel_4 = new JLabel("Full name:");
						panel.add(lblNewLabel_4);
					}
					{
						FullnameTF = new JTextField();
						FullnameTF.setColumns(10);
						panel.add(FullnameTF);
					}
					{
						JLabel lblNewLabel_5 = new JLabel("Email:");
						panel.add(lblNewLabel_5);
					}
					{
						EmailTF = new JTextField();
						EmailTF.setColumns(10);
						panel.add(EmailTF);
					}
					{
						JLabel lblNewLabel_6 = new JLabel("Mobile number:");
						panel.add(lblNewLabel_6);
					}
					{
						mobileTF = new JTextField();
						mobileTF.setColumns(10);
						panel.add(mobileTF);
					}
					{
						JLabel lblNewLabel_7 = new JLabel("Job Title:");
						panel.add(lblNewLabel_7);
					}
					{
						jobtitleTF = new JTextField();
						jobtitleTF.setColumns(10);
						panel.add(jobtitleTF);
					}
				}
			}
			
		}
		
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			{
				JButton applyBtn = new JButton("ADD");
				applyBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String orgID = orgIDTF.getText();
			            String username = usernameTF.getText();
			            String fullname= FullnameTF.getText();
			            String email = EmailTF.getText();
			            String mobile = mobileTF.getText();
			            String jobtitle = jobtitleTF.getText();
			            Organization foundOrganization = helpAid.
			            								findOrg(orgID);
			            //check input empty
			            if(username.isEmpty() ||orgID.isEmpty()
			               || fullname.isEmpty() || email.isEmpty()
			               || mobile.isEmpty() || jobtitle.isEmpty()) {
			            	
			            	JOptionPane.showMessageDialog(
			            			AddRegisterRepDialog.this, 
			            			"Input Cannot Empty");
			            }
			            else {
			            	 if (foundOrganization == null) {
					            	JOptionPane.showMessageDialog(
					            			AddRegisterRepDialog.this, 
					            			"Invalid orgID");
					            	orgIDTF.setText("");
					            	orgIDTF.requestFocus();
					            }else {
					            	if (helpAid.validateUser(username)) {
						            	JOptionPane.showMessageDialog(
						            			AddRegisterRepDialog.this, 
						            			"Username already exists.");
						            	usernameTF.setText("");
						            	usernameTF.requestFocus();
						            	return;
						    		}else {
						    			OrganizationRep orgRep = new OrganizationRep( 
												username,fullname,email, 
												mobile,jobtitle, 
												foundOrganization);
											helpAid.addUser(orgRep);
											foundOrganization.add(orgRep);
											JOptionPane.showMessageDialog(
													AddRegisterRepDialog.this, 
													"Add successfully!");
											AddRegisterRepDialog.this.dispose();
						    		}
					            	
					            }
			            }
			            
			            
			           
			            	
					}
							
				});
				applyBtn.setActionCommand("OK");				
				buttonPane.add(applyBtn);
				getRootPane().setDefaultButton(applyBtn);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						AddRegisterRepDialog.this.dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}