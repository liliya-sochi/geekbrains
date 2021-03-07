public class Lesson6_Item2 {

    public int[] main(int[] inArray) {
        for (int i = inArray.length-1; i > 0; i--) {
            if (inArray[i] == 4) {
                int[] outArray = new int[inArray.length - 1 - i];
                int temp = 0;
                for (int j = i + 1; j < inArray.length; j++) {
                    outArray[temp] = inArray[j];
                    temp++;
                }
                return outArray;
            }
        }
        throw new RuntimeException("В массиве нет 4-ки!");
    }
}
