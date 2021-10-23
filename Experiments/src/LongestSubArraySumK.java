public class LongestSubArraySumK {

    public static void main( String[] args ) {
        //int[] arr = {10, 5, 2, 7, 1, 9};
        //long k = 15;

        //int[] arr = {10, 5, 2, 7, -1, 2};
        //long k = 15;

        int[] arr = {-1, 2, 3};
        long k = 6;
        int output = longestSubArryaWithSumK(arr, k);
        System.out.println("longest sub array with sum " + k +" is " + output);
    }//end main

    public static int longestSubArryaWithSumK( int[] arr, long k ) {
        int i = 0;
        int maxlen = 0;
        long sum = 0;

        for (int j = 0; j < arr.length; j++) {
            sum += arr[j];
            if (sum == k) {
                int winsize = j - i + 1;
                if (winsize > maxlen) {
                    maxlen = winsize;
                }
            } else if (sum > k && i < j) {
                while (sum >= k && i < j) {
                    sum = sum - arr[i];
                    i++;
                }
                if (sum == k) {
                    int winsize = j - i + 1;
                    if (winsize > maxlen) {
                        maxlen = winsize;
                    }
                }
            } /*else if (sum < k) {
                while (sum <= k && i > 0) {
                    sum = sum + arr[i];
                    i--;
                }
                if (sum == k) {
                    int winsize = j - i + 1;
                    if (winsize > maxlen) {
                        maxlen = winsize;
                    }
                }
            }*/
        }//end of for loop
        return maxlen;
    }//end of function
}
