//built in Java 9

package data;

import java.io.*;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.lang.*;
import java.text.*;

import data.Timer.PageAlgorithm;

public class A3 {

	public static void main(String[] args) throws IOException {

		//input and set max frames and time quantum
		int maxFrames,timeQuantum;
		maxFrames=Integer.parseInt(args[0]);
		timeQuantum = Integer.parseInt(args[1]);
		
		//initialize number of text files gonna receive
		File[] allFiles = new File[args.length-2];
		
		//receive text files
		for(int i=2;i<args.length;i++) {

			File file = new File(args[i]);
			allFiles[i-2]=file;
			
		}
		
		//list to record each line of all text files
		ArrayList<String> inputList = new ArrayList<String>();
		
		//go through all the files
		for(File f:allFiles) {
			
			
			if(f.getName().endsWith(".txt")) {
				
				Scanner scanner = new Scanner(f);
				
				//records all the lines of text
				while(scanner.hasNext()) {
					inputList.add(scanner.next());
				}
				scanner.close();
                }
			}
			
		
		//initalize counter for processes (to set process id)
		int k=1;
		
		//go through whole list of lines
		for(int i=0;i<inputList.size();i++) {
			
		//if it is begin
		if(inputList.get(i).equalsIgnoreCase("begin")) {
		
			//that means it is a new process
			k++;
			
			//initilize two queues to store page numbers. One for LRU, One for Clock.
			Queue<Integer> storeLRUNumbers = new LinkedList<>();
			Queue<Integer> storeClockNumbers = new LinkedList<>();
			
			//go to next unit in list of lines
			int j=i+1;
			
			//while the line is not end
			while(!inputList.get(j).equalsIgnoreCase("end")) {
				
				//get that line
				String storeInput = inputList.get(j);
				
				//get the number in that line
				int number = Integer.parseInt(storeInput);
				
				//store it in LRU and Clock queues
				storeLRUNumbers.add(number);
				storeClockNumbers.add(number);
				
				//go to next line
				j++;
			}
			
			//once text file reaches end
			//get that text file name through it's argument
			String processTextFileName=args[k];
			
			//delete all non numeric characters
			String processNumber=processTextFileName.replaceAll("[^0123456789]", "");
			
			//get the number, which will be it's process id
			int processID = Integer.parseInt(processNumber);
			
			//create a process for lru utilizing the process id
			Process lruProcess = new Process(processID);
			lruProcess.initializeLRUProcessPages(storeLRUNumbers);
			
			//create a process for clock utilizing the process id
			Process clockProcess = new Process(processID);
			clockProcess.initializeClockProcessPages(storeClockNumbers);
			
			//add lru process to lru queue, and clock process to clock queue
			LRU.addLruProcesses(lruProcess);
			Clock.addClockProcesses(clockProcess);
		
		}
		}
		

		//initailize timer for lru
		Timer lruTimer = new Timer();
		
		//set the algorithm choice to lru
		Timer.setAlgorithmChoice(PageAlgorithm.LRU);
		
		//create new queue to store lru processes
		Queue<Process> lruProcesses = new LinkedList<>();
		
		//add process to that queue
		for(int i=0;i<LRU.getLruProcesses().size();i++) {
			lruProcesses.add(LRU.getLruProcesses().get(i));
		}
		
		//create new queue to store clock processes
		Queue<Process> clockProcesses = new LinkedList<>();
		
		//add process to that queue
		for(int i=0;i<Clock.getClockProcesses().size();i++) {
			clockProcesses.add(Clock.getClockProcesses().get(i));
		}
		
		
		//initailize processes for lru
		Timer.setAllProcesses(lruProcesses);
		
		//set time quantum for lru
		Timer.setDefaultQuantum(timeQuantum);
		
		//set frames allocated for lru
		int framesAllocatedPerProcess = maxFrames/lruProcesses.size();
		PageTable.setMaximumFrames(framesAllocatedPerProcess);
		
		//prepare executor service to execute timer
		ExecutorService executor = Executors.newFixedThreadPool(1);
		
		//run timer
		executor.execute(lruTimer);
		
		//shut down executor once done
		executor.shutdown();
		
		try {
			executor.awaitTermination(1, TimeUnit.HOURS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//once finish, reset page table to empty
		PageTable.resetPageTableToEmpty();
		
		
		//create new timer for clock algorithm
		Timer clockTimer = new Timer();
		
		//reset Timer duration to 0
		Timer.resetDurationToStart();
		
		//reset all processes in Timer class to empty
		Timer.resetAllProcessesToEmpty();
		
		//reset time quantum in Timer class
		Timer.setQuantumToDefault();
		
		//set algorithm to clock
		Timer.setAlgorithmChoice(PageAlgorithm.Clock);

		//initalize all processes for timer
		Timer.setAllProcesses(clockProcesses);
		
		//set time qauntum
		Timer.setDefaultQuantum(timeQuantum);
		
		//set max frames allocated
		framesAllocatedPerProcess=maxFrames/clockProcesses.size();
		PageTable.setMaximumFrames(framesAllocatedPerProcess);
		
		//prepare executor to execute timer
		executor = Executors.newFixedThreadPool(1);
		
		//run timer
		executor.execute(clockTimer);
		
		//once finish, shut down executor
		executor.shutdown();
		
		try {
			executor.awaitTermination(1, TimeUnit.HOURS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		//Print Comparisons of LRU and Clock-----------------------------------------------------------------
		System.out.println("LRU - Fixed:");
		System.out.println("Process         Name        Turnaround Time     #Faults                       Fault Times");
		for(int i=0;i<LRU.getLruProcesses().size();i++) {
			Process process = LRU.getLruProcesses().get(i);
			
			String processPageFaultTimes;
			String append="";
			for(int j=0;j<process.getLruPageFaultTimes().size();j++) {
				append=append+process.getLruPageFaultTimes().get(j);
				
				if(j!=(process.getLruPageFaultTimes().size()-1)) {
					append=append+", ";
				}
			}
			
			processPageFaultTimes="{"+append+"}";
			
			System.out.println("   "+process.getProcessID()+"        "+
								process.getProcessName()+"         "+
								process.getLruTurnAroundTime()+"               "+
								process.getLruPageFaults()+"         "+
								processPageFaultTimes
								);
		}
		
		System.out.println("\n");
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("\n");
		
		System.out.println("Clock - Fixed:");
		System.out.println("Process         Name        Turnaround Time     #Faults                       Fault Times");
		for(int i=0;i<Clock.getClockProcesses().size();i++) {
			Process process = Clock.getClockProcesses().get(i);
			
			String processPageFaultTimes;
			String append="";
			for(int j=0;j<process.getClockPageFaultTimes().size();j++) {
				append=append+process.getClockPageFaultTimes().get(j);
				
				if(j!=(process.getClockPageFaultTimes().size()-1)) {
					append=append+", ";
				}
			}
			
			processPageFaultTimes="{"+append+"}";
			
			System.out.println("   "+process.getProcessID()+"        "+
								process.getProcessName()+"         "+
								process.getClockTurnAroundTime()+"               "+
								process.getClockPageFaults()+"         "+
								processPageFaultTimes
								);
		}
		
		

	}

}
