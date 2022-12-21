/**
 * Tan Shi QI
 * B1901264
 * 10/04/2022
 *  GUI Organization Rep
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
import java.awt.GridLayout;
import java.awt.Color;

public class OrganizationRepDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private HELPAid helpAid;
	private User loginUser;
	private OrganizeAidAppeal addAppealDialog;
	private AddApplicantDialog addApplicantlDialog;
	private AddContributionDialog addContributionDialog;
	private RecordDisbursementDialog recordDisbursementDialog;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			OrganizationRepDialog dialog = new 
					OrganizationRepDialog(null,null,null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public OrganizationRepDialog(HELPAid helpAid, User loginUser,
			Applicant applicant) {
		this.helpAid=helpAid;
		this.loginUser = loginUser;
		OrganizationRep orgRep = (OrganizationRep) loginUser;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel);
			panel.setLayout(new GridLayout(4, 1, 0, 0));
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1);
				{
					JButton RegisterBtn = new JButton("Register Applicant");
					RegisterBtn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							addApplicantlDialog = new 
									AddApplicantDialog
									(helpAid,loginUser,applicant);
							addApplicantlDialog.setVisible(true);
						}
					});
					RegisterBtn.setBackground(Color.WHITE);
					panel_1.add(RegisterBtn);
				}
			}
			{
				JPanel panel_2 = new JPanel();
				panel.add(panel_2);
				{
					JButton organizeAppealbtn = new JButton
							("Organize Aid Appeal");
					organizeAppealbtn.addActionListener
						(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							
							addAppealDialog = new OrganizeAidAppeal
									(helpAid,loginUser);
							addAppealDialog.setVisible(true);
						}
						
					});
					organizeAppealbtn.setBackground(Color.WHITE);
					panel_2.add(organizeAppealbtn);
				}
			}
			{
				JPanel panel_3 = new JPanel();
				panel.add(panel_3);
				{
					JButton contributionBtn = new JButton("Record Contribution");
					contributionBtn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							addContributionDialog = new AddContributionDialog
													(helpAid,loginUser);
							addContributionDialog.setVisible(true);
						}
					});
					contributionBtn.setBackground(Color.WHITE);
					panel_3.add(contributionBtn);
				}
			}
			{
				JPanel panel_4 = new JPanel();
				panel.add(panel_4);
				{
					JButton disbursementBtn = new JButton
							("Record Aid Disbursement");
					disbursementBtn.addActionListener
					(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							
							recordDisbursementDialog = 
									new RecordDisbursementDialog(helpAid,loginUser);
							recordDisbursementDialog.setVisible(true);
						}
					});
					disbursementBtn.setBackground(Color.WHITE);
					panel_4.add(disbursementBtn);
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
						OrganizationRepDialog.this.dispose();
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
				JLabel nameTF = new JLabel("Welcome, " + orgRep.getFullname() + 
						" [" + orgRep.getJobTitle() + " of " + 
						orgRep.getOrganization().getOrgName() + "]");
				panel.add(nameTF);
			}
		}
	}

}
