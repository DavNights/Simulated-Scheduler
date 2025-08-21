public class TaskProcess
{
    private static int runningtally = 0;
    private int bursttime;
    public int workneeded;
    String identifer;

    TaskProcess(int burst)
    {
        bursttime = burst;
        //Increment running tally, then assign it to the process identifier automatically.
        //Should automatically number each process correctly, 1-n.
        runningtally++;
        identifer = "P" + runningtally;
    }

    public int getBursttime()
    {
        return bursttime;
    }


}
