package task7;

//	Changed all helper methods to private since it's only accessed within this class

//import modules
import java.util.Scanner;//allows for user input
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;//allows for decimal units. source: https://stackoverflow.com/questions/34226345/inputmismatchexception-using-scannernextdouble-with-valid-values
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;//allows for date comparisons 

public class Main implements Serializable {
	
	//declare scanner variable
	static Scanner userInput = new Scanner(System.in);
	
	//declare project and person list arrays
	static List<Project> allProjects = new ArrayList<Project>();
	static List<Person> allPeople = new ArrayList<Person>();
	
	
	public static void main(String[] args) throws Exception {
		
		//run methods that load people and project objects stored in text files
		loadExistingPeople();
		loadExistingProjects();
		
		//Print welcome message
		System.out.println("Welcome!");

		//app loop
		while (true) {	// boolean loop control variable
			
			//print message to user
			System.out.println("Please select an option: ");
			System.out.println("v\t-\tView active projects");
			System.out.println("o\t-\tView overdue projects");
			System.out.println("s\t-\tFind a specific project");
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
			if (userOption=='s') {
				findProject();
			}
			else if (userOption=='o') {
				viewOverdueProjects();
			}
			else if (userOption=='v') {
				viewActiveProjects();
			}
			else if (userOption=='c') {
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
				
				break;	//	Replaced boolean loop handler with break
				
			}
			else {
				//	Else tell user to input a correct value
				System.out.println("\n"+userOption+" is not an option. Please look at the available options below:\n");
			}
			
		}	//	End of app loop

	}
	
	//	All Methods below serve app loop above
	
	//	Method that allows user to search for a project by typing in the name or project number
	public static void findProject() {
		System.out.println("\nSearch for a project:\n");
		
		userInput.nextLine();  // Consume newline left-over -- solving the problem of using nextLine after nextInt(). Source: https://stackoverflow.com/questions/13102045/scanner-is-skipping-nextline-after-using-next-or-nextfoo
		
		//	Show user difference ways to search
		System.out.println("p\t-\tSearch by project name");
		System.out.println("n\t-\tSearch by project number");
		System.out.println("Selection:");
		
		//	Get user's selected option
		char selection = userInput.next().charAt(0);
		
		//	Based on selected search option, ask user to enter project name or number and retrieve search input
		String searchStr = "";
		if (selection == 'p') {
			System.out.println("Enter project name:");
			userInput.nextLine();
			searchStr = userInput.nextLine();
		}
		else if (selection == 'n') {
			System.out.println("Enter project number:");
			userInput.nextLine();
			searchStr = userInput.nextLine();
		}
		
		//	Declare iterator to loop through project Objects
		Iterator<Project> projIterator = allProjects.iterator();
		
		System.out.println("Resutls:");
		
		//	Loop through iterator & display project if input matches the project name or number
		while (projIterator.hasNext()) {
			Project p = projIterator.next();
			
			if ( ((selection=='p') && (p.getName().equals(searchStr))) || ((selection=='n') && (p.getNumber()==Integer.parseInt(searchStr))) ) {
				System.out.println(p.toString());
			}
		}
		
	}
	
	//	Method that displays project objects that are past their due date
	public static void viewOverdueProjects() {
		
		System.out.println("\nOverdue Projects:\n\n");
		
		LocalDate today = LocalDate.now();	//	Use this to compare with project due date
		
		Iterator<Project> projIterator = allProjects.iterator();
		
		//	Loop through iterator
		//	Find and display any project where the deadline has past the current date
		while (projIterator.hasNext()) {
			Project p = projIterator.next();
			
			if ((p.getDeadline().compareTo(today))<0) {
				System.out.println(p.toString());
				System.out.println();
				System.out.println("--------------------");
			}
		}
		
	}
	
