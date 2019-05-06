**# TreapProject**
A Project for COSC-223 at Amherst College by Maz Liu, Nick Carolan, Connor Haugh, and Ashira Mawji

**## How to Run**
To run this code, simply compile and run the file Test.java using JDK. It should create two .csv files with the results.

**## Classes**
  **Treap**
  Fields: *Root*

  Insert(Integer Key)
  >Inserts the given key value into the treap with a randomized priority.

  (private) Insert(TreapNode toAdd, TreapNode curRoot)
  >Recursive Helper method to complete the process of insertion. returns a TreapNode to aid in this process.

  Delete(Integer Key)
  >Removes the node of given key.

  (private) Delete(TreapNode toDelete, TreapNode curRoot)
  >the recursive helper method to complete the process of deletion. Returns a TeapNode.

  Find(Integer Key)
  >Returns the TreapNode containing the given key

  rotateLeft(TreapNode N)
  >Rotates the give node to the left. Used in the insert and deletion process to maintain heap and BST properties.

  rotateRight(TreapNode N)
  >Rotates the give node to the right. Used in the insert and deletion process to maintain heap and BST properties.

  **Test**
  Fields: *Root*

  Main
  >

  **RedBlack**
  Fields: *RBTree*
  Insert (Integer key)
  >Adds a node to the RB tree with the given key.

  Delete (Integer key)
  >Removes the node in the RB tree with the given key.

  **TreapNode**
  Fields: *root, key, priority, left, right*
  Print()
  >returns a string description of the node it is called upon.
