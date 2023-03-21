import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Assignment5 {

    public static void main(String[] args) {
        simpleQueueTest();
        scheduleTasks("taskset1.txt");
        scheduleTasks("taskset2.txt");
        scheduleTasks("taskset3.txt");
        scheduleTasks("taskset4.txt");
        scheduleTasks("taskset5.txt");
    }

    public static void scheduleTasks(String taskFile) {
        // TODO: Uncomment code here as you complete the tasks and scheduling algorithm
        ArrayList<Task> tasksByDeadline = new ArrayList<>();
        ArrayList<Task> tasksByStart = new ArrayList<>();
        ArrayList<Task> tasksByDuration = new ArrayList<>();

        readTasks(taskFile, tasksByDeadline, tasksByStart, tasksByDuration);

        schedule("Deadline Priority : "+ taskFile, tasksByDeadline);
        schedule("Startime Priority : " + taskFile, tasksByStart);
        schedule("Duration priority : " + taskFile, tasksByDuration);
    }

   public static void simpleQueueTest() {
       // TODO: Uncomment code here for a simple test of your priority queue code
       PriorityQueue<Integer> queue = new PriorityQueue<>();
       queue.enqueue(9);
       queue.enqueue(7);
       queue.enqueue(5);
       queue.enqueue(3);
       queue.enqueue(1);
       queue.enqueue(10);

       while (!queue.isEmpty()) {
           System.out.println(queue.dequeue());
       }
   }

    /**
     * Reads the task data from a file, and creates the three different sets of tasks for each
     */
    public static void readTasks(String filename,
                                 ArrayList<Task> tasksByDeadline,
                                 ArrayList<Task> tasksByStart,
                                 ArrayList<Task> tasksByDuration) {
        File file = new File(filename);
        ArrayList<String> copyList = new ArrayList<>();
        try (Scanner input = new Scanner(file)) {
            while (input.hasNextLine()) {
                String[] list = input.nextLine().split("[\t]");
                Collections.addAll(copyList, list);
            }
        }
        catch (java.io.IOException ex) {
            System.out.println("An error occured trying to read the file: " + ex);
        }
        for (int i=0; i<copyList.size()-2; i+=3) {
            Task taskByDeadline = new TaskByDeadline(i/3+1, Integer.parseInt(copyList.get(i)), Integer.parseInt(copyList.get(i+1)), Integer.parseInt(copyList.get(i+2)));
            Task taskByStart = new TaskByStart(i/3+1, Integer.parseInt(copyList.get(i)), Integer.parseInt(copyList.get(i+1)), Integer.parseInt(copyList.get(i+2)));
            Task taskByDuration = new TaskByDuration(i/3+1, Integer.parseInt(copyList.get(i)), Integer.parseInt(copyList.get(i+1)), Integer.parseInt(copyList.get(i+2)));
            tasksByDeadline.add(taskByDeadline);
            tasksByStart.add(taskByStart);
            tasksByDuration.add(taskByDuration);
        }
    }

    /**
     * Given a set of tasks, schedules them and reports the scheduling results
     */
    public static void schedule(String label, ArrayList<Task> tasks) {
        // TODO: Write your scheduling algorithm here
        PriorityQueue<Task> queue = new PriorityQueue<>();
        System.out.println(label);

        boolean complete=false;
        int time = 1;
        int allComplete = 0;
        int late = 0;
        while (!complete) {
            for (int i=0; i < tasks.size(); i++) {
                if (time==tasks.get(i).start && tasks.get(i).duration != 0) {
                    queue.enqueue(tasks.get(i));
                }
            }

            Task task = queue.dequeue();
            if (task != null) {
                task.duration-=1;
                String done = "";
                if (task.duration != 0) {
                    queue.enqueue(task);
                }
                if (task.duration==0) {
                    if (time > task.deadline && task.duration == 0) {
                        late+=1;
                        done+="**Late" + late;
                    }
                    else {
                        done+="**";
                    }
                    allComplete+=1;
                }
                System.out.printf("Time: %d: Task %s%s\n", time, task.toString(), done);
            }
            else {
                System.out.printf("Time: %d: ---\n", time);
            }
            time++;
            if (allComplete==tasks.size() && queue.isEmpty()) {
                System.out.printf("Tasks late %d Total late %d", late, late);
                System.out.println();
                complete=true;
            }
        }
    }
}
