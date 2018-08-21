package fa.nfa;

import fa.FAInterface;
import fa.State;
import fa.dfa.DFA;
import java.util.*;

/**
 * NFA class
 * @author prashantrizal
 *
 */
public class NFA implements FAInterface, NFAInterface {

    private Set<NFAState> set_of_states; 
    private NFAState start_state; 
    private Set<Character> setAbc; 

  /* constructor */
    public NFA(){
    	 setAbc = new LinkedHashSet<Character>();
        set_of_states = new LinkedHashSet<NFAState>();
    }

    @Override
    public void addStartState(String name) {
        NFAState state_to_add = getState(name);
        if(state_to_add == null){
            state_to_add = new NFAState(name);
            addState(state_to_add);
        }
        start_state = state_to_add;
    }

    @Override
    public void addState(String name) {
        NFAState state_to_add = new NFAState(name);
        addState(state_to_add);
    }

    /**
     * adds NFA state instead of string
     * @param state_to_add
     */
    private void addState(NFAState state_to_add){
        set_of_states.add(state_to_add);
    }


    /**
     * returns the target state
     * @param name
     * @return NFAState
     */
    private NFAState getState(String name){
        NFAState target_state = null;
        for(NFAState state : set_of_states){
            if(state.getName().equals(name)){
                target_state = state;
                break;
            }
        }
        return target_state;
    }

    @Override
    public void addFinalState(String name) {
        NFAState state_to_add = new NFAState(name, true);
        addState(state_to_add);
    }

    @Override
    public void addTransition(String fromState, char onSymb, String toState) {
        (getState(fromState)).addTransition(onSymb, getState(toState));
        if(!setAbc.contains(onSymb) && onSymb != 'e'){
            setAbc.add(onSymb);
        }
    }

    @Override
    public Set<NFAState> getStates() {
        return set_of_states;
    }

    @Override
    public Set<? extends State> getFinalStates() {
        Set<NFAState> temp_set = new LinkedHashSet<>();
        for(NFAState state : set_of_states) {
            if (state.isFinal()) {
                temp_set.add(state);
            }
        }
        return temp_set;
    }

    /**
     * checks to see if a final state exists
     * @param states
     * @return boolean
     */
    private boolean containsFinals(Set<NFAState> states){
        boolean temp = false;
        for(NFAState state: states){
            if(state.isFinal()){
                temp = true;
                break;
            }
        }

        return temp;
    }

    @Override
    public State getStartState() {
        return start_state;
    }

    @Override
    public Set<Character> getABC() {
        return setAbc;
    }

    @Override
    public DFA getDFA() {
        DFA dfa = new DFA();
        Map<Set<NFAState>, String> states_already_visited = new LinkedHashMap<>();

        Set<NFAState> states = eClosure(start_state);
        LinkedList<Set<NFAState>> queue = new LinkedList<>();
        states_already_visited.put(states, states.toString());
        queue.add(states);
        dfa.addStartState(states_already_visited.get(states));

        while(queue.size() != 0){
            states = queue.poll();

            for (char m : setAbc) {

                Set<NFAState> next_state = new LinkedHashSet<>();
                for (NFAState s : states) {
                    next_state.addAll(s.getTo(m));
                }
                Set<NFAState> next_state_one = new LinkedHashSet<>();
                for(NFAState state : next_state){
                    next_state_one.addAll(eClosure(state));
                }
                if(!states_already_visited.containsKey(next_state_one)){
                    states_already_visited.put(next_state_one, next_state_one.toString());
                    queue.add(next_state_one);

                    if(containsFinals(next_state_one)){
                        dfa.addFinalState(states_already_visited.get(next_state_one));
                    }else{
                        dfa.addState(states_already_visited.get(next_state_one));
                    }
                }

                dfa.addTransition(states_already_visited.get(states), m, states_already_visited.get(next_state_one));
            }
        }
        return dfa;
    }


    @Override
    public Set<NFAState> getToState(NFAState from, char onSymb) {
        return from.getTo(onSymb);
    }

    @Override
    public Set<NFAState> eClosure(NFAState s) {
        Set<NFAState> l = new LinkedHashSet<>();
    	return depth_first_search(l, s);
    }

    /**
     * depth_first_search
     * @param l
     * @param s
     * @return Set<NFAState>
     */

    private Set<NFAState> depth_first_search(Set<NFAState> l, NFAState s){
        Set<NFAState> temp = new LinkedHashSet<>();
        Set<NFAState> states_visited = l;
        temp.add(s);
        if(!s.getTo('e').isEmpty() && !states_visited.contains(s)){
            states_visited.add(s);
            for(NFAState nfa : s.getTo('e')){
                temp.addAll(depth_first_search(states_visited, nfa));
            }
        }
        return temp;
    }


}