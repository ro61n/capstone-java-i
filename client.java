package task7;

//import modules
import java.util.Scanner;//allows for user input
import java.util.Locale;//allows for decimal units. source: https://stackoverflow.com/questions/34226345/inputmismatchexception-using-scannernextdouble-with-valid-values
import java.time.LocalDate;//allows for date comparisons
import java.util.Arrays;//allows arrays to be displayed. Source: https://www.geeksforgeeks.org/array-class-in-java/ 

public class client {
	
	//declare scanner variable
	static Scanner userInput = new Scanner(System.in);
	
	//declare project and person arrays
	static project [] allProjects = new project[5];
	static person [] allPeople = new person[5];

	public static void main(String[] args) {
		
		//Print welcome message
		System.out.println("Welcome!");
		
		//declare variable to control while loop
		boolean appLoop = true;
		
		//app loop
		while (appLoop) {
			
			//print message to user
			System.out.println("Please select an option: ");
			System.out.println("c\t-\tCreate new project");
			System.out.println("d\t-\tChange due date of project");
			System.out.println("t\t-\tChange total amount of fee paid to date");
			System.out.println("u\t-\tUpdate contractor's details");
			System.out.println("f\t-\tFinalise a project");
			System.out.println("e\t-\tExit application");
			System.out.println("\nWhich option would you like to select:");
			
			//get user input
			char userOption = userInput.next().charAt(0);
			
			//run command (method) based on user input
			if (userOption=='c') {
				createProject();
			}
			else if (userOption=='d') {
				changeDueDate();
			}
			else if (userOption=='t') {
				changeTotPaid();
			}
			else if (userOption=='u') {
				updateContractor();
			}
			else if (userOption=='f') {
				finaliseProject();
			}
			else if (userOption=='e') {
				
				//print exit message
				System.out.println("Exit. Application Closed.");
				
				//change boolean variable to end while loop
				appLoop = false;
				
			}
			
		}

	}
	
	//method for finalising a project
	public static void finaliseProject() {
		
		//print message to user
		System.out.println("\nFinalise:");
		System.out.println("Projects listed below:");
		System.out.println("Project Number\tName\t\tTotal Fee\tTotal Paid");
		
		//for loop to display all projects
		for (int i=0; i<allProjects.length; i++) {
			if (allProjects[i] != null) {
				System.out.println(allProjects[i].getNumber()+"\t\t"+allProjects[i].getName()+"\t\t"+allProjects[i].getTotalFee()+"\t\t"+allProjects[i].getTotalPaid());
			}
		}
		
		System.out.println("\nPlease select a project number:");
		
		//get user input
		int projectNum = userInput.nextInt();
		
		//look for project that user selected and finalise that project
		for (int i=0; i<allProjects.length; i++) {
			if (allProjects[i] != null) {
				if (allProjects[i].getNumber()==projectNum) {
					System.out.println(allProjects[i].finaliseProject());
				}
			}
		}
		
		//print success message
		System.out.println("\nProject Successfully Finalised\n");
		
	}
	
	//method for updating contractor
	public static void updateContractor() {
		
		//print messsage to user
		System.out.println("\nProject / Contractor details below:");
		
		//declare variable
		String oldName="";
		
		//display all projects to user
		for (int i=0; i<allProjects.length; i++) {
			if (allProjects[i] != null) {
				System.out.println("Project Number: "+allProjects[i].getNumber()+"\tName: "+allProjects[i].getName());
				System.out.println("Contractor Details:");
				System.out.println(allProjects[i].getContractor().toString());
			}
		}
		
		System.out.println("\nPlease select a project number (to update that project's contractor):");
		
		//get user's selection
		int projectNum = userInput.nextInt();
		
		userInput.nextLine();  // Consume newline left-over -- solving the problem of using nextLine after nextInt(). Source: https://stackoverflow.com/questions/13102045/scanner-is-skipping-nextline-after-using-next-or-nextfoo
		
		//following lines get new information about contractor
		System.out.println("Enter new details below");
		
		System.out.println("Contractor's Name:");
		String newName = userInput.nextLine();
		
		System.out.println("Telephone:");
		String newTelephone = userInput.nextLine();
		
		System.out.println("Email:");
		String newEmail = userInput.nextLine();
		
		System.out.println("Address:");
		String newAddress = userInput.nextLine();
		
		//look for selected project and update that contractor
		for (int i=0; i<allProjects.length; i++) {
			if (allProjects[i] != null) {
				if (allProjects[i].getNumber()==projectNum) {
					
					//loop through people objects to find contractor
					for (int j=0; j<allPeople.length; j++) {
						if (allPeople[j] != null) {
							
							//update info if correct contractor
							if (allPeople[j].getName() == allProjects[i].getName()) {
								allPeople[j].setName(newName);
								allPeople[j].setTelephone(newTelephone);
								allPeople[j].setEmail(newEmail);
								allPeople[j].setAddress(newAddress);
							}
							
						}
						
					}
					
					//update this specific contractor
					allProjects[i].setContractor(newName, newTelephone, newEmail, newAddress);
					
					//print success message
					System.out.println("\nContractor's details successfully updated!\n");
					
				}
			}
		}
		
	}
	
