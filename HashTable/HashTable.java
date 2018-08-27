import java.math.BigInteger;

public class HashTable<T> {
	int tableSize;
	private HashObject<T>[] hashTable;
	public int n;
	int dupL;
	int dupD;
	int probeCountL;
	int probeCountD;
	

	public HashTable() {
		tableSize = getSize();
		hashTable = new HashObject[tableSize];

		for (int i = 0; i < tableSize; i++) {
			hashTable[i] = null;
		}
	}
	

	public boolean insertLinear(HashObject<T> o) {
		for (int i = 0; i < tableSize; i++) {
			int hashFunction = (((o.getKey() % tableSize) + i) % tableSize);
			if (hashTable[hashFunction] == null) {
				hashTable[hashFunction] = o;
				o.setNumProbes(o.getNumProbes() + 1);
				probeCountL = probeCountL + o.getNumProbes();
				return true;
			} else if (hashTable[hashFunction].equals(o)) {
				hashTable[hashFunction].setDupCount(hashTable[hashFunction].getDupCount() + 1);
				dupL = dupL + 1;
				return false;
			} else {
				o.setNumProbes(o.getNumProbes() + 1);
			}
		}
		return false;
	}


	public boolean insertDouble(HashObject<T> o) {
		for (int i = 0; i < tableSize; i++) {
			int hashFunc = (((o.getKey() % (tableSize)) + (i * (1 + o.getKey() % (tableSize - 2)))) % (tableSize));
			if (hashTable[hashFunc] == null) {
				hashTable[hashFunc] = o;
				o.setNumProbes(o.getNumProbes() + 1);
				probeCountD = probeCountD + o.getNumProbes();
				return true;
			} else if (hashTable[hashFunc].equals(o)) {
				dupD++;
				hashTable[hashFunc].setDupCount(hashTable[hashFunc].getDupCount() + 1);
				return false;
			} else {
				o.setNumProbes(o.getNumProbes() + 1);
			}
		}
		return false;
	}
	public double numProbesL() { // so that integer can be returned as double which helps to format it later.
		return probeCountL;
	}
	public double numProbesD() { // so that integer can be returned as double which helps to format it later.
		return probeCountD;
	}
	public int getSize() {
		BigInteger min = new BigInteger("95500");
		int thisInt;
		do {
			thisInt = min.nextProbablePrime().intValue();
			
			min = BigInteger.valueOf(thisInt);
		} while (min.nextProbablePrime().intValue() != min.nextProbablePrime().nextProbablePrime().intValue() - 2);
		thisInt = min.nextProbablePrime().intValue();
		min = BigInteger.valueOf(thisInt);
		
		return thisInt + 2;


	}

	
	public void clearArray() {
		for (int i = 0; i <= tableSize ; i++) {
			hashTable[i] = null;
		}
		dupL = 0;
		dupD = 0;
		probeCountD = 0;
		probeCountL = 0;
	}

	public HashObject getHashObject(int arrayPosition) {
		return hashTable[arrayPosition];
	}

	public String toString() {
		int i = 0;
		while (i < tableSize) {
			i++;
			if (hashTable[i] != null) {
				return ("Table[" + i + "]: " + hashTable[i].toString());
			}

		}
		return null;
	}

}
	
	
	
