package Project4;

public class RK {
    public final static int d = 256;
    public final static int q = 101;

    public int match(String T, String P) {
        int M = P.length(), N = T.length();
        int i, j, f = 0, h = 1, H = 0; // hash value for pattern
        for (i = 0; i < M - 1; i++){
            h = (h * d) % q;
        }
        for (i = 0; i < M; i++){
            H = (d * H + P.charAt(i)) % q;
            f = (d * f + T.charAt(i)) % q;
        }
        // Slide the pattern over text one by one
        for (i = 0; i <= N - M; i++){
            // Check the hash values of current window of text and pattern. 
            //If the hash values match then check the characters one by one
            if ( H == f ){
                /* Check the characters one by one */
                for (j = 0; j < M; j++){
                    if (T.charAt(i + j) != P.charAt(j)) break;
                }
                if (j == M) return i;
            }
            // Remove leading digit, add trailing digit and calculate hash value for next window of text
            if ( i < N - M ){
                f = shiftHash(f, T,h, i ,M);
                // We might get negative value of t, converting it to positive
                if (f < 0)  f = (f + q);
            }
        }
        return -1;
    }
    int shiftHash(int f, String T, int h, int i, int M) {
        return (d * (f - T.charAt(i) * h) + T.charAt(i + M)) % q;
    }
}
     