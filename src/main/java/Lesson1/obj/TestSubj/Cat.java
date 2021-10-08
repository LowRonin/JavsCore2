package Lesson1.obj.TestSubj;

import Lesson1.inter.JumpableInter;
import Lesson1.inter.RunnableInter;

public class Cat extends Testee implements JumpableInter, RunnableInter {

    private String name;

    public Cat(int id, String name) {
        super(id);
        this.name = name;
    }

    @Override
    public void jumpable() {
        System.out.println("Cat " + name + " jumping");
    }
    @Override
    public void runnable() {
        System.out.println("Cat " + name + " running");
    }
}
