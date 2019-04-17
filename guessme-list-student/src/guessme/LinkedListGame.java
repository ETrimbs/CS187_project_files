package guessme;

/**
 * A LinkedList-based implementation of the Guess-A-Number game
 */
public class LinkedListGame {
	
	private int guess, numGuesses;
	private LLIntegerNode priorGuessesHead, eliminatedHead;

	// TODO: declare data members as necessary

	
	// LinkedListGame constructor method
	public LinkedListGame() {
		this.reset();
	}

	public void reset() {
		guess = 1000;
		numGuesses = 0;
		priorGuessesHead = null;
		eliminatedHead = null;
	}

	public boolean isPriorGuess(int n) { // done
		if (numGuesses == 0) {
			return false;
		}
		LLIntegerNode current = priorGuessesHead;
		while (current.getLink() != null) {
			if (current.getInfo() == n)
				return true;
		}
		return false;
	}
	
	// Returns the number of guesses so far.
	public int numGuesses() {
		// TODO
		return 0;
	}
	
	/**
	 * Returns the number of matches between integers a and b.
	 * You can assume that both are 4-digits long (i.e. between 1000 and 9999).
	 * The return value must be between 0 and 4.
	 * 
	 * A match is the same digit at the same location. For example:
	 *   1234 and 4321 have 0 match;
	 *   1234 and 1114 have 2 matches (1 and 4);
	 *   1000 and 9000 have 3 matches (three 0's).
	 */
	public static int numMatches(int a, int b) {
		// TODO
		return 0;
	}
	
	/**
	 * Returns true if the game is over; false otherwise.
	 * The game is over if the number has been correctly guessed
	 * or if no candidate is left.
	 */
	public boolean isOver() {
		// TODO
		return false;
	}
	
	/**
	 * Returns the guess number and adds it to the list of prior guesses.
	 * The insertion should occur at the end of the prior guesses list,
	 * so that the order of the nodes follow the order of prior guesses.
	 */	
	public int getGuess() {
		// TODO: add guess to the list of prior guesses.
		return 0;
	}
	
	/**
	 * Updates guess based on the number of matches of the previous guess.
	 * If nmatches is 4, the previous guess is correct and the game is over.
	 * Check project description for implementation details.
	 * 
	 * Returns true if the update has no error; false if no candidate 
	 * is left (indicating a state of error);
	 */
	public boolean updateGuess(int nmatches) {
		// TODO
		return true;
	}
	
	// Returns the head of the prior guesses list.
	// Returns null if there hasn't been any prior guess
	public LLIntegerNode priorGuesses() {
		// TODO
		return null;
	}
	
	/**
	 * Returns the list of prior guesses as a String. For example,
	 * if the prior guesses are 1000, 2111, 3222, in that order,
	 * the returned string should be "1000, 2111, 3222", in the same order,
	 * with every two numbers separated by a comma and space, except the
	 * last number (which should not be followed by either comma or space).
	 *
	 * Returns an empty string if here hasn't been any prior guess
	 */
	public String priorGuessesString() {
		// TODO
		return "";
	}
	
	/*

	// Resets data members and game state so we can play again
	public void reset() {
		guess = 1000;
		numGuesses = 0;
		priorStore = new int[17];
		eliminated = new boolean[9000];
	}
	
	public LinkedListGame() {
		this.reset();
	}

	public void reset() {
		guess = 1000;
		numGuesses = 0;
		priorStore = new int[17];
		eliminated = new boolean[9000];
	}

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

	
	/*
	private void printArray() {
		for (int i = 0; i < eliminated.length; i++) {
			System.out.print(i + 1000 + ": " + (eliminated[i] ? "eliminated" : "possible") + ", ");
		}
	}

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

	public boolean isOver() {
		return isPriorGuess(guess);
	}

	public int getGuess() { // done
		priorStore[numGuesses] = guess;
		numGuesses++;
		return guess;
	}

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

	public ArrayGame() {
		this.reset();
	}

	public void reset() {
		guess = 1000;
		numGuesses = 0;
		priorStore = new int[17];
		eliminated = new boolean[9000];
	}

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

	public int[] priorGuesses() {
		if (numGuesses == 0) {
			return null;
		}

		int[] priors = new int[numGuesses];

		for (int i = 0; i < numGuesses; i++) {
			priors[i] = priorStore[i];
		}

		return priors;
	}*/
}
