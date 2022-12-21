package HelpAID;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Driver program for HELP Aid System
 * @author KokChyeHock
 * Date
 */
public class HELPAidConsole {
	static Scanner kbd;
	static HELPAid helpAid;
	static User loginUser;
	static DateTimeFormatter formatter = DateTimeFormatter.
		ofPattern("d/M/yyyy");

	/**
	 * The main method that starts the application.
	 * @param args
	 */
	public static void main(String[] args) {
		kbd = new Scanner(System.in);
		helpAid = new HELPAid();
		
		// initialize system's administrator
		User admin = new OrganizationRep("admin", "Administrator",
			"admin@HELPAid.com", "012-123", "Superuser",
			null);
		admin.setPassword("admin");
		
		helpAid.addUser(admin);
		addOthers(); // adding other data
		
		boolean done = false;
        do {
            String choice = mainMenu();
            switch (choice) {
	            case "0":
	            	System.out.println("Done for the day...");
	            	done = true;
	            	break;
	            case "1":
	            	if (helpAid.numOfOrganizations() == 0)
	            		System.out.println("Sorry... There does not" +  
	            			" have any registered organization.");
	            	else
	            		registerApplicant(null);
	            	break;
	            case "2":
	                login();
	                break;
	            case "3":
	            	viewAppeals();
	            	break;
	            case "4":
	            	makeCashContribution();
	            	break;
	            case "5":
	                displayAllUsers();
	                break;
	            case "6":
	            	displaySortedAppeals();
	            	break;
	            default:
	                System.out.println("Invalid choice");       
            }
            System.out.println();
        } while (!done);
	}

	/**
	 * Display the main menu, and get the user's response
	 * @return The menu option chosen by user
	 */
	public static String mainMenu() {
		System.out.println("HELP Aid System");
		System.out.println("~~~~~~~~~~~~~~~");        	
		System.out.println("1. Register as Applicant");
		System.out.println("2. Log In");
		System.out.println("3. View Appeals");
		System.out.println("4. Make Cash Donation");
		System.out.println("5. Display all users");
		System.out.println("6. Display sorted appeals");
		System.out.println();       	
		System.out.println("0. Quit");
	    System.out.print("Your choice? ");
	    String choice = kbd.nextLine();
	    return choice;
	}

//+++++++++++++++
	/**
	 * PRE-CONDITION: Organizations exist in system.
	 * @return
	 */
	public static Organization selectOrganization() {
		Organization organization = null;
		boolean done = false;
		do {
			System.out.println("List of registered organisations:");
			System.out.println(helpAid.allOrganizations());
			System.out.println();
			String orgID = getText("Please enter organization ID: ");
			organization = helpAid.findOrg(orgID);
			if (organization == null)
				System.out.println("Invalid ID!");
			else {
				//System.out.println("Selected organization:");
				//System.out.printf("%s [%s] at %s", organization.getOrgName(),
				//	organization.getOrgID(), organization.getAddress());
				done = true;	
			}
		} while (!done);
		
		return organization;
	}
	
	/**
	 * Registering new applicants.
	 */
	public static void registerApplicant(Organization organization) {		
		if (organization == null)
			organization = selectOrganization();

		System.out.print("Organization: ");
		System.out.printf("%s [%s] at %s", organization.getOrgName(),
			organization.getOrgID(), organization.getAddress());
		System.out.println();
		String fullName = getText("Full name: ");
		String email = getText("Email: ");
		String mobileNo = getText("Mobile number: ");
		String idNo = getText("ID number: ");
		
		if (helpAid.duplicateApplicant(idNo)) {
			System.out.println("This IDno '" + idNo + 
				"'has been registered!");
			System.out.println("You can only registered " + 
				"in ONE organization!");
			System.out.println("Addition aborted ...");
			return ;
		}
		
		String address = getText("Address: ");
		double householdIncome = getNumeric("Household income: ");
		kbd.nextLine();
		
		Applicant applicant = new Applicant(fullName, email,
			mobileNo, idNo,	address, householdIncome, 
			organization);
		boolean done = false;
		System.out.println("Uploading supporting document");
		do {
			String filename = getText("File name: ");
			String description = getText("File description: ");
			applicant.addDocument(new Document(filename, description));
			String response = getText("Submit another document " +
				"(<Y>es or <N>o)? ");
			done = response.equalsIgnoreCase("N");
		} while (!done);
		
		organization.add(applicant);
		helpAid.addUser(applicant);
		
		System.out.println("Applicant added successfully!");
	}
		
