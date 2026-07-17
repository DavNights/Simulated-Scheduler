import java.util.LinkedList;
import java.util.Queue;

public class RoundRobin {
    RoundRobin(TaskProcess[] processes) {
        double totalWaitingTime = 0;
        double totalTurnaroundTime = 0;
        int completionTime = 0;

        // Display formatting
        System.out.println("Processing with Dynamic Quantum Round Robin algorithm...");
        System.out.println("---------------------------------------------------------------");
        System.out.println("Process\t\tBurst Time\tWaiting Time\tTurnaround Time");
        System.out.println("---------------------------------------------------------------");

        // Track remaining burst times locally to protect the original object state
        int[] remainingBurstTimes = new int[processes.length];
        for (int i = 0; i < processes.length; i++) {
            remainingBurstTimes[i] = processes[i].burstTime;
        }

        // Initialize the Ready Queue with all processes (assuming an arrival time 0)
        Queue<Integer> readyQueue = new LinkedList<>();
        for (int i = 0; i < processes.length; i++) {
            readyQueue.add(i);
        }

        // Keep track of which processes have finished to print their final metrics
        boolean[] isCompleted = new boolean[processes.length];

        // Process loop continues until the ready queue is empty
        while (!readyQueue.isEmpty()) {

            // 1. DYNAMIC QUANTUM CALCULATION
            // Calculate the average of the remaining burst times for all processes currently in the queue
            double sumRemainingTime = 0;
            for (int index : readyQueue) {
                sumRemainingTime += remainingBurstTimes[index];
            }
            // Use Math.ceil to prevent a quantum of 0 if remaining times are fractional/very small
            int currentQuantum = (int) Math.ceil(sumRemainingTime / readyQueue.size());

            // 2. Dequeue the next process to execute
            int currentIndex = readyQueue.poll();
            TaskProcess currentProcess = processes[currentIndex];

            // 3. Execute the process for either the full quantum or its remaining time (whichever is shorter)
            int executionTime = Math.min(remainingBurstTimes[currentIndex], currentQuantum);

            remainingBurstTimes[currentIndex] -= executionTime;
            completionTime += executionTime;

            // 4. Check if the process is finished
            if (remainingBurstTimes[currentIndex] == 0) {
                isCompleted[currentIndex] = true;

                // Turnaround time = Completion Time - Arrival Time (assumed 0)
                currentProcess.turnaroundTime = completionTime;

                // Waiting time = Turnaround Time - Burst Time
                currentProcess.waitingTime = currentProcess.turnaroundTime - currentProcess.burstTime;

                totalWaitingTime += currentProcess.waitingTime;
                totalTurnaroundTime += currentProcess.turnaroundTime;

                // Print the final statistics for the completed process
                System.out.printf("%s\t\t%8d\t\t%8d\t\t%8d\n",
                        currentProcess.identifier, currentProcess.burstTime, currentProcess.waitingTime, currentProcess.turnaroundTime);
            } else {
                // If the process still has remaining work, it goes back to the end of the queue
                readyQueue.add(currentIndex);
            }
        }
        System.out.println("---------------------------------------------------------------");

        // Calculate and print the average waiting and turnaround times
        double averageWaitingTime = totalWaitingTime / processes.length;
        double averageTurnaroundTime = totalTurnaroundTime / processes.length;

        System.out.printf("Average Waiting Time: %.2f\n", averageWaitingTime);
        System.out.printf("Average Turnaround Time: %.2f\n", averageTurnaroundTime);
    }
}