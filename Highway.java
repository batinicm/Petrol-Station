package gasstation;

public class Highway extends Thread {

	private PetrolStation petrolStation;

	public Highway(PetrolStation petrolStation) {
		this.petrolStation = petrolStation;
		start();
	}
	
	public void run() {
		try {
			while(!interrupted()) {
				sleep((long)(500 + Math.random()*500));
				petrolStation.addCar(new Car(50));
			}
		}
		catch(InterruptedException i) {}
	}
	
	public synchronized void abort() {						//razmisli da li treba sync
		petrolStation.abort();
		interrupt();
	}
	
}
