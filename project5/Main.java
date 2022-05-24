package project5;

import java.util.ArrayList;

public class Main {
  public static void main(String[] args) {
    SuffixArray SA = new SuffixArray();
    ArrayList<Integer> res = SA.construct(args[0]);
    for (int i = 0; i < res.size(); i++) {
      System.out.println(res.get(i));
    }
  }
}

