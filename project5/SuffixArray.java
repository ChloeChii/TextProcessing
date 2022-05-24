package project5;

import java.util.ArrayList;
import java.util.Arrays;
 
public class SuffixArray {
    public ArrayList<Integer> construct(String S) {
        //add the dollar sign in case remain if (S.length() % 3 is not zero)
        while(S.length() % 3 != 0) S = S + "$";

        //Initialize two String array with S.length()
        String arr1[] = new String[S.length()];
        String arr2[] = new String[S.length()];
        //initialize the answer space
        ArrayList<Integer> suffix_index = new ArrayList<Integer>();
        int suffix_arr  ay[] = new int[S.length()];
        //build the substring of S
        for (int i = 0; i < S.length(); i++) {
            arr1[i] = S.substring(i);
        }
        //maintain the original order in arr2. and sort array 1
        arr2 = arr1.clone();
        Arrays.sort(arr1);

        //find the index from arr2
        for (String i : arr1) {
            String piece = i;
            int index = new SuffixArray().index(arr2, piece);
            suffix_index.add(index);
        }
        
        return suffix_index;
    }

    //return the index of item from array arr[]
    int index(String arr[], String item){
        for (int i = 0; i < arr.length; i++) {
            if (item == arr[i])
                return i;
        }
        return -1;
    }
}
    
    