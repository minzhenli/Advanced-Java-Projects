/*
 * @author Zhenli Min
 * @version 20190307
 */

import java.util.*;

public class SimpleCustomer {
	private String lastName;
	private String firstName;
	private String street;
	private String city;
	private String state;
	private String zip;
	private double tax;
	private List<SimpleProduct> productList;
	
	public SimpleCustomer() {
		lastName="";
		firstName="";
		street="";
		city="";
		state="";
		zip="";
		tax=0;
		productList= new LinkedList<SimpleProduct>();
	}
	
	public void setLastName(String lastName) {
		this.lastName=lastName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName=firstName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setStreet(String street) {
		this.street=street;
	}
	
	public String getStreet() {
		return street;
	}
	
	public void setCity(String city) {
		this.city=city;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setState(String state) {
		this.state=state;
	}
	
	public String getState() {
		return state;
	}
	
	public void setZip(String zip) {
		this.zip=zip;
	}
	
	public String getZip() {
		return zip;
	}
	
	public void setTax(double tax) {
		if(tax>1||tax<0) {
			System.out.println("Enter a valid tax rate(0<=rate<=1): ");
		}
		this.tax=tax;
	}
	
	public double getTax() {
		return tax;
	}
	
	public void setProduct(List<SimpleProduct> productList) {
		this.productList=productList;
	}
	
	public List<SimpleProduct> getProductList(){
		return this.productList;
	}
	
	public boolean readNextCustomer(Scanner inFile) {
		try {
			lastName=inFile.nextLine();
			firstName=inFile.nextLine();
			street=inFile.nextLine();
			city=inFile.nextLine();
			state=inFile.nextLine();
			zip=inFile.nextLine();
			tax=Double.parseDouble(inFile.nextLine());
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}
	
}
