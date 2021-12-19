package Lesson15;

import Lesson15.annotations.AfterSuite;
import Lesson15.annotations.BeforeSuite;
import Lesson15.annotations.MyTestAnnotation;

public class MyTest {

    public MyTest() {
    }

    @BeforeSuite
    void before() {
        System.out.println("Сделать первым");
    }

  //  @BeforeSuite
  //  void excess(){
  //  }

    @AfterSuite
    void after(){
        System.out.println("Сделать последним");
    }

    @MyTestAnnotation
    private void sequenceCheck(){
        System.out.println("Выполнилось вторым");
    }

    @MyTestAnnotation(priority = 9)
    private void myTest(){
        int x = 0;
        x = x + 2;
        System.out.println(x + "Получилось");

    }




    private void noAnnotations (){
        System.out.println("Нет аннотации");
    }

}
