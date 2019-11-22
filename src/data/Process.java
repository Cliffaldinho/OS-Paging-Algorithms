package data;

import java.util.ArrayList;
import java.util.*;
import java.lang.Integer;

//class for a process
public class Process  {

	//stores the page fault count while algorithm is ongoing
	private int pageFaultCount;
	
	//stores the page fault times while algorithm is ongoing
	private ArrayList<Integer> pageFaultTimes;
	
	//stores the turnaround time of that algorithm
	private int turnaroundTime;
	
	//stores the pages of a process, for the lru algorithm
	private Queue<Page> lruProcessPages;
	


	//stores the pages of a process, for the clock algorithm
	private Queue<Page> clockProcessPages;
	
	//stores the time remaining to swap a page in
	private int swapPageDuration;
	
	//stores the process id
	private int processID;
	
	//stores the process state
	private State processState;
	
	//a boolean indicator to indicate that the a page is being replaced by the clock replacement algorithm
	private boolean clockReplacementInProgress;
	//this indicator is only for clock algorithm, because clock algorithm changes reference bits
	//and since Timer class calls the readPageClock() method each second
	//so this indicator stops it from changing reference bits when page fault occurs
	//lru algorithm doesn't need this indicator because no reference bits changed in lru algorithm
	
	//index of page to be placed by the clock replacement algorithm
	private int clockReplaceIndex;
	//used in conjunction with the above boolean indicator
	
	//stores the process name
	private String processName;
	
	//stores the turnaround time of the clock algorithm, for this process to be outputted
	private int clockTurnAroundTime;
	
	//stores the turnaround time of the lru algorithm, for this process, to be outputted
	private int lruTurnAroundTime;
	
	//stores the amount of page faults of the clock algorithm, for this process, to be outputted
	private int clockPageFaults;
	
	//stores the amount of page faults of the lru algorithm, for this process, to be outputted
	private int lruPageFaults;
	
	//stores the page fault times of the clock algorithm, for this process, to be outputted
	private ArrayList<Integer> clockPageFaultTimes;
	
	//stores the page fault times of the lru algorithm, for this process, to be outputted
	private ArrayList<Integer> lruPageFaultTimes;
	
	//state of the process
	public enum State {
		Ready,
		Blocked;
	}

	//constructor
	public Process() {
		
		//amount of page faults initialized to 0
		pageFaultCount=0;

		//time it takes to swap a page is initialized to 6
		swapPageDuration=6;
		
		//process state set to ready
		processState=State.Ready;
		
		//clock replacement boolean indicator initialized to false
		clockReplacementInProgress=false;
		clockReplaceIndex=-1;
		
		//intialize all lists
		pageFaultTimes= new ArrayList<>();
		lruProcessPages = new LinkedList<>();
		clockProcessPages = new LinkedList<>();
		clockPageFaultTimes = new ArrayList<>();
		lruPageFaultTimes = new ArrayList<>();
		
		
	}
	
	//constructor with process id inputted
	public Process(int pID) {
		
		//initialize process id 
		processID=pID;
		
		//amount of page faults set to 0
		pageFaultCount=0;
		
		//initialize all lists
		pageFaultTimes= new ArrayList<>();
		lruProcessPages = new LinkedList<>();
		clockProcessPages= new LinkedList<>();
		clockPageFaultTimes = new ArrayList<>();
		lruPageFaultTimes = new ArrayList<>();
		
		//amount of time takes to swap a page in initialize to 6
		swapPageDuration=6;
		
		//process state set to ready
		processState=State.Ready;
		
		//clock replacement boolean indicator set to false
		clockReplacementInProgress=false;
		clockReplaceIndex=-1;
		
		//initalize process name
		processName="Process"+processID+".txt";
		
	}
	
	//setters and getters
	public int getClockTurnAroundTime() {
		return clockTurnAroundTime;
	}

	public void setClockTurnAroundTime(int clock) {
		clockTurnAroundTime = clock;
	}

