import java.util.ArrayList;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class Main {
    public static ArrayList<String> readLinesAsArrayList(String filePath) {
        try {
            // Read all lines from the file as a List
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            // Convert List to ArrayList
            return new ArrayList<>(lines);
        } catch (IOException e) {
            e.printStackTrace();
            // Return an empty ArrayList in case of an error
            return new ArrayList<>();
        }
    }

    public static void main(String[] args) {
        System.out.println(
                "   _____   _       _                   _____           _           _                       \r\n" + //
                        "  / ____| | |     (_)                 |  __ \\         (_)         | |                      \r\n"
                        + //
                        " | (___   | |__    _   _ __     ___   | |__) |  _ __   _   _ __   | |_    ___   _ __   ___ \r\n"
                        + //
                        "  \\___ \\  | '_ \\  | | | '_ \\   / _ \\  |  ___/  | '__| | | | '_ \\  | __|  / _ \\ | '__| / __|\r\n"
                        + //
                        "  ____) | | | | | | | | | | | |  __/  | |      | |    | | | | | | | |_  |  __/ | |    \\__ \\\r\n"
                        + //
                        " |_____/  |_| |_| |_| |_| |_|  \\___|  |_|      |_|    |_| |_| |_|  \\__|  \\___| |_|    |___/\r\n"
                        + //
                        "                                                                                           \r\n"
                        + //
                        "                                                                                           ");
        SharedQueue sharedQueue = new SharedQueue();
        ArrayList<PrintJob> jobList = new ArrayList<>();

        String filePath = "textfile.txt";
        ArrayList<String> lines = readLinesAsArrayList(filePath);
        for (int i = 0; i < lines.size(); i++) {
            String filename = lines.get(i);
            String[] parts = filename.split("\\.");
            String s1 = parts[0];
            String s2 = parts[1];
            PrintJob obj = new PrintJob(s1, s2);
            jobList.add(obj);

        }

        PrintJob job1 = new PrintJob("Himashi", "pdf");
        jobList.add(job1);
        PrintJob job2 = new PrintJob("Gayeshi", "pdf");
        jobList.add(job2);
        PrintJob job3 = new PrintJob("Kavindya", "pdf");
        jobList.add(job3);
        PrintJob job4 = new PrintJob("Yashodara", "jpg");
        jobList.add(job4);
        PrintJob job5 = new PrintJob("Shakeena", "pdf");
        jobList.add(job5);
        PrintJob job6 = new PrintJob("Ravishna", "ser");
        jobList.add(job6);
        PrintJob job7 = new PrintJob("Nirasha", "pdf");
        jobList.add(job7);

        Thread computer1 = new Computer(sharedQueue, "1", jobList);
        System.out.println("computer-1 is started");
        Thread computer2 = new Computer(sharedQueue, "2", jobList);
        System.out.println("computer-2 is started");
        Thread computer3 = new Computer(sharedQueue, "3", jobList);
        System.out.println("computer-3 is started");

        computer1.start();
        try {
            Thread.sleep(50);
        } catch (Exception e) {
        }
        computer2.start();
        try {
            Thread.sleep(50);
        } catch (Exception e) {
        }
        computer3.start();
        try {
            Thread.sleep(50);
        } catch (Exception e) {
        }
        Thread printer1 = new Printer(sharedQueue, 1);
        Thread printer2 = new Printer(sharedQueue, 2);

        System.out.println("Printer-1 started");
        printer1.start();
        System.out.println("Printer-2 started");
        printer2.start();
    }
}
