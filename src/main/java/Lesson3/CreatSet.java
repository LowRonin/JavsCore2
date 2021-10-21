package Lesson3;

import java.util.HashSet;
import java.util.Set;

public class CreatSet {
    public int setName;
    public Set set;


    public CreatSet(int setName) {
        this.setName = setName;
        set = new HashSet();

    }

    @Override
    public String toString() {
        return "CreatSet{" +
                "setName=" + setName +
                ", set=" + set +
                '}';
    }
}
