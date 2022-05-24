package Project4;

public class Brute {

  public int match(String T, String P) {
    // System.out.println(T + " : " + P);
    int tLen = T.length();
    int pLen = P.length();
    for(int i = 0 ; i < tLen - pLen + 1; i++){
        int j = 0;
        while(j < pLen && T.charAt(i+j) == P.charAt(j)){
            j = j + 1;
        }
        if (j == pLen) return i;
    }
    return -1;
  }
  
}
