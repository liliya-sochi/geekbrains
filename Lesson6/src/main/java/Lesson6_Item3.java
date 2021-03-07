public class Lesson6_Item3 {

    public boolean main(int[] array) {
        boolean one = false;
        boolean four = false;
        for (int temp : array) {
            if (temp != 1 && temp != 4)
                return false;
            if (temp == 1)
                one = true;
            if (temp == 4)
                four = true;
        }
        return (one && four);
    }
}
