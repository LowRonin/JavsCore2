package Lesson_5;

public class PerfoTestApp {
    public static void main(String[] args) throws InterruptedException {
        firstMethod();
        secondMethod();
    }

    public static void firstMethod() {
        int size = 10_000_000;
        float[] arr = new float[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1.0f;
        }
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.println("One thread time: " + (System.currentTimeMillis() - startTime) + " ms.");
    }

    public static void secondMethod() throws InterruptedException {
        int size = 10_000_000;
        float[] arr = new float[size];
        for (int i = 0; i < arr.length; i++){
            arr[i] = 1.0f;
        }

        long startTime = System.currentTimeMillis();
        float[] copyArrLeft = new float[size/2];
        float[] copyArrRight = new float[size/2];

        System.arraycopy(arr, 0, copyArrLeft, 0, size/2);
        System.arraycopy(arr, size/2, copyArrRight, 0, size/2);



        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < copyArrLeft.length; i++) {
                copyArrLeft[i] = (float)(copyArrLeft[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0, j = size/2; i < copyArrRight.length; j++, i++) {
                copyArrRight[i] = (float)(copyArrRight[i] * Math.sin(0.2f + j / 5) * Math.cos(0.2f + j / 5) * Math.cos(0.4f + j / 2));
            }
        });
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.arraycopy(copyArrLeft, 0, arr, 0, size/2);
        System.arraycopy(copyArrRight, 0, arr, size/2, size/2);
        System.out.println("Two thread time: " + (System.currentTimeMillis() - startTime) + " ms.");
    }
}

