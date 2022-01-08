package task7;


public class Person {
	
	//attributes
	private String name;
	private String telephone;
	private String email;
	private String address;
	private String personType;
	
	//methods
	//constructor
	public Person(String name, String telephone, String email, String address, String personType) {
		this.name = name;
		this.telephone = telephone;
		this.email = email;
		this.address = address;
		this.personType = personType;
	}
	
	//getter method - get name
	public String getName() {
		return name;
	}
	
	//	Return email
	public String getEmail() {
		return email;
	}
		
	//	Return telephone
	public String getTelephone() {
		return telephone;
	}
	
	//	Return address
	public String getAddress() {
		return address;
	}
	
	//	Return type
	public String getType() {
		return personType;
	}
	
	//setter method - allows name to be updated
	public void setName(String newName) {
		name = newName;
	}
	
	//setter method - allows telephone to be updated
	public void setTelephone(String newTelephone) {
		telephone = newTelephone;
	}
	
	//setter method - allows email to be updated
	public void setEmail(String newEmail) {
		email = newEmail;
	}
	
	//setter method - allows address to be updated
	public void setAddress(String newAddress) {
		address = newAddress;
	}
	
	//to string method
	public String toString() {
		String output = "Name:\t\t"+name+"\n";
		output += "Telephone:\t"+telephone+"\n";
		output += "Email:\t\t"+email+"\n";
		output += "Address:\t"+address+"\n";
		output += "Category:\t"+personType+"\n";
		
		return output;
	}
	
	//	Method that creates a comma seperated value string of project object for project text file
	public String getPersonFileStr() {
		String output = "\""+name+"\",\""+telephone+"\",\""+email+"\",\""+address+"\",\""+personType+"\"";
		return output;
	}

}
