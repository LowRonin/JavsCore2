package Lesson1.obj.TestSubj;

import Lesson1.inter.JumpableInter;

public class Robot extends Testee implements JumpableInter {

    private String imei;

    public Robot(int id, String imei) {
        super(id);
        this.imei = imei;
    }

    @Override
    public void jumpable() {
        System.out.println("Robot jumping");
    }
}
