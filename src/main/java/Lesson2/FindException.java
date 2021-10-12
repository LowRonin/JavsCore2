package Lesson2;
import Lesson2.MyArrayDataException.MyArrayDataException;
import Lesson2.MyArraySizeException.MyArraySizeException;

public class FindException {

    public static int findException(String[][] sArray) throws MyArrayDataException {
        if (sArray.length != 4 || sArray[0].length != 4) {
            throw new MyArraySizeException("Неподходящий размер массива");
        }
        int value = 0;
        for (int i = 0; i < sArray.length; i++) {
            for (int j = 0; j < sArray[i].length; j++) {
                /**
                 * Тут 2 задание
                 */
                try {
                    value += Integer.parseInt(sArray[i][j]);
                } catch (NumberFormatException re) {
                    throw new MyArrayDataException(i, j);
                }
            }
        }
        return value;
    }
}
