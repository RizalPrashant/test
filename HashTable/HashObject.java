public class HashObject<T> {
	private T object;
	private int dupCount;
	private int numProbes;

	public HashObject(T object) {
		setObject(object);
	}

	public T getObject() {
		// System.out.println(getKey());
		return object;
	}

	public void setObject(T o) {
		this.object = o;
	}

	public int getDupCount() {
		// System.out.println("reaches duplicate count");
		return dupCount;
	}

	public void setDupCount(int dupCount) {
		this.dupCount = dupCount;
	}

	public int getNumProbes() {
		// System.out.println("reaches number probes");
		return numProbes;
	}

	public void setNumProbes(int numProbes) {
		this.numProbes = numProbes;
	}

	public int getKey() {
		return Math.abs(object.hashCode());
	}

	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof HashObject)) {
			return false;
		}
		HashObject<T> c = (HashObject<T>) o;
		return getObject().equals(c.getObject());
	}

	public String toString() {
		return (getObject() + " " + dupCount + " " + numProbes + "\n");
	}

}