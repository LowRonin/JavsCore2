package Lesson2.MyArrayDataException;

public class MyArrayDataException extends Exception {

    public MyArrayDataException(int i, int j) {
        super("Неверный тип данных: Строка " + (i+1) + " Столбец " + (j+1));
    }

}
