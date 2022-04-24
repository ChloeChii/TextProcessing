package project2;

public class KMP {
    public int match(String T, String P) {
      /** Your code goes here */
        int i = 0, j = 0, tLen = T.length(), pLen = P.length();
        int F[] = new int[pLen];
        computeFailureFunction(P, F);
        while (i < tLen){
            if (T.charAt(i) == P.charAt(j)){
                if ( j == pLen -1){
                    return i - j;
                }
                else {
                  i += 1;
                  j += 1;
                }
            }
            else {
                if (j > 0){
                    j = F[j-1];
                }
                else {
                  i += 1;
                }
            }
        }
        return -1;
    }
    void computeFailureFunction(String P,int F[])
    {
        int j = 0;
        int i = 1;
        int pLen = P.length();
        F[0] = 0; 
        while (i < pLen) {
            if (P.charAt(i) == P.charAt(j)) {
                j += 1;
                F[i] = j;
                i += 1;
            }
            else if (j > 0){
               j = F[j - 1];
            }
            else {
                F[i] = 0;
                i += 1;
            }
        }
    }
}