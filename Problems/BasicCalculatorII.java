package leetcode;

import java.util.Stack;

/**
 * @author manmohan This is leet code Basic Calculator II problem
 *         https://leetcode.com/problems/basic-calculator-ii/ Given a string s
 *         which represents an expression, evaluate this expression and return
 *         its value.
 * 
 *         The integer division should truncate toward zero.
 * 
 *         Example 1:
 * 
 *         Input: s = "3+2*2" Output: 7 Example 2:
 * 
 *         Input: s = " 3/2 " Output: 1 Example 3:
 * 
 *         Input: s = " 3+5 / 2 " Output: 5
 */

public class BasicCalculatorII {

	public static void main(String[] args) {

		String s = " 3+5 / 2 ";
		System.out.println(calculate(s));

	}

	public static int calculate(String s) {
		Stack<Integer> stack = new Stack<>();
		int result = 0;
		int currentNumber = 0;
		char operation = '+';
		for (int i = 0; i < s.length(); i++) {
			char currentChar = s.charAt(i);
			if (Character.isDigit(currentChar)) {
				currentNumber = currentNumber * 10 + currentChar - '0';
			} else if (!Character.isDigit(currentChar) && !Character.isWhitespace(currentChar) || i == s.length() - 1) {
				if (operation == '-') {
					stack.push(-currentNumber);
				} else if (operation == '+') {
					stack.push(currentNumber);
				} else if (operation == '*') {
					stack.push(stack.pop() * currentNumber);
				} else if (operation == '/') {
					stack.push(stack.pop() / currentNumber);
				}
				operation = currentChar;
				currentNumber = 0;
			}
		}
		
		while(!stack.isEmpty()) {
			result += stack.pop();
		}
		
		return result;
	}
}
