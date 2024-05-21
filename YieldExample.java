class MyYieldingThread extends Thread {
    private String message;

    public MyYieldingThread(String message) {
        this.message = message;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(message + " - " + i);
            // Utilisation de yield() pour céder le contrôle à d'autres threads
            Thread.yield();
        }
    }
}

public class YieldExample {
    public static void main(String[] args) {
        MyYieldingThread t1 = new MyYieldingThread("Thread 1");
        MyYieldingThread t2 = new MyYieldingThread("Thread 2");

        t1.start();
        t2.start();
    }
}
