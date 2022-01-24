import Lesson14.SecondApp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestSecondApp {

    SecondApp secondApp;

    @Test
    void test(){
        secondApp = new SecondApp();
             Assertions.assertTrue(secondApp.second(new Integer[]{1, 1, 1, 4, 4, 1, 4, 4,}));
             Assertions.assertFalse(secondApp.second(new Integer[]{1, 1, 1, 1, 1, 1, 1, 1,}));
             Assertions.assertFalse(secondApp.second(new Integer[]{4, 4, 4, 4, 4, 4, 4, 4,}));
             Assertions.assertTrue(secondApp.second(new Integer[]{1, 4, 4, 1, 1, 4, 3 }));
    }
}
