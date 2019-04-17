package guessme;

import java.util.Arrays;

/**
 * An Array-based implementation of the Guess-A-Number game
 */
public class ArrayGame {

	// stores the next number to guess
	private int guess, numGuesses;
	private int[] priorStore;
	private boolean[] eliminated;

	public static void main(String[] args) {
		ArrayGame a = new ArrayGame();
		
		//4210
				System.out.println(a.getGuess());
				a.updateGuess(1);
				System.out.println(a.getGuess());
				a.updateGuess(1);
				System.out.println(a.getGuess());
				a.updateGuess(1);
				System.out.println(a.getGuess());
				a.updateGuess(0);
				System.out.println(a.getGuess());
				a.updateGuess(1);
				System.out.println(a.getGuess());
				a.updateGuess(1);
				System.out.println(a.getGuess());
				a.updateGuess(1);
				System.out.println(a.getGuess());
				a.updateGuess(0);
				System.out.println(a.getGuess());
				a.updateGuess(1);
				System.out.println(a.getGuess());
				a.updateGuess(4);
				//System.out.println(a.updateGuess(0));
				//System.out.println(a.getGuess());
				System.out.println(a.numGuesses());
	}

	private void printArray() {
		for (int i = 0; i < eliminated.length; i++) {
			System.out.print(i + 1000 + ": " + (eliminated[i] ? "eliminated" : "possible") + ", ");
		}
	}

	/**
	 * Updates guess based on the number of matches of the previous guess. If
	 * nmatches is 4, the previous guess is correct and the game is over. Check
	 * project description for implementation details.
	 * 
	 * Returns true if the update has no error; false if all candidates have been
	 * eliminated (indicating a state of error);
	 */
	public boolean updateGuess(int nmatches) {
		for (int i = 0; i < eliminated.length; i++) {
			if (numMatches(i + 1000, guess) != nmatches) {
				eliminated[i] = true;
			}
		}

		// update guess and check for error.
		for (int i = 0; i < eliminated.length; i++) {
			if (!eliminated[i]) {
				guess = i + 1000;
				// if (isOver()) {
				// reset();
				// }
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns true if the game is over; false otherwise. The game is over if the
	 * number has been correctly guessed or if all candidates have been eliminated.
	 */
	public boolean isOver() {
		return isPriorGuess(guess);
	}

	// Returns the guess number and adds it to the list of prior guesses.
	public int getGuess() { // done
		priorStore[numGuesses] = guess;
		numGuesses++;
		return guess;
	}

	/**
	 * Returns the number of matches between integers a and b. You can assume that
	 * both are 4-digits long (i.e. between 1000 and 9999). The return value must be
	 * between 0 and 4.
	 * 
	 * A match is the same digit at the same location. For example: 1234 and 4321
	 * have 0 match; 1234 and 1114 have 2 matches (1 and 4); 1000 and 9000 have 3
	 * matches (three 0's).
	 */
	public static int numMatches(int a, int b) { // done and tested
		int matches = 0;
		if (a / 1000 == b / 1000) {
			matches++;
		}
		a %= 1000;
		b %= 1000;

		if (a / 100 == b / 100) {
			matches++;
		}
		a %= 100;
		b %= 100;

		if (a / 10 == b / 10) {
			matches++;
		}
		a %= 10;
		b %= 10;

		if (a == b) {
			matches++;
		}

		return matches;
	}

	// ArrayGame constructor method
	public ArrayGame() {
		this.reset();
	}

	// Resets data members and game state so we can play again
	public void reset() {
		guess = 1000;
		numGuesses = 0;
		priorStore = new int[17];
		eliminated = new boolean[9000];
	}

	// Returns true if n is a prior guess; false otherwise.
	public boolean isPriorGuess(int n) { // done
		if (numGuesses == 0) {
			return false;
		}
		for (int guess : this.priorGuesses()) {
			if (n == guess)
				return true;
		}
		return false;
	}

	// Returns the number of guesses so far.
	public int numGuesses() { // done
		return numGuesses;
	}

	// Returns the list of guesses so far as an integer array.
	// The size of the array must be the number of prior guesses.
	// Returns null if there has been no prior guess
	public int[] priorGuesses() {
		if (numGuesses == 0) {
			return null;
		}

		int[] priors = new int[numGuesses];

		for (int i = 0; i < numGuesses; i++) {
			priors[i] = priorStore[i];
		}

		return priors;
	}
	// private static boolean contains(int four, int digit) {
	// if (four / 1000 == digit) {
	// return true;
	// }
	// four %= 1000;
	//
	// if (four / 100 == digit) {
	// return true;
	// }
	// four %= 100;
	//
	// if (four / 10 == digit) {
	// return true;
	// }
	// four %= 10;
	//
	// if (four == digit) {
	// return true;
	// }
	// return false;
	// }
}


