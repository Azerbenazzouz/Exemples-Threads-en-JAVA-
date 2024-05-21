class Task extends Thread {
    private String name;

    public Task(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(name + " running: " + i);
            try {
                Thread.sleep(1000); // Met en pause le thread pendant 1 seconde
            } catch (InterruptedException e) {
                System.out.println(name + " was interrupted");
            }
        }
        System.out.println(name + " completed");
    }
}

public class JoinExample {
    public static void main(String[] args) {
        Task task1 = new Task("Task 1");
        Task task2 = new Task("Task 2");
        Task task3 = new Task("Task 3");

        task1.start();
        task2.start();
        task3.start();

        try {
            // Attendre la fin de task1
            task1.join();
            System.out.println("Task 1 has finished. Continuing with the main thread.");

            // Attendre la fin de task2
            task2.join();
            System.out.println("Task 2 has finished. Continuing with the main thread.");

            // Attendre la fin de task3
            task3.join();
            System.out.println("Task 3 has finished. Continuing with the main thread.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All tasks are completed. Main thread ending.");
    }
}
