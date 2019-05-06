import java.util.TreeMap;
public class  RedBlack{
  public TreeMap<Integer,Integer> RBTree;
  public RedBlack(){
    RBTree = new TreeMap<Integer,Integer>();
  }
  public void insert(int key){
    RBTree.put(key,0);
  }
  public void delete(int key){
    RBTree.remove(key);
  }
}
