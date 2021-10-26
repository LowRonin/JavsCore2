package Lesson_5;

public class PerfoTestApp {
    public static void main(String[] args) {
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

    public static void secondMethod(){
        int size = 160;
        float[] arr = new float[size];
        for (int i = 0; i < arr.length; i++){
            arr[i] = arr[i-1] + 1.0f;
        }

        long startTime = System.currentTimeMillis();
        float[] copyArrLeft = new float[size/2];
        float[] copyArrRight = new float[size/2];

        System.arraycopy(arr, 0, copyArrLeft, 0, size/2);
        System.arraycopy(arr, size/2, copyArrRight, 0, size/2);

        System.out.println(copyArrLeft.length + " / " + copyArrRight.length);

        for (int i = 0; i < arr.length; i++) {
            for (int leftArrCounter = 0; leftArrCounter < copyArrLeft.length; leftArrCounter++){
                copyArrLeft[i] = (float) (copyArrLeft[i] * Math.sin(0.2f + i / 5) *
                        Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }

        }

        System.out.println("Two thread time: " + (System.currentTimeMillis() - startTime) + " ms.");
    }
}

