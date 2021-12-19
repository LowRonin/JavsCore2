package Lesson15;

import Lesson15.annotations.AfterSuite;
import Lesson15.annotations.BeforeSuite;
import Lesson15.annotations.MyTestAnnotation;

public class MyTest {

    public MyTest() {
    }

    @BeforeSuite
    void before() {
        System.out.println("Выполнить первым");
    }

  //  @BeforeSuite
  //  void excess(){
  //  }

    @AfterSuite
    void after(){
        System.out.println("Выполнить последним");
    }

    @MyTestAnnotation
    private void sequenceCheck(){
        System.out.println("Приоритет 5");
    }

    @MyTestAnnotation(priority = 9)
    private void myTest(){
        int x = 0;
        x = x + 2;
        System.out.println("приоритет 9");

    }

    @MyTestAnnotation
    private void test8(){

    }




    private void noAnnotations (){
        System.out.println("Нет аннотации");
    }

}
