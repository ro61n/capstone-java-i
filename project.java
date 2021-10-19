package task7;

//import modules & classes
import java.time.LocalDate; // class for dealing with dates. Source: https://www.w3schools.com/java/java_date.asp

public class project {
	
	//attributes
	private int number;
	private String name;
	private String buildingType;
	private String address;
	private int erfNumber;
	private double totalFee;
	private double totalPaid;
	private LocalDate deadline;
	private person architect;
	private person contractor;
	private person customer;
	private LocalDate completionDate;
	private boolean finalisedProject = false;
	
	//methods
	//constructor
	public project(int number, String name, String buildingType, String address, int erfNumber, double totalFee, double totalPaid, LocalDate deadline, person architect, person contractor, person customer) {
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
			output += "Customer - Contact Details: \n"+customer;
			output += "\nAmount Due: "+amountDue+"\n";
			output += "-------------------------------";
			
		}
		
		//return output to user
		return output;
	}
	
	//toString method
	public String toString() {
		String output = "number: "+number+"\n";
		output += "Name: "+name+"\n";
		output += "Building Type: "+buildingType+"\n";
		output += "Address: "+address+"\n";
		output += "Erf Number: "+erfNumber+"\n";
		output += "Total Fee: "+totalFee+"\n";
		output += "Total Paid: "+totalPaid+"\n";
		output += "Deadline: "+deadline+"\n";
		output += "Architect: \n"+architect+"\n";
		output += "Contractor: \n"+contractor+"\n";
		output += "Customer: \n"+customer+"\n";
		
		return output;
	}
	
	
}
