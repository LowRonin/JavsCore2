package Lesson1.obj.TestSubj;

import Lesson1.inter.RunnableInter;
import Lesson1.inter.Testee;

public class Robot implements RunnableInter, Testee {

    private final String imei;

    public Robot(String imei) {
        this.imei = imei;
    }


    @Override
    public String toString() {
        return "Robot{" +
                "imei='" + imei + '\'' +
                '}';
    }
    public int getMax_height() {
        return 0;
    }

    public int getMax_distance() {
        return 3000;
    }

    @Override
    public void runnable() {
        System.out.println("Robot" + imei + "running");
    }

    @Override
    public void jumpable() {
    }
}
