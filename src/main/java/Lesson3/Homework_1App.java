package Lesson3;

import java.util.ArrayList;
import java.util.List;

public class Homework_1App {
    public static void main(String[] args) {
        String[][] arr = new String[2][5];
        List list = new ArrayList<>();
        while (list.size() < 20){
            list.add("da");
            list.add("net");
            list.add("One");
            list.add("Two");
        }
        list.set(2, "unique");
        list.set(5, "unique2");
        list.set(9, "unique3");
        list.set(12, "unique4");
        System.out.println(list.toString());
        System.out.println();
            System.out.println(UniqueList.findUnique(list));
        System.out.println();
        UniqueList.repetitionCounter(list);
    }
}
