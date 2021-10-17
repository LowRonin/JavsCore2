package Lesson_3;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Написать простой класс Телефонный Справочник, который хранит в себе список фамилий и телефонных номеров.
 * В этот телефонный справочник с помощью метода add() можно добавлять записи,
 * а с помощью метода get() искать номер телефона по фамилии.
 * Следует учесть, что под одной фамилией может быть несколько телефонов (в случае однофамильцев),
 * тогда при запросе такой фамилии должны выводиться все телефоны.
 * __________________________________________________________________________________________________________________
 * Желательно не добавлять лишний функционал (дополнительные поля (имя, отчество, адрес),
 * взаимодействие с пользователем через консоль и т.д).
 * Консоль использовать только для вывода результатов проверки телефонного справочника.
 */
public class PhoneBook {
private int i = 0;
    private HashMap<String, HashSet<String>> phoneBook;

    public PhoneBook() {
        this.phoneBook = new HashMap<>();
    }

    public void add(String name, String phone){
        i++;
        CreatSet setPhone = new CreatSet(i);
        if (phoneBook.get(name) != null) {
            setPhone.set = phoneBook.get(name);
        }
        setPhone.set.add(phone);
        this.phoneBook.put(name, (HashSet<String>) setPhone.set);
    }

    public void get(String name){
        System.out.println(name + ": " + this.phoneBook.get(name));
    }


}