	public int getLruTurnAroundTime() {
		return lruTurnAroundTime;
	}

	public void setLruTurnAroundTime(int lru) {
		lruTurnAroundTime = lru;
	}
	
	public Queue<Page> getLruProcessPages() {
		return lruProcessPages;
	}

	public void setLruProcessPages(Queue<Page> lru) {
		lruProcessPages = lru;
	}

	public Queue<Page> getClockProcessPages() {
		return clockProcessPages;
	}

	public void setClockProcessPages(Queue<Page> clock) {
		clockProcessPages = clock;
	}

	public int getClockPageFaults() {
		return clockPageFaults;
	}

	public void setClockPageFaults(int clockFaults) {
		clockPageFaults = clockFaults;
	}

	public int getLruPageFaults() {
		return lruPageFaults;
	}

	public void setLruPageFaults(int lruFaults) {
		lruPageFaults = lruFaults;
	}

	public ArrayList<Integer> getClockPageFaultTimes() {
		return clockPageFaultTimes;
	}

	public void setClockPageFaultTimes(ArrayList<Integer> clock) {
		clockPageFaultTimes = clock;
	}

	public ArrayList<Integer> getLruPageFaultTimes() {
		return lruPageFaultTimes;
	}

	public void setLruPageFaultTimes(ArrayList<Integer> lru) {
		lruPageFaultTimes = lru;
	}

	public void setProcessName(String name) {
		processName= name;
	}
	
	public String getProcessName() {
		return processName;
	}
	public void setClockReplaceIndex(int index) {
		clockReplaceIndex=index;
	}
	
	public int getClockReplaceIndex() {
		return clockReplaceIndex;
	}
	public void setClockReplacementInProgress(boolean replacement) {
		clockReplacementInProgress=replacement;
	}
	
	public boolean getClockReplacementInProgress() {
		return clockReplacementInProgress;
	}
	public State getProcessState() {
		return processState;
	}

	public void setProcessState(State state) {
		processState = state;
	}
	
	public int getProcessID() {
		return processID;
	}

	public void setProcessID(int id) {
		processID = id;
	}
	
	public int getPageFaultCount() {
		return pageFaultCount;
	}

	public void setPageFaultCount(int faultCount) {
		pageFaultCount = faultCount;
	}

	public ArrayList<Integer> getPageFaultTimes() {
		return pageFaultTimes;
	}

	public void setPageFaultTimes(ArrayList<Integer> faultTimes) {
		pageFaultTimes = faultTimes;
	}
	
	public int getTurnaroundTime() {
		return turnaroundTime;
	}

	public void setTurnaroundTime(int turnaround) {
		turnaroundTime = turnaround;
	}

	//adds a page fault time to the arraylist of times
	//then adds a page fault count too
	public void addPageFaultTimes(int time) {
		pageFaultTimes.add(time);
		pageFaultCount=pageFaultTimes.size();
	}
	
	//initialize process name through process id
	public void setProcessName() {
		processName="Process"+processID+".txt";
	}
	
	//resets the time remaining to swap a page in (to 6)
	public void setSwapPageDurationToDefault() {
		swapPageDuration=6;
	}
	
	//for the clock algorithm process pages. Add a page to the process pages. 
	public void addClockProcessPages(Page page) {
		clockProcessPages.add(page);
	}
	
	//for the lru algorithm process pages. Add a page to the process pages
	public void addLRUProcessPages(Page page) {
		lruProcessPages.add(page);
	}
	
	//initializes lru algorithm process pages through queue input
	public void initializeLRUProcessPages(Queue<Integer> lruPages) {
		
		//while the queue inputted is not empty
		while(!lruPages.isEmpty()) {
			
			//create a new page
			Page page = new Page();
			
			//removes a unit from the queue inputted
			Integer lruNumber = lruPages.poll();
			
			//set the new page frame number to that input
			page.setFrameNumber(lruNumber);
			
			//add this page to the lru algorithm process pages
			lruProcessPages.add(page);
		}
	}
	
