package data;


import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import data.Process.State;

public class Timer implements Runnable {



	//stop watch for timer
	private static int duration;
	
	//permanent time quantum
	private static int defaultQuantum;
	
	//time remaining in time quantum during run of algorithm
	private static int quantum;
	
	//processes to be run
	private static Queue<Process> allProcesses;
	
	//algorithm to run (LRU or Clock)
	private static PageAlgorithm algorithmChoice;


	//initalize
	static {
		allProcesses = new LinkedList<>();
		duration=0;
		
	}
	
	public enum PageAlgorithm {
		LRU,
		Clock;
	}
	
	//reset all processes in Timer
	public static void resetAllProcessesToEmpty() {
		Queue<Process> all = new LinkedList<>();
		allProcesses = all;
	}
	public static void setAlgorithmChoice(PageAlgorithm algorithm) {
		algorithmChoice=algorithm;
	}
	
	//add processes to Timer
	public static void addProcesses(Process process) {
		allProcesses.add(process);
	}
	
	public static Queue<Process> getAllProcesses() {
		return allProcesses;
	}

	public static void setAllProcesses(Queue<Process> processes) {
		allProcesses = processes;
	}
	
	public static void setQuantumToDefault() {
		
		quantum=defaultQuantum;
	}
	
	public static int getDuration() {
		return duration;
	}

	public static void setDuration(int dur) {
		duration = dur;
	}
	
	//reset stop watch to 0
	public static void resetDurationToStart() {
		duration=0;
	}

	public static int getDefaultQuantum() {
		return defaultQuantum;
	}

	public static void setDefaultQuantum(int defaultQ) {
		defaultQuantum = defaultQ;
		quantum=defaultQuantum;
	}

	public static int getQuantum() {
		return quantum;
	}

	public static void setQuantum(int quant) {
		quantum = quant;
	}
	
	//Timer stop watch method
	public void run() {
		
		//while there are still processes in the processes queue
		while(!allProcesses.isEmpty()) {
		
			//Round Robin Scheduling
			
			//take out a process from the processes queue
			Process process = allProcesses.poll();
			
			//while it's time quantum is not 0
			while(quantum!=0) {
				
				//decrement it's time quantum
				quantum--;
				
				//run the LRU algorithm or Clock algorithm
				//depending on which choice has been set
				switch(algorithmChoice) {
				case LRU:
					process.readPageLRU();
					break;
				case Clock:
					process.readPageClock();
					break;
				default:
					System.out.println("Error: No algorithm choice setted for running method.");
				}
				
				
	
				duration++;
				
			}
			//finish time quantum
			
			//add the process back to processes queue
			//if that process still has pages in it
			//in the algorithm it is running
			switch(algorithmChoice) {
			case LRU:
				if(!process.getLruProcessPages().isEmpty()) {
					
					allProcesses.add(process);
				}
				break;
			case Clock:
				if(!process.getClockProcessPages().isEmpty()) {
					
					allProcesses.add(process);
				}
				break;
			default:
				System.out.println("Error: No algorithm choice setted for adding to queue.");
			}
			
	
			//set quantum to default value
			setQuantumToDefault();
			
			
		//once all processes finish and removed from queue, exit while loop	
		}
	
	}
	

	
}
