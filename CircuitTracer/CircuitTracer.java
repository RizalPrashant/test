import java.awt.List;
import java.awt.Point;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Search for shortest paths between start and end points on a circuit board
 * as read from an input file using either a stack or queue as the underlying
 * search state storage structure and displaying output to the console or to
 * a GUI according to options specified via command-line arguments.
 * 
 * @author mvail
 */
public class CircuitTracer {
	private CircuitBoard board;
	private Storage<TraceState> stateStore;
	private ArrayList<TraceState> bestPaths;

	/** launch the program
	 * @param args three required arguments:
	 *  first arg: -s for stack or -q for queue
	 *  second arg: -c for console output or -g for GUI output
	 *  third arg: input file name 
	 */
	public static void main(String[] args) {
		if (args.length != 3) {
			printUsage();
			System.exit(1);
		}
		try {
			new CircuitTracer(args); //create this with args
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	/** Print instructions for running CircuitTracer from the command line. */
	private static void printUsage() {
		//TODO: print out clear usage instructions when there are problems with
		// any command line args
		System.out.println("Please enter -s for stack or -q for queue for first argument.");
		System.out.println("Please enter -c for console mode or -g for GUI mode for second argument.");
		System.out.println("Please input filename for third argument");
	}
	
	/** 
	 * Set up the CircuitBoard and all other components based on command
	 * line arguments.
	 * 
	 * @param args command line arguments passed through from main()
	 * @throws FileNotFoundException 
	 */
	private CircuitTracer(String[] args) throws FileNotFoundException {
		//TODO: parse command line args
		//parsing command line args
		String firstArg = args[0];   // for -s or -q
		String secondArg = args[1];		// for -c or -g
		String thirdArg = args[2];		// for filename
		//TODO: initialize the Storage to use either a stack or queue
		// initializing the storage to use stack or queue
		if(firstArg.compareTo("-s")!= 0 && firstArg.compareTo("-q")!=0)
		{
			CircuitTracer.printUsage();
		}
		if (firstArg.equals("-q")){
				stateStore = new Storage<TraceState>(Storage.DataStructure.queue);
			} else {
				stateStore = new Storage<TraceState>(Storage.DataStructure.stack);
			}
		
		//TODO: read in the CircuitBoard from the given file
		//reading in circuit board from the given file
		try{
		board = new CircuitBoard(thirdArg);
		}
		catch(FileNotFoundException E){
			throw new FileNotFoundException();
		}
//		System.out.println(board.toString());
		
		//TODO: run the search for best paths
		// running the search for best paths
		ArrayList<TraceState> bestPaths = new ArrayList<TraceState>();
		
		Point startPoint = board.getStartingPoint();
		Point endPoint = board.getEndingPoint();
		
		TraceState movePath;
		if (board.isOpen(startPoint.x-1, startPoint.y)){    // left trace state
			 movePath = new TraceState(board, startPoint.x-1, startPoint.y);
			 stateStore.store(movePath);
		}
		if (board.isOpen(startPoint.x+1, startPoint.y)){	// right trace state
			 movePath = new TraceState(board, startPoint.x+1, startPoint.y);
			 stateStore.store(movePath);


		}
		if (board.isOpen(startPoint.x, startPoint.y+1)){	// up trace state
			 movePath = new TraceState(board, startPoint.x, startPoint.y+1);
			 stateStore.store(movePath);
 

		}
		if (board.isOpen(startPoint.x, startPoint.y-1)){	// down trace state
			 movePath = new TraceState(board, startPoint.x, startPoint.y-1);
			 stateStore.store(movePath);


		}
		while (!stateStore.isEmpty()){
			TraceState retrievedObject = stateStore.retreive();
			
			if (retrievedObject.isComplete() == true){
				if (bestPaths.isEmpty()){
					bestPaths.add(retrievedObject);
				}
				else if (retrievedObject.pathLength() == bestPaths.get(0).pathLength()){
					bestPaths.add(retrievedObject);
				}
				else if (retrievedObject.pathLength() < bestPaths.get(0).pathLength()){
					bestPaths.clear();
					bestPaths.add(retrievedObject);
				}
			}
				else{
					if (retrievedObject.getBoard().isOpen(retrievedObject.getRow()-1, retrievedObject.getCol()) ){ 
//							!retrievedObject.getPath().contains(new Point(retrievedObject.getRow()-1,retrievedObject.getCol())))	{// left movement from tracestate
					TraceState nextPath = new TraceState(retrievedObject, retrievedObject.getRow()-1, retrievedObject.getCol());
					stateStore.store(nextPath);
				}
				if (retrievedObject.getBoard().isOpen(retrievedObject.getRow()+1, retrievedObject.getCol()) ){
//						!retrievedObject.getPath().contains(new Point(retrievedObject.getRow()+1,retrievedObject.getCol()))){		// right movement from tracestate
					TraceState nextPath = new TraceState(retrievedObject, retrievedObject.getRow()+1, retrievedObject.getCol());
					stateStore.store(nextPath);

				}
				if (retrievedObject.getBoard().isOpen(retrievedObject.getRow(), retrievedObject.getCol()-1) ){ 
//						!retrievedObject.getPath().contains(new Point(retrievedObject.getRow(),retrievedObject.getCol()-1))){		// up movement from tracestate
				TraceState nextPath = new TraceState(retrievedObject, retrievedObject.getRow(), retrievedObject.getCol()-1);
				stateStore.store(nextPath);

			}
				if (retrievedObject.getBoard().isOpen(retrievedObject.getRow(), retrievedObject.getCol()+1)){ 
					TraceState nextPath = new TraceState(retrievedObject, retrievedObject.getRow(), retrievedObject.getCol()+1 );
					stateStore.store(nextPath);

				}
				
			}
			
		}
		
	
		//TODO: output results to console or GUI, according to specified choice
			if (secondArg.equals("-c")){
				//System.out.println(board.toString());
				for (int i=0; i<bestPaths.size(); i++){
					System.out.println(bestPaths.get(i).toString());
				}
			} else{
				System.out.println("Not supported. Get over it!");
			}
	}
	
}// class CircuitTracer
