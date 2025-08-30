public class TaskProcess
{
    // RunningTally used to increment identifier P1,P2...
    static int runningtally = 0;
    // Identifier for initial P used in identification "P1"
    String identifier;
    // Variable to represent burst time of a process
    int burstTime;
    // Variable to represent the TAT (Turn around time) of a process
    int turnaroundTime;
    // Variable to hold waiting time of a process
    int waitingTime;

    TaskProcess(int burst)
    {
        // Increment running tally, assigning each process identifier, should process correctly as 1-n.
        identifier = "P" + runningtally++;
        burstTime = burst;
    }

    public int getBurstTime()
    {
        return burstTime;
    }
}
