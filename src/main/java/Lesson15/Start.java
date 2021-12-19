package Lesson15;

import Lesson15.annotations.AfterSuite;
import Lesson15.annotations.BeforeSuite;
import Lesson15.annotations.MyTestAnnotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;

public class Start {

    public static void start(Class MyTest) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Lesson15.MyTest myTest = new MyTest();
        Method[] methods = MyTest.getDeclaredMethods();

        System.out.println(Arrays.toString(methods));
        prioritySort(methods, 0, methods.length);

        isRedundancySuits(methods);

        for (Method method : methods) {
            method.setAccessible(true);
            if (method.getAnnotation(BeforeSuite.class) != null) {
                method.invoke(myTest);
            }
        }

        CountDownLatch countDownLatch = new CountDownLatch(methods.length);
        for (Method method : methods) {
            new Thread(() -> {
                method.setAccessible(true);
                if (method.getAnnotation(MyTestAnnotation.class) != null) {
                    try {
                        method.invoke(myTest);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
                countDownLatch.countDown();
            }).start();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (Method method : methods) {
            method.setAccessible(true);
            if (method.getAnnotation(AfterSuite.class) != null) {
                method.invoke(myTest);
            }
        }
    }

    private static void isRedundancySuits(Method[] methods) {
        int beforeCounter = 0;
        int afterCounter = 0;
        for (Method method : methods) {
            method.setAccessible(true);
            Annotation[] annotations = method.getAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation.annotationType() == BeforeSuite.class) {
                    beforeCounter++;
                }
                if (annotation.annotationType() == AfterSuite.class) {
                    afterCounter++;
                }
            }
            if (afterCounter > 1) {
                throw new RuntimeException("Exceeding the number of AfterSuit annotations");
            }
            if (beforeCounter > 1) {
                throw new RuntimeException("Exceeding the number of BeforeSuit annotations");
            }
        }
    }

    private static void prioritySort(Method[] methods, int leftBorder, int rightBorder) {
        int leftMarker = leftBorder;
        int rightMarker = rightBorder;
        int pivot = methods[(leftMarker + rightMarker) / 2].getDeclaredAnnotation(MyTestAnnotation.class).priority();
        do {
            // Двигаем левый маркер слева направо пока элемент меньше, чем pivot
            while (methods[leftMarker].getAnnotations().priority() < pivot) {
                leftMarker++;
            }
            // Двигаем правый маркер, пока элемент больше, чем pivot
            while (methods[rightMarker].getAnnotation(MyTestAnnotation.class).priority() > pivot) {
                rightMarker--;
            }
            // Проверим, не нужно обменять местами элементы, на которые указывают маркеры
            if (leftMarker <= rightMarker) {
                // Левый маркер будет меньше правого только если мы должны выполнить swap
                if (leftMarker < rightMarker) {
                    Method tmp = methods[leftMarker];
                    methods[leftMarker] = methods[rightMarker];
                    methods[rightMarker] = tmp;
                }
                // Сдвигаем маркеры, чтобы получить новые границы
                leftMarker++;
                rightMarker--;
            }
        } while (leftMarker <= rightMarker);
    }
}
