import java.io.*;
import java.util.Date;

public class SnapshotRecorder {
    private Date snapshotTime;
    private String fileName = "snapshot.txt";

    public SnapshotRecorder() {
        loadSnapshotTime();
    }

    public void takeSnapshot() {
        snapshotTime = new Date();
        saveSnapshotTime();
    }

    public Date getSnapshotTime() {
        return snapshotTime;
    }

    private void saveSnapshotTime() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            writer.println(snapshotTime.getTime());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadSnapshotTime() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = reader.readLine();
            if (line != null) {
                long timeMillis = Long.parseLong(line);
                snapshotTime = new Date(timeMillis);
            } else {
                snapshotTime = new Date();
            }
        } catch (IOException e) {
            e.printStackTrace();
            snapshotTime = new Date();
        }
    }

    public void saveStateToTextFile(String textFileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(textFileName))) {
            writer.println(snapshotTime.getTime());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SnapshotRecorder snapshotRecorder = new SnapshotRecorder();

        System.out.println("Loaded Snapshot Time: " + snapshotRecorder.getSnapshotTime());

        snapshotRecorder.takeSnapshot();
        System.out.println("New Snapshot Time: " + snapshotRecorder.getSnapshotTime());
    }
}
