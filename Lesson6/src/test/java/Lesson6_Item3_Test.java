import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Lesson6_Item3_Test {
    private Lesson6_Item3 lesson6_item3;

    @BeforeEach
    public void init() {
        lesson6_item3 = new Lesson6_Item3();
    }

    @Test
    public void testOne() {
        Assertions.assertTrue(lesson6_item3.main(new int[] {1, 1, 1, 4, 4, 1, 4, 4}));
    }

    @Test
    public void testTwo() {
        Assertions.assertFalse(lesson6_item3.main(new int[] {1, 1, 1, 1, 1, 1}));
    }

    @Test
    public void testThree() {
        Assertions.assertFalse(lesson6_item3.main(new int[] {4, 4, 4, 4}));
    }

    @Test
    public void testFour() {
        Assertions.assertFalse(lesson6_item3.main(new int[] {1, 4, 4, 1, 1, 4, 3}));
    }
}
