 /* Tan Shi QI
 * B1901264
 * GUI Show All appeal
 */
package HelpAID;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewDisbursementDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JScrollPane scrollPane;
	private ViewDisbursement alldisbursementTM;

	/**
	 * Create the dialog.
	 */
	public ViewDisbursementDialog(HELPAid helpAid,User loginUser) {
		Applicant applicant = (Applicant) loginUser;
		setTitle("Disbursement");
		setBounds(100, 100, 450, 300);
		alldisbursementTM = new  ViewDisbursement(applicant);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(0, 1, 0, 0));
		{
			scrollPane = new JScrollPane();
			contentPanel.add(scrollPane);
			{
				/**Display all the users 
				 * by setting the UserTableModel 
				 * to the JTable
				 */
				table = new JTable();
				table.setModel(alldisbursementTM);
				scrollPane.setViewportView(table);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			{
				JButton okButton = new JButton("Close");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				okButton.setActionCommand("Close");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

}
