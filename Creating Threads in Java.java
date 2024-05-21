// Creating Threads in Java
// By Extending the Thread Class:
class UnThread extends Thread {
    public void run() {
        // Code for the thread
    }
}
UnThread ut = new UnThread();

// By Implementing the Runnable Interface:
class UnThread implements Runnable {
    public void run() {
        // Code for the thread
    }
}
Thread t = new Thread(new UnThread());
