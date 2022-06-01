package project7;

import java.io.IOException;

public class Main {
  public static void main(String[] args) throws IOException {
    Minhash mh = new Minhash();
    double res = mh.jaccard(args[0], args[1]);
    System.out.println(res);
    
  }
}

