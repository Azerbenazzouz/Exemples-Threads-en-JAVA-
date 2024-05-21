class SharedResource {
    private int data;
    private boolean dataAvailable = false;

    // Méthode pour le producteur de produire des données
    public synchronized void produce(int value) {
        while (dataAvailable) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        data = value;
        dataAvailable = true;
        System.out.println("Produced: " + data);
        notify(); // Notifie le consommateur que des données sont disponibles
    }

    // Méthode pour le consommateur de consommer des données
    public synchronized int consume() {
        while (!dataAvailable) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        dataAvailable = false;
        System.out.println("Consumed: " + data);
        notify(); // Notifie le producteur que des données ont été consommées
        return data;
    }
}

class Producer extends Thread {
    private SharedResource resource;

    public Producer(SharedResource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            resource.produce(i);
            try {
                Thread.sleep(1000); // Pause pour simuler le temps de production
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

class Consumer extends Thread {
    private SharedResource resource;

    public Consumer(SharedResource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            resource.consume();
            try {
                Thread.sleep(1500); // Pause pour simuler le temps de consommation
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

public class WaitNotifyExample {
    public static void main(String[] args) {
        SharedResource resource = new SharedResource();
        Producer producer = new Producer(resource);
        Consumer consumer = new Consumer(resource);

        producer.start();
        consumer.start();
    }
}
