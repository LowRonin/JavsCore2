package Lesson14;

import java.util.Arrays;

public class FirstApp {
/*    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 4, 4, 2, 3, 4, 1, 7};
        int[] arr2 = new int[]{1, 2, 7, 8, 2, 3, 5, 1, 7};
        int[] arr3 = new int[]{1, 4, 7, 8, 2, 3, 5, 1, 7};
        System.out.println(Arrays.toString(first(arr)));
        System.out.println(Arrays.toString(first(arr2)));
        System.out.println(Arrays.toString(first(arr3)));

    }*/
    /**
     * * Написать метод, которому в качестве аргумента передается не пустой одномерный целочисленный массив.
     * * Метод должен вернуть новый массив, который получен путем вытаскивания из исходного массива элементов, идущих после последней четверки.
     * * Входной массив должен содержать хотя бы одну четверку, иначе в методе необходимо выбросить RuntimeException.
     * * Написать набор тестов для этого метода (по 3-4 варианта входных данных).
     * * Вх: [ 1 2 4 4 2 3 4 1 7 ] -> вых: [ 1 7 ].
     */

    public int[] first(int[] inArr) {
        int i = inArr.length - 1;
        int outArrLength = 0;
        try {
            while (inArr[i] != 4) {
                i--;
                outArrLength++;
            }
        } catch (RuntimeException e) {
           e.printStackTrace();
            return null;
        }
        i++;
        int[] outArr = new int[outArrLength];
        for (int j = 0; j <= outArrLength-1; j++,i++){
            outArr[j] = inArr[i];
        }
        return outArr;
    }
}
