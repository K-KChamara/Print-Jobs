import java.util.ArrayList;

public class Computer extends Thread {
    private final SharedQueue sharedQueue;
    private static ArrayList<PrintJob> jobList;
    private String compuetrID;

    public Computer(SharedQueue sharedQueue, String id, ArrayList<PrintJob> jobList) {
        this.sharedQueue = sharedQueue;
        this.compuetrID = id;
        this.jobList = jobList;
    }

    @Override
    public void run() {
        while (!jobList.isEmpty()) {
            PrintJob job = jobList.remove(0);
            try {
                sharedQueue.enqueue(job, this.compuetrID);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
