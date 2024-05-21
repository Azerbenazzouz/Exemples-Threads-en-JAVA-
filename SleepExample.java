class MySleepingThread extends Thread {
    private String message;
    private int sleepTime;

    public MySleepingThread(String message, int sleepTime) {
        this.message = message;
        this.sleepTime = sleepTime;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(message + " - " + i);
            try {
                // Met en pause le thread pendant sleepTime millisecondes
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                System.err.println("Thread interrupted: " + e.getMessage());
            }
        }
    }
}

public class SleepExample {
    public static void main(String[] args) {
        MySleepingThread t1 = new MySleepingThread("Thread 1", 1000); // Pause de 1 seconde
        MySleepingThread t2 = new MySleepingThread("Thread 2", 1500); // Pause de 1.5 secondes

        t1.start();
        t2.start();
    }
}
