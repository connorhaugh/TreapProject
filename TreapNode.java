public class TreapNode {
	
	int key;
	int prio;
	
	TreapNode left;
	TreapNode right;
	
	public TreapNode(int key, int prio) {
		this.key = key;
		this.prio = prio;
	}
	
	// this method is for test purposes
	public String print() {
		return key + ", " + prio;
	}
}
