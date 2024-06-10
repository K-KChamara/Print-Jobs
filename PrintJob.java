public class PrintJob {
    private String jobId;
    private String fileType;

    public PrintJob(String jobId, String fileType) {
        this.jobId = jobId;
        this.fileType = fileType;
    }

    // Getters
    public String getJobId() {
        return jobId;
    }

    public String getFileType() {
        return fileType;
    }

}
