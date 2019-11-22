package data;

//class to store page details
public class Page {

	//frame number for page table and process pages
	private int frameNumber;
	
	//reference bit for Clock algorithm
	private int referenceBit;
	
	public Page() {
	
		//initialize reference bit to 0
		referenceBit=0;
	}
	
	//setters and getters
	public int getFrameNumber() {
		return frameNumber;
	}
	public void setFrameNumber(int frame) {
		frameNumber = frame;
	}
	
	public void setReferenceBit(int ref) {
		referenceBit=ref;
	}
	
	public int getReferenceBit() {
		return referenceBit;
	}
	
}
