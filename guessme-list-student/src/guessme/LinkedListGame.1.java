
package guessme;

public class LinkedListGame {

	private int guess, numGuesses;
	private LLIntegerNode priorGuesses, candidates, newCandidates, newCandidatesLast;

	public LinkedListGame() {
		this.reset();
	}

	public void reset() {
		guess = 1000;
		numGuesses = 0;
		priorGuesses = null;
		newCandidates = null;
		newCandidatesLast = null;
		loadCandidates();
	}



	private void loadCandidates() {
		candidates = new LLIntegerNode(1000);
		LLIntegerNode current = candidates;
		for (int i = 1001; i < 10000; i++) {
			current.setLink(new LLIntegerNode(i));
			current = current.getLink();
		}
	}

	public boolean updateGuess(int nmatches) {
		if (nmatches != 4) {
			newCandidates = null; 
			newCandidatesLast = null;

			if (candidates.getLink() != null) {
				for (LLIntegerNode current = candidates.getLink(); current != null; current = current.getLink()) {
					if (numMatches(current.getInfo(), guess) == nmatches) {
						appendCan(current.getInfo());
					}
				}
			}

			if (newCandidates == null) {

				return false;
			}

			// candidates = candidates.getLink();
			guess = newCandidates.getInfo();
			candidates = newCandidates;
		}
		return true;
	}

	public boolean isPriorGuess(int n) {
		return contains(priorGuesses, n);
	}

	// Returns the number of guesses so far.
	public int numGuesses() {
		return numGuesses;
	}

	public static int numMatches(int a, int b) {
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
	

	public boolean isOver() {
		return isPriorGuess(guess);
	}

	/**
	 * Returns the guess number and adds it to the list of prior guesses. The
	 * insertion should occur at the end of the prior guesses list, so that the
	 * order of the nodes follow the order of prior guesses.
	 */
	public int getGuess() {
		priorGuesses = append(priorGuesses, guess);
		numGuesses++;
		return guess;
	}

	private static LLIntegerNode append(LLIntegerNode head, int info) {
		if (head != null) {
			LLIntegerNode current = head;
			while (current.getLink() != null) {
				current = current.getLink();
			}
			current.setLink(new LLIntegerNode(info));
		} else {
			head = new LLIntegerNode(info);
		}
		return head;
	}

	/**
	 * Updates guess based on the number of matches of the previous guess. If
	 * nmatches is 4, the previous guess is correct and the game is over. Check
	 * project description for implementation details.
	 * 
	 * Returns true if the update has no error; false if no candidate is left
	 * (indicating a state of error);
	 */

	private static LLIntegerNode append(LLIntegerNode master, LLIntegerNode last, int info) {
		if (master != null) {
			if (last == null) {
				last = master;
				while (last.getLink() != null) {
					last = last.getLink();
				}
			}

			last.setLink(new LLIntegerNode(info));
			last = last.getLink();
		} else {
			master = new LLIntegerNode(info);
			last = master;
		}
		return master;
	}
	
	private void appendCan(int info) {
		if (newCandidates != null) {
			if (newCandidatesLast == null) {
				newCandidatesLast = newCandidates;
				while (newCandidatesLast.getLink() != null) {
					newCandidatesLast = newCandidatesLast.getLink();
				}
			}

			newCandidatesLast.setLink(new LLIntegerNode(info));
			newCandidatesLast = newCandidatesLast.getLink();
		} else {
			newCandidates = new LLIntegerNode(info);
			newCandidatesLast = newCandidates;
		}
	}

	private static boolean contains(LLIntegerNode list, int n) {
		while (list != null) {
			if (list.getInfo() == n) {
				return true;
			}
			list = list.getLink();
		}

		return false;
	}

	// Returns the head of the prior guesses list.
	// Returns null if there hasn't been any prior guess
	public LLIntegerNode priorGuesses() {
		return priorGuesses;
	}

	/**
	 * Returns the list of prior guesses as a String. For example, if the prior
	 * guesses are 1000, 2111, 3222, in that order, the returned string should be
	 * "1000, 2111, 3222", in the same order, with every two numbers separated by a
	 * comma and space, except the last number (which should not be followed by
	 * either comma or space).
	 *
	 * Returns an empty string if here hasn't been any prior guess
	 */
	public String priorGuessesString() {
		String out = "";
		if (priorGuesses != null) {
			for (LLIntegerNode priors = priorGuesses; priors != null; priors = priors.getLink()) {
				out += priors.getInfo() + ", ";
			}
			out = out.substring(0, out.length() - 2);
		}
		return out;
	}
}