    /**
     * Method that initiate user login.
     * Once login, a different menu options will be 
     * displayed, depends on the type of login user.
     */
    public static void login() {
    	boolean finished = false;
    	do {   		
            String username = getText("Username: ");
            loginUser = helpAid.findUser(username);
    		if (loginUser == null) {
            	System.out.println("User name DOES NOT exist!");
          	   	return ;
    		}
    		else {
    			boolean correctPassword = false;
    			do {
			        String inPassword = getText("Password: ");
			        correctPassword = loginUser.getPassword().equals(inPassword); 
			    	if (!correctPassword)
			            System.out.println("Invalid password!");
    			} while (!correctPassword);
        	}
    		finished = true;
    	} while (!finished);

    	// Once login different menu will display,
    	// depending what type of User.
        System.out.println();
        if (loginUser instanceof OrganizationRep) {
        	OrganizationRep user = (OrganizationRep) loginUser;
        	if (user.getUsername().equalsIgnoreCase("Admin"))
        		adminTasks();
        	else 
        		orgRepresentativeTasks();
        }
        else
        	applicantTasks();
    }

    /**
     * Method for viewing appeals by Donor
     */
    public static void viewAppeals() {
		System.out.println();
		if (helpAid.numOfAppeals() == 0)
			System.out.println("No appeal has been created.");
		else {
			boolean done = false;
			do {
				System.out.println("View ");
				System.out.println("1. Current Appeals");
				System.out.println("2. Past Appeals");
				System.out.println();
				System.out.println("0. Back to Main");
				String response = getText("Your choice? ");
				switch (response) {
				case "0":
					done = true;
					break;
				case "1":
					ArrayList<Appeal> currentAppealList = 
						helpAid.currentAppealList();
					if (currentAppealList.isEmpty()) {
						System.out.println("No current appeals!");
					}
					else {
						System.out.println("Current Appeals:");
						viewAppealDetails(currentAppealList);
					}
					break;
				case "2":
					ArrayList<Appeal> pastAppealList = 
						helpAid.pastAppealList();
					if (pastAppealList.isEmpty()) {
						System.out.println("No past appeals!");
					}
					else {
						System.out.println("Past Appeals:");
						viewAppealDetails(pastAppealList);
					}
					break;
				default:
					System.out.println("Invalid choice! Try Again!");
					break;
				}
				System.out.println();
			} while (!done);
		}    	
    }
    
    /**
     * Helper method for viewing appeals by donor.
     * @param appealList Array list of Appeal
     */
    public static void viewAppealDetails(ArrayList<Appeal> appealList) {
    	appealList.forEach(a -> System.out.println(a.summaryOfAppeal()));
    	System.out.println();
    	String appealID = getText("Select appeal ID to view detail: ");
    	Appeal appeal = appealList.stream()
    		.filter(a -> appealID.equalsIgnoreCase(a.getAppealID()))
    		.findAny()
    		.orElse(null);
    	
    	if (appeal == null)
    		System.out.println("Invalid appeal ID!");
    	else {
    		System.out.println("Appeal by " + 
    			appeal.getOrganizationName() + " at " + 
    			appeal.getOrganization().getAddress());
    	}
    }
    
