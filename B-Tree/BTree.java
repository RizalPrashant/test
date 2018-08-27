import java.util.LinkedList;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class BTree {
	private int degree;
	private int rootOffSet;
	private BTreeNode root;
	private int insertLoc;
	private File file;
	private int treeSize;
	private BTreeCache cache;
	private RandomAccessFile randFile;

	// constructor when creating file
	public BTree(int degree, String fileName, boolean cacheStorage, int cacheSize) {
		rootOffSet = 12;
		treeSize = 32 * degree - 3;
		insertLoc = treeSize + rootOffSet;
		this.degree = degree;
		if (cacheStorage == true) {
			cache = new BTreeCache(cacheSize);
		}
		BTreeNode curr = new BTreeNode();
		root = curr;
		root.setOffset(rootOffSet);
		curr.setIsLeaf(true);
		curr.setT(0);
		try {
			file = new File(fileName);
			file.delete();
			file.createNewFile();
			randFile = new RandomAccessFile(file, "rw");
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Input output exception");
		}
		writeMetaData();
	}

	// constructor when passing existing File
	public BTree(int degree, File file, boolean cacheStorage, int cacheSize) {
		try {
			randFile = new RandomAccessFile(file, "r");
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
		readMetaData();
		root = readNode(rootOffSet);
	}

	public void insert(long val) {
		BTreeNode rootNode = root;
		int thisValue = rootNode.getT();
		if (thisValue == (2 * degree - 1)) {
			TreeObject object = new TreeObject(val);
			while (thisValue > 0 && object.compareTo(rootNode.getObj(thisValue - 1)) < 0) {
				thisValue--;
			}
			if (thisValue > 0 && object.compareTo(rootNode.getObj(thisValue - 1)) == 0) {
				rootNode.getObj(thisValue - 1).increaseFreq();
			} else {
				BTreeNode currNode = new BTreeNode();
				currNode.setOffset(rootNode.getOffset());
				root = currNode;
				rootNode.setOffset(insertLoc);
				rootNode.setParent(currNode.getParent());
				currNode.setIsLeaf(false);
				currNode.addChild(rootNode.getOffset());
				splitChild(currNode, 0, rootNode);
				insertWhenNotFull(currNode, val);
			}
		} else {
			insertWhenNotFull(rootNode, val);
		}
	}

	public void insertWhenNotFull(BTreeNode passNode, long value) {
		int getT = passNode.getT();
		TreeObject object = new TreeObject(value);
		if (passNode.isLeaf()) {
			if (passNode.getT() != 0) {
				while (getT > 0 && object.compareTo(passNode.getObj(getT - 1)) < 0) {
					getT--;
				}
			}
			if (getT > 0 && object.compareTo(passNode.getObj(getT - 1)) == 0) {
				passNode.getObj(getT - 1).increaseFreq();
			} else {
				passNode.addObj(object, getT);
				passNode.setT(passNode.getT() + 1);
			}
			writeNode(passNode, passNode.getOffset());
		} else {
			while (getT > 0 && object.compareTo(passNode.getObj(getT - 1)) < 0) {
				getT--;
			}
			if(getT > 0 && object.compareTo(passNode.getObj(getT - 1)) == 0) {
				passNode.getObj(getT - 1).increaseFreq();
				writeNode(passNode, passNode.getOffset());
				return;
			}
			int offSetVal = passNode.getChild(getT);
			BTreeNode obtNode = readNode(offSetVal);
			if(obtNode.getT() == 2* degree - 1) {
				int i = obtNode.getT();
				while(i > 0 && object.compareTo(obtNode.getObj(i-1)) < 0) {
					i--;
				}
				if(i > 0 && object.compareTo(obtNode.getObj(i-1)) == 0) {
					obtNode.getObj(i - 1).increaseFreq();
					writeNode(obtNode, obtNode.getOffset());
					return;
				}
				else {
					splitChild(passNode, getT, obtNode);
					if(object.compareTo(passNode.getObj(getT)) > 0) {
						getT++;
					}
				}
			}
			offSetVal = passNode.getChild(getT);
			BTreeNode childNode = readNode(offSetVal);
			insertWhenNotFull(childNode, value);
		}
	}
	public void splitChild(BTreeNode xNode, int i, BTreeNode yNode)	{
		BTreeNode zNode = new BTreeNode();
		zNode.setIsLeaf(yNode.isLeaf());
        zNode.setParent(yNode.getParent());
        for (int j = 0; j < degree - 1; j++){
            zNode.addObj(yNode.removeObj(degree));
            zNode.setT(zNode.getT()+1);
            yNode.setT(yNode.getT()-1);

        }
        if (!yNode.isLeaf()){
            for (int j = 0; j < degree; j++){
                zNode.addChild(yNode.removeChild(degree));
            }
        }
        xNode.addObj(yNode.removeObj(degree - 1), i);
        xNode.setT(xNode.getT()+1);
        yNode.setT(yNode.getT()-1);
        if (xNode == root && xNode.getT() == 1){
            writeNode(yNode,insertLoc);
            insertLoc += treeSize;
            zNode.setOffset(insertLoc);
            xNode.addChild(zNode.getOffset(),i+1);
            writeNode(zNode,insertLoc);
            writeNode(xNode,rootOffSet);
            insertLoc += treeSize;
        }
        else{
            writeNode(yNode,yNode.getOffset());
            zNode.setOffset(insertLoc);
            writeNode(zNode,insertLoc);
            xNode.addChild(zNode.getOffset(),i+1);
            writeNode(xNode,xNode.getOffset());
            insertLoc += treeSize;
        }
	}
	public TreeObject search(BTreeNode passNode, long val){
        int init = 0;    
        TreeObject obj = new TreeObject(val);
        while (init < passNode.getT() && (obj.compareTo(passNode.getObj(init)) > 0)){
            init++;
        }
        if (init < passNode.getT() && obj.compareTo(passNode.getObj(init)) == 0){
            return passNode.getObj(init);
        }
        if (passNode.isLeaf()){
            return null;
        }
        else {
            int offset = passNode.getChild(init);
            BTreeNode currNode = readNode(offset);
            return search(currNode,val);
        }
    }
	public void orderedPrint(BTreeNode passNode){
        System.out.println(passNode);
        if (passNode.isLeaf() == true){
            for (int i = 0; i < passNode.getT(); i++){
                System.out.println(passNode.getObj(i));
            }
            return;
        }
        for (int i = 0; i < passNode.getT() + 1; ++i){
            int offset = passNode.getChild(i);
            BTreeNode y = readNode(offset);
            orderedPrint(y);
            if (i < passNode.getT())
                System.out.println(passNode.getObj(i));
        }
    }
	public void orderedPrintWriter(BTreeNode passNode,PrintWriter printWriter, int seqLength) throws IOException {
        GeneBankConvert geneBankConverter = new GeneBankConvert();
        for (int i = 0; i < passNode.getT(); i++){
            printWriter.print(passNode.getObj(i).getFreq()+ " ");
            printWriter.println(geneBankConverter.convertLongToString(passNode.getObj(i).getData(),seqLength));
        }
        if (!passNode.isLeaf()){
	        for (int i = 0; i < passNode.getT() + 1; ++i){
	            int offSetVal = passNode.getChild(i);
	            BTreeNode currNode = readNode(offSetVal);
	            orderedPrintWriter(currNode,printWriter,seqLength);
	            if (i < passNode.getT()) {
	                printWriter.print(passNode.getObj(i).getFreq() + " ");
                    printWriter.println(geneBankConverter.convertLongToString(passNode.getObj(i).getData(),seqLength));
	            }
	        }
        }
    }
	public BTreeNode getRoot() {
		return root;
	}
	
	public void writeNode(BTreeNode passNode, int offSetVal){
        if (cache != null) {
        	BTreeNode cacheNode = cache.add(passNode, offSetVal);
        	if (cacheNode != null) writeNodeToFile(cacheNode,cacheNode.getOffset());
        } else {
        	writeNodeToFile(passNode, offSetVal);
        }
    }
	
	private void writeNodeToFile(BTreeNode passNode, int offSetVal) {
        int i = 0;
        try {
            writeNodeMetadata(passNode,passNode.getOffset());
            randFile.writeInt(passNode.getParent());
            for (i = 0; i < 2 * degree - 1; i++){
                if (i < passNode.getT() + 1 && !passNode.isLeaf()){
                    randFile.writeInt(passNode.getChild(i));
                }
                else if (i >= passNode.getT() + 1 || passNode.isLeaf()){
                    randFile.writeInt(0);
                }
                if (i < passNode.getT()){
                    long data = passNode.getObj(i).getData();
                    randFile.writeLong(data);
                    int frequency = passNode.getObj(i).getFreq();
                    randFile.writeInt(frequency);
                }
                else if (i >= passNode.getT() || passNode.isLeaf()){
                    randFile.writeLong(0);
                }
            }
            if (i == passNode.getT() && !passNode.isLeaf()){
                randFile.writeInt(passNode.getChild(i));
            }
        }
        catch (IOException e){
        System.out.println("Input output Exception");
        }
    }
	
public BTreeNode readNode(int offSetVal){
    	
    	BTreeNode newNode = null;
        if (cache != null) {
        	newNode = cache.readNode(offSetVal);
        	}
        if (newNode != null) {
        	return newNode;
        }
        newNode = new BTreeNode();
        TreeObject obj = null;
        newNode.setOffset(offSetVal);
        int i = 0;
        try {
            randFile.seek(offSetVal);
            boolean isLeaf = randFile.readBoolean();
            newNode.setIsLeaf(isLeaf);
            int readVal = randFile.readInt();
            newNode.setT(readVal);
            int parent = randFile.readInt();
            newNode.setParent(parent);
            for (i = 0; i < 2 * degree - 1; i++){
                if (i < newNode.getT() + 1 && !newNode.isLeaf()){
                    int child = randFile.readInt();
                    newNode.addChild(child);
                }
                else if (i >= newNode.getT() + 1 || newNode.isLeaf()){
                    randFile.seek(randFile.getFilePointer() + 4);
                }
                if (i < newNode.getT()){
                    long value = randFile.readLong();
                    int frequency = randFile.readInt();
                    obj = new TreeObject(value,frequency);
                    newNode.addObj(obj);
                }
            }
            if (i == newNode.getT() && !newNode.isLeaf()){
                int child = randFile.readInt();
                newNode.addChild(child);
            }
        }
        catch (IOException e){
            System.out.println("Input output exception");
        }
        
        return newNode;
    }
	
	public void writeMetadata(){
        try {
            randFile.seek(0);
            randFile.writeInt(degree);
            randFile.writeInt(32*degree-3);
            randFile.writeInt(12);
        }
        catch (IOException e){
            System.out.println("Input output exception");
        }
    }
	public void readMetadata(){
        try {
            randFile.seek(0);
            degree = randFile.readInt();
            treeSize = randFile.readInt();
            rootOffSet = randFile.readInt();
        }
        catch (IOException e){
            System.out.println("Input output exception");
        }
    }
	public void writeNodeMetadata(BTreeNode passNode, int offSetVal){
        try {
            randFile.seek(offSetVal);
            randFile.writeBoolean(passNode.isLeaf());
            randFile.writeInt(passNode.getT());
        }
        catch (IOException e){
            System.out.println("Input Output Exception");
        }
    }
	public void transferCache() {
    	if (cache != null) {
    		for (BTreeNode thisNode : cache) writeNodeToFile(thisNode,thisNode.getOffset());
    	}
    }
	
	
		public class BTreeNode {
			private int t;
			private LinkedList<TreeObject> objList;
			private LinkedList<Integer> childList;
			private boolean isLeaf;
			private int parent;
			private int offset;

			public BTreeNode() {
				parent = -1;
				objList = new LinkedList<TreeObject>();
				childList = new LinkedList<Integer>();
				t = 0;
			}

			public int getT() {
				return t;
			}

			public void setT(int t) {
				this.t = t;
			}

			public int getParent() {
				return parent;
			}

			public void setParent(int parent) {
				this.parent = parent;
			}

			public int getOffset() {
				return offset;
			}

			public void setOffset(int offset) {
				this.offset = offset;
			}

			public void addChild(int child) {
				childList.add(child);
			}

			public void addChild(Integer child, int value) {
				childList.add(value, child);
			}

			public int removeChild(int child) {
				int remVal = childList.remove(child);
				return remVal;
			}

			public int getChild(int child) {
				int getVal = childList.get(child).intValue();
				return getVal;
			}

			public void addObj(TreeObject obj) {
				objList.add(obj);
			}

			public void addObj(TreeObject obj, int value) {
				objList.add(value, obj);
			}

			public TreeObject removeObj(int value) {
				TreeObject remObj = objList.remove(value);
				return remObj;

			}

			public TreeObject getObj(int value) {
				TreeObject getObj = objList.get(value);
				return getObj;
			}

			public void setIsLeaf(boolean isLeaf) {
				this.isLeaf = isLeaf;
			}

			public boolean isLeaf() {
				return isLeaf;
			}

			public LinkedList<TreeObject> getObjList() {
				return objList;
			}

			public LinkedList<Integer> getChildList() {
				return childList;
			}

			public String toString() {
				String s = "";
				s += "objList: ";
				for (int i = 0; i < objList.size(); i++) {
					s += objList.get(i) + " ";
				}
				s += "\n";
				s += "childList: ";
				for (int i = 0; i < childList.size(); i++) {
					s += childList.get(i) + " ";
				}
				return s;
			}
		}
	
}
