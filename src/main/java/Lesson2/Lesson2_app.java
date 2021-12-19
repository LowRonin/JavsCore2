package Lesson2;

import Lesson2.MyArrayDataException.MyArrayDataException;
import Lesson2.MyArraySizeException.MyArraySizeException;

public class Lesson2_app {

    public static void main(String[] args) throws MyArrayDataException {
        try {
            System.out.println(FindException.findException(sArray(4, 4)));
        } catch (MyArraySizeException e){
            System.err.println(e.getMessage());
        }

        try {
            System.out.println(FindException.findException(sArray(5, 4)));
        } catch (MyArraySizeException e){
            System.err.println(e.getMessage());
        }
    }

    private static String[][] sArray(int arrHeight, int arrWeight){
        String[][] sArray = new String[arrHeight][arrWeight];
        for (int i = 0; i < sArray.length; i++){
            for (int j = 0; j < sArray[i].length; j++){
                sArray[i][j] = Integer.toString((int)(Math.random()*100));
                System.out.print(sArray[i][j] + " ");
            }
            System.out.println();
        }

        return sArray;
    }

    }
