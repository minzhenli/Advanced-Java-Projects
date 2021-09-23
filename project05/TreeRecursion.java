/*
 * @author Zhenli Min
 * @version 20190421
 */
import java.util.*;
import osu.cse2123.TreeNode;
public class TreeRecursion extends ExpressionTree {
	public static void main(String[] args) {
		System.out.println("No expression in memory");
		System.out.println();
		System.out.println("Enter your choice:\n" + 
							"[S]et the display format\n" + 
							"[E]nter a new expression\n" + 
							"[Q]uit");
		System.out.print(">");
		Scanner in = new Scanner(System.in);
		String input = in.nextLine();
		TreeNode<String> tree=new TreeNode<String>("");
		while(!input.equals("Q")&&!input.equals("q")) {
			if(input.equals("E")||input.equals("e")) {
				tree=enterNew(in);
				System.out.println();
				System.out.println("Enter your choice:\n" + 
						"[S]et the display format\n" + 
						"[E]nter a new expression\n" + 
						"[Q]uit");
				System.out.print(">");
				input = in.nextLine();
			}
			else if(input.equals("S")||input.equals("s")) {
				setFormat(in,tree);
				System.out.println();
				System.out.println("Enter your choice:\n" + 
						"[S]et the display format\n" + 
						"[E]nter a new expression\n" + 
						"[Q]uit");
				System.out.print(">");
				input = in.nextLine();
			}
			else {
				System.out.println("ERROR!");
				System.out.println("Enter your choice:\n" + 
						"[S]et the display format\n" + 
						"[E]nter a new expression\n" + 
						"[Q]uit");
				System.out.print(">");
				input = in.nextLine();
			}
		}
		System.out.println();
		System.out.println("Goodbye!");
	}
	public static TreeNode<String> enterNew(Scanner in) {
		String input ="";
		TreeNode <String> tree = new TreeNode<String>("");
		System.out.println();
		System.out.print("Enter your expression in:\n"+
							"[P]ostfix notation\n"+
							"p[R]efix notation\n"+
							">");
		String choice = in.nextLine();
		System.out.println();
		while(!choice.equals("p")&&!choice.equals("P")&&!choice.equals("r")&&!choice.equals("R")) {
			System.out.print("Enter your expression in:\n"+
					"[P]ostfix notation\n"+
					"p[R]efix notation\n"+
					">");
			 choice = in.nextLine();
			 System.out.println();
		}
		if(choice.equals("p")||choice.equals("P")) {
			System.out.print("Enter your expression in postfix notation: ");
			input = in.nextLine();
			tree = buildTreeFromString(input);
			while(tree==null) {
				System.out.println("Error!");
				System.out.print("Enter your expression in postfix notation: ");
				input = in.nextLine();
				tree = buildTreeFromString(input);
			}
		}
		else {
			System.out.print("Enter your expression in pretfix notation: ");
			input = in.nextLine();
			
			tree = buildPrefixTreeFromString(input);
			while(tree==null) {
				System.out.println("Error!");
				System.out.print("Enter your expression in pretfix notation: ");
				input = in.nextLine();
				tree = buildPrefixTreeFromString(input);
			}
		}
		
		System.out.println(input+" = "+evaluate(tree));
		return tree;
	}
	
	public static void setFormat(Scanner in,TreeNode <String> tree) {
		if(tree==null) {
			System.out.print("No expression available");
		}
		System.out.println();
		System.out.print("Enter your preferred output display:\n" + 
						"[P]ostfix\n" + 
						"[I]nfix\n" + 
						"p[R]efix\n" + 
						">");
		String choice = in.nextLine();
		while(!choice.equals("p")&&!choice.equals("P")&&!choice.equals("i")&&!choice.equals("I")&&!choice.equals("r")&&!choice.equals("R")) {
			System.out.println("ERROR!");
			System.out.print("Enter your preferred output display:\n" + 
					"[P]ostfix\n" + 
					"[I]nfix\n" + 
					"p[R]efix\n" + 
					">");
			choice = in.nextLine();
		}
		if(choice.equals("p")||choice.equals("P")) {
			System.out.println(toPostfixString(tree)+" = "+evaluate(tree));
		}
		if(choice.equals("i")||choice.equals("I")) {
			System.out.println(toInfixString(tree)+" = "+evaluate(tree));
		}
		if(choice.equals("r")||choice.equals("R")) {
			System.out.println(toPrefixString(tree)+" = "+evaluate(tree));
		}		
	}
}
