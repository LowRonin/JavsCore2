package Lesson1.obj.obstacle;

import Lesson1.inter.Obstacle;

public class Wall implements Obstacle {
    private final int height;
    public Wall() {
        height = (int)(Math.random()*100);
    }

    @Override
    public String toString() {
        return "Wall{" +
                "id=" +
                ", height=" + height +
                '}';
    }

    public int getHeight() {
        return height;
    }

    @Override
    public int getDistance() {

        return 0;
    }


}
