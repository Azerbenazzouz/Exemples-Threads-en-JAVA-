class LongRunningTask extends Thread {
    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                System.out.println("Task running: " + i);
                Thread.sleep(1000); // Met en pause le thread pendant 1 seconde
            }
        } catch (InterruptedException e) {
            System.out.println("Task was interrupted");
        }
    }
}

public class InterruptExample {
    public static void main(String[] args) {
        LongRunningTask task = new LongRunningTask();
        task.start();

        try {
            Thread.sleep(5000); // Main thread se met en pause pendant 5 secondes
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Interruption du thread long running task
        task.interrupt();
    }
}
