public class TreapNode {

	TreapNode root;
	int key;
	int prio;

	TreapNode left;
	TreapNode right;

	public TreapNode(int key, int prio) {
		this.key = key;
		this.prio = prio;
	}
}
