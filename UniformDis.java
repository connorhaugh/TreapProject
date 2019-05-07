import java.util.Random;

public class UniformDis {
  public int max;

  public UniformDis (int n) {
    this.max = n;
  }

  public int[] generateUniform (int max) {
    int[] elems = new int[max];
    for (int i = 0; i < max; i++) {
      elems[i] = i;
    }
    //Fisher-Yates shuffle
    for (int i = max - 1; i > 0; i--) {
      int j = (int)(i * Math.random());
      int temp = elems[i];
      elems[i] = elems[j];
      elems[j] = temp;
    }
    return elems;
  }

}
