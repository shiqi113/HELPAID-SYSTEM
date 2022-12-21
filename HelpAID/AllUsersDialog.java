 /* Tan Shi QI
 * B1901264
 * 10/04/2022
 *  table model of all user
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

public class AllUsersDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private UserTableModel userTM;
	private JScrollPane scrollPane;
	private AllUserOriginalTM allUserOriginalModel;
	private AllUserSortedTM allUserSortedModel;
	

	/**
	 * Create the dialog.
	 */
	public AllUsersDialog(JFrame parent,HELPAid helpAid) {
		setTitle("All Users");
		//userTM = new UserTableModel(helpAid);
		allUserOriginalModel = new AllUserOriginalTM(helpAid);
		allUserSortedModel = new AllUserSortedTM(helpAid);
		setBounds(parent.getX(), parent.getY(), 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(0, 1, 0, 0));
		{
			scrollPane = new JScrollPane();
			contentPanel.add(scrollPane,BorderLayout.CENTER);
			{
				/**
				 * Display all the users by setting the UserTableModel to the JTable
				 */
				table = new JTable();
				table.setModel(allUserOriginalModel);
				scrollPane.setViewportView(table);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				{
					JButton btnOriginal = new JButton("Original");
					btnOriginal.addActionListener(
							new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							table = new JTable(allUserOriginalModel);
							scrollPane.setViewportView(table);
						}
					});
					buttonPane.add(btnOriginal);
				}
				{
					JButton btnSortedfullname = 
							new JButton("Sorted Full Name");
					btnSortedfullname.addActionListener(
							new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							table = new JTable(allUserSortedModel);
							scrollPane.setViewportView(table);
						}
					});
					buttonPane.add(btnSortedfullname);
				}
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

}