	//method that updates the total paid amount
	public static void changeTotPaid() {
		userInput.useLocale(Locale.US); //Change decimal system to . not , source: https://stackoverflow.com/questions/5929120/nextdouble-throws-an-inputmismatchexception-when-i-enter-a-double
		
		//print message to user
		System.out.println("\nChange Total Paid:");
		System.out.println("Projects listed below:");
		System.out.println("Project Number\tName\t\tTotal Fee\tTotal Paid");
		
		//loop through and display all projects
		for (int i=0; i<allProjects.length; i++) {
			if (allProjects[i] != null) {
				System.out.println(allProjects[i].getNumber()+"\t\t"+allProjects[i].getName()+"\t\t"+allProjects[i].getTotalFee()+"\t\t"+allProjects[i].getTotalPaid());
			}
		}
		
		System.out.println("\nPlease select a project number:");
		
		//get user selection
		int projectNum = userInput.nextInt();
		
		//loop through all projects
		for (int i=0; i<allProjects.length; i++) {
			
			if (allProjects[i] != null) {
				
				//find correct project based on user input
				if (allProjects[i].getNumber()==projectNum) {
					
					System.out.println("Enter updated paid amount:");
					
					//get user input (updated amount)
					double updatedPaid = userInput.nextDouble();
					
					//update paid amount
					allProjects[i].setTotalPaid(updatedPaid);
					
					//display success message to user
					System.out.println("Total Paid has been successfully updated");
					System.out.println(allProjects[i].getNumber()+"\t\t"+allProjects[i].getName()+"\t\t"+allProjects[i].getTotalFee()+"\t\t"+allProjects[i].getTotalPaid()+"\n");
					
				}
				
			}
		}
				
		
		
	}
	
	//method that changes project due date
	public static void changeDueDate() {
		
		//display message to user
		System.out.println("\nProjects listed below:");
		System.out.println("Project Number\tName\t\tDue Date");
		
		//loop through and display all projects
		for (int i=0; i<allProjects.length; i++) {
			if (allProjects[i] != null) {
				System.out.println(allProjects[i].getNumber()+"\t\t"+allProjects[i].getName()+"\t\t"+allProjects[i].getDeadline());
			}
		}
		
		System.out.println("\nPlease select a project number:");
		
		//get user's chosen project
		int projectNum = userInput.nextInt();
		
		//for loop to find selected project
		for (int i=0; i<allProjects.length; i++) {
			
			if (allProjects[i] != null) {
				
				if (allProjects[i].getNumber()==projectNum) {
					
					userInput.nextLine(); // Consume newline left-over -- solving the problem of using nextLine after nextInt(). Source: https://stackoverflow.com/questions/13102045/scanner-is-skipping-nextline-after-using-next-or-nextfoo
					
					System.out.println("Enter new deadline [format: 'yyyy-mm-dd']");
					
					//get new deadline from user
					String newDeadlineStr = userInput.nextLine();
					
					//convert deadlineStr to date variable
					LocalDate newDeadline = LocalDate.parse(newDeadlineStr);
					
					//update deadline
					allProjects[i].setDeadline(newDeadline);
					
					//print success message
					System.out.println("Due date has successfully been changed!");
					System.out.println(allProjects[i].getNumber()+"\t"+allProjects[i].getName()+"\t"+allProjects[i].getDeadline());
				}
				
			}
			
		}
		
	}
	
