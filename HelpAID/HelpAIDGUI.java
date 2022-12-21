/*
 * Version: 2021-12 (4.22.0)
 * Tan Shi QI
 * B1901264
 * 10/04/2022
 * GUI Help AID
 */
package HelpAID;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.awt.event.ActionEvent;

public class HelpAIDGUI extends JFrame {
	
	private JPanel contentPane;
	private static HELPAid helpAid;
	private User loginUser;
	private Applicant applicant;
	private Appeal appeal;
	private loginDialog logindialog;
	private AllUsersDialog usersDialog;
	private AddRegisterRepDialog allorgdialog;
	private AllAppealDialog allappealdialog;
	private SignUpDialog signupdialog;
	private AllAppealSortedDialog sortedAppealdialog;
	private DonorContributionDialog contributionialog;
	private JFileChooser fileChooser;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HelpAIDGUI frame = new HelpAIDGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public HelpAIDGUI() {
		setTitle("Help AID System");
		helpAid = new HELPAid();
		fileChooser = new JFileChooser();
		// initialize system's administrator
		User admin = new OrganizationRep("admin", "Administrator",
			"admin@HELPAid.com", "012-123", "Superuser",
			null);
		admin.setPassword("admin");
		
		helpAid.addUser(admin);
		addOthers(); // adding other data
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);
		JMenuItem loadbtn = new JMenuItem("Load");
		loadbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 int option = fileChooser.showOpenDialog(
	                		HelpAIDGUI.this);
				 if(option == JFileChooser.APPROVE_OPTION) {
	                    File file = fileChooser.getSelectedFile();
	                    try (ObjectInputStream ois = new 
	                    		ObjectInputStream(
	                            new FileInputStream(file));)
	                    {
	                        helpAid = (HELPAid) ois.readObject();
	                        //Applicant.nextApplicantID(helpAid.numOfUsers()+1);
	                        JOptionPane.showMessageDialog(HelpAIDGUI.this, 
	                            "Loaded Successfully", "File loader"
	                            , 1);    
	                    }
	                    catch (IOException ioe)
	                    {
	                        ioe.printStackTrace();
	                    }
	                    catch (ClassNotFoundException cnfe)
	                    {
	                        System.out.println(cnfe.getMessage());
	                        cnfe.printStackTrace();
	                    }
	                    catch(Exception ex)
	                    {
	                        ex.printStackTrace();
	                    }
	                }
			}
		});
		mnNewMenu.add(loadbtn);
		
		JMenuItem savebtn = new JMenuItem("save");
		savebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int option = fileChooser.showSaveDialog(
						HelpAIDGUI.this);
                File file = fileChooser.getSelectedFile();
                if(option == JFileChooser.APPROVE_OPTION) {
                    try (ObjectOutputStream oos = new 
                    		ObjectOutputStream(
                            new FileOutputStream(file));) {
                        oos.writeObject(helpAid);
                        oos.flush();
                        JOptionPane.showMessageDialog(
                        		HelpAIDGUI.this, 
                            "Saved Successfully", "File loader"
                            , 1);
                    }
                    catch (IOException ioe)
                    {
                        ioe.printStackTrace();
                    }
                    catch(Exception ex)
                    {
                        ex.printStackTrace();
                    }
                }
			}
		});
		mnNewMenu.add(savebtn);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Help AID System");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblNewLabel, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(7, 0, 0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		
		JButton btnRegister = new JButton("Register As Applicant");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SignUpDialog signupdialog;
				signupdialog = new SignUpDialog(helpAid,applicant);
				signupdialog.setVisible(true);
			}
		});
		btnRegister.setBackground(Color.WHITE);
		panel_1.add(btnRegister);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBackground(Color.WHITE);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logindialog = new loginDialog(helpAid);
				logindialog.setVisible(true);
			}
		});
		panel_2.add(btnLogin);
		
		JPanel panel_3 = new JPanel();
		panel.add(panel_3);
		
		JButton btnViewAppeal = new JButton("View Appeals");
		btnViewAppeal.setBackground(Color.WHITE);
		btnViewAppeal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				allappealdialog = new AllAppealDialog(HelpAIDGUI.this,helpAid);
				allappealdialog.setVisible(true);
			}
		});
		panel_3.add(btnViewAppeal);
		
		JPanel panel_4 = new JPanel();
		panel.add(panel_4);
		
		JButton btnCashDonation = new JButton("Make Contribution");
		btnCashDonation.setBackground(Color.WHITE);
		btnCashDonation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contributionialog = new DonorContributionDialog(helpAid,appeal);
				contributionialog.setVisible(true);
			}
		});
		panel_4.add(btnCashDonation);
		
		JPanel panel_5 = new JPanel();
		panel.add(panel_5);
		
		JButton btnViewUser = new JButton("Display all users");
		btnViewUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				usersDialog = new AllUsersDialog
						(HelpAIDGUI.this,helpAid);
				usersDialog.pack();
				usersDialog.setVisible(true);
				
			}
		});
		btnViewUser.setBackground(Color.WHITE);
		panel_5.add(btnViewUser);
		
		JPanel panel_6 = new JPanel();
		panel.add(panel_6);
		
		JButton btnAppeal = new JButton("Display sorted appeals");
		btnAppeal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sortedAppealdialog = new AllAppealSortedDialog
						(HelpAIDGUI.this,helpAid);
				sortedAppealdialog.setVisible(true);
			}
		});
		btnAppeal.setBackground(Color.WHITE);
		panel_6.add(btnAppeal);
		
		JPanel panel_7 = new JPanel();
		panel.add(panel_7);
		
		JButton btnNewButton = new JButton("Logout");
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HelpAIDGUI.this.dispose();
			}
		});
		panel_7.add(btnNewButton);
	}

	public static void addOthers() {
    	Organization oracle = new Organization("Oracle", "Jalan Ipoh");
    	Organization amazon = new Organization("Amazon", "Jalan Kuching");
    	
    	helpAid.addOrganization(oracle);
    	helpAid.addOrganization(amazon);
    	
    	OrganizationRep orgRep01 = new OrganizationRep("james",
    		"James Bond","jb@gmail.com", "012-123", "Manager",
    		oracle);
    	oracle.add(orgRep01);
    	helpAid.addUser(orgRep01);
 
    	OrganizationRep orgRep02 = new OrganizationRep("spiderman",
    		"Peter Parker","pp@gmail.com", "012-123", "Executive",
    		amazon);
    	amazon.add(orgRep02);
    	helpAid.addUser(orgRep02);		
	
        Applicant app1 = new Applicant("Poor Man", "pm@gmail.com",
    		"012-345", "20202020", "5, Jln 123", 1000.00,
    		oracle);
        app1.addDocument(new Document("income.xlsx", "Income Tax file"));
    	oracle.add(app1);
    	helpAid.addUser(app1);
    }

}
