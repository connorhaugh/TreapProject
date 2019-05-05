
public class Treap {

	Node root;

	// inserts a node
	// edge case: what if *key* is already in the Treap.
	public void insert(int key, int prio) {
		Node toAdd = new Node(key, prio);

		// if the Treap is empty, set the node to add as the root
		if (root == null) {
			root = toAdd;
			return;
		}

		// BST insertion
		Node curNode = root;

		while (curNode != null) {
			if (key < curNode.key) {
				curNode = curNode.left;
			} else {
				curNode = curNode.right;
			}
		}
		curNode = toAdd;

		// Heap Situp
		Node parent = toAdd.parent;
		int parent_key = parent.key;
		int parent_prio = parent.prio;

		while (prio > parent_prio) {
			if (key < parent_key) { // the new node is a left child
				rightRotate(toAdd, parent);
			} else {
				leftRotate(parent, toAdd);
			}
			parent_key = parent.key;
			parent_prio = parent.prio;
		}
	}

	// returns the node given an input key
	public Node find(int key) {
		// normal binary search tree-find

		// if the Treap is empty
		if (root == null) {
			System.out.println("The treap is empty!");
			return null;
		}

		// if the Treap is not empty
		Node curNode = root;

		boolean notExist = false; // variable to track if the key can be found

		while (notExist != true) {
			if (curNode.key == key) {
				return curNode;
			} else {
				if (key < curNode.key) {
					if (curNode.left != null) {
						curNode = curNode.left;
					} else {
						notExist = true;
					}

				} else if (key > curNode.key) {
					if (curNode.right != null) {
						curNode = curNode.right;
					} else {
						notExist = true;
					}
				}
			}
		}

		System.out.println("The key is not in the treap!");
		return null;
	}

	// deletes the node given an input key
	public void delete(int key) {

		// if the Treap is empty
		if (root == null) {
			System.out.println("THe Treap is empty!");
			return;
		}

		// find the node
		Node toDelete = find(key);

		if (toDelete == null) {
			return;
		}

		// case 1: if the node is root, then just reset root
		if (toDelete.equals(root)) {
			root = null;
		}

		// this loop repeats until the node is deleted
		while (true) {
			// case 2: if the node is already a leaf
			if (toDelete.left == null && toDelete.right == null) {
				deleteLeaf(toDelete);
				return;
			}

			// case 3: if the node is in the middle part of the tree

			// case 3.1: the node only has left child
			if (toDelete.left != null && toDelete.right == null) {
				deleteLeft(toDelete);
				return;
			}
			// case 3.2: the node only has right child
			else if (toDelete.right != null && toDelete.left == null) {
				deleteRight(toDelete);
				return;
			}
			// case 3.3: the node has two children
			else {
				int left_prior = toDelete.left.prio;
				int right_prior = toDelete.right.prio;

				if (left_prior < right_prior) {
					leftRotate(toDelete, toDelete.right);
				} else {
					rightRotate(toDelete.left, toDelete);
				}
			}
		}
	}

	// deletes a node that is a leaf
	public void deleteLeaf(Node toDelete) {
		if (toDelete.key < toDelete.parent.key) {
			toDelete.parent.left = null;
		} else {
			toDelete.parent.right = null;
		}
	}
	
	// deletes a node that has only one child--right child
	private void deleteRight(Node toDelete) {
		Node node = toDelete.right;
		leftRotate(toDelete, node);
		node.left = null; // cut off the pointer to the node to be deleted
	}
	
	// deletes a node that has only one child--left child
	public void deleteLeft(Node toDelete) {
		Node node = toDelete.left;
		rightRotate(node, toDelete);
		node.right = null; // cut off the pointer to the node to be deleted
	}

	// rotates once so that
	// originally, x is the left child of y
	// after rotation, y is the right child of x
	public void rightRotate(Node x, Node y) {

		// first, check if subtrees exist

		// if x has a right subtree;
		// update right subtree of x to be left subtree of y
		if (x.right != null) {
			x.right.parent = y;
			y.left = x.right;
		}

		// update pointers related to grandparent
		if (y.equals(root)) { // case 1: y is root, i.e. grandparent does not exist
			x.parent = null;
			root = x;
		} else {
			// case 2: y != root, i.e. y has a parent and grandparent exists
			x.parent = y.parent;
			if (y.key < y.parent.key) {
				y.parent.left = x;
			} else {
				y.parent.right = x;
			}
		}

		// change pointers of x and y
		x.right = y;
		y.parent = x;
	}

	// rotates once so that
	// originally y is the right child of x
	// after rotation, x is the left child of y
	public void leftRotate(Node x, Node y) {

		// if y has a left subtree;
		// update left subtree of y to be right subtree of x
		if (y.left != null) {
			y.left.parent = x;
			x.right = y.left;
		}

		// update pointers related to grandparent
		if (x.equals(root)) { // case 1: x is root, i.e. grandparent does not exist
			y.parent = null;
			root = y;
		} else {
			// case 2: x != root, i.e. x has a parent and grandparent exists
			y.parent = x.parent;
			if (x.key < x.parent.key) {
				x.parent.left = y;
			} else {
				x.parent.right = y;
			}
		}

		// change pointers of x and y
		y.left = x;
		x.parent = y;
	}
}
