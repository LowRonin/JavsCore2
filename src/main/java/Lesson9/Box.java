package Lesson9;

import Lesson9.Fruits.Apple;
import Lesson9.Fruits.Fruit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Box <T extends Fruit> {

    private List<T> list;

    public Box() {
        list = new ArrayList<T>();
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
        for (int i = 0; i<this.list.size(); i++){
            string = string + "[" + this.list.get(i).getClass().getName() + "] ";
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