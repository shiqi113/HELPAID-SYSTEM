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

public class AllAppealSortedDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JScrollPane scrollPane;
	private AllAppealTableModel allappealTM;
	private AllAppealSortedTM allsortedappealTM;

	/**
	 * Create the dialog.
	 */
	public AllAppealSortedDialog(JFrame parent, HELPAid helpAid) {
		setTitle("All Appeal");
		allappealTM = new AllAppealTableModel(helpAid);
		allsortedappealTM = new AllAppealSortedTM(helpAid);
		setBounds(parent.getX(), parent.getY(), 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(0, 1, 0, 0));
		{
			scrollPane = new JScrollPane();
			contentPanel.add(scrollPane);
			{
				/**
				 * Display all the users by 
				 * setting the AllTableModel to the JTable
				 */
				table = new JTable();
				table.setModel(allappealTM);
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
				{
					JButton btnOriginal = new JButton("Orginal");
					btnOriginal.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							table = new JTable(allappealTM);
							scrollPane.setViewportView(table);
						}
					});
					buttonPane.add(btnOriginal);
				}
				{
					JButton btnOriginal = new JButton("Sorted With Org Name");
					btnOriginal.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							table = new JTable(allsortedappealTM);
							scrollPane.setViewportView(table);
						}
					});
					buttonPane.add(btnOriginal);
				}
				{
					okButton.setActionCommand("Close");
					buttonPane.add(okButton);
					getRootPane().setDefaultButton(okButton);
				}
			}
		}
	}

}
