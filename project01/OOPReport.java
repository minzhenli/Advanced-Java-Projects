/*
 * A program that takes the file as input
 * and generate a product inventory summary report.
 * 
 * @author Zhenli Min
 * @version 20190130
 */

import java.util.*;
import java.io.*;

public class OOPReport {
	
	public static void main(String[]args) throws IOException {
		Scanner in = new Scanner(System.in);
		System.out.print("Enter an inventory filename: ");
		String fileName = in.nextLine();
		ArrayList<Product> product = getProduct(fileName);
		display(product); 
	}
	
	public static ArrayList<Product> getProduct(String fileName) {
		File file = new File(fileName);
		ArrayList<Product> product = new ArrayList<Product>();
		try {
			Scanner inFile = new Scanner(file);
			while (inFile.hasNext()) {
				String name = inFile.nextLine();
				String code = inFile.nextLine();
				int quantity = Integer.parseInt(inFile.nextLine());
				double price = Double.parseDouble(inFile.nextLine());
				String type = inFile.nextLine();
				ArrayList<Integer> ratings=new ArrayList<Integer>();
				int next =Integer.parseInt(inFile.nextLine());
				while (next!=-1) {
				    ratings.add(next);
				    next=Integer.parseInt(inFile.nextLine());
				}
				int numOfRate=ratings.size();
				Product p = new Product(name,code,quantity,price,type,ratings);
				product.add(p);
				}
			inFile.close();
		} catch (IOException e) {
			System.out.println("There was a problem reading from "+fileName);
		}
		return product;
	}
	
	public static String getRating(Product p) {
		int avgRating=p.getAvgUserRating();
		String stars= "";
		for (int i=1;i<=avgRating;i++) {
			stars=stars+"*";
		}
		return stars;
	}
	
	public static Product getHighestRate(ArrayList<Product> arr){
		Product highest = arr.get(0);
		for(int i=1;i<arr.size();i++) {
			if(arr.get(i).getAvgUserRating()>highest.getAvgUserRating()) {
				highest =arr.get(i);
			}
		}
		return highest;
	}
	
    public static Product getLowestRate(ArrayList<Product> arr){
		Product lowest = arr.get(0);
		for(int i=1;i<arr.size();i++) {
			if(arr.get(i).getAvgUserRating()<lowest.getAvgUserRating()) {
				lowest =arr.get(i);
			}
		}
		return lowest;
	}
    
    public static Product getHighestDollar(ArrayList<Product> arr) {
    	Product highest = arr.get(0);
    	for(int i=1;i<arr.size();i++) {
			if(arr.get(i).getPrice()*arr.get(i).getQuantity()>highest.getPrice()*highest.getQuantity()) {
				highest =arr.get(i);
			}
		}
    	return highest;
    }
    
    public static Product getLowestDollar(ArrayList<Product> arr) {
    	Product lowest = arr.get(0);
    	for(int i=1;i<arr.size();i++) {
			if(arr.get(i).getPrice()*arr.get(i).getQuantity()<lowest.getPrice()*lowest.getQuantity()) {
				lowest =arr.get(i);
			}
		}
    	return lowest;
    }
	
	public static void display(ArrayList<Product> arr) {
		System.out.println("Product Inventory Summary Report");
		System.out.println("---------------------------------------------------------------------------");
		System.out.println();
		System.out.println("Product Name              I Code    Type  Rating # Rat. Quant. Price ");
		System.out.println("------------------------- --------- ----  ------ ------ ------ ------");
		for (int i=0; i<arr.size();i++) {
			Product p = arr.get(i);
			String rating = getRating(p);
			System.out.printf("%-26.25s%-10s%-5s%-7s%7d%7d%7.2f",
					p.getName(),p.getInventoryCode(),p.getType(),rating,
					p.getUserRatingCount(),p.getQuantity(),p.getPrice());
			System.out.println();
		}
		System.out.println("---------------------------------------------------------------------------");
		System.out.println("Total products in database: "+arr.size());
		System.out.println("Highest Average Ranked item: "+ getHighestRate(arr).getName()+" ("+getRating(getHighestRate(arr))+")");
		System.out.println("Lowest Average Ranked item: "+ getLowestRate(arr).getName()+" ("+getRating(getLowestRate(arr))+")");
		double highDollar = getHighestDollar(arr).getPrice()*getHighestDollar(arr).getQuantity();
		System.out.printf("Highest Total Dollar item: %s ($%,.2f)",getHighestDollar(arr).getName(),highDollar);
		System.out.println();
		double lowDollar = getLowestDollar(arr).getPrice()*getLowestDollar(arr).getQuantity();
		System.out.printf("Lowest Total Dollar item: %s ($%,.2f)",getLowestDollar(arr).getName(),lowDollar);
		System.out.println();
		System.out.println("---------------------------------------------------------------------------");
	}

}
