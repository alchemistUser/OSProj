package Helpers;

public class Process {

    int id;
    int burstTime;
    int arrivalTime;
    int completionTime;
    int turnaroundTime;
    int waitingTime;

    public Process(int id, int burstTime, int arrivalTime) {
        this.id = id;
        this.burstTime = burstTime;
        this.arrivalTime = arrivalTime;
    }
}