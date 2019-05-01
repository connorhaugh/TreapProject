public class Node{
  int priority;
  int key;
  Node lchild;
  Node rchild;
  Node parent;
  private Node(int p, int k){
    priority=p;
    key=k;
  }
  private Node(int p, int k, Node par){
    priority=p;
    key=k;
    parent=par;
  }
}
