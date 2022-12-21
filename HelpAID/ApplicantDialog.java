 /* Tan Shi QI
 * B1901264
 * 10/04/2022
 *  GUI Applicant menu 
 */
package HelpAID;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ApplicantDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private ViewDisbursementDialog viewDisbursementdialog;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ApplicantDialog dialog = new ApplicantDialog(null,null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ApplicantDialog(HELPAid helpAid, User loginUser) {
		Applicant applicant = (Applicant) loginUser;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel);
			{
				JButton ViewDisbusementBtn = new JButton("View Disbursement");
				ViewDisbusementBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						viewDisbursementdialog = new ViewDisbursementDialog(helpAid,loginUser);
						viewDisbursementdialog.setVisible(true);
					}
				});
				panel.add(ViewDisbusementBtn);
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
						ApplicantDialog.this.dispose();
					}
				});
				LogoutButton.setActionCommand("Cancel");
				buttonPane.add(LogoutButton);
				getRootPane().setDefaultButton(LogoutButton);
			}
		}
		{
			JPanel panel = new JPanel();
			getContentPane().add(panel, BorderLayout.NORTH);
			{
				JLabel nameTF = new JLabel("Welcome, " + applicant.getFullname() + 
						" [Applicant of " + 
						applicant.getOrganization().getOrgName() + "]");
				panel.add(nameTF);
			}
		}
	}

}
