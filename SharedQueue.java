import java.util.LinkedList;
import java.util.Queue;

public class SharedQueue {
    public final Queue<PrintJob> queue = new LinkedList<>();
    private final int capacity = 5;

    public synchronized void enqueue(PrintJob job, String computerID) throws InterruptedException {
        while (queue.size() == capacity) {
            System.out.println("Queue is Full. Wait until printers are free...");
            wait();
        }
        System.out.println("Computer-" + computerID + " ---> Enqueued ---> " + job.getJobId());
        queue.add(job);
        notifyAll();
    }

    public synchronized PrintJob dequeue(int printerID) throws InterruptedException {
        while (queue.isEmpty()) {
            Thread.sleep(3000);
            System.out.println("waiting for order...");
        }
        PrintJob job = queue.poll();
        System.out.println("printer-" + printerID + " ---> Dequeuing ---> " + job.getJobId());
        notifyAll();
        return job;
    }

    public Queue<PrintJob> GetQueue() {
        return this.queue;
    }
}
