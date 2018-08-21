package re;

import fa.State;
import fa.nfa.NFA;
import fa.nfa.NFAState;

/**
 * 04/25/2018
 * Class Re which implements REInterface
 * @author Prashant Rizal
 */
public class RE implements REInterface {

    private String regEx; 
    private int stateCount = 0; 

    /**
     * constructor
     * @param regEx
     */
    public RE(String regEx){
        this.regEx = regEx;
    }

    @Override
    public NFA getNFA() {
        return regex();
    }

    /**
     * just peeks at the next item
     * @return a character
     */
    private char peek(){
        return regEx.charAt(0);
    }

    /**
     * the item is eaten
     * @param ch
     */
    private void eat(char ch){
        if(peek() == ch){
            regEx = regEx.substring(1);
        } else {
            throw new RuntimeException("Error");
        }
    }

   /**
    * the next item is done.
    * @return a character
    */
    private char doNext() {
        char ch = peek();
        eat(ch);
        return ch;
    }

    /**
     * check if there are more
     * @return boolean
     */
    private boolean isMore() {
        return regEx.length() > 0;
    }

    /**
     * if pipe found parse two terms
     * @return NFA
     */
    private NFA regex() {
        NFA term = parseTerm();
        if(isMore() && peek() == '|'){
            eat('|');
            return unionNFA(term, regex());
        } else {
            return term;
        }
    }

    /**
     * parse a term
     * @return NFA
     */
    private NFA parseTerm() {
        NFA factor = new NFA();
        String stateName = getStateName();
        factor.addStartState(stateName);
        factor.addFinalState(stateName);

        while (isMore() && peek() != ')' && peek() != '|'){
            NFA next_factor = parseFactor();
            factor =  concatenateNFA(factor, next_factor);
        }
        return factor;
    }

    /**
     * parse a factor
     * @return NFA
     */
    private NFA parseFactor() {
        NFA base = parseBase();
        while (isMore() && peek() == '*'){
            eat('*');
            base = capture_repetition(base);
        }
        return base;
    }

    /**
     * parse a base
     * @return NFA
     */
    private NFA parseBase(){
        NFA thisNfa;
        switch (peek()){
            case '(':
                eat('(');
                thisNfa = regex();
                eat(')');
                return thisNfa;

            default:
                thisNfa = new NFA();
                String startName = getStateName();
                thisNfa.addStartState(startName);
                String finalName = getStateName();
                thisNfa.addFinalState(finalName);
                thisNfa.addTransition(startName, doNext(), finalName);
                return thisNfa;
        }
    }

    /**
     * concatenate two NFA
     * @param nfa1
     * @param nfa2
     * @return NFA
     */
	private NFA concatenateNFA(NFA nfa1, NFA nfa2) {


        for (State nfa : nfa1.getFinalStates()) {
            ((NFAState) nfa).setNonFinal();
            ((NFAState) nfa).addTransition('e', (NFAState) nfa2.getStartState());
        }

        nfa1.addNFAStates(nfa2.getStates());
		nfa1.addAbc(nfa2.getABC());
		return nfa1;
	}
	/**
	 * Union of two NFA
	 * @param nfa1
	 * @param nfa2
	 * @return NFA
	 */
    private NFA unionNFA(NFA nfa1, NFA nfa2) {
        NFAState nfa_state_1 = (NFAState) nfa1.getStartState();
        NFAState nfa_state_2 = (NFAState) nfa2.getStartState();

        nfa1.addNFAStates(nfa2.getStates());
        nfa1.addAbc(nfa2.getABC());

        String name_of_state = getStateName();
        nfa1.addStartState(name_of_state);

        nfa1.addTransition(name_of_state, 'e', nfa_state_1.getName());
        nfa1.addTransition(name_of_state, 'e', nfa_state_2.getName());
        return nfa1;
    }

    /**
     * capture repetition
     * @param nfa
     * @return NFA
     */
	private NFA capture_repetition(NFA nfa) {
		NFAState nfaState = (NFAState) nfa.getStartState();
		for(State nfa1 : nfa.getFinalStates()){
		    nfa.addTransition(nfa1.getName(), 'e', nfaState.getName());
        }

		String stateName = getStateName();
		nfa.addStartState(stateName);
		nfa.addFinalState(stateName);
		nfa.addTransition(stateName, 'e', nfaState.getName());
		return nfa;
	}

    /**
     * get state name
     * @return String
     */
	private String getStateName() {
		return "q" + (stateCount++);
	}

}