import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class Lesson6_Item2_Test {
    private Lesson6_Item2 lesson6_item2;

    @BeforeEach
    public void init() {
        lesson6_item2 = new Lesson6_Item2();
    }

    /** Не получилось сделать параметризованный тест,
     *  не могу понять как передавать массывы в качестве параметра. */
//    @CsvSource({
//            "new int[] {1, 7}, new int[] {1, 2, 4, 4, 2, 3, 4, 1, 7}",
//            "new int[] {}, new int[] {4, 4, 4, 4, 4}",
//            "new int[] {3, 2, 1}, new int[] {5, 4, 3, 2, 1}"
//    })
//
//    @ParameterizedTest
//    public void massTest(int[] result, int[] check) {
//        Assertions.assertArrayEquals(result, check);
//    }

    @Test
    public void testOne() {
        Assertions.assertArrayEquals(new int[] {1, 7}, lesson6_item2.main(new int[] {1, 2, 4, 4, 2, 3, 4, 1, 7}));
    }

    @Test
    public void testTwo() {
        Assertions.assertArrayEquals(new int[] {}, lesson6_item2.main(new int[] {4, 4, 4, 4, 4}));
    }

    @Test
    public void testThree() {
        Assertions.assertArrayEquals(new int[] {3, 2, 1}, lesson6_item2.main(new int[] {5, 4, 3, 2, 1}));
    }

    @Test
    public void testFour() throws RuntimeException {
        Assertions.assertThrows(RuntimeException.class, () -> {
            lesson6_item2.main(new int[] {0, 0, 0});
        });
    }
}
