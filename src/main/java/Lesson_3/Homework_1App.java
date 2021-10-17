package Lesson_3;

import java.util.*;

public class Homework_1App {
    public static void main(String[] args) {
        /**
         * 1 задание
         */
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
            System.out.println("Unique elements: " + UniqueList.findUnique(list));
        System.out.println();
        UniqueList.repetitionCounter(list);

        /**
         * 2 задание
         */
        System.out.println();
     PhoneBook pb = new PhoneBook();
        pb.add("Ronenko",
                "89639346725");
        pb.add("Ronenko",
                "89685452412");
        pb.add("Ronenko",
                "89685452412");
        pb.add("Trofimova",
                "89851342466");
        pb.get("Ronenko");
        pb.get("Trofimova");




    }
}
