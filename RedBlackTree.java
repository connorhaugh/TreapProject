public class  RedBlackTree{
  public RBNode root;
  public RedBlackTree(){
    root=null;
  }
  public void insert(int n){
    if(root==null) { // if the first add
        RBNode toAdd =new RBNode(n);
        root = toAdd;
        root.priority = 1;
        return;

      }
    else{
      //BST insert
      RBNode toAdd = new RBNode(n);
      toAdd.priority=0;//set it to red!
      addRBNode(root, toAdd,null);
    }

    //ReColor
    insertBalance(toAdd);
  }
  public void addRBNode(RBNode subroot, RBNode toAdd, RBNode p){

      // if the subtree we just called is null, make it the value we add.

      if (subroot.lchild == null && toAdd.key < subroot.key){
          subroot.lchild = toAdd;
          toAdd.parent = subroot;
      }
      if(subroot.rchild == null && toAdd.key > subroot.key){
        subroot.rchild = toAdd;
        toAdd.parent = subroot;
      }
      // if the root of the subtree is greater than the add key, send it left
      if(toAdd.key<subroot.key) addRBNode(subroot.lchild,toAdd,subroot);
      // if the root of the subtree is less than the add key, send it right
      if(toAdd.key>subroot.key) addRBNode(subroot.rchild,toAdd,subroot);
      //if it is equal, get out it must already be contianed in the tree.
      return;
  }

/*  public void delete(int key){
    RBNode n = find(key);

    //case 0: it has no children
    if (n.lchild == null & n.rchild ==null){
      if(n.parent.lchild == n) n.parent.lchild=null;
      else n.parent.rchild =null;
      return ;
    }
    RBNode child;
    if(n.lchild!=null)child =n.lchild;
    else child = n.rchild;

    //case 1s

  } */
  private void insertBalance(RBNode n){
    RBNode uncle;
    RBNode p=n.parent;
    RBNode grandparent = null;
  //Case 1: N is the root
    if(n==root){
      System.out.println("found root");
      n.priority=1;
      return;
    }
  //Case 2: Parent is black
    if(p.priority==1){
      return;
    }
  // Case 3: uncle is red
    if(n.parent.parent != null) grandparent=n.parent.parent;

    if (grandparent.lchild == p){
      uncle=grandparent.rchild;
    }
    else uncle=grandparent.lchild;


    if(uncle != null & uncle.priority==0){
      p.priority=1;
      uncle.priority=1;
      grandparent.priority=0;
      insertBalance(grandparent);
      return;
    }
    //Case 4: all else
    if(n==p.rchild && p == grandparent.lchild){
      rotateLeft(p);
      n=n.rchild;
    }
    else if(n==n.parent.lchild && p == grandparent.rchild){
      rotateRight(p);
      n=n.rchild;
    }
    if(n == p.lchild){
      rotateRight(grandparent);
    }
    else rotateLeft(grandparent);
    p.priority=1;
    grandparent.priority=0;
  }


  private void rotateLeft(RBNode g){
    RBNode p = g.lchild;
    //fix grandparents
    p.parent = g.parent;
    g.lchild = p.rchild;
    p.rchild.parent = g;
    p.rchild = g;
    g.parent = p;
  }
  private void rotateRight(RBNode p){
    RBNode q = p.rchild;
    // fix grandparents
    q.parent=p.parent;
    p.rchild=q.lchild;
    q.lchild.parent = p;
    q.lchild=p;
    p.parent=q;
  }
  public static void printTree(RBNode n){
    System.out.println(n.key);
    if(n.rchild != null) printTree(n.rchild);
    if(n.rchild != null) printTree(n.lchild);
  }
  public static void main(String[] args) {
    RedBlackTree rb = new RedBlackTree();
    rb.insert(4);
    //printTree(rb.root);
    rb.insert(45);
    rb.insert(1);
    rb.insert(5);
    for (int i = 1 ; i <25; i++){
      rb.insert(i);
    }

  }
}
