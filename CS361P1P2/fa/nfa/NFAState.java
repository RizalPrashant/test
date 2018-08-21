package fa.nfa;
import fa.State;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * NFA state class
 * @author prashantrizal
 */
public class NFAState extends State{

    private HashMap<Character, Set<NFAState>> delta;
    private boolean isFinal;
   
    //constructor 1
    public NFAState(String name){
        initialize_default(name);
        isFinal = false;
    }

    // constructor 2
    public NFAState(String name, boolean isFinal){
        initialize_default(name);
        this.isFinal = isFinal;
    }
    
    /**
     * @param name
     */
    private void initialize_default(String name){
        this.name = name;
        delta = new HashMap<Character, Set<NFAState>>();
    }

    /**
     * @return boolean
     */
    public boolean isFinal(){
        return isFinal;
    }


    /**
     * adds transition
     * @param onSymb
     * @param to_state
     */
    public void addTransition(char onSymb, NFAState to_state){
        if(delta.containsKey(onSymb)){
            delta.get(onSymb).add(to_state);
        }else{
            Set<NFAState> temp = new LinkedHashSet<>();
            temp.add(to_state);
            delta.put(onSymb, temp);
        }
    }

    /**
     * @param onSymb
     * @return Set<NFAState>
     */
    public Set<NFAState> getTo(char onSymb){
        if(delta.containsKey(onSymb)){
            return delta.get(onSymb);
        }else{
            return new LinkedHashSet<>();
        }
    }

}