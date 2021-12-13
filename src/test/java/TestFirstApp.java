import Lesson14.FirstApp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.Arrays;

public class TestFirstApp {
    FirstApp firstApp;

    @BeforeEach
    public void init(){
        firstApp = new FirstApp();
    }

    @Test
    @Timeout(5)
    public void testMatchFour() {
        Assertions.assertEquals("[1, 7]", Arrays.toString(firstApp.first(new int[]{1, 2, 4, 4, 2, 3, 4, 1, 7})));
    }


    //Не придумал как тут поймать исключение если в методе все обернутов try/catch
    @Test
    @Timeout(5)
    public void testAbsenceFour(){
       RuntimeException thrown = Assertions.assertThrows(RuntimeException.class, () -> firstApp.first(new int[]{1, 2, 2, 3, 2, 3, 8, 1, 7}));
    }

    @Test
    @Timeout(5)
    public void testReturnNull() {
        Assertions.assertNull(firstApp.first(new int[]{}));
    }


}
