
public class TreapNode {

	// five fields of a single node
	int key;
	int prio;
	
	TreapNode left;
	TreapNode right;
	TreapNode parent;
	
	// constructor, initializes a node
	public TreapNode(int key, int prio) {
		this.key = key;
		this.prio = prio; 
	}
}
