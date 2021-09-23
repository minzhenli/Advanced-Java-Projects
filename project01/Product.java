/**
 * Product
 * 
 *   A simple class framework used to demonstrate the design
 *   of Java classes.
 *   
 *   @author Zhenli Min
 *   @version 1/27/2019
 */
import java.util.ArrayList;

public class Product {
	
	private String name;
	private String type;
	private int quantity;
	private double price;
	private String code;
	private ArrayList<Integer> rating;
	private int numOfRating;
	

	/*
	 * Product constructor
	 */
	public Product() {
		name=" ";
		type=" ";
		quantity=0;
		price=0;
		code=" ";
		rating= new ArrayList<Integer>();
		numOfRating=0;
	}
	
	/*
	 * Another constructor
	 */
	public Product(String name, String code, int quantity, double price, String type, ArrayList ratings) {
		this.name=name;
		this.code=code;
		this.quantity=quantity;
		this.price=price;
		this.type=type;
		this.rating= ratings;
		this.numOfRating=ratings.size();
	}
	
	/*
	 * setName
	 *  @param name - new name for the product
	 */
	public void setName(String name) {
		this.name=name;
	}
	
	/*
	 * getName
	 *  @return the name of the product
	 */
	public String getName() {
		return this.name;
	}
	
	/*
	 * setType
	 *  @param type - the type of the product
	 */
	public void setType(String type) {
		this.type=type;
	}
	
	/*
	 * getType
	 * @return - the product type
	 */
	public String getType() {
		return this.type;
	}
	
	/*
	 * setPrice
	 * @param price - the price of the product
	 */
	public void setPrice(double price) {
		this.price=price;
	}
	
	/*
	 * getPrice
	 * @return the price of the product
	 */
	public double getPrice() {
		return this.price;
	}
	
	/*
	 * setQuantity
	 * @param quantity - the number of this product in inventory
	 */
	public void setQuantity(int quantity) {
		this.quantity=quantity;
	}
	
	/*
	 * getQuantity
	 * @return the number of this product in inventory
	 */
	public int getQuantity() {
		return this.quantity;
	}
	
	/*
	 * setInventoryCode
	 * @param code - the new inventory code for the product
	 */
	public void setInventoryCode(String code) {
		this.code=code;
	}
	
	/*
	 * getInventoryCode
	 * @return the inventory code of the product
	 */
	public String getInventoryCode() {
		return this.code;
	}
	
	/*
	 * addUserRating
	 * NOTE: Each individual rating is stored with the product, so you need to maintain a list
	 * of user ratings.  This method should append a new rating to the end of that list
	 * @param rating - the new rating to add to this product
	 */
	public void addUserRating(int rating) {
		this.rating.add(rating);
	}
	
	/*
	 * getUserRating
	 * 	NOTE:  See note on addUserRating above.  This method should be written to allow you
	 * 	to access an individual value from the list of user ratings 
	 * @param index - the index of the rating we want to see
	 * @return the rating indexed by the value index
	 */
	public int getUserRating(int index) {
		return this.rating.get(index);
	}
	
	/*
	 * getUserRatingCount
	 *  NOTE: See note on addUserRating above.  This method should be written to return
	 *  the total number of ratings this product has associated with it
	 * @return the number of ratings associated with this product
	 */
	public int getUserRatingCount() {
		return this.rating.size();
	}
	
	/*
	 * getAvgUserRating
	 *  NOTE: see note on addUserRating above.  This method should be written to compute
	 *  the average user rating on demand from a stored list of ratings.
	 * @return the average rating for this product as a whole integer value (use integer math)
	 */
	public int getAvgUserRating() {
		int sum=0;
		int count=0;
		int avg = 0;
		for(int i=0;i<this.rating.size();i++) {
			sum=sum+this.rating.get(i);
			count++;
			avg = sum/count;
		}
		return avg;
		
	}
}