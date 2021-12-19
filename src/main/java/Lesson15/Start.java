package Lesson15;

import Lesson15.annotations.AfterSuite;
import Lesson15.annotations.BeforeSuite;
import Lesson15.annotations.MyTestAnnotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Start {

    public static void start(Class MyTest) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Lesson15.MyTest myTest = new MyTest();
        Method[] methods = MyTest.getDeclaredMethods();

        isRedundancySuits(methods);

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
            }).start();
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
}
