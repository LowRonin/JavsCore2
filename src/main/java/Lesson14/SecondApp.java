package Lesson14;

import java.util.Arrays;

public class SecondApp {

/*    public static void main(String[] args) {
        System.out.println(second(new Integer[]{1, 4, 64, 654, 26, 6, 8}));
        System.out.println(second(new Integer[]{1, 2, 64, 654, 26, 6, 8}));
        System.out.println(second(new Integer[]{4, 4, 64, 654, 26, 6, 8}));
        System.out.println(second(new Integer[]{64, 654, 26, 6, 8}));
    }*/

    /**
     * Написать метод, который проверяет состав массива из чисел 1 и 4. Если в нем нет хоть одной четверки или единицы,
     * то метод вернет false; Написать набор тестов для этого метода (по 3-4 варианта входных данных).
     * * [ 1 1 1 4 4 1 4 4 ] -> true
     * * [ 1 1 1 1 1 1 ] -> false
     * * [ 4 4 4 4 ] -> false
     * * [ 1 4 4 1 1 4 3 ] -> false
     */

    public boolean second(Integer[] inArr) {
        return Arrays.asList(inArr).contains(1) && Arrays.asList(inArr).contains(4);
    }
}
