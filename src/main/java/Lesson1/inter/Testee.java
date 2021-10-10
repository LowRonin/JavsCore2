package Lesson1.inter;

import Lesson1.inter.JumpableInter;
import Lesson1.inter.RunnableInter;

public interface Testee extends RunnableInter, JumpableInter {
   int getMax_height();
   int getMax_distance();
}
