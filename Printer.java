public class Printer extends Thread {
    private final SharedQueue sharedQueue;
    private int PrinterID;

    public Printer(SharedQueue sharedQueue, int ID) {
        this.sharedQueue = sharedQueue;
        this.PrinterID = ID;
    }

    public int GetID() {
        return PrinterID;
    }

    public static void checkType(String type) throws TypeNotSupportedException {
        if (type.equalsIgnoreCase("pdf"))
            return;
        throw new TypeNotSupportedException("This file is not support");

    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                PrintJob job = sharedQueue.dequeue(this.PrinterID);

                try {
                    checkType(job.getFileType());
                    Thread.sleep(2000);
                    System.out.println("printer " + this.GetID() + " ---> Processing ---> " + job.getJobId());
                } catch (TypeNotSupportedException e) {
                    System.out.println(e);
                    System.out.println("!!!!" + job.getJobId() + " file is not supported " + "!!!!");
                    System.out.println();

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Properly handle interruption
                break;
            }
        }
    }
}
