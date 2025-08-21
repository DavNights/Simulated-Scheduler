import java.util.Arrays;

public class SimulatedScheduler
{
    public static void main(String[] args)
    {
        //Retrieve an array of all task lengths without the algorithm selector. Turns (FIFO, 2, 5, 6, 7, 8) to. (2, 5, 6, 7, 8)
        final String[] tasksarray = Arrays.copyOfRange(args, 1, args.length);

        TaskProcess taskprocesses[] = compileProcceses(tasksarray);

        switch(args[0])
        {
            case "FIFO":
                FIFO algorithmFIFO = new FIFO(taskprocesses);
                break;

            case "RR":
                RoundRobin algorithmRR = new RoundRobin(taskprocesses);
                break;

            case "SJF":
                SJF algorithmSJF = new SJF(taskprocesses);
                break;

            case "Test":
                for (int i = 0; i < taskprocesses.length; i++)
                {
                    System.out.println(taskprocesses[i].identifer + ", " + taskprocesses[i].getBursttime());
                }
                break;
        }
        // Desired format for command line execution
        // Array for Organization P1
        // SimulatedScheduler FIFO 15 2 3 4 5
    }

    //turns array of strings into an array of processes, with correct identifiers. See @TaskProcess to see how they are assigned.
    private static TaskProcess[] compileProcceses(String[] array)
    {
        TaskProcess[] taskprocessarray = new TaskProcess[array.length];
        for (int i = 0; i < array.length; i++)
        {
            taskprocessarray[i] = new TaskProcess(Integer.parseInt(array[i]));
        }
        return taskprocessarray;
    }
}