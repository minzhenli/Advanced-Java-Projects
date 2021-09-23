/*
 * @author Zhenli Min
 * @version 20190307
 */

import java.util.*;

public class SimpleProduct implements Product{
	
	private String name;
	private String type;
	private double price;
	private int quantity;
	public boolean inStock;
 
	public SimpleProduct() {
		name=" ";
		type=" ";
		price=0;
		quantity=0;
		inStock=true;
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
		return name;
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
		return type;
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
		return price;
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
		return quantity;
	}
	
	/*
	 * setInStock
	 * @param inStock - true if this product is in stock
	 */
	public void setInStock(boolean inStock) {
		this.inStock=inStock;
	}

	/*
	 * getQuantity
	 * @return true if this product is in stock
	 */
	public boolean getInStock() {
		return inStock;
	}
	
	/*
	 * readNextProduct
	 * @param inFile - a Scanner containing product entries
	 * @return false if the product cannot be completely read,
	 * 			true otherwise
	 */
	public boolean readNextProduct(Scanner inFile) {
		
		if(inFile.hasNext()) {
			setName(inFile.nextLine());
		}
		else {
			return false;
		}
		if (inFile.hasNext()) {
			setType(inFile.nextLine());
		}
		else {
			return false;
		}
		if (inFile.hasNext()) {
			setPrice(Double.parseDouble(inFile.nextLine()));
		}
		else {
			return false;
		}
		if(inFile.hasNext()) {
			setQuantity(Integer.parseInt(inFile.nextLine()));
		}
		else {
			return false;
		}
		if (inFile.hasNext()) {
			setInStock(Boolean.parseBoolean(inFile.nextLine()));
			return true;
		}
		else {
			return false;
		}
		
		
	}
	
	@Override 
	public boolean equals(Object obj) {
		boolean result = false;
		if(obj instanceof SimpleProduct) {
			SimpleProduct a = (SimpleProduct) obj;
			if(this.name.equals(a.getName())
			    &&this.type.equals(a.getType())){
				result=true;
			}
		}
		return result;
	}
	
	@Override
	public String toString() {
		String a = "";
		a="("+this.name+", "+this.type+", "+this.price+", "+this.quantity+")";
		return a;
	}
}
