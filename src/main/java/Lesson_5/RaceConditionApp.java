package Lesson_5;

import java.security.PublicKey;

public class RaceConditionApp {
    public static void main(String[] args){

    }

    public static class Counter{
        private int value;

        public int getValue(){
            return value;
        }

        public synchronized void increment(){
            value++;
        }
    }

    public static void firstMethod() throws InterruptedException {


        int SIZE = 10_000_000;
        float[] arr = new float[SIZE];
        long startTime = System.currentTimeMillis();
        Counter counter = new Counter();
        Thread t1 = new Thread(() -> {
            for (int i =0; i<1000000; i++){
                counter.increment();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i =0; i<1000000; i++){
                counter.increment();
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println(counter.getValue());
        System.out.println(System.currentTimeMillis() - startTime);
    }

    public static void secondMethod(){

    }

}