	//	Method that displays projects that have not yet been finalised
	private static void viewActiveProjects() {
		
		System.out.println("\nProjects to be completed:\n\n");
		
		Iterator<Project> projIterator = allProjects.iterator();
		
		//	Loop through iterator
		//	Display project if finalised status is false
		while (projIterator.hasNext()) {
			Project p = projIterator.next();
			if (!p.getFinalisedStatus()) {
				System.out.println(p.toString());
				System.out.println();
				System.out.println("--------------------");
			}
		}
		
		
	}
	
	//	Method that finalises a project
	private static void finaliseProject() {
		
		//print message to user
		System.out.println("\nFinalise:");
		System.out.println("Projects listed below:");
		System.out.println("Project Number\tName\t\tTotal Fee\tTotal Paid");
		
		Iterator<Project> projIterator = allProjects.iterator();
		
		//	Loop through iterator to display projects
		while (projIterator.hasNext()) {
			Project p = projIterator.next();
			if (!p.getFinalisedStatus()) {
				System.out.println(p.getNumber()+"\t\t"+p.getName()+"\t\t"+p.getTotalFee()+"\t\t"+p.getTotalPaid());
			}
			
		}
		
		userInput.nextLine();  // Consume newline left-over -- solving the problem of using nextLine after nextInt(). Source: https://stackoverflow.com/questions/13102045/scanner-is-skipping-nextline-after-using-next-or-nextfoo
		
		System.out.println("\nPlease select a project number or name:");
		
		//get user's selection
		String projectN = userInput.nextLine();
		
		//	Reset iterator variable for the next while loop
		projIterator = allProjects.iterator();
		
		//print new line
		System.out.println();		
		
		String output = "";
		
		//	Look for project that user selected and finalise that project
		while ( (projIterator.hasNext()) && (true)) {
			Project p = projIterator.next();
			if ((p.getName().equals(projectN)) || (projectN.matches("\\d+") && (p.getNumber()==Integer.parseInt(projectN)))) {
				
				output = p.finaliseProject();
				
				System.out.println(output);
				
				break;
			}
		}
		
	
		
		//print success message
		System.out.println("\nProject Successfully Finalised\n");
		
		//	Write finalised project / Invoice to Complete project text file
		try {
			FileWriter fwAns = new FileWriter("./Complete project.txt",true);
			fwAns.write(output+"\r\n");
			fwAns.close();	
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		//	Run method to update project records
		updateProjectRecords();
		
	}
	
	
	//	Method for updating contractor
	private static void updateContractor() {
		
		//print messsage to user
		System.out.println("\nProject / Contractor details below:");
		
		Iterator<Project> projIterator = allProjects.iterator();
		
		//	Loop through iterator to display projects
		while (projIterator.hasNext()) {
			Project p = projIterator.next();
			
			System.out.println("Project Number: "+p.getNumber()+"\tName: "+p.getName());
			System.out.println("Contractor Details:");
			System.out.println(p.getContractor().toString());
		}
		
		userInput.nextLine();  // Consume newline left-over -- solving the problem of using nextLine after nextInt(). Source: https://stackoverflow.com/questions/13102045/scanner-is-skipping-nextline-after-using-next-or-nextfoo
		
		System.out.println("\nPlease select a project number or name (to update that project's contractor):");
		
		//	Get user's selection
		String projectN = userInput.nextLine();
		
		//	Following lines get new information about contractor
		
		System.out.println("Enter new details below");
		
		System.out.println("Contractor's Name:");
		String newName = userInput.nextLine();
		
		System.out.println("Telephone:");
		String newTelephone = userInput.nextLine();
		
		System.out.println("Email:");
		String newEmail = userInput.nextLine();
		
		System.out.println("Address:");
		String newAddress = userInput.nextLine();
		
		//	Reset project iterator for new while loop
		projIterator = allProjects.iterator();
		
		//	Loop through iterator
		while ( (projIterator.hasNext()) && (true)) {
			Project p = projIterator.next();
			
			//	Find selected project (based on name/number) and update project's contractor
			if ((p.getName().equals(projectN)) || (projectN.matches("\\d+") && (p.getNumber()==Integer.parseInt(projectN)))) {
				
				//	Also declare people iterator
				Iterator<Person> peopleIt = allPeople.iterator();
				
				//	Loop through people iterator to also update person's details
				while ( (peopleIt.hasNext()) && (true)) {
					Person currentPerson = peopleIt.next();
					
					//	If person associated with project is found
					if (currentPerson.getName().equals(p.getContractor().getName()) ) {
						
						//	Remove this person from list array
						allPeople.remove(currentPerson);
						
						//	Then update details 
						currentPerson.setName(newName);
						currentPerson.setTelephone(newTelephone);
						currentPerson.setEmail(newEmail);
						currentPerson.setAddress(newAddress);
						
						//	Add person with updated details to list array
						allPeople.add(currentPerson);
						
						//	Run method that updates person text file
						updatePersonRecords();
						
						break;
					}
				}
				
				
				//update this specific contractor
				p.setContractor(newName, newTelephone, newEmail, newAddress);
				
				//print success message
				System.out.println("\nContractor's details successfully updated!\n");
				
				//	Update project records text file
				updateProjectRecords();
				
				break;	//	Added break because not necessary to continue loop
			}
		}
		

		
	}
	
	
	//	Method that updates the total paid amount
	private static void changeTotPaid() {
		userInput.useLocale(Locale.US); //Change decimal system to . not , source: https://stackoverflow.com/questions/5929120/nextdouble-throws-an-inputmismatchexception-when-i-enter-a-double
		
		//print message to user
		System.out.println("\nChange Total Paid:");
		System.out.println("Projects listed below:");
		System.out.println("Project Number\tName\t\tTotal Fee\tTotal Paid");
		
		Iterator<Project> projIterator = allProjects.iterator();
		
		//	Loop throguh iterator to display all projects
		while (projIterator.hasNext()) {
			Project p = projIterator.next();
			System.out.println(p.getNumber()+"\t\t"+p.getName()+"\t\t"+p.getTotalFee()+"\t\t"+p.getTotalPaid());
		}
		
		userInput.nextLine();  // Consume newline left-over -- solving the problem of using nextLine after nextInt(). Source: https://stackoverflow.com/questions/13102045/scanner-is-skipping-nextline-after-using-next-or-nextfoo
		
		System.out.println("\nPlease select a project number or name:");
		
		//	Get user's selection
		String projectN = userInput.nextLine();
		
		//	Reset iterator for next while loop
		projIterator = allProjects.iterator();
		
		//	Loop through iterator to find selected project
		while ( (projIterator.hasNext()) && (true)) {
			Project p = projIterator.next();
			
			//	Find selected project based on project name or project number input
			if ((p.getName().equals(projectN)) || (projectN.matches("\\d+") && (p.getNumber()==Integer.parseInt(projectN)))) {
				System.out.println("Enter updated paid amount:");
				
				// Keep asking user until correct value is received
				while(!userInput.hasNextDouble()) {
					System.out.println("\nEntered incorrect value");
					System.out.println("Please enter a price [double value]:");
					userInput.next();
				}
				
				//	Assign variable if input is double value
				double updatedPaid = 0;
				if (userInput.hasNextDouble()){
					updatedPaid = userInput.nextDouble();
				}
				
				//update paid amount
				p.setTotalPaid(updatedPaid);
				
				//display success message to user
				System.out.println("Total Paid has been successfully updated");
				System.out.println(p.getNumber()+"\t\t"+p.getName()+"\t\t"+p.getTotalFee()+"\t\t"+p.getTotalPaid()+"\n");
				
				//	Run method to update project text file
				updateProjectRecords();
				
				break;	//	Added break because not necessary to continue loop
				
			}
		}
		

	}
	
	
	//method that changes project due date
	private static void changeDueDate() {
		
		//display message to user
		System.out.println("\nProjects listed below:");
		System.out.println("Project Number\tName\t\tDue Date");
		
		Iterator<Project> projIterator = allProjects.iterator();
		
		//	Loop through iterator to display all projects
		while (projIterator.hasNext()) {
			Project p = projIterator.next();
			System.out.println(p.getNumber()+"\t\t"+p.getName()+"\t\t"+p.getDeadline());
		}
		
		userInput.nextLine(); // Consume newline left-over -- solving the problem of using nextLine after nextInt(). Source: https://stackoverflow.com/questions/13102045/scanner-is-skipping-nextline-after-using-next-or-nextfoo
		
		System.out.println("\nPlease select a project number or name:");
		
		//get user's selection
		String projectN = userInput.nextLine();
		
		//	Reset project iterator for next loop
		projIterator = allProjects.iterator();
		
		//	Loop through iterator to find selected project
		while ( (projIterator.hasNext()) && (true)) {
			Project p = projIterator.next();
			
			//	Find selected project based on project name or project number input
			if ((p.getName().equals(projectN)) || (projectN.matches("\\d+") && (p.getNumber()==Integer.parseInt(projectN)))) {
				
				//	Declare placeholder variables
				String newDeadlineStr = "";
				LocalDate newDeadline = LocalDate.now();
				
				//	Keep asking user until correct value is received
				while (true) {
					
					System.out.println("Enter new deadline [format: 'yyyy-mm-dd']");
					
					newDeadlineStr = userInput.nextLine();
					
					//	Assign deadline variable if in correct format and end loop   
					if (newDeadlineStr.matches("^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$")) {
						newDeadline = LocalDate.parse(newDeadlineStr);
						break;
					}
					else {	//	Else show error message and loop again
						System.out.println("\nEntered incorrect value");
						System.out.println("Please enter a date [format: 'yyyy-mm-dd']:");
					}
					
				}
				
				//update deadline
				p.setDeadline(newDeadline);
				
				//	Update project text file
				updateProjectRecords();
				
				//print success message
				System.out.println("Due date has successfully been changed!");
				
				System.out.println(p.getNumber()+"\t"+p.getName()+"\t"+p.getDeadline()+"\n");				
				
				break;	//	Added break because not necessary to continue loop
			}
		}
		
		
		
	}
	
	
	//method to create a new project
	private static void createProject() {	
		
		userInput.useLocale(Locale.US); //Change decimal system to . not , source: https://stackoverflow.com/questions/5929120/nextdouble-throws-an-inputmismatchexception-when-i-enter-a-double
		
		//print message to user
		System.out.println("\nCreating a new project");
		System.out.println("Please enter the following details...");
		
		//The following lines ask user to input variables for new project
		
		System.out.println("Project Number:");
		
		// Keep asking user until correct value is received
		while(!userInput.hasNextInt()) {
			System.out.println("\nEntered incorrect value");
			System.out.println("Please enter an integer value:");
			userInput.next();
		}
		
		//	Assign variable if input is int value
		int projectNum = 0;
		if (userInput.hasNextInt()){
			projectNum = userInput.nextInt();
		}
		
		userInput.nextLine();  // Consume newline left-over -- solving the problem of using nextLine after nextInt(). Source: https://stackoverflow.com/questions/13102045/scanner-is-skipping-nextline-after-using-next-or-nextfoo 
		
		System.out.println("Project Name:");
		String projectName = userInput.nextLine();
		
		System.out.println("Building Type (House, Apartment etc.):");
		String buildingType = userInput.nextLine();
		
		System.out.println("Address:");
		String address = userInput.nextLine();
		
		System.out.println("Erf Number:");
		
		// Keep asking user until correct value is received
		while(!userInput.hasNextInt()) {
			System.out.println("\nEntered incorrect value");
			System.out.println("Please enter an integer value:");
			userInput.next();
		}
		
		//	Assign variable if input is int value
		int erfNum = 0;
		if (userInput.hasNextInt()){
			erfNum = userInput.nextInt();
		}
		
		System.out.println("Total Fee:");
		
		// Keep asking user until correct value is received
		while(!userInput.hasNextDouble()) {
			System.out.println("\nEntered incorrect value");
			System.out.println("Please enter a price [double value]:");
			userInput.next();
		}
		
		//	Assign variable if input is int value
		double totFee = 0;
		if (userInput.hasNextDouble()){
			totFee = userInput.nextDouble();
		}
		
		System.out.println("Total Paid:");
		
		// Keep asking user until correct value is received
		while(!userInput.hasNextDouble()) {
			System.out.println("\nEntered incorrect value");
			System.out.println("Please enter a price [double value]:");
			userInput.next();
		}
		
		//	Assign variable if input is int value
		double totPaid = 0;
		if (userInput.hasNextDouble()){
			totPaid = userInput.nextDouble();
		}
		
		userInput.nextLine();
		
		//	Declare placeholder variables
		String deadlineStr = ""; 
		LocalDate deadline = LocalDate.now();
		
		// Keep asking user until correct value is received
		while (true) {
			
			System.out.println("Deadline [format: 'yyyy-mm-dd']:");
			
			deadlineStr = userInput.nextLine();
			
			//	Only assign LocalDate variable if in correct format (and end loop)
			if (deadlineStr.matches("^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$")) {
				deadline = LocalDate.parse(deadlineStr);
				break;
			}
			else {	//	Else continue loop
				System.out.println("\nEntered incorrect value");
				System.out.println("Please enter a date [format: 'yyyy-mm-dd']:");
			}
			
		}
		
		//display architect options to user
		System.out.println("\nArchitect options:");
		System.out.println("a\t-\tAdd an existing architect");
		System.out.println("n\t-\tNew architect");
		System.out.println("Please select an option:");
		
		//get user's selected option
		char archSelection = userInput.next().charAt(0);
		
		//declare architect variable
		Person architect = null;
		
		//	Either add existing or create new person and assign as architect variable
		if (archSelection=='a') {
			System.out.println("Add existing Architect...\n");
			
			//	Run method to get existing architect person
			architect = getExistingPerson("architect");
			
		}
		else {
			
			System.out.println("Create New Architect...");
			
			//	Run method to create new architect person
			architect = createPerson("architect");
		}
		
		
		//display contractor options
		System.out.println("\nContractor options:");
		System.out.println("a\t-\tAdd an existing contractor");//I will make this option available when we use text files for storage
		System.out.println("n\t-\tNew contractor");
		System.out.println("Please select an option:");
		
		//get user's selection
		char conSelection = userInput.next().charAt(0);
		
		//declare person variable
		Person contractor = null;
		
		//	Either add existing or create new person and assign as contractor variable
		if (conSelection=='a') {
			System.out.println("Add existing Contractor...\n");
			
			//	Run method to get existing contractor person
			contractor = getExistingPerson("contractor");
			
		}
		else {
			
			System.out.println("Create New Contractor...");
			
			//	Run method to create new contractor person
			contractor = createPerson("contractor");
		}
		
		//display customer options
		System.out.println("\nCustomer options:");
		System.out.println("a\t-\tAdd an existing customer");
		System.out.println("n\t-\tNew customer");
		System.out.println("Please select an option:");
		
		//get user's selection
		char custSelection= userInput.next().charAt(0);
		
		//declare person variable
		Person customer = null;
		
		//	Either add existing or create new person and assign as customer variable
		if (custSelection == 'a') {
			System.out.println("Add existing Customer...\n");
			
			//	Run method to get existing customer person
			customer = getExistingPerson("customer");
			
		}
		else {
			
			System.out.println("Create New Customer...");
			
			//	Run method to create new customer person
			customer = createPerson("customer");
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
		Project newProject = new Project(projectNum, projectName, buildingType, address, erfNum, totFee, totPaid, deadline, architect, contractor, customer); 
		
		//add new project to project array
		allProjects.add(newProject);
		
		//	Update project records text file
		updateProjectRecords();
		
		System.out.println("\nNew project successfully created.");
		
	}
	
	//	Method used to get existing person (to assign as architect, contractor or customer)
	private static Person getExistingPerson(String type) {
		
		userInput.nextLine();
		
		System.out.println("Available "+type+"s:");
		
		System.out.println("Name\t\tEmail\t\tTelephone\t\tAddress");
		
		Iterator<Person> pIterator = allPeople.iterator();
		
		//	Loop through iterator and display any person objects of the same type (architect, customer, contractor)
		while (pIterator.hasNext()) {
			Person p = pIterator.next();
			
			if (p.getType().equals(type)) {
				System.out.println(p.getName()+"\t\t"+p.getEmail()+"\t\t"+p.getTelephone()+"\t\t"+p.getAddress());
			}
			
		}
		
		//	If loop, architect specified for vowel in output string ('an' instead of 'a')
		if (type=="architect") {
			System.out.println("Please select an "+type+" by name:");			
		}
		else {
			System.out.println("Please select a "+type+" by name:");
		}
		
		String name = userInput.nextLine();
		
		//	Declare variables for person
		String pName = "";
		String pEmail = "";
		String pTel = "";
		String pAddress = "";
		
		//	Reset person iterator for next while loop
		pIterator = allPeople.iterator();
		
		//	Loop through iterator to find person with same name
		while ( (pIterator.hasNext()) && (true)) {
			Person p = pIterator.next();
			if (p.getName().equals(name)) {
				
				//	Assign return person variables with that specific person's variables
				pName = p.getName();
				pEmail = p.getEmail();
				pTel = p.getTelephone();
				pAddress = p.getAddress();
				
				break;
			}
		}
		
		//	Create a person object to return
		Person returnedPerson = new Person(pName, pEmail, pTel, pAddress, type);
		
		//	Return person object
		return returnedPerson;
	}
	
	//method to create person
	private static Person createPerson(String personType) {
		
		
		userInput.nextLine();
		
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
		Person newPerson = new Person(name, telephone, email, address, personType);
		
		Iterator<Person> pIterator = allPeople.iterator();
		
		//	Declare boolean variable to determine whether person should be added or not
		boolean addNewPerson = true;
		
		//	Loop thorugh iterator to check if person exists
		while ( (pIterator.hasNext()) && (true)) {
			Person p = pIterator.next();
			if (p.getName().equals(newPerson.getName())) {
				
				//	If person exists, then change boolean to false to not add person again
				addNewPerson = false;
				break;
			}
		}
		
		//	Only add person to person list array and text file if boolean variable is true 
		if (addNewPerson) {
			allPeople.add(newPerson);
			updatePersonRecords();
		}
		
		//return person variable
		return newPerson;
		
	}
	
	
	
	//	Method that takes the person list array and writes it to a text file
	private static void updatePersonRecords() {
		
		//	Declare iterator and use it in while loop
		Iterator<Person> peopleIterator = allPeople.iterator();
		
		try {
			FileWriter writer = new FileWriter("allPeople.txt");
			
			//	Loop through iterator and add each person to the text file on a new line
			while (peopleIterator.hasNext()) {
				writer.write(peopleIterator.next().getPersonFileStr());	//	Use getPersonFileStr method to get correct format from project class for text file
				writer.write(System.getProperty( "line.separator" )); //	Source: https://stackoverflow.com/questions/18549704/create-a-new-line-in-javas-filewriter
				
			}
			
			//	Close file writer
			writer.close();
		}
		catch (IOException e) {
			//	Display any error if there is a problem with the text file
			e.printStackTrace();
		}
		
	}
	
	//	Method that reads person text file and adds people objects to list array
	private static void loadExistingPeople() {
		
		try {
			File peopleFile = new File("allPeople.txt");
			Scanner people = new Scanner(peopleFile);
			
			//	Loop through file scanner to add people objects to list array
			while (people.hasNext()) {
				
				//	Each person object is on a new line
				String peopleStr = people.nextLine();
				
				//	All person object variables are separated by a comma
				String[] peopleArr = peopleStr.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);	//Do not count commas inside quotations. Source: https://stackoverflow.com/questions/1757065/java-splitting-a-comma-separated-string-but-ignoring-commas-in-quotes
				
				//	Loop through object variables and remove any quotations
				for (int i=0; i<peopleArr.length; i++) {
					peopleArr[i] = peopleArr[i].replace("\"", "");
				}
				
				//create new person as newToProject
				Person loadPerson = new Person(peopleArr[0], peopleArr[1], peopleArr[2], peopleArr[3], peopleArr[4]);
				
				//add new person to person array
				allPeople.add(loadPerson);
				
			}
			
			people.close();
		}
		catch (FileNotFoundException e) {
			//	'Catch' error and print error message if there is a problem with the file
			System.out.println(e.getMessage());
		}
		
		
	}
	
	//	Method that takes the project list array and writes it to a text file
	private static void updateProjectRecords() {
				
		//	Declare iterator and use it in while loop
		Iterator<Project> projIterator = allProjects.iterator();
		try {
			FileWriter writer = new FileWriter("allProjects.txt");
			
			//	Loop through iterator and add each person to the text file on a new line
			while (projIterator.hasNext()) {
				writer.write(projIterator.next().getProjectFileStr());	//	Use getPersonFileStr method to get correct format from project class for text file
				writer.write(System.getProperty( "line.separator" )); 	//	Source: https://stackoverflow.com/questions/18549704/create-a-new-line-in-javas-filewriter
			}

			//	Close file writer
			writer.close();
		}
		catch (IOException e) {
			//	'Catch' error and print error message if there is a problem with the file
			e.printStackTrace();
		}
		
	}
	
	//	Method that reads project text file and adds project objects to list array
	private static void loadExistingProjects() {
		try {
			File projectFile = new File("allProjects.txt");
			Scanner projects = new Scanner(projectFile);
			
			//	Loop through scanner to add each project object to list array
			while (projects.hasNext()) {
				
				//	Each project object is on a new line
				String projectsStr = projects.nextLine();
				
				//	All person object variables are separated by a comma
				String[] projectsArr = projectsStr.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);	//Do not count commas inside quotations. Source: https://stackoverflow.com/questions/1757065/java-splitting-a-comma-separated-string-but-ignoring-commas-in-quotes
				
				//	Loop through object variables and remove any quotations
				for (int i=0; i<projectsArr.length; i++) {
					projectsArr[i] = projectsArr[i].replace("\"", "");
				}

				//	Change any variables from strings to respective types to create a person and project object
				int projNum = Integer.parseInt(projectsArr[0]);
				int projErf = Integer.parseInt(projectsArr[4]);
				double projFee = Double.parseDouble(projectsArr[5]);
				double projPaid = Double.parseDouble(projectsArr[6]);
				LocalDate projDeadline = LocalDate.parse(projectsArr[7]);
				
				//	Create 3 person objects for project
				Person projArch = new Person(projectsArr[8], projectsArr[9], projectsArr[10], projectsArr[11], projectsArr[12]);
				Person projContr = new Person(projectsArr[13], projectsArr[14], projectsArr[15], projectsArr[16], projectsArr[17]);
				Person projCust = new Person(projectsArr[18], projectsArr[19], projectsArr[20], projectsArr[21], projectsArr[22]);
				
				//	Create project object
				Project loadProject = new Project(projNum, projectsArr[1] , projectsArr[2], projectsArr[3], projErf, projFee, projPaid, projDeadline, projArch, projContr, projCust);
				
				//	Set completion date of project if not null
				if (!projectsArr[23].equals("null")) {
					loadProject.setCompletionDate(LocalDate.parse(projectsArr[23]));
				}
				
				//	Set finalised status of project if not false
				if (!projectsArr[24].equals("false")) {
					loadProject.setFinalisedStatus(Boolean.valueOf(projectsArr[24])); // https://stackoverflow.com/questions/1538755/how-to-convert-string-object-to-boolean-object
				}
				
				//add new person to person array
				allProjects.add(loadProject);
				
			}
			
			//	Close project scanner
			projects.close();
			
		}
		catch (FileNotFoundException e) {
			//	'Catch' error and print error message if there is a problem with the file
			System.out.println(e.getMessage());
		}
	}
	

}
