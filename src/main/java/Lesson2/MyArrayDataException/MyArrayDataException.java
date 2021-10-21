package Lesson2.MyArrayDataException;

public class MyArrayDataException extends Exception {
    int string;
    public MyArrayDataException() {
    }

    public MyArrayDataException(int i, int j) {
        super("Неверный тип данных: Строка " + (i+1) + " Столбец " + (j+1));
    }

    public MyArrayDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyArrayDataException(Throwable cause) {
        super(cause);
    }

    public MyArrayDataException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
