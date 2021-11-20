package Lesson9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 1 Написать метод, который меняет два элемента массива местами (массив может быть любого ссылочного типа);
 *
 * 2 Написать метод, который преобразует массив в ArrayList;
 *
 * 3 Задача:
 * a. Даны классы Fruit, Apple extends Fruit, Orange extends Fruit;
 *
 * b. Класс Box, в который можно складывать фрукты. Коробки условно сортируются по типу фрукта,
 * поэтому в одну коробку нельзя сложить и яблоки, и апельсины;
 *
 * c. Для хранения фруктов внутри коробки можно использовать ArrayList;
 *
 * d. Сделать метод getWeight(), который высчитывает вес коробки, зная вес одного фрукта и их количество: вес яблока – 1.0f,
 * апельсина – 1.5f (единицы измерения не важны);
 *
 * e. Внутри класса Box сделать метод compare(), который позволяет сравнить текущую коробку с той,
 * которую подадут в compare() в качестве параметра. true – если их массы равны, false в противоположном случае.
 * Можно сравнивать коробки с яблоками и апельсинами;
 *
 * f. Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую.
 * Помним про сортировку фруктов: нельзя яблоки высыпать в коробку с апельсинами. Соответственно,
 * в текущей коробке фруктов не остается, а в другую перекидываются объекты, которые были в первой;
 *
 * g. Не забываем про метод добавления фрукта в коробку.
 */

public class App {

    private static String[] arr = new String[5];

    public static void main(String[] args) {
        arr[0]="1";
        arr[1]="2";
        arr[2]="3";
        arr[3]="4";
        arr[4]="5";
        System.out.println(Arrays.toString(arr));
        Scanner scanner = new Scanner(System.in);
        while (true){
            String firstElement = null;
            String secondElement = null;
            int firstIntElement;
            int secondIntElement;

            firstElement = scanner.nextLine();
            if (firstElement.startsWith("/end")){
                break;
            }

            secondElement = scanner.nextLine();
            if (secondElement.startsWith("/end")){
                break;
            }
            try {
              firstIntElement = Integer.valueOf(firstElement)-1;
               secondIntElement = Integer.valueOf(secondElement)-1;
            } catch (Exception e){
                System.out.println("Неподходящие значения");
                break;
            }
            swap(firstIntElement, secondIntElement);
            System.out.println(Arrays.toString(arr));
        }
    }

    public static void swap(int firstElement, int secondElement){
      String  i = arr[firstElement];
      arr[firstElement] = arr[secondElement];
      arr[secondElement] = i;

    }

    public static List arraysToList(Object[] arr){

        List list = new ArrayList();
        return list;

    }
}
