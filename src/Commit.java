import java.util.Date;

public class Commit {
    private Date snapshotTime;
    private boolean hasChanges;

    public Commit() {
        this.snapshotTime = new Date();
        this.hasChanges = false;
    }

    public void updateSnapshot() {
        this.snapshotTime = new Date();
        this.hasChanges = false;
    }

    public Date getSnapshotTime() {
        return snapshotTime;
    }

    public boolean hasChanges() {
        return hasChanges;
    }

    public void makeChanges() {
        // Simulate making changes to the state
        this.hasChanges = true;
    }

    public static void main(String[] args) {
        Commit commit = new Commit();

        System.out.println("Initial Snapshot Time: " + commit.getSnapshotTime());
        System.out.println("Has Changes: " + commit.hasChanges());

        commit.makeChanges();
        System.out.println("Made Changes");

        System.out.println("Snapshot Time Before Commit: " + commit.getSnapshotTime());
        System.out.println("Has Changes Before Commit: " + commit.hasChanges());

        commit.updateSnapshot();
        System.out.println("Committed");

        System.out.println("Snapshot Time After Commit: " + commit.getSnapshotTime());
        System.out.println("Has Changes After Commit: " + commit.hasChanges());
    }
}
