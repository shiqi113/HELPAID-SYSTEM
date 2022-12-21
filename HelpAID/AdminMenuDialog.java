 /* Tan Shi QI
 * B1901264
 * 10/04/2022
 * GUI Amin Menu
 */
package HelpAID;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.JLabel;

public class AdminMenuDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	
	private RegisterOrganizationDialog rgtOrgDialog;
	private AddRegisterRepDialog allorgdialog;
	
	private AllUsersDialog usersDialog;
	private HELPAid helpAid;
	private User loginUser;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AdminMenuDialog dialog = new AdminMenuDialog(null,null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AdminMenuDialog(HELPAid helpAid,User loginUser) {
		//helpAid = new HELPAid();
		this.helpAid=helpAid;
		this.loginUser = loginUser;
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel panel = new JPanel();
			getContentPane().add(panel, BorderLayout.NORTH);
			{
				JLabel nameTF = new JLabel("Welcome, " + loginUser.getFullname() + 
						" [Administrator]");
				panel.add(nameTF);
			}
		}
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(new BorderLayout(0, 0));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel);
			panel.setLayout(new GridLayout(2, 0, 0, 0));
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1);
				{
					JButton AddOrgbtn = new JButton("Add Organization");
					AddOrgbtn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							rgtOrgDialog = new RegisterOrganizationDialog(helpAid,loginUser);
							rgtOrgDialog.setVisible(true);
						}
					});
					panel_1.add(AddOrgbtn);
				}
			}
			{
				JPanel panel_2 = new JPanel();
				panel.add(panel_2);
				{
					JButton AddRepbtn = new JButton("Add Organization Representative");
					AddRepbtn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							allorgdialog = new AddRegisterRepDialog(helpAid,loginUser);
							allorgdialog.setVisible(true);
						
							
						}
					});
					panel_2.add(AddRepbtn);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER,5,5));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton LogoutButton = new JButton("Logout");
				LogoutButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						AdminMenuDialog.this.dispose();
					}
				});
				LogoutButton.setActionCommand("Cancel");
				buttonPane.add(LogoutButton);
				getRootPane().setDefaultButton(LogoutButton);
			}
		}
	}

}
