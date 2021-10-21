package task7;

public class person {
	
	//attributes
	private String name;
	private String telephone;
	private String email;
	private String address;
	
	//methods
	//constructor
	public person(String name, String telephone, String email, String address) {
		this.name = name;
		this.telephone = telephone;
		this.email = email;
		this.address = address;
	}
	
	//getter method - get name
	public String getName() {
		return name;
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
		String output = "Name:"+name+"\n";
		output += "Telephone:"+telephone+"\n";
		output += "Email:"+email+"\n";
		output += "Address:"+address+"\n";
		
		return output;
	}

}
