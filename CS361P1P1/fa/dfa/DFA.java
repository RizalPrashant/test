package fa.dfa;



import java.util.Iterator;

import java.util.LinkedHashSet;

import java.util.Set;



import fa.FAInterface;

import fa.State;



public class DFA implements FAInterface, DFAInterface {

	

	protected LinkedHashSet<DFAState> set_of_states;

	protected LinkedHashSet<Character> set_of_alphabets;

	

	public DFA() {

		set_of_states = new LinkedHashSet<>();

		set_of_alphabets = new LinkedHashSet<>();

	}

	

	@Override

	public boolean accepts(String s) {

		DFAState traversal = new DFAState("Traversal", false, false);

		char[] s_array = s.toCharArray();

		traversal = getStartState();

		if(s_array[0] == 'e'){

			return traversal.isFinal;

		}else{

			for(int i = 0; i < s_array.length; i++){

				traversal = traversal.getTransitionKey((s_array[i]));

			}

			return traversal.isFinal;

		}

	}

	

	public String[][] makeStateTable(){

		String[][] stateTable = new String[getStates().size()+1][getABC().size()+1];

		stateTable[0][0] = "";

		Iterator<Character> itr1 = getABC().iterator();

		for(int i = 1; i < getABC().size()+1; i++){

			stateTable[0][i] = itr1.next().toString();

		}

		Iterator<DFAState> itr2 = set_of_states.iterator();

		DFAState current_state = new DFAState("currentstate", false, false);

		for(int j = 1; j < getStates().size()+1; j++){

			current_state = itr2.next();

			stateTable[j][0] = current_state.getName();

			for(int k = 1; k < getABC().size()+1; k++){
				try {
				stateTable[j][k] = getToState(current_state, stateTable[0][k].charAt(0)).getName();
				}
				catch(NullPointerException e) {
					System.out.println("Null Pointer Caught");
				}

			}

		}

		return stateTable;

	}

	

	public String toString(){

		String output_string = "Q = { ";

		Iterator<? extends State> itr1 = getStates().iterator();

		while(itr1.hasNext()){

			output_string += itr1.next().getName() + " ";

		}

		output_string += "}\nSigma = { ";

		Iterator<Character> itr2 = getABC().iterator();

		while(itr2.hasNext()){

			output_string += itr2.next().toString() + " ";

		}

		output_string += "}\ndelta = \n";

		String[][] create_state_table = makeStateTable();

		for(int i = 0; i < getStates().size()+1; i++){

			for(int j = 0; j < getABC().size()+1; j++){

				output_string += "\t" + create_state_table[i][j];

			}

			output_string += "\n";

		}

		output_string += "q0 = " + getStartState().getName() + "\nF = { ";

		Iterator<? extends State> itr3 = getFinalStates().iterator();

		while(itr3.hasNext()){

			output_string += itr3.next().getName() + " ";

		}

		output_string += "}\n";	

		return output_string;

	}



	@Override

	public DFAState getToState(DFAState from, char onSymb) {

		DFAState from_this_state = new DFAState("fromthisstate", false, false);

		Iterator<DFAState> itr = set_of_states.iterator();

		while(!from_this_state.equals(from)){

			from_this_state = itr.next();

		}

		return from_this_state.getTransitionKey(onSymb);

	}



	@Override

	public void addStartState(String name) {

		DFAState startState = new DFAState(name, false, true);

		set_of_states.add(startState);

		

	}



	@Override

	public void addState(String name) {

		DFAState state = new DFAState(name, false, false);

		set_of_states.add(state);	

		

	}



	@Override

	public void addFinalState(String name) {

		DFAState finalState = new DFAState(name, true, false);

		set_of_states.add(finalState);	

	}



	@Override

	public void addTransition(String fromState, char onSymb, String toState) {

		DFAState transitionFromState = new DFAState ("transitionFrom", false, false);

		DFAState transitionToState = new DFAState ("transitionTo", false, false);

		Iterator<DFAState> itr1 = set_of_states.iterator();

		while (!transitionFromState.getName().equals(fromState)) {

			transitionFromState = itr1.next();

		}

		Iterator<DFAState> itr2 = set_of_states.iterator();

		while (!transitionToState.getName().equals(toState)) {

			transitionToState = itr2.next();

		}

		transitionFromState.setTransition(onSymb, transitionToState);

		set_of_alphabets.add(onSymb);

		

	}



	@Override

	public Set<? extends State> getStates() {

			return set_of_states;

		}



	@Override

	public Set<? extends State> getFinalStates() {

		DFAState thisFinalState = new DFAState("thisFinalState", false, false);

		LinkedHashSet<DFAState> set_of_finals = new LinkedHashSet<>();

		Iterator<DFAState> itr = set_of_states.iterator();

		while(itr.hasNext()){

			thisFinalState = itr.next();

			if(thisFinalState.isFinal){

				set_of_finals.add(thisFinalState);

			}

		}

		return set_of_finals;

	}



	@Override

	public DFAState getStartState() {

		DFAState thisStartState = new DFAState("thisStartState", false, false);

		Iterator<DFAState> itr = set_of_states.iterator();

		while(!thisStartState.isStart){

			thisStartState = itr.next();

		}

		return thisStartState;

	}



	@Override

	public Set<Character> getABC() {

		return set_of_alphabets;

	}



}