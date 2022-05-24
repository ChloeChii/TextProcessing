package Project4;

public class BMH {
  
  public int match(String T, String P) {
    /** Your code goes here */
    int i = P.length() -1, j = P.length() -1;
    int[] L = new int[256];
    do{
        if(T.charAt(i) == P.charAt(j)){
            if (j == 0){
              return i;
            }
            else {
              i -= 1;
              j -= 1;
            }
        }
        else{
          int l = L[T.charAt(i)];
          i = i + P.length() - Math.min(j, i + l);
          j = P.length() - 1;
        }
        
    } while (i <= T.length() - 1);
    
    return -1;
  }
 
}
