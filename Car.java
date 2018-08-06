package gasstation;

public class Car {
	
	private static int gId=0;
	private int id=++gId;
	private int tankCap,currFill;
	
	
	public Car(int tankCap) {
		this.tankCap = tankCap;
		currFill=(int)(10+Math.random()*21)*tankCap/100;
	}
	
	public int Id() {
		return id;
	}
	public int tankCapacity() {
		return tankCap;
	}
	public int currFill() {
		return currFill;
	}
	
	public synchronized void fillUp(int jos) throws ExcOverflow {
		currFill+=jos;
		if(currFill>tankCap) {
			currFill=tankCap;
			throw new ExcOverflow();
		}
	}
	

}
