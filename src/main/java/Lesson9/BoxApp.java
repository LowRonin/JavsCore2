package Lesson9;

import Lesson9.Fruits.Apple;
import Lesson9.Fruits.Orange;

import java.util.List;

public class BoxApp {

    public static void main(String[] args) {
        Box box1 = new Box<>();

        Apple apple1 = new Apple();
        box1.add(new Apple());
        box1.add(new Apple());
        box1.add(apple1);

        System.out.println(box1.toString());
        System.out.println(box1.weight());

        Box box2 = new Box<>();

        box2.add(new Orange());
        //box2.add(new Orange());

        System.out.println(box2.toString());

        System.out.println(box2.weight());
        System.out.println(box1.compare(box2));

        Box<Apple> box3 = new Box<>();
        box1.transfer(box2);
        System.out.println(box2.toString());
        System.out.println(box1.toString());
        box1.transfer(box3);


        System.out.println(box3.toString());

    }



}