    /**
     * Method where Applicant make a cash donation online.
     */
    public static void makeCashContribution() {
    	System.out.println();
		if (helpAid.numOfAppeals() == 0)
			System.out.println("No appeal has been created.");
		else {
			System.out.println();
			
			ArrayList<Appeal> currentAppeals = helpAid.currentAppealList();
			if (currentAppeals.isEmpty())
				System.out.println("There does not have current appeals!");
			else {
				System.out.println("Current Appeals:");
				currentAppeals.forEach(System.out::println);

				System.out.println();
				String appealID = getText("Enter appeal ID: ");
				
				Appeal appeal = currentAppeals.stream()
		    		.filter(a -> appealID.equalsIgnoreCase(a.getAppealID()))
		    		.findAny()
		    		.orElse(null);
				
				if (appeal == null)
					System.out.println("Wrong appeal ID!");
				else {
					System.out.println(appeal.summaryOfAppeal());
					System.out.print("Amount? ");
					double amount = kbd.nextDouble();
					kbd.nextLine();
					String paymentChannel = getText("Payment channel: ");
					String referenceNo = getText("Reference number: ");
					Contribution contribution = new CashDonation(appeal, 
						amount, paymentChannel, referenceNo);
					appeal.addContribution(contribution);
					System.out.println();
					System.out.println(contribution);
					System.out.println("Contribution recorded. Thanks.");
				}
			}
		}
    }
    
    /**
     * Display a menu showing the options what an Administrator
     * can do, obtain user response, and carried out the appropriate
     * actions
     */
	public static void adminTasks() {
		System.out.println("Welcome, " + loginUser.getFullname() + 
			" [Administrator]");
		boolean done = false;
		do {
	        String choice = adminMenu();
	        switch (choice) {
	        	case "0":
	        		done = true;
	        		System.out.println("Logging out...");
	        		break;
	        	case "1": 
	        		createOrganization();
	        		break;
	        	case "2": 
	        		createOrganizationRep();
	        		break;
	        	default:
	                System.out.println("Invalid choice");
	        }
	        System.out.println();
		} while (!done);
	}

	/**
	 * Display the Administrator menu options, and get the 
	 * user's response
	 * @return The menu option chosen by user
	 */
	public static String adminMenu() {
		System.out.println("Administrator Menu");
		System.out.println("~~~~~~~~~~~~~~~~~~");
        System.out.println("1. Add Organization");
        System.out.println("2. Add Organization Representative");
        System.out.println();
        System.out.println("0. Log Out");
        System.out.println();
        System.out.print("Your choice: ");
        String choice = kbd.nextLine();
        return choice;		
	}

	/**
	 * Create a new organization, by admin
	 */
	public static void createOrganization() {
		System.out.println();
		String name = getText("Orgnization name: ");
		String address = getText("Organization address: ");
		Organization organization = new Organization(name, address);
		if (helpAid.addOrganization(organization))
			System.out.println("Addition success...");
		else
			System.out.println("Addition failed...");
	}
	
	/**
	 * Admin creating new organization representative
	 */
	public static void createOrganizationRep() {
		if (helpAid.numOfOrganizations() == 0)
			System.out.println("Please add an organization ...");
		else {
			System.out.println("Organizations:");
			System.out.println(helpAid.allOrganizations());
			System.out.println();
			String orgID = getText("Please enter organization ID: ");
			Organization foundOrganization = helpAid.findOrg(orgID);
			if (foundOrganization == null)
				System.out.println("Invalid ID!");
			else {
				System.out.println("Adding new representative for " + 
					foundOrganization.getOrgName());
				boolean done = false;
				do {
					String username = getUsername();
					String fullName = getText("Full name: ");
					String email = getText("Email: ");
					String mobileNo = getText("Mobile number: ");
					String jobTitle = getText("Job title: ");
					OrganizationRep orgRep = new OrganizationRep( 
						username,fullName, email, mobileNo, jobTitle, 
						foundOrganization);
					helpAid.addUser(orgRep);
					foundOrganization.add(orgRep);
					System.out.println("Addition success...");
					String response = getText("Add another " +
						"representative (<Y>es or <N>o)? ");
					done = response.equalsIgnoreCase("N");
				} while (!done);
			}
		}
	}

