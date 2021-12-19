package Lesson15;

import java.lang.reflect.InvocationTargetException;

public class TestApp {


    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Start.start(new MyTest().getClass());
    }
}
