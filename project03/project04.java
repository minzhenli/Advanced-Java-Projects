/*
 * @author Zhenli Min
 * @version 20190307
 * 
 * I have implemented the customer class 
 * and also create a method for output file.
 */

import java.io.*;
import java.util.*;

public class project04 {
	public static void main(String[]args) {
		Scanner in = new Scanner(System.in);
		System.out.print("Enter database filename: ");
		String fileName=in.nextLine();
		while(fileName.equals("")) {
			System.out.print("Enter database filename again: ");
			fileName=in.nextLine();
		}
		SimpleCustomer c;
		try {
			c = getCustomer(fileName);
			Queue<SimpleProduct> fulfill = new LinkedList<SimpleProduct>();
			Stack<SimpleProduct> delay = new Stack<SimpleProduct>();
			fillLists(fulfill,delay,c);
			displayFirst(fulfill,c);
			displaySecond(delay,c);
			outPut(fileName,c);
		} catch (IOException e) {
			System.out.println("Error reading the file or no such file exist.");
		}
	}
	
	public static SimpleCustomer getCustomer(String fileName) throws IOException {
		SimpleCustomer c = new SimpleCustomer();
		List<SimpleProduct> productList = new LinkedList<SimpleProduct>();
		Scanner inFile = new Scanner(new File(fileName));
		c.readNextCustomer(inFile);
			while(inFile.hasNextLine()) {
				SimpleProduct product= new SimpleProduct();
				if(product.readNextProduct(inFile)) {
					productList.add(product);
				}
			}
		c.setProduct(productList);
		return c;
	}
	
	public static void fillLists(Queue<SimpleProduct> fulfill,Stack<SimpleProduct> delay,SimpleCustomer c) {
		List<SimpleProduct> list= c.getProductList();
		for(int i=0;i<list.size();i++) {
			SimpleProduct sp = list.get(i);
			if(sp.inStock) {
				fulfill.add(sp);
			}
			else {
				delay.push(sp);
			}
		}
	}
	
	public static void displayFirst(Queue<SimpleProduct> fulfill,SimpleCustomer c) {
		System.out.println();
		System.out.println("Shipping To:");
		System.out.println("        "+c.getFirstName()+" "+c.getLastName());
		System.out.println("        "+c.getStreet());
		System.out.println("        "+c.getCity()+" "+c.getState()+" "+c.getZip());
		System.out.println("-------------------------------------------------------------------------------");
		double subtotal = 0;
		while(!fulfill.isEmpty()) {
			SimpleProduct sp = fulfill.remove();
			System.out.printf("%-2s%-2d%-2s%-30.30s%-21.21s%6.2f%n","",
					sp.getQuantity(),"x",sp.getName(),"("+sp.getType()+")",
					sp.getPrice()*sp.getQuantity());
			subtotal = subtotal+sp.getPrice()*sp.getQuantity();
		}
		System.out.println("  ---------------------------------------------------------------------------");
		System.out.printf("%-2s%-9.9s%52.2f%n","","Subtotal:",subtotal);
		double tax=subtotal*c.getTax();
		System.out.printf("%-2s%10.10s%6.6s%45.2f%n","","Sales Tax:","("+c.getTax()+")",tax);
		double rate=0;
		if(subtotal>=25) {
			rate=0;
		}
		else if(subtotal>=10) {
			rate=0.05;
		}
		else {
			rate=0.15;
		}
		double shipping= rate*subtotal;
		double total= shipping+subtotal+tax;
		System.out.printf("%-2s%-9.9s%52.2f%n","","Shipping:",shipping);
		System.out.println("  ---------------------------------------------------------------------------");
		System.out.printf("%-2s%-6.6s%55.2f%n","","Total:",total);
		System.out.println("-------------------------------------------------------------------------------");
	}
	
	public static void displaySecond(Stack<SimpleProduct> delay,SimpleCustomer c) {
		System.out.println();
		System.out.println("Orders Outstanding For: ");
		System.out.println("        "+c.getFirstName()+" "+c.getLastName());
		System.out.println("        "+c.getStreet());
		System.out.println("        "+c.getCity()+" "+c.getState()+" "+c.getZip());
		System.out.println("-------------------------------------------------------------------------------");
		double subtotal = 0;
		while(!delay.isEmpty()) {
			SimpleProduct sp = delay.pop();
			System.out.printf("%-2s%-2d%-2s%-30.30s%-21.21s%6.2f%n","",
					sp.getQuantity(),"x",sp.getName(),"("+sp.getType()+")",
					sp.getPrice()*sp.getQuantity());
			subtotal = subtotal+sp.getPrice()*sp.getQuantity();
		}
		System.out.println("  ---------------------------------------------------------------------------");
		System.out.printf("%-2s%-20.20s%41.2f%n","","Outstanding Balance:",subtotal);
		System.out.println("-------------------------------------------------------------------------------");
	}
	
	public static void outPut(String fileName, SimpleCustomer c) {
		Queue<SimpleProduct> fulfill = new LinkedList<SimpleProduct>();
		Stack<SimpleProduct> delay = new Stack<SimpleProduct>();
		fillLists(fulfill,delay,c);
		Scanner in = new Scanner(System.in);
		System.out.print("Enter output filename: ");
		String out=in.nextLine();
		while(out.equals(fileName)) {
			System.out.print("Enter output filename: (should be different from input filename)");
			out=in.nextLine();
		}
		Stack<SimpleProduct> reverse = new Stack<SimpleProduct>();
		while(!delay.isEmpty()) {
			reverse.push(delay.pop());
		}
		try {
			PrintWriter outF = new PrintWriter(out);
			outF.println(c.getLastName());
			outF.println(c.getFirstName());
			outF.println(c.getStreet());
			outF.println(c.getCity());
			outF.println(c.getState());
			outF.println(c.getZip());
			outF.println(c.getTax());
			while(!reverse.isEmpty()) {
				SimpleProduct sp = reverse.pop();
				outF.println(sp.getName());
				outF.println(sp.getType());
				outF.println(sp.getPrice());
				outF.println(sp.getQuantity());
				outF.println(sp.getInStock());
				}
			outF.close();
		}
		catch(IOException e){
			System.out.println("Error creating the file.");
		}
	}

}
