package Lesson1;

import Lesson1.obj.TestSubj.Cat;
import Lesson1.obj.TestSubj.Human;
import Lesson1.obj.TestSubj.Robot;
import Lesson1.obj.TestSubj.Testee;
import Lesson1.obj.obstacle.Obstacle;
import Lesson1.obj.obstacle.Treadmill;
import Lesson1.obj.obstacle.Wall;

public class Lesson1_app {

    public static void main(String[] args) {
       /* Cat bars = new Cat(1, "Bars");
        Human jhonConor = new Human(2, "Jhon Conor");
        Robot t1000 = new Robot(3, "T1000");

      t1000.jumpable();
        jhonConor.runnable();
        jhonConor.jumpable();
        bars.jumpable();
        bars.runnable();*/
        Testee[] testee = new  Testee[3];
        testee[0] = new Cat(1, "Bars");
        testee[1] = new Human(2, "Jhon Conor");
        testee[2] = new Robot(3, "T1000");
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

    }

    public void isRunning(Testee[] testees, Obstacle[] obstacles){
        for (int i = 0; i < testees.length; i++){
            for (int j = 0; j < obstacles.length; j++){
              obstacles[i].
            }
        }
    }

    }
