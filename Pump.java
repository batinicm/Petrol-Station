package gasstation;

public class Pump extends Thread {
	
	private PetrolStation ps;
	private Viewer vwr;
	private boolean running=false;

	public Pump(PetrolStation bp, Viewer pr) {
		this.ps = bp;
		this.vwr = pr;
		start();
	}

	public Pump(PetrolStation bp) {
		this(bp,null);							
	}
	
	public void assignViewer(Viewer p) {
		vwr=p;
	}
	
	public void run() {
		try {
			while(!interrupted()) {
				synchronized (this) {
					while(!running) wait();
				}
				Car a=ps.takeCar();			
				try {
					while(true) {
						sleep(100);
						a.fillUp(1);
						if(vwr!=null) vwr.view(a);
					}
				}
				catch(ExcOverflow g) {ps.done();}		
			}
		}
		catch(InterruptedException i) {}
	}
	
	public void abort() {
		interrupt();
	}
	
	public synchronized void go() {
		running=true;
		notify();
	}

}
