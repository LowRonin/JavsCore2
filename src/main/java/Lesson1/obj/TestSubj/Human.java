package Lesson1.obj.TestSubj;

import Lesson1.inter.JumpableInter;
import Lesson1.inter.RunnableInter;
import Lesson1.inter.Testee;

public class Human implements JumpableInter,RunnableInter, Testee {

    private String name;
    private int max_height = 150;
    private int max_distance = 3000;

    public Human(int id, String name) {
        this.name = name;
    }

    @Override
    public void jumpable() {
        System.out.println(name + " jumping");
    }

    @Override
    public void runnable() {
        System.out.println(name + " running");
    }

    @Override
    public String toString() {
        return "Human{" +
                "name='" + name + '\'' +
                ", max_height=" + max_height +
                ", max_distance=" + max_distance +
                '}';
    }

    public int getMax_height() {
        return max_height;
    }

    public int getMax_distance() {
        return max_distance;
    }
}