    /**
     * Display a menu showing the options what an organization
     * representative can do, obtain user response, and 
     * carried out the appropriate actions
     */
	public static void orgRepresentativeTasks() {
		OrganizationRep orgRep = (OrganizationRep) loginUser;
		System.out.println("Welcome, " + orgRep.getFullname() + 
			" [" + orgRep.getJobTitle() + " of " + 
			orgRep.getOrganization().getOrgName() + "]");
		
		boolean done = false;
		do {
	        String choice = orgRepresentativeMenu();
	        switch (choice) {
		        case "0":
		        	System.out.println("Logging out...");
		        	done = true;
		        	break;
		        case "1": 
	        		registerApplicant(orgRep.getOrganization());
	        		break;
		        case "2": 
	        		organisedAidAppeal(orgRep);
	        		break;
	        	case "3":
	        		recordContribution(orgRep);
	        		break;
	        	case "4":
	        		recordDisbursement(orgRep);
	        		break;	        	
	        	default:
	                System.out.println("Invalid choice");
	        }
	        System.out.println();
		} while (!done);		
	}
	
	/**
	 * Display the organization representative menu options, and get 
	 * the staff's response
	 * @return The menu option chosen by user
	 */
	public static String orgRepresentativeMenu() {
		System.out.println("Organization Representative Menu");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("1. Register Applicant");
		System.out.println("2. Organize Aid Appeal");
		System.out.println("3. Record Contribution");
		System.out.println("4. Record Aid Disbursement");
        System.out.println();
        System.out.println("0. Log Out");
        System.out.println();
        System.out.print("Your choice: ");
        String choice = kbd.nextLine();
        return choice;
	}

	/**
	 * Organising new aid appeal
	 * @param user Organization representative that organised the appeal
	 */
	public static void organisedAidAppeal(OrganizationRep user) {
		
		LocalDate fromDate = getDate("From date (dd/mm/yyyy): ");
		LocalDate toDate = getDate("To date (dd/mm/yyyy): ");
		String appealDescription = getText("Description: ");
		Organization organization = user.getOrganization();
		Appeal appeal = new Appeal(fromDate, toDate, appealDescription, 
			organization);
		organization.add(appeal);
		helpAid.addAppeal(appeal);
		System.out.println("Appeal successfully added ... ");
	}

	/**
	 * Method for organization representative to record contributions
	 * by kind donors.
	 * @param user Organization representative who will record the 
	 * contribution
	 */
	public static void recordContribution(OrganizationRep user) {
		System.out.println();
		Organization organization = user.getOrganization();
		if (organization.numOfAppeals() == 0)
			System.out.println("No appeal has been created.");
		else {
			System.out.println();
			String currentAppeals = organization.listCurrentAppeals();
			if (currentAppeals.trim().isEmpty())
				System.out.println("No current appeals...");
			else {
				System.out.println("Current Appeals of " + 
					organization.getOrgName() + ":");
				System.out.println(currentAppeals);
				String appealID = getText("Enter appeal ID: ");
				Appeal appeal = organization.findAppeal(appealID);
				if (appeal == null || appeal.isPastAppeal())
					System.out.println("Wrong appeal ID!");
				else { 
					System.out.println(appeal.summaryOfAppeal());
					String contributionType = getText("Contribute " + 
						"<C>ash or <G>oods? ");
					Contribution contribution = null;
					if (contributionType.equalsIgnoreCase("C")) {
						double amount = getNumeric("Amount? ");
						kbd.nextLine();
						String paymentChannel = getText("Payment channel: ");
						String referenceNo = getText("Reference number: ");
						contribution = new CashDonation(appeal, amount, 
							paymentChannel, referenceNo);
					} else {
						System.out.println();
						double estimatedValue = getNumeric("Estimated value" +
							" of goods: ");
						kbd.nextLine();
						String description = getText("Description " +
							"of goods: ");
						contribution = new Goods(appeal, description,
							estimatedValue);
					}
					// helpAid's appeal should be UPDATED as well
					// as both are alias
					appeal.addContribution(contribution);
					System.out.println();
					System.out.println("Contribution received:\n" + 
						contribution);
				}
			}
		}		
	}
	
