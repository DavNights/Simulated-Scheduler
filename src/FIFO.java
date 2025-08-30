public class FIFO {
    FIFO(TaskProcess[] processes)
    {
        // Variables for FIFO wait time, turn around and completion times
        double totalWaitingTime = 0;
        double totalTurnaroundTime = 0;
        int completionTime = 0;

        // Display formatting
        System.out.println("Processing with FIFO algorithm...");
        System.out.println("---------------------------------------------------------------");
        System.out.println("Process\t\tBurst Time\tWaiting Time\tTurnaround Time");
        System.out.println("---------------------------------------------------------------");

        // Iterate through processes array, for each process calculate waiting and turn around time
        for (int i = 0; i < processes.length; i++)
        {
            TaskProcess currentProcess = processes[i];

            // The waiting time for the current process is the completion time of the previous process
            currentProcess.waitingTime = completionTime;

            // The turnaround time is the burst time plus the waiting time
            currentProcess.turnaroundTime = currentProcess.burstTime + currentProcess.waitingTime;

            // Update the total waiting and turnaround times for calculating the average
            totalWaitingTime += currentProcess.waitingTime;
            totalTurnaroundTime += currentProcess.turnaroundTime;

            // The completion time for the next process is the completion time of the current one, plus its burst time
            completionTime += currentProcess.burstTime;

            // Print the results for the current process
            System.out.printf("%s\t\t%8d\t\t%8d\t\t%8d\n", currentProcess.identifier, currentProcess.burstTime, currentProcess.waitingTime, currentProcess.turnaroundTime);
        }
        System.out.println("---------------------------------------------------------------");

        // Calculate and print the average waiting and turnaround times
        double averageWaitingTime = totalWaitingTime / processes.length;
        double averageTurnaroundTime = totalTurnaroundTime / processes.length;

        // Displays average waiting and turn around time
        System.out.printf("Average Waiting Time: %.2f\n", averageWaitingTime);
        System.out.printf("Average Turnaround Time: %.2f\n", averageTurnaroundTime);
    }
}
