import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class SJF {
    SJF(TaskProcess[] processes) {
        // Variables for SJF wait time, turn around and completion times
        double totalWaitingTime = 0;
        double totalTurnaroundTime = 0;
        int completionTime = 0;

        // Initialize a PriorityQueue that prioritizes the shortest burst time first
        PriorityQueue<TaskProcess> priorityQueue = new PriorityQueue<>(new Comparator<TaskProcess>() {
            @Override
            public int compare(TaskProcess p1, TaskProcess p2) {
                return Integer.compare(p1.burstTime, p2.burstTime);
            }
        });

        // Add all elements from the processes array into the priority queue
        priorityQueue.addAll(Arrays.asList(processes));

        // Display formatting
        System.out.println("Processing with SJF algorithm...");
        System.out.println("---------------------------------------------------------------");
        System.out.println("Process\t\tBurst Time\tWaiting Time\tTurnaround Time");
        System.out.println("---------------------------------------------------------------");

        // Use a standard count variable for tracking the total number of processed elements for averages
        int initialProcessCount = priorityQueue.size();

        // Process elements one by one from the PriorityQueue until it is empty
        while (!priorityQueue.isEmpty()) {
            // Retrieves and removes the head of the queue (the process with the lowest burst time)
            TaskProcess currentProcess = priorityQueue.poll();

            // The waiting time for the current process is the completion time of the previous processes
            currentProcess.waitingTime = completionTime;

            // The turnaround time is the burst time plus the waiting time
            currentProcess.turnaroundTime = currentProcess.burstTime + currentProcess.waitingTime;

            // Update the total waiting and turnaround times for calculating the average
            totalWaitingTime += currentProcess.waitingTime;
            totalTurnaroundTime += currentProcess.turnaroundTime;

            // The completion time for the next process is updated
            completionTime += currentProcess.burstTime;

            // Print the results for the current process
            System.out.printf("%s\t\t%8d\t\t%8d\t\t%8d\n",
                    currentProcess.identifier, currentProcess.burstTime, currentProcess.waitingTime, currentProcess.turnaroundTime);
        }
        System.out.println("---------------------------------------------------------------");

        // Calculate and print the average waiting and turnaround times
        double averageWaitingTime = totalWaitingTime / initialProcessCount;
        double averageTurnaroundTime = totalTurnaroundTime / initialProcessCount;

        // Displays average waiting and turn around time
        System.out.printf("Average Waiting Time: %.2f\n", averageWaitingTime);
        System.out.printf("Average Turnaround Time: %.2f\n", averageTurnaroundTime);
    }
}