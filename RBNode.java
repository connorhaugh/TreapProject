public class RBNode{
  int priority; // red = 0 black = 1
  int key;
  RBNode lchild;
  RBNode rchild;
  RBNode parent;
  public RBNode(int k){
    priority=0; // init as red
    key=k;
  }
}