	//initialize clock algorithm process pages through queue input
	public void initializeClockProcessPages(Queue<Integer> clockPages) {
		
		//while the queue inputted is not empty
		while(!clockPages.isEmpty()) {
			
			//create a new page
			Page page = new Page();
			
			//removes a unit from the queue inputted
			Integer clockNumber = clockPages.poll();
			
			//set the new page frame number to that input
			page.setFrameNumber(clockNumber);
			
			//add this page to the lru algorithm process pages
			clockProcessPages.add(page);
		}
	}

	public int getSwapPageDuration() {
		return swapPageDuration;
	}

	public void setSwapPageDuration(int swapPage) {
		swapPageDuration = swapPage;
	}

	//clock algorithm
	public void readPageClock() {
		
		//stores the frame number of the page of this process
		int tempFrameNumber;
		
		//boolean indicator to indicate page hit
		boolean pageHit=false;
		//boolean pageFaultFrameExist=false;
		
		//for this process, from the clock algorithm pages queue,
		//gets frame number of the head unit
		tempFrameNumber = clockProcessPages.peek().getFrameNumber();
		
		//if currently there is no clock replacement ongoing
		if(clockReplacementInProgress==false) {
			
			//Page Hit Section-----------------------------------------------------------------------------
			//trawls through page table to see if this process's page already exist in page table
			pageHitLoop:
			for(int i=0;i<PageTable.getPageTable().size();i++) {
				
				int pageFrameNumber=PageTable.getPageTable().get(i).getFrameNumber();
				
				//if this process's page exists in page table already
				if(pageFrameNumber==tempFrameNumber) {
					
					//set the reference bit of this frame in the page table to 1
					PageTable.getPageTable().get(i).setReferenceBit(1);
					
					//remove this page from this process's clock algorithm pages
					clockProcessPages.poll();
					
					//set page hit indicator to true
					pageHit=true;
					
					//ensure time remaining to swap a page is 6
					setSwapPageDurationToDefault();
					
					//break out of loop
					break pageHitLoop;
				}
			}
		
		//Page Replacement Section--------------------------------------------------------------------
		if(pageHit==false&&PageTable.getPageTable().size()==PageTable.getMaximumFrames()) {
			
		//trawls through history of clock algorithm frames for FIFO	
		pageFaultFrameExistLoop:
		for(int i=0;i<Clock.getClockFrameHistory().size();i++) {
			
			//trawls through page table frames
			for(int j=0;j<PageTable.getPageTable().size();j++) {
				
				//gets the clock frame history number
				Integer frameHistoryNumber=Clock.getClockFrameHistory().get(i);
				
				//if the clock frame history number matches to page table frame number
				if(frameHistoryNumber==PageTable.getPageTable().get(j).getFrameNumber()) {
					
					Page page = PageTable.getPageTable().get(j);
					
					//it gets a second chance if reference bit is 1
					if(page.getReferenceBit()==1) {
						
						//reset it's reference bit to 0
						PageTable.getPageTable().get(j).setReferenceBit(0);
					} 
					
					//it doesn't get a second chance if reference bit is 0
					else {
						
						//set the clock replacement boolean indicator to true
						clockReplacementInProgress=true;
						
						//record the index in the page table that is to be replaced
						clockReplaceIndex=j;
						
						//record this page fault time
						addPageFaultTimes(Timer.getDuration());
						
						//break the loop
						break pageFaultFrameExistLoop;
					}
					
					
				}
			}
		}
		
		}
		
		//Page Add Section-------------------------------------------------------------------------
		//if page hit didn't happen, and page replacement didn't happen
		if(pageHit==false&&clockReplacementInProgress==false) {
			
			
			
			//record this page fault time
			if(swapPageDuration==6) {
			
			addPageFaultTimes(Timer.getDuration());
			}
		
			//countdown the 6 seconds it takes to swap in a page
			if(swapPageDuration!=0) {
			
			swapPageDuration--;
			}
			//once ready,
			else {
			
			//get the page from this process's clock algorithm process pages queue
			Page page = clockProcessPages.poll();
			
			//add the page to page table
			PageTable.addPageToPageTable(page);
			
			//add this frame number to clock frame number history
			Clock.addNumberToClockFrameHistory(page.getFrameNumber());
			
			//reset time remaining to swap in a page (to 6)
			setSwapPageDurationToDefault();
			
			}
		
		}
		
		}
		
		//Page Replacement Section continued---------------------------------------------------------
		//if clock replacement boolean indicator shows clock replacement ongoing
		if(clockReplacementInProgress==true) {
			
			
			//count down time remaining to swap a page in
			if(swapPageDuration!=0) {
			
				swapPageDuration--;
			}
			
			//once ready,
			else {
				
				//get the page from the clock algorithm process pages queue
				Page page = clockProcessPages.poll();
				
				//replace the page at the clock replacement index, in the page table
				//with the page from the clock algorithm process pages
				PageTable.getPageTable().set(clockReplaceIndex, page);
				
				//add this frame number to clock frame history
				Clock.addNumberToClockFrameHistory(page.getFrameNumber());
				
				//reset time remaining to swap in a page to 6
				setSwapPageDurationToDefault();
				
				//reset clock replacement boolean indicator to false
				clockReplacementInProgress=false;
				clockReplaceIndex=-1;
			}
			
		}
		
		//Record Details-----------------------------------------------------------------------------
		//once all pages completed for this process,
		//quantum end, record clock details for this process
		//then reset algorithm details
		if(clockProcessPages.isEmpty()) {
			
			//get the turnaround time, and record it for the clock algorithm
			turnaroundTime=Timer.getDuration();
			clockTurnAroundTime=turnaroundTime;
			
			//record clock algorithm page fault times
			clockPageFaultTimes = pageFaultTimes;
			
			//record clock algorithm page fault counts
			clockPageFaults=pageFaultCount;
			
			//reset turnaround time to 0
			turnaroundTime=0;
			
			//reset page fault times to new empty list
			ArrayList<Integer> resetList = new ArrayList<>();
			pageFaultTimes=resetList;
			
			//reset page fault count to 0
			pageFaultCount=0;
			
			//saves clock algorithm details, into the Clock class processes arraylist, to be outputted
			for(int i=0;i<Clock.getClockProcesses().size();i++) {
				if(Clock.getClockProcesses().get(i).getProcessID()==processID) {
					
					Clock.getClockProcesses().get(i).setProcessID(processID);
					Clock.getClockProcesses().get(i).setProcessName(processName);
					Clock.getClockProcesses().get(i).setClockPageFaults(clockPageFaults);
					Clock.getClockProcesses().get(i).setClockPageFaultTimes(clockPageFaultTimes);
					Clock.getClockProcesses().get(i).setClockTurnAroundTime(clockTurnAroundTime);
					
				}
			}
			

			//end this process's time quantum
			Timer.setQuantum(0);
		}
		
		
		
	}
	
