/*
  Author >>        Tyler Zoucha >> tzoucha@unomaha.edu
  Program Title >> Maximum Contiguous Subsequence Sum
  Class >>         CSCI3320-820, Fall 2020
  Assignment >>    CSCI-3320-ZC-F20-PA1

  Objective >>  This program used modified algorithms from the textbook; it will take an input size (N)
                   from the user to generate a random sequence of N integers ranging from -9999 to 9999.
                   If N is less than 50, the program prints out the randomly generated numbers. Then it
                   finds the maximum subsequence and the starting and the ending of the subsequence.
 */

import java.util.Random;
import java.util.Scanner;

/**
 * Initialize MaxSumTest Object
 * @author Tyler Zoucha >> tzoucha@unomaha.edu
 */
public final class MaxSumTest {
    private static final int MAX_RANDOM_UPPER_BOUND = 9999;                 // Set sequence bounds && init vars
    private static final int MIN_RANDOM_LOWER_BOUND = -9999;
    private static final int MAX_SIZE_TO_PRINT_ARRAY = 50;
    //private static int seqStart = 0;
    //private static int seqEnd = -1;

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
     *  >>                      Then prints each algorithm's result of Maximum Subsequence,
     *  >>                      Sequence Start, Sequence End, and each algorithm's execution time.
     * @param randomIntArray Passes the randomly generated Integer Array
     * No return values     <n/a></n/a>
     */
    private static void getResults(int[] randomIntArray) {
        for( int alg = 2; alg <= 4; alg++ )                                 // Algorithm iteration
        {
            // Process each algorithm separately
            switch (alg) {
                /*
                  Algorithm 2
                 */
                case 2:
                    long startTime2 = System.nanoTime();                    // Begin timestamp
                    SubsequenceAlgorithm.maxSubSum2(randomIntArray);                             // Execute Algorithm 2
                    long endTime2 = System.nanoTime();                      // End timestamp
                    double Time2 = (endTime2 - startTime2) / 1000;          // Execution time for Algorithm 2
                    System.out.println();
                    System.out.println("Algorithm 2:");
                    System.out.printf("MaxSum: %d, S_index: %d, E_index: %d\n",
                            SubsequenceAlgorithm.maxSubSum2(randomIntArray),
                                SubsequenceAlgorithm.seqStart, SubsequenceAlgorithm.seqEnd);  // Sum, Sequence Start, Sequence End
                    System.out.printf("Execution Time: %.0f milliseconds", Time2);
                    System.out.println();
                    break;
                 /*
                  Algorithm 3
                 */
                case 3:
                    long startTime3 = System.nanoTime();                    // Begin timestamp
                    SubsequenceAlgorithm.maxSubSum3(randomIntArray);                             // Execute Algorithm 3
                    long endTime3 = System.nanoTime();                      // End timestamp
                    double Time3 = (endTime3 - startTime3) / 1000;          // Execution time for Algorithm 3
                    System.out.println();
                    System.out.println("Algorithm 3:");
                    System.out.printf("MaxSum: %d, S_index: %d, E_index: %d\n",
                                        SubsequenceAlgorithm.maxSubSum3(randomIntArray),
                                        SubsequenceAlgorithm.seqStart, SubsequenceAlgorithm.seqEnd);                   // Sum, Sequence Start, Sequence End
                    System.out.printf("Execution Time: %.0f milliseconds", Time3);
                    System.out.println();
                    break;
                /*
                  Algorithm 4
                 */
                case 4:
                    long startTime4 = System.nanoTime();                    // Begin timestamp
                    SubsequenceAlgorithm.maxSubSum4(randomIntArray);                             // Execute Algorithm 4
                    long endTime4 = System.nanoTime();                      // End timestamp
                    double Time4 = (endTime4 - startTime4) / 1000;          // Execution time for Algorithm 4
                    System.out.println();
                    System.out.println("Algorithm 4:");
                    System.out.printf("MaxSum: %d, S_index: %d, E_index: %d\n",
                            SubsequenceAlgorithm.maxSubSum4(randomIntArray),
                                SubsequenceAlgorithm.seqStart, SubsequenceAlgorithm.seqEnd);  // Sum, Sequence Start, Sequence End
                    System.out.printf("Execution Time: %.0f milliseconds", Time4);
                    System.out.println();
                    break;
            }
        }
    }

 /*   /**
     * Method Name >>       maxSubSum2
     * Description >>       Modified code from the textbook to track the Maximum Subsequence Sum,
     *  >>                      and the starting and ending indices of Algorithm 2.
     * @param randomIntArray int array Passes the randomly generated Integer Array
     * @return int Algorithm 2's Maximum Subsequence Sum
     */
/*    private static int maxSubSum2(int[] randomIntArray) {
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
  /*  private static int maxSubSum3(int[] randomIntArray) {
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
 /*   private static int maxSumRec(int[] randomIntArray, int left, int right) {
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
 /*   private static int max3(int maxLeftSum, int maxRightSum, int i) {
        return Math.max(Math.max(maxLeftSum, maxRightSum), i);
    }

    /**
     * Method Name >>       maxSubSum4
     * Description >>       Modified code from the textbook to track the Maximum Subsequence Sum,
     *      *   >>                    and the starting and ending indices of Algorithm 4.
     * @param randomIntArray int array Randomly generated array
     * @return int Algorithm 4's Maximum Subsequence Sum
     */
 /*   private static int maxSubSum4(int[] randomIntArray) {
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

    /**
     * Method Name >>       getNumberFromUser
     * Description >>       This method prompts for user number to create size of problem
     * No input parameters  <n/a></n/a>
     * @return int User's number == size of problem
     */
    private static int getNumberFromUser() {
        System.out.print("Please enter the size of the problem (N): ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    /**
     * Method Name >>       generateIntegerArray
     * Description >>       Randomly generates integer array based on numberFromUser.
     *  >>                    The contents of the randomly generated away must be within the assignment bounds.
     *  >>                      Assignment explicitly bounds array content from -9999 to 9999
     * @param numberFromUser int numberFromUser == size of Problem == size of Array to generate
     * @return int array Randomly generated Integer Array
     */
    private static int[] generateIntegerArray(int numberFromUser) {
        int[] randomIntArray = new int[numberFromUser];
        Random random = new Random();
        for (int i= 0; i < numberFromUser; i++) {                       // Randomly generate array of user size
            int randomInt = random.nextInt((MAX_RANDOM_UPPER_BOUND - MIN_RANDOM_LOWER_BOUND) + 1)
                    + MIN_RANDOM_LOWER_BOUND;                           // Randomly generated contents within bounds
            randomIntArray[i] = randomInt;
        }
        return randomIntArray;
    }

    /**
     * Method Name >>       printArray
     * Description >>       This method builds a string from the integer array content then prints out the string
     * @param randomIntArray int array Randomly generated Integer Array
     * No return values     <n/a></n/a>
     */
    private static void printArray(int[] randomIntArray) {
        StringBuilder builtString = new StringBuilder();

        if (randomIntArray.length != 0) {
            for (int x : randomIntArray) {
                builtString.append(x).append(" ");                      // Add each index of array to built string
            }                                                           // Add necessary space after each number
            builtString.deleteCharAt(builtString.length() - 1);         // Remove the last unnecessary space
        }
        System.out.println(builtString.toString());                     // Print out built string
    }

}