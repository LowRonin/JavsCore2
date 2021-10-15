package Lesson3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UniqueList {
    /**
     * Создать массив с набором слов (10-20 слов, должны встречаться повторяющиеся).
     * Найти и вывести список уникальных слов,
     * из которых состоит массив (дубликаты не считаем). Посчитать, сколько раз встречается каждое слово.
     **/
    public static List findUnique(List list) {
        List targetList = new ArrayList();
        Iterator<String> iter = list.listIterator();
        for (int i = 0; i < list.size(); i++) {
            String target = (String) list.get(i);
            int repCounter = 0;
            for (int j = 0; iter.hasNext(); j++) {

                if (target == iter.next() && isValueNonRepetitive(target, targetList)) {
                    repCounter++;
                }
            }
            if (repCounter == 1) {
                targetList.add(target);
            }
            iter = list.listIterator();
        }
        return targetList;
    }

    private static boolean isValueNonRepetitive(String target, List targetList) {
        Iterator iter = targetList.listIterator();
        while (iter.hasNext()) {
            if (iter.next() == target) {
                return false;
            }
        }
        return true;
    }

    public static void repetitionCounter(List list) {
        List targetList = new ArrayList();
        Iterator<String> iter = list.listIterator();
        for (int i = 0; i < list.size(); i++) {
            String target = (String) list.get(i);
            int repCounter = 0;
            for (int j = 0; iter.hasNext(); j++) {
                if (target == iter.next() && isValueNonRepetitive(target, targetList)) {
                    repCounter++;
                }

            }
            if (repCounter > 0) {
                System.out.println("Element \"" + target + "\" is repeated " + repCounter + " times");
            }
            targetList.add(target);
            iter = list.listIterator();
        }
    }
}
