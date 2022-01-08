package task7;

//import modules & classes
import java.time.LocalDate; // class for dealing with dates. Source: https://www.w3schools.com/java/java_date.asp

public class Project {
	
	//attributes
	private int number;
	private String name;
	private String buildingType;
	private String address;
	private int erfNumber;
	private double totalFee;
	private double totalPaid;
	private LocalDate deadline;
	private Person architect;
	private Person contractor;
	private Person customer;
	private LocalDate completionDate;
	private boolean finalisedProject = false;
	
	//methods
	//constructor
	public Project(int number, String name, String buildingType, String address, int erfNumber, double totalFee, double totalPaid, LocalDate deadline, Person architect, Person contractor, Person customer) {
		this.number = number;
		this.name = name;
		this.buildingType = buildingType;
		this.address = address;
		this.erfNumber = erfNumber;
		this.totalFee = totalFee;
		this.totalPaid = totalPaid;
		this.deadline = deadline;
		this.architect = architect;
		this.contractor = contractor;
		this.customer = customer;
	}
	
	//getter method - get number
	public int getNumber() {
		return number;
	}
	
	//getter method - get name
	public String getName() {
		return name;
	}
	
	//getter method - get deadline
	public LocalDate getDeadline() {
		return deadline;
	}
	
	//getter method - get total fee
	public double getTotalFee() {
		return totalFee;
	}
	
	//getter method - get total paid
	public double getTotalPaid() {
		return totalPaid;
	}
	
	//getter method - get contractor
	public Person getContractor() {
		return contractor;
	}
	
	//	Return finalised status if called
	public boolean getFinalisedStatus() {
		return finalisedProject;
	}
	
	//	Change completion date
	public void setCompletionDate(LocalDate newCompletionDate) {
		completionDate = newCompletionDate;
	}
	
	//	Change finalised status
	public void setFinalisedStatus(boolean newStatus) {
		finalisedProject = newStatus;
	}
	
	//allow project deadline to be updated
	public void setDeadline(LocalDate newDeadline) {
		deadline = newDeadline;
	}
	
	//allow total paid amount to be updated
	public void setTotalPaid(double newTotalPaid) {
		totalPaid = newTotalPaid;
	}
	
	//allow contractor details to be updated
	public void setContractor(String newName, String newTelephone, String newEmail, String newAddress) {
		
		//call set method in person class since a contractor is a person
		contractor.setName(newName);
		contractor.setTelephone(newTelephone);
		contractor.setEmail(newEmail);
		contractor.setAddress(newAddress);
		
	}
	
	//method that allows projects to be finalised
	public String finaliseProject() {
		
		//declare variables
		String output ="";
		
		//calculate amount due
		double amountDue = totalFee - totalPaid;
		
		//round amount due to 2 decimal places
		amountDue = Math.round(amountDue * 100.0) / 100.0;
		
		//print invoice if amount due > 0
		if (amountDue>0) {
			
			//set completion date and finalise
			completionDate = LocalDate.now();
			finalisedProject = true;
			
			//add projet to output variable
			//output += "Customer - Contact Details: \n"+customer;
			output += "___INVOICE___\n"+customer;
			output += "\nAmount Due:\tR"+amountDue+"\n";
			output += "-------------------------------";
			
		}
		
		//return output to user
		return output;
	}
	
	//toString method
	public String toString() {
		String output = "Project Number:\t "+number+"\n";
		output += "Name:\t "+name+"\n";
		output += "Building Type:\t "+buildingType+"\n";
		output += "Address:\t "+address+"\n";
		output += "Erf Number:\t "+erfNumber+"\n";
		output += "Total Fee:\t "+totalFee+"\n";
		output += "Total Paid:\t "+totalPaid+"\n";
		output += "Deadline:\t "+deadline+"\n";
		output += "\nArchitect: \n"+architect+"\n";
		output += "Contractor: \n"+contractor+"\n";
		output += "Customer: \n"+customer+"\n";
		
		return output;
	}
	
	//	Method that creates a comma seperated value string of project object for project text file
	public String getProjectFileStr() {
		String output = number+",\""+name+"\",\""+buildingType+"\",\""+address+"\","+erfNumber+","+totalFee+","+totalPaid+","+deadline+","+architect.getPersonFileStr()+","+contractor.getPersonFileStr()+","+customer.getPersonFileStr()+","+completionDate+","+finalisedProject;
		return output;
	}
	
	
}
