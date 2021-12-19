package Lesson9;

import Lesson9.Fruits.Apple;
import Lesson9.Fruits.Fruit;

import java.util.ArrayList;
import java.util.List;

public class Box <T extends Fruit> {

    private final List<T> list;

    public Box() {
        list = new ArrayList<>();
    }

    public boolean add(T fruit) {
        if (!this.list.isEmpty()) {
            if (this.list.get(0).getClass() == fruit.getClass()) {
                this.list.add(fruit);
                return true;
            } else {
                System.out.println("В коробке другие фруткы");
                return false;
            }
        } else
            this.list.add(fruit);
            return true;
    }

    public String toString() {
        String string = "";
        for (T t : this.list) {
            string = string + "[" + t.getClass().getName() + "] ";
        }
        return string;
    }

    public double weight(){
        try {
            if (this.list.get(0) instanceof Apple) {
                return this.list.size() * Constants.APPLE_WEIGHT;
            } else return this.list.size() * Constants.ORANGE_WEIGHT;
        }catch (IndexOutOfBoundsException ioe){
            return 0.0;
        }
    }

    public boolean compare(Box comparedBox){
        return this.weight() == comparedBox.weight();
    }

    public  void  transfer(Box secondBox){
        while (this.list.size()>0){
            if (secondBox.add(this.list.get(0))) { // FIXME: 20.11.2021 добавить провреку на перемещаемость
                this.list.remove(0);
            } else break;
        }
    }
}