	/**
	 * Organization representative to record disbursement to applicant.
	 * @param user Organization representative that record the
	 * disbursement.
	 */
	public static void recordDisbursement(OrganizationRep user) {
		System.out.println();
		Organization organization = user.getOrganization();
		if (organization.numOfAppeals() == 0)
			System.out.println("No appeal has been created.");
		else {
			System.out.println("Current Appeals of '" + 
				organization.getOrgName() + "':");
			System.out.println(organization.listCurrentAppeals());
			System.out.println();
			String appealID = getText("Enter appeal ID: ");
			Appeal appeal = organization.findAppeal(appealID);
			if (appeal == null)
				System.out.println("Wrong appeal ID!");
			else {
				System.out.println(appeal);
				if (appeal.numOfContributions() != 0) {
					System.out.println("Contributions:");
					System.out.println(appeal.allContributions());
					System.out.println();
					if (organization.numOfApplicants() != 0) {
						String allApplicants = organization.allApplicants();
						System.out.print("Press <Enter> " + 
							"key to view the applications");
						kbd.nextLine();
						
						boolean done = false;
						do {
							System.out.println("Applicants:");
							System.out.println(allApplicants);
							String IDno = getText("Enter ID no " +
								"of applicant: ");
							Applicant app = 
								organization.findApplicantByID(IDno);
							if (app != null) {
								System.out.printf("%s at %s with income" +
									"of %.2f\n", app.getFullname(), 
									app.getAddress(), 
									app.getHouseholdIncome());
								
								String response = getText("Viewing " +
									"applicants's document " + 
									"(<Y>es or <N>o): ");
								if (response.equalsIgnoreCase("Y"))
									System.out.println(app.allDocuments());
								System.out.println();
								
								LocalDate disbursementDate = 
									getDate("Disbursed date (dd/mm/yyy): ");
								System.out.print("Amount: ");
								double amount = kbd.nextDouble();
								kbd.nextLine();
								String goodsProvided = 
									getText("Goods provided: ");
								Disbursement disbursement = new Disbursement(
									disbursementDate, amount, goodsProvided,
									app);
								app.addDisbursement(disbursement);
								appeal.addDisbursement(disbursement);

								System.out.println("Details: " + appeal);			
								System.out.println("Disbursement done...");					
								String outcome = getText("Appeal outcome: ");
								appeal.setOutcome(outcome.toUpperCase());
								
								System.out.println();
								response = getText("More disbursement " +
									"(<Y>es or <N>o)? ");
								if (response.equalsIgnoreCase("N"))
									done = true;
								System.out.println();
							}
							else
								System.out.println("Wrong ID no!");
						}while (!done);
					}
					else
						System.out.println("No applicant yet...");
				}
				else
					System.out.println("No contributions yet...");
			}
		}
	}
	
    /**
     * Display a menu showing the options what an applicant
     * can do, obtain user response, and carried out the appropriate
     * actions
     */
	public static void applicantTasks() {
		Applicant applicant = (Applicant) loginUser;
		System.out.println("Welcome, " + applicant.getFullname() + 
			" [Applicant of " + 
			applicant.getOrganization().getOrgName() + "]");
		
		boolean done = false;
		do {
	        String choice = applicantMenu();
	        switch (choice) {
	        case "0":
	        	System.out.println("Logging out...");
	        	done = true;
	        	break;
	        case "1":
	        	viewDisbursements(applicant);
	        	break;
	        default:
	        	System.out.println("Invalid choice");
	        } 
	        System.out.println();
		} while (!done);		
	}

