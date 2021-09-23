/*
 * @author Zhenli Min
 * @version 20190404
 */
import java.util.*;
import java.io.*;

public class ShortestPath {
	public static void main(String[]args) {
		System.out.print("Enter a filename with paths: ");
		Scanner in = new Scanner(System.in);
		String fname = in.nextLine();
		while (fname.length()==0) {
			System.out.print("Enter a filename with paths: ");
			fname = in.nextLine();
		}
		Map<String,List<Path>> adjList = readPaths(fname);
		if(adjList!=null) {
			displayAdjacencyList(adjList);
			System.out.print("Enter a start city (empty line to quit): ");
			String city = in.nextLine();
			while(!city.isEmpty()) {
				if(adjList.containsKey(city)) {
					System.out.println();
					Map<String,Double> shortest = findDistances(city,adjList);
					displayShortest(city,shortest);
					System.out.println();
					System.out.print("Enter a start city (empty line to quit): ");
					city = in.nextLine();
				}
				else {
					System.out.println();
					System.out.print("Enter a start city (empty line to quit): ");
					city = in.nextLine();
				}
			}
			System.out.println("Goodbye!");
		}
	}
	
	public static Map<String,List<Path>> readPaths(String fname){
		Map<String,List<Path>> adjList = new HashMap<String,List<Path>>();
		try {
			File f = new File(fname);
			Scanner inFile = new Scanner(f);
			while(inFile.hasNext()) {
				String[]tokens = inFile.nextLine().split(",");
				if(adjList.containsKey(tokens[0])) {
					Path p = new Path(tokens[1],Double.parseDouble(tokens[2]));
					List<Path> list = adjList.get(tokens[0]);
					list.add(p);
					adjList.put(tokens[0], list);
				}
				else {
					Path p = new Path(tokens[1],Double.parseDouble(tokens[2]));
					List<Path> list = new LinkedList<Path>();
					list.add(p);
					adjList.put(tokens[0], list);
				}
				if(adjList.containsKey(tokens[1])) {
					Path p = new Path(tokens[0],Double.parseDouble(tokens[2]));
					List<Path> list = adjList.get(tokens[1]);
					list.add(p);
					adjList.put(tokens[1], list);
				}
				else {
					Path p = new Path(tokens[0],Double.parseDouble(tokens[2]));
					List<Path> list = new LinkedList<Path>();
					list.add(p);
					adjList.put(tokens[1], list);	
				}
			}
		}
		catch(IOException e) {
			System.out.println("There is an error reading from the input file.");
			return null;
		}
		return adjList;
	}
	
	public static void displayAdjacencyList(Map< String,List<Path> > map) {
		System.out.println("Start City     Paths");
		System.out.println("-------------- ------------------------------");
		Set<String> mySet = map.keySet();
		Iterator<String> iter = mySet.iterator();
		while(iter.hasNext()) {
			String city = iter.next();
			List<Path> list = new LinkedList<Path>();
			list.addAll(map.get(city));
			System.out.printf("%-14s",city);
			while(!list.isEmpty()) {
				Path p = list.remove(0);
				if(!list.isEmpty()) {
				System.out.print("("+p.getEndpoint()+":"+p.getCost()+"), ");
				}
				else {
				System.out.print("("+p.getEndpoint()+":"+p.getCost()+")");
				}
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static Map<String, Double> findDistances(String start, Map<String,List<Path>> adj_list){
		Map<String,Double> shortest = new HashMap<String,Double>();
		Map<String,List<Path>> myMap = new HashMap<String,List<Path>>();
		myMap.putAll(adj_list);
		PriorityQueue<Path> priQ = new PriorityQueue<Path>();
		Path p = new Path(start,0);
		priQ.add(p);
		Path p1=priQ.remove();
		String city = p1.getEndpoint();
		Double cost = p1.getCost();
		shortest.put(city, cost);
		while(shortest.size()!=myMap.size()) {
			List<Path> list1 = new LinkedList<Path>();
			List<Path> list2 = new LinkedList<Path>();
			list1 = myMap.get(city);
			for(int i=0; i<list1.size();i++) {
				list2.add(list1.get(i));
			}
			while(!list2.isEmpty()) {
				Path p2 = list2.remove(0);
				Path p3 = new Path(p2.getEndpoint(),p2.getCost()+cost);
				priQ.add(p3);
			}
			p1=priQ.remove();
			city = p1.getEndpoint();
			cost = p1.getCost();
			if(!shortest.containsKey(city)) {
				shortest.put(city,cost);
			}
		}
		return shortest;
	}
	
	public static void displayShortest(String start, Map<String, Double> shortest) {
		System.out.println("Distances from " + start + " to each city:");
		System.out.println("Dest. City     Distance");
		System.out.println("-------------- --------");
		Map<String,Double> myMap = new HashMap<String,Double>();
		myMap.putAll(shortest);
		Set<String> citySet = myMap.keySet();
		Iterator<String> iter = citySet.iterator();
		while(iter.hasNext()) {
			String city = iter.next();
			System.out.printf("%-14s %8.2f", city,myMap.get(city));
			System.out.println();
		}
		
	}

	

}
