package evaluator;

import parser.ArithParser;
import stack.LinkedStack;

public class InfixEvaluator extends Evaluator {

	private LinkedStack<String> operators = new LinkedStack<String>();
	private LinkedStack<Integer> operands = new LinkedStack<Integer>();

	/** return stack object (for testing purpose) */
	public LinkedStack<String> getOperatorStack() {
		return operators;
	}

	public LinkedStack<Integer> getOperandStack() {
		return operands;
	}

	

	// me, working
	public void process_operator() throws Exception {
		String operator = operators.pop();

		if (operator.equals("!")) {
			Integer right = operands.pop();

			if (right == null)
				throw new Exception("too few operands");
			operands.push(-right);
		} else {
			Integer right = operands.pop(), left = operands.pop();

			if (right == null || left == null)
				throw new Exception("too few operands");

			switch (operator) {
			case "+":
				operands.push(left + right);
				break;
			case "-":
				operands.push(left - right);
				break;
			case "*":
				operands.push(left * right);
				break;
			case "/":
				if (right == 0)
					throw new Exception("division by zero");
				operands.push(left / right);
				break;
			default:
				throw new Exception("invalid operator");
			}
		}
	}

	/**
	 * This method performs one step of evaluation of a infix expression. The input
	 * is a token. Follow the infix evaluation algorithm to implement this method.
	 * If the expression is invalid, throw an exception with the corresponding
	 * exception message.
	 */
	public void evaluate_step(String token) throws Exception {
		if (isOperand(token)) {
			operands.push(Integer.parseInt(token));
		} else {
			switch (token) {
			case "(":
				operators.push(token);
				break;
			case ")":
				boolean flag = true;
				while (flag) {
					if (operators.top() == null)
						throw new Exception("missing (");
					if (operators.top().equals("("))
						flag = false;
					else
						process_operator();
				}
				operators.pop();
				break;
			default:
				while (!operators.isEmpty() && !(precedence(token) > precedence(operators.top()))) {
					process_operator();
				}
				operators.push(token);

			}
		}
	}

	/**
	 * This method evaluates an infix expression (defined by expr) and returns the
	 * evaluation result. It throws an Exception object if the infix expression is
	 * invalid.
	 */
	public Integer evaluate(String expr) throws Exception {

		for (String token : ArithParser.parse(expr)) {
			evaluate_step(token);
		}

		// me
		while (!operators.isEmpty()) {
			process_operator();
		}

		// The operand stack should have exactly one operand after the evaluation is
		// done
		if (operands.size() > 1)
			throw new Exception("too many operands");
		else if (operands.size() < 1)
			throw new Exception("too few operands");

		return operands.pop();
	}

}
