/**
 * Tan Shi QI
 * B1901264
 * 10/04/2022
 *  GUI Login
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

public class loginDialog extends JDialog {
	private JTextField usernameTF;
	private JTextField passwordTF;
	private HELPAid helpAid;
	private static User loginUser;
	private static Applicant applicant;
	private static Organization organization;
	private AdminMenuDialog adminMenu;
	private OrganizationRepDialog orgRepMenu;
	private ApplicantDialog applicantMenu;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			loginDialog dialog = new loginDialog(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public loginDialog(HELPAid helpAid) {
		this.helpAid=helpAid;
		setTitle("Login");
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton loginButton = new JButton("Login");
				loginButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String username = usernameTF.getText();
			            String password = passwordTF.getText();
			            if(username.isEmpty() || password.isEmpty()) {
			            	
			            	JOptionPane.showMessageDialog(
			            			loginDialog.this, 
			            			"Input Cannot Empty");
			            }else {
			            	loginUser = helpAid.findUser(username);
				    		if (loginUser == null) {
				            	JOptionPane.showMessageDialog(
				            			loginDialog.this, 
				            			"User does not exist. Please Try again");
				            	usernameTF.setText("");
				            	usernameTF.requestFocus();
				            	return;
				    		}
				            if (!loginUser.getPassword()
				        			.equals(password)) {
				            	JOptionPane.showMessageDialog(
				            			loginDialog.this, 
				            			"Invalid password.Please try again");
				            	passwordTF.setText("");
				            	passwordTF.requestFocus();
				        		return;
				    		}
				            loginDialog.this.dispose();
				            if (loginUser instanceof OrganizationRep) {
				            	OrganizationRep user = (OrganizationRep) loginUser;
				            	if (user.getUsername().equalsIgnoreCase("Admin")) {
				            		adminMenu = new AdminMenuDialog(helpAid,loginUser);
				            		adminMenu.setVisible(true);
				            	}
				            	else {
				            		orgRepMenu = new OrganizationRepDialog(helpAid,loginUser,applicant);
				            		orgRepMenu.setVisible(true);
				            	}
				            }
				            else {
				       
				            	applicantMenu = new ApplicantDialog(helpAid,loginUser);
				            	applicantMenu.setVisible(true);
				            }
				            	
				            	
			            }
			            
					}
				});
				
				buttonPane.add(loginButton);
				getRootPane().setDefaultButton(loginButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						loginDialog.this.dispose();
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
				JLabel lblNewLabel = new JLabel("Username");
				panel.add(lblNewLabel);
			}
			{
				usernameTF = new JTextField();
				panel.add(usernameTF);
				usernameTF.setColumns(10);
			}
			{
				JLabel lblNewLabel_1 = new JLabel("Password");
				panel.add(lblNewLabel_1);
			}
			{
				passwordTF = new JTextField();
				panel.add(passwordTF);
				passwordTF.setColumns(10);
			}
		}
	}

}
