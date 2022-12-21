package HelpAID;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class RecordDisbursementDialog2 extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JTable table_1;
	private JTextField idnoTF;
	private JTextField disbursedDateTF;
	private JTextField amountTF;
	private JTextField goodsTF;
	private AllApplicantWithOrgTM allOrgapplicant;
	private AllContributionTM allcontribution;
	static DateTimeFormatter formatter = DateTimeFormatter.
			ofPattern("dd/MM/yyyy");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RecordDisbursementDialog2 dialog = 
					new RecordDisbursementDialog2(null,null,null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RecordDisbursementDialog2(HELPAid helpAid,User loginUser,Appeal appeal) {
		Organization org=(((OrganizationRep)loginUser)
				.getOrganization());
		Appeal a= helpAid.findAppeal(appeal.getAppealID());
		setTitle("Record AID Disbursement");
		allOrgapplicant= new AllApplicantWithOrgTM(org);
		allcontribution= new AllContributionTM(a);
		setBounds(100, 100, 658, 400);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(5, 0, 0, 0));
		{
			JLabel lblNewLabel_6 = new JLabel("Contribution List");
			lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
			contentPanel.add(lblNewLabel_6);
		}
		{
			{
				JScrollPane scrollPane = new JScrollPane();
				
				contentPanel.add(scrollPane);
				{
					table = new JTable();
					table.setModel(allcontribution);
					table.setBorder(new LineBorder(new Color(0, 0, 0)));
					scrollPane.setViewportView(table);
				}
				
			}
			{
				JLabel lblNewLabel_4 = new JLabel("Applicant List");
				lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
				contentPanel.add(lblNewLabel_4);
			}
			{
				JScrollPane scrollPane = new JScrollPane();
				
				contentPanel.add(scrollPane);
				{
					table_1 = new JTable();
					table_1.setModel(allOrgapplicant);
					table_1.setBorder(new LineBorder(new Color(0, 0, 0)));
					scrollPane.setViewportView(table_1);
				}
			}
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel);
			panel.setLayout(new GridLayout(0, 2, 0, 0));
			{
				JLabel lblNewLabel_5 = new JLabel("Appeal ID:");
				panel.add(lblNewLabel_5);
			}
			{
				JLabel appealIDLB = new JLabel(appeal.getAppealID());
				panel.add(appealIDLB);
			}
			{
				JLabel lblNewLabel = new JLabel("ID No:");
				panel.add(lblNewLabel);
			}
			{
				idnoTF = new JTextField();
				panel.add(idnoTF);
				idnoTF.setColumns(10);
			}
			{
				JLabel lblNewLabel_1 = new JLabel("Disbursed date (dd/mm/yyy):");
				panel.add(lblNewLabel_1);
			}
			{
				disbursedDateTF = new JTextField();
				panel.add(disbursedDateTF);
				disbursedDateTF.setColumns(10);
			}
			{
				JLabel lblNewLabel_2 = new JLabel("Amount: ");
				panel.add(lblNewLabel_2);
			}
			{
				amountTF = new JTextField();
				panel.add(amountTF);
				amountTF.setColumns(10);
			}
			{
				JLabel lblNewLabel_3 = new JLabel("Goods provided:");
				panel.add(lblNewLabel_3);
			}
			{
				goodsTF = new JTextField();
				panel.add(goodsTF);
				goodsTF.setColumns(10);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnViewDocument = new JButton("View Document");
				btnViewDocument.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String idnoStr = idnoTF.getText();
						Applicant app = 
								org.findApplicantByID(idnoStr);
						if(app != null){
			    			JOptionPane.showMessageDialog(
			    					RecordDisbursementDialog2.this, 
			            			"Document Detail:"+"\n"
			    					+app.getFullname()+"\n"+
			            			app.getHouseholdIncome());
			    	}	
					}
				});
				btnViewDocument.setActionCommand("OK");
				buttonPane.add(btnViewDocument);
			}
			{
				JButton okButton = new JButton("Assign");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String idnoStr = idnoTF.getText();
						String disDateStr = disbursedDateTF.getText();
						String stramount = amountTF.getText();
						String goods = goodsTF.getText();
						double amount= 0;
						Applicant app = org.findApplicantByID(idnoStr);
						 if(idnoStr.isEmpty() ||disDateStr.isEmpty()
						         || goods.isEmpty()){
					            	JOptionPane.showMessageDialog(
					            			RecordDisbursementDialog2.this, 
					            			"Input Cannot Empty");
						 }else if(app == null){
				    			JOptionPane.showMessageDialog(
				    					RecordDisbursementDialog2.this, 
				            			"Invalid ID no");
				    			idnoTF.setText("");
				    			idnoTF.requestFocus();
				    	}				    	
						 else {
				    		
				    		/**
				    		 * Try catch if the date entered matched 
				    		 * the correct format
				    		 */
				    		try{
				    			
				    			LocalDate DISDate = LocalDate.parse(
				    					disDateStr, formatter);
				    			/**
				    			 * Create a new Disbursement
				    			 */
				    			amount= Double.parseDouble
		            					(stramount);
								if(amount < 0) {
									JOptionPane.showMessageDialog(
											RecordDisbursementDialog2.this, 
					            			"Please Enter valid number");
								}else {
									Disbursement disbursement = new Disbursement(
											DISDate,amount,goods,app);
									app.addDisbursement(disbursement);
									appeal.addDisbursement(disbursement);
									JOptionPane.showMessageDialog(
											RecordDisbursementDialog2.this, 
											disbursement);
									RecordDisbursementDialog2.this.dispose();
								}
				    			
								
				    		}
				    		
				    		catch (DateTimeParseException dtpe) {
				    			
				    			JOptionPane.showMessageDialog(
				    					RecordDisbursementDialog2.this,
				    					"Invalid date format!");
				    			disbursedDateTF.setText("");
				    			disbursedDateTF.requestFocus();
				    		}
				    		
				    	}
					 }
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
