package fizzbuzz;

import java.util.Arrays;

public class FizzBuzz {
	private final int fizzNumber;
	private final int buzzNumber;

	private static final int I=5;
	
	
	
	
	public static void main(String[] args) {
		FizzBuzz game = new FizzBuzz(6, 9);
		System.out.println(Arrays.toString(game.getValues(1, 100)));
		
		int x=2;
		int y=5;
		double d = x/y;
		
		System.out.println(d);

	}

	/**
	 * Construct an object that can compute fizzbuzz values for a game of Fizz and
	 * Buzz.
	 * 
	 * @param fizzNumber:
	 *            an integer between 1 and 9
	 * @param buzzNumber:
	 *            an integer between 1 and 9
	 */
	public FizzBuzz(int fizzNumber, int buzzNumber) {
		this.fizzNumber = fizzNumber;
		this.buzzNumber = buzzNumber;
	}

	/**
	 * Returns the fizzbuzz value for n. The rules are: - if n is divisible by
	 * fizzNumber, or contains the digit fizzNumber, return "fizz" - if n is
	 * divisible by buzzNumber, or contains the digit buzzNumber, return "buzz" -
	 * however, if both the above conditions are true, you must return "fizzbuzz" -
	 * if none of the above conditions is true, return the number itself.
	 *
	 * For example, getValue(1) returns "1".
	 * 
	 * @param n:
	 *            a positive integer
	 * @return the fizzbuzz value, as a String
	 */
	public String getValue(int n) {
		if (n % fizzNumber == 0 || contains(n, fizzNumber)) {

			if (n % buzzNumber == 0 || contains(n, buzzNumber)) {
				return "fizzbuzz";
			}
			return "fizz";
		} else if (n % buzzNumber == 0 || contains(n, buzzNumber)) {
			return "buzz";
		}

		return Integer.toString(n); // return the number itself as a String
	}

	// check if biggerNum contains num
	private boolean contains(int biggerNum, int num) {
		if (biggerNum == 0) {
			if (num == 0) {
				return true;
			}
			return false;
		}
		if (biggerNum % 10 == num) {
			return true;
		}

		return contains(biggerNum / 10, num);
	}

	/**
	 * Returns an array of the fizzbuzz values from start to end, inclusive.
	 * 
	 * For example, if the fizz number is 3 and buzz number is 4, getValues(2,6)
	 * should return an array of Strings:
	 * 
	 * {"2", "fizz", "buzz", "5", "fizz"}
	 * 
	 * @param start
	 *            the number to start from; start > 0
	 * @param end
	 *            the number to end at; end >= start
	 * @return the array of fizzbuzz values
	 */
	public String[] getValues(int start, int end) {
		int range = end - start + 1;
		String[] out = new String[range];
		for (int i = 0; i < range; i++) {
			out[i] = getValue(i+start);
		}

		return out;
	}
}
