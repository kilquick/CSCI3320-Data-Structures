/**
 * Author >>        Tyler Zoucha
 * Program Title >> Maximum Contiguous Subsequence Sum
 * Class >>         CSCI3320-820, Fall 2020
 * Assignment >>    CSCI-3320-ZC-F20-PA1
 *
 * Objective >>
 *
 **/

import java.util.Random;
import java.util.Scanner;

public final class MaxSumTest {
    private static final int MAX_RANDOM_UPPER_BOUND = 9999;
    private static final int MIN_RANDOM_LOWER_BOUND = -9999;
    private static final int MAX_SIZE_TO_PRINT_ARRAY = 50;
    private static int seqStart = 0;
    private static int seqEnd = -1;

    public static void main(String[] args) {
        int numberFromUser = getNumberFromUser();
        int[] randomIntArray = generateIntegerArray(numberFromUser);

        if (numberFromUser < MAX_SIZE_TO_PRINT_ARRAY) {
            printArray(randomIntArray);
        }
        getResults(randomIntArray);
    }

    private static void getResults(int[] randomIntArray) {
        for( int alg = 2; alg <= 4; alg++ )
        {
            switch (alg) {
                case 2 -> {
                    long startTime2 = System.nanoTime();
                    maxSubSum2(randomIntArray);
                    long endTime2 = System.nanoTime();
                    double Time2 = (endTime2 - startTime2) / 1000;
                    System.out.println();
                    System.out.println("Algorithm 2:");
                    System.out.printf("MaxSum: %d, S_index: %d, E_index: %d\n",
                            maxSubSum2(randomIntArray), seqStart, seqEnd);
                    System.out.printf("Execution Time: %.0f milliseconds", Time2);
                    System.out.println();
                }
                case 3 -> {
                    long startTime3 = System.nanoTime();
                    maxSubSum3(randomIntArray);
                    long endTime3 = System.nanoTime();
                    double Time3 = (endTime3 - startTime3) / 1000;
                    System.out.println();
                    System.out.println("Algorithm 3:");
                    System.out.printf("MaxSum: %d, S_index: %d, E_index: %d\n", maxSubSum3(randomIntArray),
                            seqStart, seqEnd);
                    System.out.printf("Execution Time: %.0f milliseconds", Time3);
                    System.out.println();
                }
                case 4 -> {
                    long startTime4 = System.nanoTime();
                    maxSubSum4(randomIntArray);
                    long endTime4 = System.nanoTime();
                    double Time4 = (endTime4 - startTime4) / 1000;
                    System.out.println();
                    System.out.println("Algorithm 4:");
                    System.out.printf("MaxSum: %d, S_index: %d, E_index: %d\n",
                            maxSubSum4(randomIntArray), seqStart, seqEnd);
                    System.out.printf("Execution Time: %.0f milliseconds", Time4);
                    System.out.println();
                }
            }
        }
    }

    private static int maxSubSum2(int[] randomIntArray) {
        int maxSum = 0;

        for (int i = 0; i < randomIntArray.length; i++) {
            int thisSum = 0;
            for (int j = i; j < randomIntArray.length; j++) {
                thisSum += randomIntArray[j];
                if (thisSum > maxSum) {
                    maxSum = thisSum;
                    seqStart = i;
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