public class LessonFive {
    static  final int SIZE = 10000000;
    static final int H = SIZE / 2;

    public static void main(String[] args) throws InterruptedException {
        float[] arr1 = new float[SIZE];
        for(int i = 0; i < arr1.length; i++) arr1[i] = 1;

        long startTimeMethod1 = System.currentTimeMillis();
        for(int i = 0; i < arr1.length; i++) arr1[i] = (float)(arr1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        long stopTimeMethod1 = System.currentTimeMillis();
        System.out.println("Время работы первого метода: " + (stopTimeMethod1 - startTimeMethod1));

        float[] arr2 = new float[SIZE];
        for(int i = 0; i < arr2.length; i++) arr2[i] = 1;
        float[] a1 = new float[H];
        float[] a2 = new float[H];

        long startTimeMethod2 = System.currentTimeMillis();
        System.arraycopy(arr2, 0, a1, 0, H);
        System.arraycopy(arr2, H, a2, 0, H);
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < a1.length; i++) a1[i] = (float)(a1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < a2.length; i++) a2[i] = (float)(a2[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        });
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.arraycopy(a1, 0, arr2, 0, H);
        System.arraycopy(a2, 0, arr2, H, H);
        long stopTimeMethod2 = System.currentTimeMillis();
        System.out.println("Время работы второго метода: " + (stopTimeMethod2 - startTimeMethod2));
    }
}
