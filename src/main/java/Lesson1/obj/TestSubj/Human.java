package Lesson1.obj.TestSubj;

import Lesson1.inter.JumpableInter;
import Lesson1.inter.RunnableInter;

public class Human extends Testee implements JumpableInter, RunnableInter {

    private String name;

    public Human(int id, String name) {
        super(id);
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
}
