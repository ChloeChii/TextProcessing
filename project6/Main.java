package project6;

import java.util.ArrayList;

public class Main {
  public static void main(String[] args) {
    LCS lcs = new LCS();
    String res = lcs.find(args[0], args[1]);

    System.out.println(res);
    
  }
}

