public class TreapNode {
	
	int key;
	int prio;

	TreapNode left;
	TreapNode right;

	public TreapNode(int key, int prio) {
		this.key = key;
		this.prio = prio;
	}
	
	public String print() {
		return "node: (" + key + ", " + prio + ")";
	}
}
