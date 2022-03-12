package com.lol;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Input data (Separate with Spaces): ");
        String data = sc.nextLine();

        int[] numberArray = getNumbers(data);
        int n = numberArray.length;

        System.out.println("How many classes do you want to make?");
        int classes = sc.nextInt();
        int H = findHighest(numberArray);
        int L = findLowest(numberArray);
        int range = H - L;
        int cw = calculateCW(range, classes);

        int[][] classLimits = createClasses(L, H, classes, cw);

        System.out.println("\nClasses");
        for (int[] classLimit : classLimits){
            System.out.println(Arrays.toString(classLimit));
        }


    }

    private static int[] getNumbers(String data) {
        //Split data
        String[] dataArray = data.split("\\s");

        int[] numberArray = new int[dataArray.length];
        for (int i = 0; i < numberArray.length; i++) {
            numberArray[i] = Integer.parseInt(dataArray[i]);
        }

        return numberArray;
    }

    private static int findLowest(int[] numberArray){
        int lowest = 0;

        for (int i = 0; i < numberArray.length; i++){
            if (numberArray[i] < lowest || i == 0){
                lowest = numberArray[i];
            }
        }

        return lowest;
    }

    private static int findHighest(int[] numberArray){
        int highest = 0;

        for (int i = 0; i < numberArray.length; i++){
            if (numberArray[i] > highest || i == 0){
                highest = numberArray[i];
            }
        }

        return highest;
    }

    private static int calculateCW(int range, int classes) {
        return (int) Math.ceil(range/classes);
    }

    private static int[][] createClasses(int l, int h, int classes, int cw) {
        ArrayList<int[]> tempClassLimits = new ArrayList<int[]>();
        int lowerLimit = 0, upperLimit = 0;

        int i = 0;
        while (!(h >= lowerLimit && h <= upperLimit)) {
            if (i == 0) {
                lowerLimit = l;
                upperLimit = lowerLimit + cw - 1;
            } else {
                lowerLimit += cw;
                upperLimit = lowerLimit + cw - 1;
            }

            int[] limits = {lowerLimit, upperLimit};
            tempClassLimits.add(limits);

            i++;
        }

        int[][] classLimits = new int[tempClassLimits.size()][];

        for (int j = 0; j < tempClassLimits.size(); j++){
            int[] classLimit = tempClassLimits.get(j);
            classLimits[j] = classLimit;
        }

        return classLimits;
    }


}
