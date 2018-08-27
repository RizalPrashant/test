
public class TreeObject implements Comparable<TreeObject> {

	private int freq;
	private long data;

	public TreeObject(long data, int freq) {
		this.data = data;
		this.freq = freq;
	}

	public TreeObject(long data) {
		this.data = data;
		this.freq = 1;
	}

	public int getFreq() {
		return freq;
	}

	public long getData() {
		return data;
	}

	public void increaseFreq() {
		this.freq++;
	}

	@Override
	public int compareTo(TreeObject o) {
		if (this.getData() < o.getData()) {
			return -1;
		} else if (this.getData() > o.getData()) {
			return 1;
		} else {
			return 0;
		}
	}

	public boolean equals(Object o) {
		return super.equals(o);
	}

	public String toString() {
		return super.toString();
	}

}
