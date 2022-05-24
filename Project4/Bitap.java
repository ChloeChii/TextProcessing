package Project4;

public class Bitap {
    public int match(String T, String P) {
        long state = 0;
        char[] p  = P.toCharArray();
        char[] t = T.toCharArray();
        long[] mask = new long[256];
        int m = P.length();
        if ( m == 0 || m > 63) return -1;
        for (int i = 0; i < m; i++) {
            mask[p[i]] = mask[p[i]] | (1 << i);
        }
        for (int i = 0; i < t.length; i++) {
            // Update state by shifting it and masking with the record from table 
            state = (state << 1) + 1;
            // System.out.println(t[i]);
            // System.out.println(mask[t[i]]);
            state = state & mask[t[i]];
            if ((state & (1 << (m-1))) != 0) {
                // It's a match!
                return i - m + 1;
            }
        }
        
        return -1;
    }
}

