public class TaskByStart extends Task {
    private int ID;
    public int start;
    public int deadline;
    public int duration;

    public TaskByStart(int ID, int start, int deadline, int duration) {
        super(ID, start, deadline, duration);
        this.ID = ID;
        this.start = start;
        this.deadline = deadline;
        this.duration = duration;
    }


    public String toString() {
        return "Task " + ID;
    }

    @Override
    public int compareTo(Task o) {
        if (this.start == o.start) {
            if (this.deadline < o.deadline) {
                return -1;
            }
            if (this.deadline > o.deadline) {
                return 1;
            }
        }
        if (this.start < o.start) {
            return -1;
        }
        if (this.start > o.start) {
            return 1;
        }
        return 0;
    }
}
