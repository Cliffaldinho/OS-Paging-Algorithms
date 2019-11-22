package data;

import java.util.ArrayList;

//class to store page table details
public class PageTable {

	//page table to store pages
	private static ArrayList<Page> pageTable;
	
	//maximum frames in the page table
	private static int maximumFrames;
	
	public PageTable() {
		
	}
	
	static {
		//initialize page table
		pageTable = new ArrayList<Page>();
	}
	
	//setters and getters
	public static int getMaximumFrames() {
		return maximumFrames;
	}

	public static void setMaximumFrames(int maximum) {
		maximumFrames = maximum;
	}
	
	public static ArrayList<Page> getPageTable() {
		return pageTable;
	}

	public static void setPageTable(ArrayList<Page> pgTable) {
		pageTable = pgTable;
	}
	
	//adds page to page table
	public static void addPageToPageTable(Page page) {
		pageTable.add(page);
	}
	
	//resets page table to empty
	public static void resetPageTableToEmpty() {
		ArrayList<Page> newPageTable = new ArrayList<>();
		pageTable = newPageTable;
	}
}
