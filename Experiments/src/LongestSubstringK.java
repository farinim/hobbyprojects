/* Longest substring containing k distinct letters
    eg. aagaagacaabcg with k=2, output should be aagaaga (a,g two distinct letters)

    Solution presented below uses a sliding window.
 */

import java.util.HashMap;

public class LongestSubstringK {
    public static void main( String[] args ) {
        //String input = "aagagcdefad";
        //String input = null;
        //int k = 2;

        String input = "aagaaaggcdefad";
        int k = 1;
        String output = longestSubstr(input, k);
        System.out.println("longest substring in " + input + " is " + output);
    }

    public static String longestSubstr( String s, int k ) {
        if(s == null || "".equals(s) || k <=0) return s;
        int maxlen = k;
        int start = 0;
        int windowlen = 0;
        int winstart = 0;
        int kCount = k;
        HashMap<Character, Integer> letterLastIndex = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            if (kCount == 0) {
                if (letterLastIndex.containsKey(c)) {
                    if (letterLastIndex.get(c) == -1) { //new letter discovered
                        //find char to drop
                        Character charToDrop = s.charAt(winstart);
                        //find the last index of the char to drop
                        int charToDropIndex = letterLastIndex.get(charToDrop);
                        //compare current window len with maxlen
                        if (maxlen < windowlen) {
                            //update maxlen & start;
                            maxlen = windowlen;
                            start = winstart;
                        }
                        //currentwindlen = winstart + 1 ***Boundary check?
                        windowlen = i - charToDropIndex;
                        //winstart = chartodrop_lastindx+1 ***Boundary check?
                        winstart = charToDropIndex + 1;
                        //update the lastindex value of charToDrop to -1;
                        letterLastIndex.put(charToDrop, -1);
                        letterLastIndex.put(c, i);
                    } else {
                        //its a visited char for current window
                        //update this latest char lastindex value in the map
                        letterLastIndex.put(c, i);
                        //increment current windowlen
                        windowlen++;
                    }
                } else {
                    // this char doesn’t exist in the map and is a new char altogether for current window
                    Character charToDrop = s.charAt(winstart);
                    //find the last index of the char to drop
                    int charToDropIndex = letterLastIndex.get(charToDrop);
                    //compare current window len with maxlen
                    if (maxlen < windowlen) {
                        //update maxlen & start;
                        maxlen = windowlen;
                        start = winstart;
                    }
                    //currentwindlen = winstart + 1 ***Boundary check?
                    windowlen = i - charToDropIndex;
                    //winstart = chartodrop_lastindx+1 ***Boundary check?
                    winstart = charToDropIndex + 1;
                    //update the lastindex value of charToDrop to -1;
                    letterLastIndex.put(charToDrop, -1);
                    //add the char to map
                    letterLastIndex.put(c, i);
                }
            }
            else{
                //kCount is no zero yet, new char allowed
                //increment windowlen
                windowlen++;
                if(letterLastIndex.get(c) == null || letterLastIndex.get(c) == -1){
                    kCount--;
                }
                //add/update the lastindex of this current char in the map
                letterLastIndex.put(c, i);

            }
        }//end of for loop
        //compare maxlen and windowlen, update start accordingly
        if (maxlen < windowlen) {
            //update maxlen & start;
            maxlen = windowlen;
            start = winstart;
        }
        //compute longest substr using start & maxlen and return the value
        return s.substring(start, start+maxlen);
    }//end of function

}

