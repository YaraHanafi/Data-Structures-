public class BalancedParentheses {
	public static boolean isParenthesisMatch(String str) {
		Stack<Character> stack = new Stack<Character>();
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			// If c is a opening parenthesis then push it
			if (c == '{' || c == '(' || c == '[')
				stack.push(c);
			/*
			 * If c is a closing parenthesis, pop a parenthesis from stack and check if the
			 * popped parenthesis and c is a matched pair
			 */
			if (c == '}' || c == ')' || c == ']') {
				// No opening parenthesis exists for the current closing parenthesis
				if (stack.isEmpty())
					return false;
				// pop a parenthesis and compare
				char c2 = stack.pop();
				if (!isMatchingPair(c2, c))
					return false;
			}
		}
		/*
		 * If there is something left in stack, then there is a starting parenthesis
		 * without a closing parenthesis
		 */
		if (stack.isEmpty())
			return true;
		else
			return false;
	}

	private static boolean isMatchingPair(char character1, char character2) {
		if (character1 == '(' && character2 == ')')
			return true;
		else if (character1 == '{' && character2 == '}')
			return true;
		else if (character1 == '[' && character2 == ']')
			return true;
		else
			return false;
	}

	public static void main(String[] args) {
		String str = "[{(})]";
		if (isParenthesisMatch(str))
			System.out.println("Balanced");
		else
			System.out.println("Not Balanced");
	}
}