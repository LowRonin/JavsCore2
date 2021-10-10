package Lesson1.obj.TestSubj;

import Lesson1.inter.JumpableInter;
import Lesson1.inter.RunnableInter;
import Lesson1.inter.Testee;

public class Cat implements JumpableInter,RunnableInter, Testee {

    private String name;
    private int max_height = 150;
    private int max_distance = 0;

    public Cat(int id, String name) {
        this.name = name;
    }

    @Override
    public void jumpable() {
        System.out.println("Cat " + name + " jumping");
    }
    @Override
    public void runnable() {
        System.out.println("Cat " + name + " could run "+ max_distance +"," +
                " but he doesn't want to");
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                '}';
    }

    public int getMax_height() {
        return max_height;
    }

    public int getMax_distance() {
        return max_distance;
    }
}


