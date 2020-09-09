public class SubsequenceAlgorithm {
    protected static int seqStart = 0;
    protected static int seqEnd = -1;

    /**
     * Method Name >>       maxSubSum2
     * Description >>       Modified code from the textbook to track the Maximum Subsequence Sum,
     *  >>                      and the starting and ending indices of Algorithm 2.
     * @param randomIntArray int array Passes the randomly generated Integer Array
     * @return int Algorithm 2's Maximum Subsequence Sum
     */
    public static int maxSubSum2(int[] randomIntArray) {
        int maxSum = 0;

        for (int i = 0; i < randomIntArray.length; i++) {                   // Check values one by one
            int thisSum = 0;
            for (int j = i; j < randomIntArray.length; j++) {
                thisSum += randomIntArray[j];
                if (thisSum > maxSum) {
                    maxSum = thisSum;
                    seqStart = i;                                           // Update sequence points
                    seqEnd = j;
                }
            }
        }
        return maxSum;
    }

    /**
     * Method Name >>       maxSubSum3
     * Description >>       Calculates the maximum sum by using Algorithm 3.
     * @param randomIntArray int array Randomly generated Integer Array
     * @return int Algorithm 3's Maximum Subsequence Sum after recursive action calling maxSumRec
     */
    public static int maxSubSum3(int[] randomIntArray) {
        if (randomIntArray.length > 0) {
            return maxSumRec(randomIntArray, 0, randomIntArray.length - 1);
        } else return 0;
    }

    /**
     * Method Name >>       maxSumRec
     * Description >>       Modified code from the textbook to track the Maximum Subsequence Sum,
     *   >>                    and the starting and ending indices of Algorithm 3.
     * @param randomIntArray int array Randomly generated int array
     * @param left int Sequence left index
     * @param right int Sequence right index
     * @return int Algorithm 3's Maximum Subsequence Sum from max3
     */
    public static int maxSumRec(int[] randomIntArray, int left, int right) {
        int maxLeftBorderSum = 0, maxRightBorderSum = 0, leftBorderSum = 0, rightBorderSum = 0;
        int center = (left + right) / 2;

        if (left == right) {                                                // Base case
            return Math.max(randomIntArray[left], 0);
        }

        int maxLeftSum = maxSumRec(randomIntArray, left, center);
        int maxRightSum = maxSumRec(randomIntArray, center + 1, right);

        for (int i = center; i >= left; i-- ) {
            leftBorderSum += randomIntArray[i];
            if( leftBorderSum > maxLeftBorderSum )
                maxLeftBorderSum = leftBorderSum;
        }
        for (int i = center + 1; i <= right; i++ ) {
            rightBorderSum += randomIntArray[i];
            if( rightBorderSum > maxRightBorderSum )
                maxRightBorderSum = rightBorderSum;
        }

        return max3(maxLeftSum, maxRightSum,maxLeftBorderSum + maxRightBorderSum );

    }

    /**
     * Method Name >>       max3
     * Description >>       Find Algorithm 3 max out of three ints
     * @param maxLeftSum int Left Side Max Sum
     * @param maxRightSum int Right Side Max Sum
     * @param i int Max Left Border Sum + Max Right Border Sum
     * @return int Algorithm 3's Maximum Subsequence Sum
     */
    public static int max3(int maxLeftSum, int maxRightSum, int i) {
        return Math.max(Math.max(maxLeftSum, maxRightSum), i);
    }

    /**
     * Method Name >>       maxSubSum4
     * Description >>       Modified code from the textbook to track the Maximum Subsequence Sum,
     *      *   >>                    and the starting and ending indices of Algorithm 4.
     * @param randomIntArray int array Randomly generated array
     * @return int Algorithm 4's Maximum Subsequence Sum
     */
    public static int maxSubSum4(int[] randomIntArray) {
        int maxSum = 0, thisSum = 0;

        for (int i = 0, j = 0; j < randomIntArray.length; j++) {
            thisSum += randomIntArray[j];
            if (thisSum > maxSum) {
                maxSum = thisSum;
                seqStart = i;
                seqEnd = j;
            } else if (thisSum < 0) {
                i = j + 1;
                thisSum = 0;
            }
        }
        return maxSum;
    }
}