	/**
	 * Display the Applicant menu options, and get the user's 
	 * response
	 * @return The menu option chosen by user
	 */
	public static String applicantMenu() {
		System.out.println("Applicant Menu");
		System.out.println("~~~~~~~~~~~~~~");
		System.out.println("1. View Disbursement");
        System.out.println();
        System.out.println("0. Log Out");
        System.out.println();
        System.out.print("Your choice: ");
        String choice = kbd.nextLine();
        return choice;
	}	

	/**
	 * Method to view disbursements received by an applicant
	 * @param Volunteer who initiate the option
	 */
	public static void viewDisbursements(Applicant user) {
		if (user.numOfDisbursements() == 0) {
			System.out.println("You have not been given " +
				"any disbursements yet.");
			return ;
		}
		System.out.println("\nDisbursements:");
		user.getDisbursements().stream()
			.forEach(System.out::println);
	}
	
	/**
	 * Display all users in the system
	 */
	public static void displayAllUsers() {
       System.out.println();
	   if (helpAid.numOfUsers() == 0) {
	       System.out.println("No users has been registered yet");
	   } 
	   else {
		   String criteria = getText("Criteria? " +
				"(<O>riginal or <F>ullname) ").toUpperCase();
	       System.out.println("List of users:");
	       System.out.println(criteria.charAt(0)=='O'? 
				helpAid.allUsers(): 
				helpAid.listUsersSortedByFullname());
	   }		
	}
	
	/**
	 * Display all appeals submitted in the system, sorted
	 * according to Organization Name followed by fromDate.
	 */
	public static void displaySortedAppeals() {
		System.out.println();
		if (helpAid.numOfAppeals() == 0)
			System.out.println("No appeal has been created.");
		else {
			System.out.println("All appeals sorted according Organization " +
				"name, followed by fromDate:");
			System.out.println(helpAid.listSortedAppeals());
		}
	}
	
    /**
     * Method used to initialize some domain objects, such as 
     * Organization, OrganizationRep (admin), and Applicant.
    */
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

    // The following methods are used for getting user input, 
    // and also performing validation checks
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    /**
     * Get a valid username
     * @return String username
     */
    public static String getUsername() {
    	String username = getText("Username: ");
    	while (helpAid.findUser(username) != null) {
            System.out.println("Username already exists. "
                    + "Please enter another username!\n");
            username = getText("Username: ");
    	}
    	return username;
    }

    /**
     * Get a valid date
     * @return date a valid LocalDate
     */
    public static LocalDate getDate(String prompt) {
    	String dateJoinedStr = getText(prompt);
    	LocalDate date = LocalDate.parse(dateJoinedStr, formatter); 
    	return date;
    }

    /**
     * Getting text input from user, with validation for empty string. 
     * Value of input depends on the prompt.
     * @param prompt The prompt showing what information is
     * being read.
     * @return text The textual value entered by user.
     */
    public static String getText(String prompt) {
        System.out.print(prompt);
        String text = kbd.nextLine().trim();
        while (text.equals("")) {
            System.out.println("Entry cannot be blank! "
                    + "Please enter again!\n");
            System.out.print(prompt);
            text = kbd.nextLine().trim();
        }
        return text;
    }
    
    /**
     * Getting a positive numeric value
     * @param prompt Prompt that display the message
     * @return a positive numeric value
     */
    public static double getNumeric(String prompt) {
    	System.out.print(prompt);
    	double value = kbd.nextDouble();
    	while (value <= 0) {
    		System.out.println("Value must be positive!");
    		System.out.print(prompt);
        	value = kbd.nextDouble();
    	}
    	return value;
    }
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
}