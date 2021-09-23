/*
 * @author Zhenli Min
 * @version 20190421
 */
import osu.cse2123.TreeNode;
import java.util.*;

public class ExpressionTree {
	public static TreeNode<String> buildTreeFromString(String expr){
		if(expr.isEmpty()) {
			return null;
		}
		else {
			String[] exprArr=expr.split("\\s+");
			Stack<TreeNode> exprStack = new Stack<TreeNode>();
			for(int i=0;i<exprArr.length;i++) {
				if(Character.isDigit(exprArr[i].charAt(0))) {
					TreeNode<String> node = new TreeNode<String>(exprArr[i]);
					exprStack.push(node);
				}
				else {
					if(exprStack.size()<2) {
						return null;
					}
					else {
						TreeNode<String> node = new TreeNode<String>(exprArr[i]);
						node.setRightChild(exprStack.pop());
						node.setLeftChild(exprStack.pop());
						exprStack.push(node);	
					}
				}
			}
			if(exprStack.size()>1) {
				return null;
			}
			else {
				return exprStack.pop();
			}
		}
	}
	
	public static String toPostfixString(TreeNode<String> expr) {
		String node = expr.getData();
		if(expr.getLeftChild()==null&&expr.getRightChild()==null) {
			return node;
		}
		else {
			String left = toPostfixString(expr.getLeftChild());
			String right = toPostfixString(expr.getRightChild());
			String result = left+" "+right+" "+node;
			return result;
		}
	}
	
	public static String toInfixString(TreeNode<String> expr) {
		String node = expr.getData();
		if(expr.getLeftChild()==null&&expr.getRightChild()==null) {
			return node;
		}
		else {
			String left = toInfixString(expr.getLeftChild());
			String right = toInfixString(expr.getRightChild());
			String result = "("+left+" "+node+" "+right+")";
			return result;
		}
	}
	
	public static String toPrefixString(TreeNode<String> expr) {
		String node = expr.getData();
		if(expr.getLeftChild()==null&&expr.getRightChild()==null) {
			return node;
		}
		else {
			String left = toPrefixString(expr.getLeftChild());
			String right = toPrefixString(expr.getRightChild());
			String result = node+" "+left+" "+right;
			return result;
		}
		
	}

	public static int evaluate(TreeNode<String> expr) {
		String node = expr.getData();
		int result = 0;
		if(Character.isDigit(node.charAt(0))) {
			int i = Integer.parseInt(node);
			return i;
		}
		else {
			int left = evaluate(expr.getLeftChild());
			int right = evaluate(expr.getRightChild());
			if(node.charAt(0)=='+') {
				result = left+right;
			}
			if(node.charAt(0)=='-') {
				result = left-right;
			}
			if(node.charAt(0)=='*') {
				result = left*right;
			}
			if(node.charAt(0)=='/') {
				result = left/right;
			}
			if(node.charAt(0)=='%') {
				result = left%right;
			}
			return result;
		}
	}
	
	public static TreeNode<String> buildPrefixTreeFromString(String expr){
		if(expr.isEmpty()) {
			return null;
		}
		else {
			String[] exprArr=expr.split("\\s+");
			Stack<TreeNode> exprStack = new Stack<TreeNode>();
			for(int i=exprArr.length-1;i>=0;i--) {
				if(Character.isDigit(exprArr[i].charAt(0))) {
					TreeNode<String> node = new TreeNode<String>(exprArr[i]);
					exprStack.push(node);
				}
				else {
					if(exprStack.size()<2) {
						return null;
					}
					else {
						TreeNode<String> node = new TreeNode<String>(exprArr[i]);
						node.setLeftChild(exprStack.pop());
						node.setRightChild(exprStack.pop());
						exprStack.push(node);	
					}
				}
			}
			if(exprStack.size()>1) {
				return null;
			}
			else {
				return exprStack.pop();
			}
		}
	}

}
