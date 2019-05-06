/**
 * @author Max Liu Date: 5/5/2019
 */

public class Treap {

	TreapNode root;

	/**
	 * inserts a new key into the node
	 *
	 * @param key
	 *            input key to be inserted into the treap
	 */
	public void insert(int key) {

		// get a randomly generated priority from a uniform distribution.
		int prio = (int) (2147483647 * Math.random());

		// handle input error--when the key already exists in the treap.
		if (root != null) {
			if (find(key) != null) {
				System.out.println("Key already exists!");
				return;
			}
		}

		TreapNode toAdd = new TreapNode(key, prio);
		root = insert(toAdd, root);
	}

	/**
	 * finds the node containing a given key-normal BST search
	 * 
	 * @param key
	 *            the key of which node we want to find
	 * @return returns the node containing the given key or returns null if not
	 *         found
	 */
	public TreapNode find(int key) {

		TreapNode curRoot = root; // start searching from the root

		while (true) {

			if (key == curRoot.key) {
				return curRoot;
			} else if (key < curRoot.key) {
				if (curRoot.left == null) {
					return null;
				} else {
					curRoot = curRoot.left; // search left subtree
				}
			} else {
				if (curRoot.right == null) {
					return null;
				} else {
					curRoot = curRoot.right; // search right subtree
				}
			}
		}
	}

	/**
	 * recursively insert a node into a sub-tree
	 * 
	 * @param toAdd
	 *            the node to insert
	 * @param curRoot
	 *            the node that is the root of the subtree
	 * @return the current root to add
	 */
	private TreapNode insert(TreapNode toAdd, TreapNode curRoot) {

		if (curRoot == null) {
			return toAdd;
		}

		if (toAdd.key < curRoot.key) { // insert to the left subtree
			curRoot.left = insert(toAdd, curRoot.left);

			if (curRoot.left.prio > curRoot.prio) {
				curRoot = rotateRight(curRoot);
			}
		} else { // insert to the right subtree
			curRoot.right = insert(toAdd, curRoot.right);

			if (curRoot.right.prio > curRoot.prio) {
				curRoot = rotateLeft(curRoot);
			}
		}

		return curRoot;
	}

	/**
	 * rotates a parent node with its right child
	 * 
	 * @param parent
	 *            the original parent node
	 * @return new parent node--the original parent node's right child
	 */
	private TreapNode rotateLeft(TreapNode parent) {

		// get the nodes to be updated
		TreapNode child = parent.right;
		TreapNode grandChild = child.left;

		// update the pointers
		child.left = parent;
		parent.right = grandChild;

		return child;
	}

	/**
	 * rotates a parent node with its left child
	 * 
	 * @param parent
	 *            the original parent node
	 * @return new parent node--the original parent node's left child
	 */
	private TreapNode rotateRight(TreapNode parent) {

		// get the nodes to be updated
		TreapNode child = parent.left;
		TreapNode grandChild = child.right;

		// update the pointers
		child.right = parent;
		parent.left = grandChild;

		return child;
	}

	/**
	 * deletes a node containing an input key
	 * 
	 * @param key
	 *            the input key to be deleted
	 */
	public void delete(int key) {

		if (root == null) {
			System.out.println("The treap is Empty!");
			return;
		}
		TreapNode toDelete = find(key);
		if (toDelete == null) {
			System.out.println("NOT in the treap! ");
		} else {
			root = delete(toDelete, root);
		}
	}

	/**
	 * deletes a node recursively in a subtree
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

			} else if (toDelete.key > curRoot.key) {

				curRoot.right = delete(toDelete, curRoot.right);

			} else {

				// locate the node to delete.
				if (curRoot.left == null && curRoot.right == null) { // it is a leaf
					curRoot = null;
				} else if (curRoot.right != null && curRoot.left == null) { // it is a single-right-child's parent
					curRoot = rotateLeft(curRoot);
				} else if (curRoot.left != null && curRoot.right == null) { // it is a single-left-child's parent
					curRoot = rotateRight(curRoot);
				} else { // it has two children, then compare children's priorities
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
}
