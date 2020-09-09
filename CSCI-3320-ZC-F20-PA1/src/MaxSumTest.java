/**
 * Author >>        Tyler Zoucha >> tzoucha@unomaha.edu
 * Program Title >> Maximum Contiguous Subsequence Sum
 * Class >>         CSCI3320-820, Fall 2020
 * Assignment >>    CSCI-3320-ZC-F20-PA1
 *
 * Objective >>  This program used modified algorithms from the text book; it will take an input size (N)
 *                  from the user to generate a random sequence of N integers ranging from -9999 to 9999.
 *                  If N is less than 50, the program prints out the randomly generated numbers. Then it
 *                  finds the maximum subsequence and the starting and the ending of the subsequence.
 **/

import java.util.Random;
import java.util.Scanner;

/**
 * Initilize MaxSumTest Object
 * @author Tyler Zoucha >> tzoucha@unomaha.edu
 */
public final class MaxSumTest {
    private static final int MAX_RANDOM_UPPER_BOUND = 9999;                 // Set sequence bounds && init vars
    private static final int MIN_RANDOM_LOWER_BOUND = -9999;
    private static final int MAX_SIZE_TO_PRINT_ARRAY = 50;
    private static int seqStart = 0;
    private static int seqEnd = -1;

    /**
     * Main method to drive program and three different Maximum Subsequence Sum algorithms/
     * @param args String array
     */
    public static void main(String[] args) {
        int numberFromUser = getNumberFromUser();                           // Asks user for (N) array
        int[] randomIntArray = generateIntegerArray(numberFromUser);        // Randomly generates (N) array

        if (numberFromUser < MAX_SIZE_TO_PRINT_ARRAY) {                     // Prints array if N < 50
            printArray(randomIntArray);
        }
        getResults(randomIntArray);                                         // Processes algorithms for (N) array
    }

    /**
     * Method Name >>       getResults
     * Description >>       Processes the integer array through the subsequence sum algorithms one by one.
     *  >>                    Then prints each algorithm's result of Maximum Subsequence,
     *  >>                    Sequence Start, Sequence End, and each algorithm's execution time.
     * @param randomIntArray Passes the randomly generated Integer Array
     * Return Value >>      <n/a>
     */
    private static void getResults(int[] randomIntArray) {
        for( int alg = 2; alg <= 4; alg++ )                                 // Algorithm iteration
        {
            switch (alg) {                                                  // Process each algorithm separately
                /**
                 * Algorithm 2
                 */
                case 2 -> {
                    long startTime2 = System.nanoTime();                    // Begin timestamp
                    maxSubSum2(randomIntArray);                             // Execute Algorithm 2
                    long endTime2 = System.nanoTime();                      // End timestamp
                    double Time2 = (endTime2 - startTime2) / 1000;          // Execution time for Algorithm 2
                    System.out.println();
                    System.out.println("Algorithm 2:");
                    System.out.printf("MaxSum: %d, S_index: %d, E_index: %d\n",
                            maxSubSum2(randomIntArray), seqStart, seqEnd);  // Sum, Sequence Start, Sequence End
                    System.out.printf("Execution Time: %.0f milliseconds", Time2);
                    System.out.println();
                }
                /**
                 * Algorithm 3
                 */
                case 3 -> {
                    long startTime3 = System.nanoTime();                    // Begin timestamp
                    maxSubSum3(randomIntArray);                             // Execute Algorithm 3
                    long endTime3 = System.nanoTime();                      // End timestamp
                    double Time3 = (endTime3 - startTime3) / 1000;          // Execution time for Algorithm 3
                    System.out.println();
                    System.out.println("Algorithm 3:");
                    System.out.printf("MaxSum: %d, S_index: %d, E_index: %d\n", maxSubSum3(randomIntArray),
                            seqStart, seqEnd);                              // Sum, Sequence Start, Sequence End
                    System.out.printf("Execution Time: %.0f milliseconds", Time3);
                    System.out.println();
                }
                /**
                 * Algorithm 4
                 */
                case 4 -> {
                    long startTime4 = System.nanoTime();                    // Begin timestamp
                    maxSubSum4(randomIntArray);                             // Execute Algorithm 4
                    long endTime4 = System.nanoTime();                      // End timestamp
                    double Time4 = (endTime4 - startTime4) / 1000;          // Execution time for Algorithm 4
                    System.out.println();
                    System.out.println("Algorithm 4:");
                    System.out.printf("MaxSum: %d, S_index: %d, E_index: %d\n",
                            maxSubSum4(randomIntArray), seqStart, seqEnd);  // Sum, Sequence Start, Sequence End
                    System.out.printf("Execution Time: %.0f milliseconds", Time4);
                    System.out.println();
                }
            }
        }
    }

    /**
     * Method Name >>       maxSubSum2
     * Description >>       Modified code from the text book to track the Maximum Subsequence Sum,
     *  >>                    and the starting and ending indices of Algorithm 2.
     * @param randomIntArray Passes the randomly generated Integer Array.
     * @return Algorithm 2's Maximum Subsequence Sum
     */
    private static int maxSubSum2(int[] randomIntArray) {
        int maxSum = 0;

        // check values one by one
        for (int i = 0; i < randomIntArray.length; i++) {
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

    private static int maxSubSum3(int[] randomIntArray) {
        if (randomIntArray.length > 0) {
            return maxSumRec(randomIntArray, 0, randomIntArray.length - 1);
        } else return 0;
    }

    private static int maxSumRec(int[] randomIntArray, int left, int right) {
        int maxLeftBorderSum = 0, maxRightBorderSum = 0, leftBorderSum = 0, rightBorderSum = 0;
        int center = (left + right) / 2;

        if (left == right) {
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

    private static int max3(int maxLeftSum, int maxRightSum, int i) {
        return Math.max(Math.max(maxLeftSum, maxRightSum), i);
    }

    private static int maxSubSum4(int[] randomIntArray) {
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

    private static int getNumberFromUser() {
        System.out.print("Please enter the size of the problem (N): ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    private static int[] generateIntegerArray(int numberFromUser) {
        int[] randomIntArray = new int[numberFromUser];
        Random random = new Random();
        for (int i= 0; i < numberFromUser; i++) {
            int randomInt = random.nextInt((MAX_RANDOM_UPPER_BOUND - MIN_RANDOM_LOWER_BOUND) + 1)
                    + MIN_RANDOM_LOWER_BOUND;
            randomIntArray[i] = randomInt;
        }
        return randomIntArray;
    }

    private static void printArray(int[] randomIntArray) {
        StringBuilder builtString = new StringBuilder();

        if (randomIntArray.length != 0) {
            for (int x : randomIntArray) {
                builtString.append(x).append(" ");
            }
            builtString.deleteCharAt(builtString.length() - 1);
        }
        System.out.println(builtString.toString());
    }

}