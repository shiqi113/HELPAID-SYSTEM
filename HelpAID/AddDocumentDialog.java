 /* Tan Shi QI
 * B1901264
 * 10/04/2022
 *  Gui Add Document
 */
package HelpAID;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import javax.swing.ButtonGroup;

public class AddDocumentDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField filenameTF;
	private JTextField DescriptionTF;
	
	
	
	
	private HELPAid helpAid;
	private User loginUser;
	private Appeal appeal;
	private OrganizationRep user;
	private Organization org;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AddDocumentDialog dialog = new AddDocumentDialog
					(null, null,null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AddDocumentDialog(HELPAid helpAid,User loginUser,Applicant applicant) {
		this.helpAid=helpAid;
		this.loginUser = loginUser;
		OrganizationRep orgRep = (OrganizationRep) loginUser;
		setTitle("Uploading supporting document");
		setModal(true);
		setBounds(100, 100, 570, 220);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(0, 2, 0, 0));
		{
			JLabel descLbl = new JLabel("File name:");
			contentPanel.add(descLbl);
		}
		{
			filenameTF = new JTextField();
			contentPanel.add(filenameTF);
			filenameTF.setColumns(10);
		}
		{
			JLabel tripDateLbl = new JLabel("Description:");
			contentPanel.add(tripDateLbl);
		}
		{
			DescriptionTF = new JTextField();
			contentPanel.add(DescriptionTF);
			DescriptionTF.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			{
				JButton submitBtn = new JButton("Submit");
				submitBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						
						String filename = filenameTF.getText();
						
						String decription = DescriptionTF.getText();
		    	
				    	if (filename.isEmpty() || decription.isEmpty()) {
				    		
				    		JOptionPane.showMessageDialog(
				    				AddDocumentDialog.this, 
				    				"Please fill up everything!");
				    	}
				    	
				    	else {
				    		//add document
				    		applicant.addDocument(new Document(filename,decription));
				    		JOptionPane.showMessageDialog(
				    				AddDocumentDialog.this, 
									"Add successfully!");
				    		filenameTF.setText("");
				    		DescriptionTF.setText("");
				    		
				    	}
						
						
						
					}
				}

				);
				submitBtn.setActionCommand("OK");
				buttonPane.add(submitBtn);
				getRootPane().setDefaultButton(submitBtn);
			}
			{
				JButton CancelBtn = new JButton("Cancel");
				CancelBtn.setActionCommand("OK");
				CancelBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						AddDocumentDialog.this.dispose();
					}
				});
				buttonPane.add(CancelBtn);
			}
		}
	}

}