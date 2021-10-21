package Lesson1;

import Lesson1.inter.Obstacle;
import Lesson1.inter.Testee;
import Lesson1.obj.TestSubj.Cat;
import Lesson1.obj.TestSubj.Human;
import Lesson1.obj.TestSubj.Robot;
import Lesson1.obj.obstacle.Treadmill;
import Lesson1.obj.obstacle.Wall;

public class Lesson1app {

    public static void main(String[] args) {
        Testee[] testees = createTestee();
        Obstacle[] obstacles = createObstacles();
        isRunning(obstacles, testees);
    }

    private static Obstacle[] createObstacles() {
        Obstacle[] obstacles = new Obstacle[10];
        obstacles[0] = new Treadmill();
        obstacles[1] = new Wall();
        obstacles[2] = new Wall();
        obstacles[3] = new Treadmill();
        obstacles[4] = new Wall();
        obstacles[5] = new Treadmill();
        obstacles[6] = new Treadmill();
        obstacles[7] = new Wall();
        obstacles[8] = new Treadmill();
        obstacles[9] = new Wall();
        return obstacles;
    }

    public static Testee[] createTestee() {
        Testee[] testee = new Testee[3];
        testee[0] = new Cat(1, "Bars");
        testee[1] = new Human(2, "John connor");
        testee[2] = new Robot(3, "T1000");
        return testee;
    }

    public static void isRunning(Obstacle[] obstacles, Testee[] testee) {
        for (int i = 0; i < testee.length; i++) {
            for (int j = 0; j < obstacles.length; j++) {
                if (testee[i].getMax_distance() >= obstacles[j].getDistance() &&
                        obstacles[j].getDistance() != 0) {
                    testee[i].runnable();
                    obstacles[j].getDistance();
                } else if (testee[i].getMax_height() >= obstacles[j].getHeight() &&
                        obstacles[j].getHeight() != 0) {
                    testee[i].jumpable();
                } else break;


            }
        }
    }
}

