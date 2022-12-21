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

public class AddContributionDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JTextField appealIDTF;
	private JTextField amountTF;
	private JTextField channelTF;
	private JTextField refTF;
	private JTextField EstimatedvalueTF;
	private JTextField DescriptionTF;
	private AllCurrentAppealWithOrgTM allcurrentappealTMWithorg;
	private JRadioButton cashRB;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private Organization org;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AddContributionDialog dialog = new AddContributionDialog
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
	public AddContributionDialog(HELPAid helpAid,User loginUser) {
		Organization org=(((OrganizationRep)loginUser)
				.getOrganization());
		amountTF = new JTextField();
		channelTF = new JTextField();
		refTF = new JTextField();
		EstimatedvalueTF = new JTextField();
		DescriptionTF = new JTextField();
		allcurrentappealTMWithorg= new AllCurrentAppealWithOrgTM(org);
		setTitle("Record Contribution");
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
				lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
				panel.add(lblNewLabel);
			}
			{
				appealIDTF = new JTextField();
				panel.add(appealIDTF);
				appealIDTF.setColumns(10);
			}
			{
				cashRB = new JRadioButton("Cash");
				cashRB.setHorizontalAlignment(SwingConstants.RIGHT);
				cashRB.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent arg0) {
						if(cashRB.isSelected()) {
							amountTF.setEnabled(true);
							channelTF.setEnabled(true);
							refTF.setEnabled(true);
							EstimatedvalueTF.setEnabled(false);
							DescriptionTF.setEnabled(false);
						}else {
							amountTF.setEnabled(false);
							channelTF.setEnabled(false);
							refTF.setEnabled(false);
							EstimatedvalueTF.setEnabled(true);
							DescriptionTF.setEnabled(true);
						}
					}

				});
				buttonGroup.add(cashRB);
				//cashRB.setSelected(true);
				panel.add(cashRB);
			}
			{
				JRadioButton goodsRB = new JRadioButton("Goods");
				goodsRB.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent arg0) {
						if(goodsRB.isSelected()) {
							amountTF.setEnabled(false);
							channelTF.setEnabled(false);
							refTF.setEnabled(false);
							EstimatedvalueTF.setEnabled(true);
							DescriptionTF.setEnabled(true);
						}
					}

				});
				buttonGroup.add(goodsRB);
				panel.add(goodsRB);
			}
			{
				JLabel amountLB = new JLabel("Amount(RM)");
				amountLB.setHorizontalAlignment(SwingConstants.RIGHT);
				panel.add(amountLB);
			}
			{
				amountTF = new JTextField();
				panel.add(amountTF);
				amountTF.setColumns(10);
			}
			{
				JLabel channelLB = new JLabel("Payment channel:");
				channelLB.setHorizontalAlignment(SwingConstants.RIGHT);
				panel.add(channelLB);
			}
			{
				channelTF = new JTextField();
				panel.add(channelTF);
				channelTF.setColumns(10);
			}
			{
				JLabel Reflb = new JLabel("Reference number:");
				Reflb.setHorizontalAlignment(SwingConstants.RIGHT);
				panel.add(Reflb);
			}
			{
				refTF = new JTextField();
				panel.add(refTF);
				refTF.setColumns(10);
			}
			{
				JLabel estimatedLB = new JLabel("Estimated value:");
				estimatedLB.setHorizontalAlignment(SwingConstants.RIGHT);
				panel.add(estimatedLB);
			}
			{
				EstimatedvalueTF = new JTextField();
				panel.add(EstimatedvalueTF);
				EstimatedvalueTF.setColumns(10);
			}
			{
				JLabel descriptionlb = new JLabel("Description:");
				descriptionlb.setHorizontalAlignment(SwingConstants.RIGHT);
				panel.add(descriptionlb);
			}
			{
				DescriptionTF = new JTextField();
				panel.add(DescriptionTF);
				DescriptionTF.setColumns(10);
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
						//check input empty
						if(appealID.isEmpty())
						{
			            	JOptionPane.showMessageDialog(
			            			 AddContributionDialog.this, 
			            			"Input Cannot Empty");
						} else {
							//find appeal
							if (foundAppeal == null) {
				            	JOptionPane.showMessageDialog(
				            			AddContributionDialog.this, 
				            			"Invalid appealID");
				            	appealIDTF.setText("");
				            	appealIDTF.requestFocus();
				            	
				            }else {
				            	if(cashRB.isSelected()) {
				            		try {
				            			amount= Double.parseDouble
				            					(stramount);
										if(amount < 0) {
											JOptionPane.showMessageDialog(
							            			 AddContributionDialog.this, 
							            			"Please Enter valid number");
										} 
										else if(channel.isEmpty() || 
												ref.isEmpty() 
												||appealID.isEmpty()) {
											JOptionPane.showMessageDialog(
							            			 AddContributionDialog.this, 
							            			"Input Cannot Empty");
										}
										else {
											Contribution contribution = 
													new CashDonation
													(foundAppeal,amount, 
															channel, ref);
											foundAppeal.addContribution
														(contribution);
											JOptionPane.showMessageDialog(
							            			 AddContributionDialog.this, 
							            			 contribution);
											AddContributionDialog.
														this.dispose();
										}
									} catch(NumberFormatException nfe) {
										JOptionPane.showMessageDialog(
						            			 AddContributionDialog.this, 
						            			"Please Enter valid number");
												amountTF.setText("");
												amountTF.requestFocus();
										}
										
									}else {
										try {
											estimatedValue= Double.
													parseDouble(strestimatedValue);
											if(estimatedValue < 0) {
												JOptionPane.showMessageDialog(
								            			 AddContributionDialog.this, 
								            			"Please Enter valid number");
											} 
											else if(description.isEmpty()
													||appealID.isEmpty()
													||description.isEmpty()) {
												JOptionPane.showMessageDialog(
								            			 AddContributionDialog.this, 
								            			"Input Cannot Empty");
											}
											else {
												Contribution contribution = new Goods
														(foundAppeal, description,
														estimatedValue);
												foundAppeal.addContribution(contribution);
												JOptionPane.showMessageDialog(
								            			 AddContributionDialog.this, 
								            			 contribution);
												AddContributionDialog.this.dispose();
											}
										} catch(NumberFormatException nfe) {
											JOptionPane.showMessageDialog(
							            			 AddContributionDialog.this, 
							            			"Please Enter valid number");
											EstimatedvalueTF.setText("");
											EstimatedvalueTF.requestFocus();
											}
									}
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
						AddContributionDialog.this.dispose();
					}
				});
				buttonPane.add(cancelButton);
			}
		}
	}
	
	
	

}
