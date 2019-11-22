package data;

import java.util.ArrayList;

public class LRU {

	//store lru frame number history
	private static ArrayList<Integer> frameHistory;
	
	//store process for lru algorithm
	private static ArrayList<Process> lruProcesses;

	//initialize arraylists
	static {
		frameHistory = new ArrayList<>();
		lruProcesses= new ArrayList<>();
	}
	
	//setters and getters
	public static ArrayList<Process> getLruProcesses() {
		return lruProcesses;
	}

	public static void setLruProcesses(ArrayList<Process> lru) {
		lruProcesses = lru;
	}
	
	public static ArrayList<Integer> getFrameHistory() {
		return frameHistory;
	}

	public static void setFrameHistory(ArrayList<Integer> history) {
		frameHistory = history;
	}
	
	//adds a process to the lru algorithm
	public static void addLruProcesses(Process process) {
		lruProcesses.add(process);
	}

	//adds a number to the lru frame number history
	public static void addNumberToFrameHistory(int number) {
		
		//boolean indicator to show if number inputted exists in frame number history
		boolean frameExistsInHistory=false;
		
		//trawls through frame number history
		for(int i=0;i<frameHistory.size();i++) {
			
			Integer frameNumber=frameHistory.get(i);
			
			//if number inputted exists in frame number history
			if(frameNumber==number) {
				
				//set boolean indicator to true
				frameExistsInHistory=true;
			}
		}
		
		//if number inputted doesn't exist in frame history
		if(frameExistsInHistory==false) {
			
		//add number to frame history
		frameHistory.add(number);
		}
	}
	
	//resets lru frame number history to empty
	public static void resetFrameHistoryToEmpty() {
		ArrayList<Integer> newFrameHistory = new ArrayList<>();
		frameHistory = newFrameHistory;
	}
	


	
	
	
}
