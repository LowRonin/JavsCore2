package Lesson1.obj.obstacle;

import Lesson1.inter.Obstacle;

public class Treadmill implements Obstacle {
    private final int distance;
    public Treadmill() {
        distance = (int)(Math.random()*1000);
    }

    public int getDistance() {
        return distance;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public String toString() {
        return "Treadmill{" +
                "id=" +
                ", distance=" + distance +
                '}';
    }

}
