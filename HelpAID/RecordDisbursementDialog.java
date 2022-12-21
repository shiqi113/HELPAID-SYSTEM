 /* Tan Shi QI
 * B1901264
 * 10/04/2022
 *  GUI Add Contribution
 */
package HelpAID;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class RecordDisbursementDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JTextField appealIDTF;
	private JTextField amountTF;
	private JTextField channelTF;
	private JTextField refTF;
	private JTextField EstimatedvalueTF;
	private JTextField DescriptionTF;
	private AllCurrentAppealWithOrgTM allcurrentappealTMWithorg;
	private RecordDisbursementDialog2 showcontribution;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private Organization org;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RecordDisbursementDialog dialog = new RecordDisbursementDialog
											(null,null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RecordDisbursementDialog(HELPAid helpAid,User loginUser) {
		Organization org=(((OrganizationRep)loginUser)
				.getOrganization());
		amountTF = new JTextField();
		channelTF = new JTextField();
		refTF = new JTextField();
		EstimatedvalueTF = new JTextField();
		DescriptionTF = new JTextField();
		allcurrentappealTMWithorg= new AllCurrentAppealWithOrgTM(org);
		setTitle("Select Appeal ID");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(0, 2, 0, 0));
		{
			JScrollPane scrollPane = new JScrollPane();
			
			contentPanel.add(scrollPane);
			{
				table = new JTable();
				table.setModel(allcurrentappealTMWithorg);
				scrollPane.setViewportView(table);
			}
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel);
			panel.setLayout(new GridLayout(0, 2, 0, 0));
			{
				JLabel lblNewLabel = new JLabel("Appeal ID:");
				lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
				panel.add(lblNewLabel);
			}
			{
				appealIDTF = new JTextField();
				panel.add(appealIDTF);
				appealIDTF.setColumns(10);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER,5,5));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton submitButton = new JButton("Submit");
				submitButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						String appealID=appealIDTF.getText();
						String stramount= amountTF.getText().toString();
						String channel= channelTF.getText();
						String ref= refTF.getText();
						String strestimatedValue= EstimatedvalueTF
													.getText().toString();
						String description= DescriptionTF.getText();
						double amount= 0;
						double estimatedValue=0;
						Organization org=(((OrganizationRep)loginUser)
    							.getOrganization());
						Appeal foundAppeal = org.findAppeal(appealID);
						if(appealID.isEmpty())
						{
			            	JOptionPane.showMessageDialog(
			            			 RecordDisbursementDialog.this, 
			            			"Input Cannot Empty");
						} else {
							if (foundAppeal == null) {
				            	JOptionPane.showMessageDialog(
				            			RecordDisbursementDialog.this, 
				            			"Invalid appealID");
				            	appealIDTF.setText("");
				            	appealIDTF.requestFocus();
				            	
				            }else {
				            	showcontribution = 
				            			new RecordDisbursementDialog2(helpAid,
				        				loginUser,foundAppeal);
				            	showcontribution.setVisible(true);
				            }
									
				            
							
						}
					}
				});
				submitButton.setActionCommand("OK");
				buttonPane.add(submitButton);
				getRootPane().setDefaultButton(submitButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						RecordDisbursementDialog.this.dispose();
					}
				});
				buttonPane.add(cancelButton);
			}
		}
	}
	
	
	

}
