package gasstation;

public class PetrolStation {
	
	protected Pump[] pumps=new Pump[4];
	protected Car[] line=new Car[20];
	protected boolean running=false;
	private boolean filling=false;
	protected int num;
	private int head,tail;
	
	public PetrolStation() {
		for(int i=0; i<4; i++)
			pumps[i]=new Pump(this);
	}
	
	public synchronized void open() {
		running=true;
		for(Pump p: pumps) p.go();
	}
	
	public synchronized void close() {
		running=false;	
			try {
				while(filling==false)wait();
			} catch (InterruptedException e) {}
	}
	
	public synchronized void abort() {
		for(Pump p: pumps) p.abort();
	}
	
	public synchronized void addCar(Car a) throws InterruptedException {
		if(!running || num==line.length) return;
		line[tail]=a;
		tail=(tail+1)%line.length;
		num++;
		notifyAll();
	}
	
	public synchronized Car takeCar() throws InterruptedException {
		while(num==0) wait();
		Car a=line[head];
		head=(head+1)%line.length;
		num--;
		filling=false;
		return a;
	}
	
	public synchronized void done() {
		filling=true;
		notify();
	}

}
