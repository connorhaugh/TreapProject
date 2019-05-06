import java.io.PrintWriter;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class Test{

  Treap treapee;
  RedBlack rbee;
  LinkedList <Integer> treapKeyTracker = new LinkedList <Integer> ();
  LinkedList <Integer> rbKeyTracker = new LinkedList <Integer> ();
  static UniformDis unifDis;

  public Test (int max) {
    this.treapee = new Treap();
    this.rbee = new RedBlack();
  }

  public static void main(String[] args){
    int max = 5;
    Test t = new Test(max);

    unifDis = new UniformDis (max);

    //try {
      t.treapee.insert(2);
      t.treapee.insert(3);
      t.treapee.insert(1);
      t.treapee.insert(4);
      t.treapee.insert(0);
      t.treapee.delete(3);
      t.treapee.delete(0);
      t.treapee.delete(1);
      t.treapee.delete(4);
      t.treapee.delete(2);
      t.treapee.insert(2);
      t.treapee.insert(3);
      t.treapee.insert(1);
      t.treapee.insert(4);
      t.treapee.insert(0);
      t.treapee.delete(3);
      t.treapee.delete(0);
      t.treapee.delete(1);
      t.treapee.delete(4);
      t.treapee.delete(2);
      //t.testTreap(max);
      //t.testRB(max);
    //}
    //catch (IOException io) {
      //System.out.println ("File not found.");
    //}
  }


  public void testTreap (int max) throws IOException {
    File fileTreapInsert = new File ("treapInsert.csv");
    File fileTreapDelete = new File ("treapDelete.csv");
    PrintWriter pTreapInsert = new PrintWriter (fileTreapInsert);
    PrintWriter pTreapDelete = new PrintWriter (fileTreapDelete);

    //Array of runtimes per size
    long[][] timeInsert = new long[100][max];
    long[][] timeDelete = new long[100][max];
    for (int i = 0; i < 100; i++) {
      System.out.println ("Insert iteration " + i);
      int[] unifElems= unifDis.generateUniform(max);
      //treapKeyTracker = new LinkedList <Integer> ();
      for (int j = 0; j < max; j++) {
        timeInsert[i][j] = insertTreapTime(unifElems[j]);
      }
      System.out.println ("Delete iteration " + i);
      for (int j = 0; j < max; j++) {
        timeDelete[i][j] = deleteTreapTime();
      }
    }

    //Average over 100 iterations at each n value
    for (int i = 0; i < max; i++) {
      long avgInsert = 0;
      long avgDelete = 0;
      for (int j = 0; j < 100; j++) {
        avgInsert += timeInsert[j][i];
        avgDelete += timeDelete [j][i];
      }
      avgInsert = avgInsert/(long)100;
      pTreapInsert.println (avgInsert);
      avgDelete = avgDelete/(long)100;
      pTreapDelete.println (avgDelete);
    }
    pTreapInsert.close();
    pTreapDelete.close();
  }

  public long insertTreapTime (int key) {
    long startTime, endTime, runTime;
    startTime = System.nanoTime();
    //Uniformly distributed
    //int key = (int)(n*Math.random());
    treapee.insert(key);
    endTime = System.nanoTime();
    runTime = endTime - startTime;
    //keyTracker not factored into runTime
    treapKeyTracker.add(key);
    System.out.print (" Treap Insert " + key);
    return runTime;
  }

  public long deleteTreapTime () {
    long startTime, endTime, runTime;
    //keyTracker not factored into runTime
    int randomKey = (int)(treapKeyTracker.size() * Math.random()-1);
    int blah = treapKeyTracker.remove(randomKey);
    startTime = System.nanoTime();
    //Uniformly distributed
    treapee.delete(randomKey);
    endTime = System.nanoTime();
    runTime = endTime - startTime;
    System.out.print (" Treap Delete " + blah);
    return runTime;
  }


  public void testRB (int max) throws IOException {
    File fileRBInsert = new File ("rbInsert.csv");
    File fileRBDelete = new File ("rbDelete.csv");

    PrintWriter pRBInsert = new PrintWriter (fileRBInsert);
    PrintWriter pRBDelete = new PrintWriter (fileRBDelete);

    //Array of runtimes per size
    long[][] timeInsert = new long[100][max];
    long[][] timeDelete = new long[100][max];
    for (int i = 0; i < 100; i++) {
      System.out.println ("RB iteration " + i);
      int[] unifElems= unifDis.generateUniform(max);
      for (int j = 0; j < max; j++) {
        timeInsert[i][j] = insertRBTime(unifElems[j]);
      }
      for (int j = 0; j < max; j++) {
        timeDelete[i][j] = deleteRBTime();
      }
    }

    //Average over 100 iterations at each n value
    for (int i = 0; i < max; i++) {
      long avgInsert = 0;
      long avgDelete = 0;
      for (int j = 0; j < 100; j++) {
        avgInsert += timeInsert[j][i];
        avgDelete += timeDelete [j][i];
      }
      avgInsert = avgInsert/(long)100;
      pRBInsert.println (avgInsert);
      avgDelete = avgDelete/(long)100;
      pRBDelete.println (avgDelete);
    }
    pRBInsert.close();
    pRBDelete.close();
  }

  public long insertRBTime (int key) {
    long startTime, endTime, runTime;
    startTime = System.nanoTime();
    //Uniformly distributed
    //int key = (int)(n*Math.random());
    rbee.insert(key);
    endTime = System.nanoTime();
    runTime = endTime - startTime;
    //keyTracker not factored into runTime
    rbKeyTracker.add(key);
    System.out.print (" RB insert " + key);
    return runTime;
  }

  public long deleteRBTime () {
    long startTime, endTime, runTime;
    //keyTracker not factored into runTime
    int randomKey = (int)(rbKeyTracker.size() * Math.random()-1);
    int hi = rbKeyTracker.remove(randomKey);
    startTime = System.nanoTime();
    //Uniformly distributed
    rbee.delete(randomKey);
    endTime = System.nanoTime();
    runTime = endTime - startTime;
    System.out.print (" RB delete " + hi);
    return runTime;
  }


}
