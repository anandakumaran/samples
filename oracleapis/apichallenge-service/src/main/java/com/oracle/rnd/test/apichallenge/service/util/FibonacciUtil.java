/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oracle.rnd.test.apichallenge.service.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author anandakumaran
 */
public class FibonacciUtil {

    public static Integer[] getFibonacciSeries(int size, boolean sort) {

        Integer fibonacciArray[] = new Integer[size];

        int n = size, t1 = 0, t2 = 1;
        for (int i = 0; i < n; ++i) {
            fibonacciArray[i] = t1;
            int sum = t1 + t2;
            t1 = t2;
            t2 = sum;
        }

        System.out.println("Array:" + Arrays.toString(fibonacciArray));
        if (sort) {
            FibonacciUtil.twoWaySort(fibonacciArray, fibonacciArray.length, false);
        }
        return fibonacciArray;
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the fibonacci series size to be generated:");
        int size = sc.nextInt();

        System.out.println("Do you need to sort the series? Y for Yes and N for No");

        String sort = sc.next();
        Integer[] t = FibonacciUtil.getFibonacciSeries(size, "Y".equals(sort));

        System.out.println("Fibonacci Series is:" + Arrays.toString(t));
    }

    private static void twoWaySort(Integer arr[], int n, boolean isAscending) {
        // Current indexes from left and right
        int l = 0, r = n - 1;

        // Count of odd numbers
        int k = 0;

        while (l < r) {
            // Find first odd number from left side.
            while (arr[l] % 2 != 0) {
                l++;
                k++;
            }

            // Find first even number from right side.
            while (arr[r] % 2 == 0 && l < r) {
                r--;
            }

            // Swap odd number present on left and even
            // number right.
            if (l < r) {
                //  swap arr[l] arr[r]
                int temp = arr[l];
                arr[l] = arr[r];
                arr[r] = temp;
            }
        }

        if (isAscending) {

            // Sort odd number in ascending order
            Arrays.sort(arr, 0, k);

            // Sort even number in ascending order
            Arrays.sort(arr, k, n);
        } else {
            // Sort odd number in descending order
            Arrays.sort(arr, 0, k, Collections.reverseOrder());

            // Sort even number in descending order
            Arrays.sort(arr, k, n, Collections.reverseOrder());
        }
    }
}
