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

public class AllAppealDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JScrollPane scrollPane;
	private AllCurrentAppealTM allcurrentappealTM;
	private AllPastAppealTM allpastappealTM;
	private AllAppealTableModel allappealTM;
	/**
	 * Create the dialog.
	 */
	public AllAppealDialog(JFrame parent,HELPAid helpAid) {
		setTitle("View Appeal");
		allcurrentappealTM = new AllCurrentAppealTM(helpAid);
		allpastappealTM = new AllPastAppealTM (helpAid);
		allappealTM = new  AllAppealTableModel(helpAid);
		setBounds(parent.getX(), parent.getY(), 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(0, 1, 0, 0));
		{
			scrollPane = new JScrollPane();
			contentPanel.add(scrollPane);
			{
				/**Display all the appeal 
				 * by setting the appealTableModel 
				 * to the JTable
				 */
				table = new JTable();
				table.setModel(allappealTM );
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
					JButton btnOriginal = new JButton("View All");
					btnOriginal.addActionListener(
							new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							table = new JTable(allappealTM );
							scrollPane.setViewportView(table);
						}
					});
					buttonPane.add(btnOriginal);
				}
				{
					JButton btnOriginal = new JButton("Current Appeal");
					btnOriginal.addActionListener(
							new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							table = new JTable(allcurrentappealTM);
							scrollPane.setViewportView(table);
						}
					});
					buttonPane.add(btnOriginal);
				}
				{
					JButton btnSortedfullname = 
							new JButton("Past Appeal");
					btnSortedfullname.addActionListener(
							new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							table = new JTable(allpastappealTM);
							scrollPane.setViewportView(table);
						}
					});
					buttonPane.add(btnSortedfullname);
				}
				okButton.setActionCommand("Close");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

}
