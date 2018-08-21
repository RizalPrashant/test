package fa.dfa;



import java.util.LinkedHashMap;



import fa.State;



public class DFAState extends State{



	protected String name;

	LinkedHashMap<Object, DFAState> map;

	protected boolean isFinal;

	protected boolean isStart;

	

	public DFAState(String name, boolean isFinal, boolean isStart){

		this.name = name;

		map = new LinkedHashMap<>();

		this.isFinal = isFinal;

		this.isStart = isStart;

	}

	

	public String getName(){

		return name;

	}

	
	public void setTransition(Object key, DFAState value){

		map.put(key, value);

	}

	public DFAState getTransitionKey(Object key){

		return map.get(key);

	}

	public void setFinal(){

		isFinal = true;

	}

	public void setStart(){

		isStart = true;

	}

	

	

	@Override

	public String toString(){

		return name;

	}

	

}