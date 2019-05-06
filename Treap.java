
public class Treap {

	TreapNode root;

	// creates a treap node given a key
	// and inserts the node into the treap
	public void insert(int key) {

		// get a randomly generated priority from a uniform distribution.
		
		// comment out the line below for test purposes
		int prio = (int) (2147483647 * Math.random());

		TreapNode toAdd = new TreapNode(key, prio);
		root = insert(toAdd, root);
	}

	/**
	 * recursively insert into a sub-tree
	 * @param toAdd
	 *            the node to insert
	 * @param curRoot
	 *            the node that is the root of the subtree
	 */
	private TreapNode insert(TreapNode toAdd, TreapNode curRoot) {

		if (curRoot == null) {
			return toAdd;
		}

		if (toAdd.key < curRoot.key) {
			curRoot.left = insert(toAdd, curRoot.left);
			if (curRoot.left.prio > curRoot.prio) {
				curRoot = rotateRight(curRoot);
			}

		} else {

			curRoot.right = insert(toAdd, curRoot.right);

			if (curRoot.right.prio > curRoot.prio) {
				curRoot = rotateLeft(curRoot);
			}
		}

		return curRoot;
	}

	public void delete(int key) {

		TreapNode toDelete = find(key);
		if (toDelete != null) {
			root = delete(toDelete, root);
		}
	}

	public TreapNode find(int key) {
		TreapNode curRoot = root;

		boolean notFound = true;
		while (notFound) {

			if (key == curRoot.key) {
				notFound = false;
				return curRoot;
			}

			else if (key < curRoot.key) {
				if (curRoot.left == null) {
					System.out.println("NOT IN THE TREAP!");
					return null;
				} else {
					curRoot = curRoot.left;
				}
			}

			else {

				if (curRoot.right == null) {
					System.out.println("NOT IN THE TREAP!");
					return null;
				} else {
					curRoot = curRoot.right;

				}

			}
		}
		return null;
	}

	/**
	 * deletes a node from a subtree
	 * 
	 * @param toDelete
	 *            the node to deletes
	 * @param curRoot
	 *            the node that is the root of the subtree
	 * @return the new root of the subtree
	 */
	private TreapNode delete(TreapNode toDelete, TreapNode curRoot) {

		if (curRoot != null) {

			if (toDelete.key < curRoot.key) {
				curRoot.left = delete(toDelete, curRoot.left);
			}

			else if (toDelete.key > curRoot.key) {
				curRoot.right = delete(toDelete, curRoot.right);
			}

			else {
				// find it!
				
				if (curRoot.left == null && curRoot.right == null) {	// it is a leaf
					curRoot = null;
				} else if (curRoot.right != null) {	// it is a single-right-child's parent
					curRoot = rotateLeft(curRoot);
				} else if (curRoot.left != null) {	// it is a single-left-child's parent
					curRoot = rotateRight(curRoot);
				}
				else {	// it has two children, then compare children's priorities
					if (curRoot.left.prio < curRoot.right.prio) {	 
						curRoot = rotateLeft(curRoot);
					} else {	
						curRoot = rotateRight(curRoot);
					}
				}
				if (curRoot != null) { // continue down
					curRoot = delete(toDelete, curRoot);
				}
			}
		}
		return curRoot;
	}

	// rotates the parent and its right child
	// returns the right 
	private TreapNode rotateLeft(TreapNode parent) {
		TreapNode child = parent.right;
		TreapNode grandChild = child.left;

		// update the pointers
		child.left = parent;
		parent.right = grandChild;

		return child;
	}

	private TreapNode rotateRight(TreapNode parent) {
		TreapNode child = parent.left;
		TreapNode grandChild = child.right;

		// update the pointers
		child.right = parent;
		parent.left = grandChild;

		return child;
	}
}