	//LRU algorithm
	public void readPageLRU() {
		
		//store frame number for this process's lru process pages,
		int tempFrameNumber;
		
		//boolean indicator for page hit
		boolean pageHit=false;
		
		//boolean indicator for page replacement
		boolean pageFaultFrameExist=false;
		
		//get the frame number for this process's lru process pages
		//from the first unit at the head of queue
		tempFrameNumber = lruProcessPages.peek().getFrameNumber();
		
		//Page Hit Section-------------------------------------------------------------------------
		//trawls through Page Table
		pageHitLoop:
		for(int i=0;i<PageTable.getPageTable().size();i++) {
			int pageFrameNumber=PageTable.getPageTable().get(i).getFrameNumber();
			
			//if lru process pages frame number matches the frame number in page table
			if(pageFrameNumber==tempFrameNumber) {
				
				//remove that page
				lruProcessPages.poll();
				
				//indicate page hit
				pageHit=true;
				
				//ensure time remaining to swap page in is 6
				setSwapPageDurationToDefault();
				
				//break loop
				break pageHitLoop;
			}
		}
		
		//Page Replacement Section--------------------------------------------------------------------
		if(pageHit==false&&PageTable.getPageTable().size()==PageTable.getMaximumFrames()) {
			
			//trawl through lru's frame history (for least recently used)
			pageFaultFrameExistLoop:
			for(int i=0;i<LRU.getFrameHistory().size();i++) {
				
				//trawl through page table
				for(int j=0;j<PageTable.getPageTable().size();j++) {
					
					Integer frameHistoryNumber = LRU.getFrameHistory().get(i);
					
					//if the earliest page in the lru frame history
					//matches a page in the page table
					if(frameHistoryNumber==PageTable.getPageTable().get(j).getFrameNumber()) {
						
						//set boolean indicator for page replacement to true
						pageFaultFrameExist=true;
						
						//record page fault time
						if(swapPageDuration==6) {
							addPageFaultTimes(Timer.getDuration());
							
						}
						
						//count down time it takes to swap page in
						if(swapPageDuration!=0) {
							
							swapPageDuration--;
							break pageFaultFrameExistLoop;
						}
						
						//once ready,
						else {
							
							//get the page from this process's lru process pages
							Page page = lruProcessPages.poll();
							
							//swap it into the page table
							PageTable.getPageTable().set(j, page);
							
							//add this frame to lru frame history
							LRU.addNumberToFrameHistory(page.getFrameNumber());
							
							//reset time remaining to swap page (to 6)
							setSwapPageDurationToDefault();
							
							break pageFaultFrameExistLoop;
						}
						
						
						
					}
				}
			}
		}
		
		//Page Add Section----------------------------------------------------------------------------
		if(pageFaultFrameExist==false&&pageHit==false) {
			
			//record page fault time
			if(swapPageDuration==6) {
				addPageFaultTimes(Timer.getDuration());
			}
			
			//count down time remaining to swap page in
			if(swapPageDuration!=0) {
				swapPageDuration--;
			}
			//once ready
			else {
				
				//get a page from this process's lru process pages queue
				Page page = lruProcessPages.poll();
				
				//add it to page table
				PageTable.addPageToPageTable(page);
				
				//add to frame history
				LRU.addNumberToFrameHistory(page.getFrameNumber());
				
				//reset time it takes to swap page in to 6
				setSwapPageDurationToDefault();
				
			}
			
			
		}
		
		//Record Details------------------------------------------------------------------------------------------
		//once all pages processed, quantum end, record details for this process
		if(lruProcessPages.isEmpty()) {
			
			//record lru turnaround time
			turnaroundTime=Timer.getDuration();
			lruTurnAroundTime=turnaroundTime;
			
			//record lru page fault times
			lruPageFaultTimes = pageFaultTimes;
			
			//record lru page fault count
			lruPageFaults=pageFaultCount;
			
			//reset turnaround time to 0
			turnaroundTime=0;
			
			//reset page fault times list
			ArrayList<Integer> resetList = new ArrayList<>();
			pageFaultTimes=resetList;
			
			//reset page fault count to 0
			pageFaultCount=0;
			
			//record all lru details for this process to LRU class's array of processes
			for(int i=0;i<LRU.getLruProcesses().size();i++) {
				if(LRU.getLruProcesses().get(i).getProcessID()==processID) {
					LRU.getLruProcesses().get(i).setProcessID(processID);
					LRU.getLruProcesses().get(i).setProcessName(processName);
					LRU.getLruProcesses().get(i).setLruPageFaults(lruPageFaults);
					LRU.getLruProcesses().get(i).setLruPageFaultTimes(lruPageFaultTimes);
					LRU.getLruProcesses().get(i).setLruTurnAroundTime(lruTurnAroundTime);
				}
			}
			
			//end quantum for this process
			Timer.setQuantum(0);
		}
		
	
		
	}
}
