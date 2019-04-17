package search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * An implementation of a Searcher that performs an iterative search, storing
 * the list of next states in a Stack. This results in a depth-first search.
 * 
 */
public class StackBasedDepthFirstSearcher<T> extends Searcher<T> {

	public StackBasedDepthFirstSearcher(SearchProblem<T> searchProblem) {
		super(searchProblem);
	}

	@Override
	public List<T> findSolution() {

		ArrayList<T> visited = new ArrayList<T>();
		Stack<StatePair<T>> stack = new Stack<StatePair<T>>();
		StatePair<T> currState = new StatePair<T>(searchProblem.getInitialState());
		
		stack.push(currState);
		visited.add(currState.state);
		
		while(!stack.isEmpty()) {
			currState = stack.peek();
			
			
			
			
		}
		
		
		
		
		
		
		return null;
	}

}

class StatePair<T> {
	public T state;
	public T predecessor;

	StatePair(T state, T predecessor) {
		this.state = state;
		this.predecessor = predecessor;
	}

	StatePair(T state) {
		this.state = state;
	}
}
