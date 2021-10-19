package task7;

import java.time.LocalDate;

public class client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//create person objects
		person architect = new person("John Schultz", "123 964 2351", "js@gmail.com", "1 B Street, Fairmont, Durban");
		person contractor = new person("Dave Chappel", "072 123 1235", "dave@chappel.com", "24 A Street, Rondebosch, Cape Town");
		person customer = new person("Damon Web", "123 000 0011", "dweb@gmail.com", "10 C Street, Linden, Jhb");
		
		//create project object
		project highveldDamHouse= new project(01, "Dam House", "House", "2 Beacon St, Glenwood", 24, 2400.24, 1000, LocalDate.parse("2022-01-08"), architect, contractor, customer );
		//Souce for learning to create local dates: https://www.baeldung.com/java-creating-localdate-with-values
		
		//update deadline
		highveldDamHouse.setDeadline(LocalDate.parse("2021-11-11"));
		
		//update total paid
		highveldDamHouse.setTotalPaid(2000.00);
		
		//change contractor
		highveldDamHouse.setContractor("Joe Hull", "123 456 7890", "jh@jhcontracts.co.za", "124 A Road, Potchefstroom");
		
		//finalise project
		System.out.println(highveldDamHouse.finaliseProject());
		
		//test toString()
		//System.out.println(highveldDamHouse);

	}

}
