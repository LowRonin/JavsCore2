package Lesson13;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class Car implements Runnable {
    private static int CARS_COUNT;

    public static CountDownLatch countDownCar = new CountDownLatch(TestApp.CARS_COUNT / 2);
    public static Semaphore semaphore = new Semaphore(TestApp.CARS_COUNT / 2);

    static {
        CARS_COUNT = 0;
    }

    private Race race;
    private int speed;
    private String name;

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }

    @Override
    public void run() {

        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int) (Math.random() * 800));
            System.out.println(this.name + " готов");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Начало гонки
        TestApp.checkpointPreparation.countDown();
        try {
            TestApp.checkpointPreparation.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            if (race.getStages().get(i) instanceof Tunnel) {
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownCar.countDown();
                race.getStages().get(i).go(this);

            } else {
                race.getStages().get(i).go(this);
            }
        }
        if (TestApp.win == false) {
            TestApp.win = true;
            System.out.println(this.name + " WIN");
        }
        TestApp.checkpointPreparation.countDown();
    }
}
