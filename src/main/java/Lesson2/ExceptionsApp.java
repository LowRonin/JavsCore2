package Lesson2;

public class ExceptionsApp {
    public static void main(String[] args) {
        try {
            int a = 10;
            int b = 2;
            System.out.println("a = "+ a + " b = "+ b + "\na/b = " + div(a,b));
            b = 0;
            System.out.println("a = "+ a+ "b = "+ b+ "\na/b = " + div(a,b));
        } catch (ArithmeticException ae) {
            System.out.println("Поймай исключение");
            ae.printStackTrace(System.out);
        }

        try {
            divFirstAndSecond(args);
        } catch (ArithmeticException ae) {
            System.out.println("Попытались поделить на 0");
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Массив неподходящей длины");
        }catch (Exception ex){
            System.out.println("Что-то другое");
        } finally {
            System.out.println("Будет выполнено всегда");
        }

    }


    static int div(int a, int b){
        return div2(a,b);
    }

    static int div2(int a, int b){
        return a/b;
    }

    static int divFirstAndSecond(String [] array){
    return Integer.parseInt(array[0])/Integer.parseInt(array[1]);
    }
}
