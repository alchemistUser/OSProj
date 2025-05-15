package Helpers;

public class Process {

    public int id;
    public int burstTime;
    public int arrivalTime;
    public int completionTime;
    public int turnaroundTime;
    public int waitingTime;

    public Process(int id, int burstTime, int arrivalTime) {
        this.id = id;
        this.burstTime = burstTime;
        this.arrivalTime = arrivalTime;
    }
}