	//method to create a new project
	public static void createProject() {
		
		userInput.useLocale(Locale.US); //Change decimal system to . not , source: https://stackoverflow.com/questions/5929120/nextdouble-throws-an-inputmismatchexception-when-i-enter-a-double
		
		//print message to user
		System.out.println("\nCreating a new project");
		System.out.println("Please enter the following details...");
		
		//The following lines ask user to input variables for new project
		
		System.out.println("Project Number:");
		int projectNum = userInput.nextInt();
		
		userInput.nextLine();  // Consume newline left-over -- solving the problem of using nextLine after nextInt(). Source: https://stackoverflow.com/questions/13102045/scanner-is-skipping-nextline-after-using-next-or-nextfoo 
		
		System.out.println("Project Name:");
		String projectName = userInput.nextLine();
		
		System.out.println("Building Type:");
		String buildingType = userInput.nextLine();
		
		System.out.println("Address:");
		String address = userInput.nextLine();
		
		System.out.println("Erf Number:");
		int erfNum = userInput.nextInt();
		
		System.out.println("Total Fee:");
		double totFee = userInput.nextDouble();
		
		System.out.println("Total Paid:");
		double totPaid = userInput.nextDouble();
		
		userInput.nextLine();
		
		System.out.println("Deadline [format: 'yyyy-mm-dd']:");
		String deadlineStr = userInput.nextLine();
		
		//convert deadlineStr to date variable
		LocalDate deadline = LocalDate.parse(deadlineStr);
		
		//display architect options to user
		System.out.println("\nArchitect options:");
		System.out.println("a\t-\tAdd an existing architect (option not available yet)");//I will make this option available when we use text files for storage
		System.out.println("n\t-\tNew architect");
		System.out.println("Please select an option:");
		
		//get user's selected option
		char archSelection = userInput.next().charAt(0);
		
		//declare architect variable
		person architect = null;
		
		//if user opts to create new architect
		if (archSelection=='n') {
			
			System.out.println("Create New Architect...");
			
			//create new person as an architect
			architect = createPerson();
		}
		
		//display contractor options
		System.out.println("\nContractor options:");
		System.out.println("a\t-\tAdd an existing contractor");//I will make this option available when we use text files for storage
		System.out.println("n\t-\tNew contractor");
		System.out.println("Please select an option:");
		
		//get user's selection
		char conSelection = userInput.next().charAt(0);
		
		//declare person variable
		person contractor = null;
		
		if (conSelection=='n') {
			
			System.out.println("Create New Contractor...");
			
			//create person as contractor
			contractor = createPerson();
		}
		
		//display customer options
		System.out.println("\nCustomer options:");
		System.out.println("a\t-\tAdd an existing customer");//I will make this option available when we use text files for storage
		System.out.println("n\t-\tNew customer");
		System.out.println("Please select an option:");
		
		//get user's selection
		char custSelection= userInput.next().charAt(0);
		
		//declare person variable
		person customer = null;
		
		if (custSelection =='n') {
			
			System.out.println("Create New Customer...");
			
			//create person as customer
			customer = createPerson();
		}
		
		//additional condition based on instructions
		if (projectName=="") {
			
			String customerName = customer.getName();
			
			//split name by space
			String[] names = customerName.split(" ");
			
			//display project name with instructed changes
			projectName = buildingType +" "+names[1];
		}

		//create new project
		project newProject = new project(projectNum, projectName, buildingType, address, erfNum, totFee, totPaid, deadline, architect, contractor, customer); 
		
		//add new project to project array
		addToProjectArray(newProject);
		
		System.out.println("\nNew project successfully created.");
		
	}
	
	//method to create person
	public static person createPerson() {
		
		//The following lines ask user to input variables for new person
		
		System.out.println("Enter name:");
		String name = userInput.nextLine();
		
		System.out.println("Enter telephone:");
		String telephone = userInput.nextLine();
		
		System.out.println("Enter email:");
		String email = userInput.nextLine();
		
		System.out.println("Enter address:");
		String address = userInput.nextLine();
		
		
		//create new person as newToProject
		person newToProject = new person(name, telephone, email, address);
		
		//add new person to person array
		addToPersonArray(newToProject);
		
		//return person variable
		return newToProject;
		
	}
	
	//method to add person object to person array
	public static void addToPersonArray(person newPerson) {
		
		//declare variables
		boolean stillToAdd = true;
		int countPlaces = 0;
		
		//use while loop to get null position
		while ((stillToAdd) && (countPlaces<allPeople.length)) {
			
			if (allPeople[countPlaces] == null) {
				
				//add new person to null position
				allPeople[countPlaces] = newPerson;
				
				//change boolean to false to stop while loop
				stillToAdd = false;
				
			}
		
			countPlaces++;
		}
	}
	
	//method to add project to project array
	public static void addToProjectArray(project newProject) {
		
		//declare variables
		boolean stillToAdd = true;
		int countPlaces = 0;
		
		//while loop to look for null value
		while ((stillToAdd) && (countPlaces<allProjects.length)) {
			if (allProjects[countPlaces] == null) {
				
				//add project to the first null value
				allProjects[countPlaces] = newProject;
				
				//change boolean to false to stop while loop
				stillToAdd = false;
			
			}
			countPlaces++;
		}
	}
	

}
