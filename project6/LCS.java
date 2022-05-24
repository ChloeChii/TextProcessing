package project6;

public class LCS {
  
  public String find(String A, String B) {
      // Implement the Hirschberg LCS algorithm 
      return algC(A.length(), B.length(), A, B);
  }
    public String algC(int m, int n, String a, String b) {
        StringBuilder sb = new StringBuilder();
        //Initialize 2xn 2D array
        int[][] K = new int[2][n];
        //initialize the value of row 1
        for(int j = 0 ; j < n ; j++) K[1][j] = 0;
        for(int i = 1 ; i < m ; i++) {
            for(int j = 0 ; j < n; j++) K[0][j] = K[1][j];
            for(int j = 1 ; j < n ; j++) {
                if(a.charAt(i) == b.charAt(j)) K[1][j] = K[0][j-1] + 1;
                else K[1][j] = Math.max(K[1][j-1], K[0][j]);
            }
        }
        for(int i = 0 ; i < n; i++){
            sb.append(K[1][i]);
        }
        return sb.toString();
    }
}