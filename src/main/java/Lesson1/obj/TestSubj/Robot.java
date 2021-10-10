package Lesson1.obj.TestSubj;

import Lesson1.inter.RunnableInter;
import Lesson1.inter.Testee;

public class Robot implements RunnableInter, Testee {

    private String imei;
    private int max_height = 0;
    private int max_distance = 3000;

    public Robot(int id, String imei) {
        this.imei = imei;
    }


    @Override
    public String toString() {
        return "Robot{" +
                "imei='" + imei + '\'' +
                '}';
    }
    public int getMax_height() {
        return max_height;
    }

    public int getMax_distance() {
        return max_distance;
    }

    @Override
    public void runnable() {
        System.out.println("Robot" + imei + "running");
    }

    @Override
    public void jumpable() {
    }
}
