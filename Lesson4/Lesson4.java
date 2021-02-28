public class Lesson5 {
    static final Object monitor = new Object();
    static  volatile char currentChar = 'A';
    static final int howManyTimes = 5;

    public static void main(String[] args) {

        new Thread(() -> {
            try{
                synchronized (monitor) {
                    for (int i = 0; i < howManyTimes; i++) {
                        while (currentChar != 'A') monitor.wait();
                        System.out.print("A");
                        currentChar = 'B';
                        monitor.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try{
                synchronized (monitor) {
                    for (int i = 0; i < howManyTimes; i++) {
                        while (currentChar != 'B') monitor.wait();
                        System.out.print("B");
                        currentChar = 'C';
                        monitor.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try{
                synchronized (monitor) {
                    for (int i = 0; i < howManyTimes; i++) {
                        while (currentChar != 'C') monitor.wait();
                        System.out.print("C");
                        currentChar = 'A';
                        monitor.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}

/**
 * Не могу, пока что понять, зачем мне применять ExecutorService
 * в моём чате, так как он ещё толком не дописан. Но я поняла, что этот класс
 * создаёт пул потоков. В этом случае для подключённых клиентов на сервере,
 * для удобства добавления и удаления потоков.
 */