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
        int classNumber = sc.nextInt();
        int H = findHighest(numberArray);
        int L = findLowest(numberArray);
        int range = H - L;
        int cw = calculateCW(range, classNumber);

        int[][] classes = createClasses(L, H, cw);
        int[] frequencies = getFrequencies(numberArray, classes);
        int[] cumulativeFs = getCumulativeFs(frequencies);

        System.out.println("\nClasses\t\tf\t\tcf");
        for (int i = 0; i < classes.length; i++){
                System.out.printf("%d - %d\t\t%d\t\t%d\n", classes[i][0], classes[i][1], frequencies[i], cumulativeFs[i]);
        }

        System.out.println("Summation of f: " + summation(frequencies, frequencies.length));


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

    private static int[][] createClasses(int l, int h, int cw) {
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

        int[][] classes = new int[tempClassLimits.size()][];

        for (int j = 0; j < tempClassLimits.size(); j++){
            int[] classLimit = tempClassLimits.get(j);
            classes[j] = classLimit;
        }

        return classes;
    }

    private static int[] getFrequencies(int[] numberArray, int[][] classes){
        int[] frequencies = new int[classes.length];
        Arrays.fill(frequencies, 0);

        for (int value : numberArray) {
            for (int j = 0; j < classes.length; j++) {
                if (value >= classes[j][0] && value <= classes[j][1]) {
                    frequencies[j] += 1;
                }
            }
        }

        return frequencies;
    }

    private static int[] getCumulativeFs(int[] frequencies) {
        int[] cumulativeFs = new int[frequencies.length];
        int cumulativeF = 0;
        for (int i = 0; i < cumulativeFs.length; i++){
            cumulativeF += frequencies[i];
            cumulativeFs[i] = cumulativeF;
        }

        return cumulativeFs;
    }

    private static int summation(int[] values, int i){
        if(i <= 0) {
            return 0;
        }
            return (summation(values, i - 1) + values[i - 1]);
    }


}
