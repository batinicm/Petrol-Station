package gasstation;

import java.awt.*;

public class GraphicStation extends PetrolStation {
	
	private Panel pan=new Panel(new BorderLayout());
	private List waiting=new List();
	
	public GraphicStation() {
		pan.add(waiting,BorderLayout.WEST);
		Panel plo=new Panel(new GridLayout(4,1,4,1));
		pan.add(plo, BorderLayout.CENTER);
		
		for(Pump p: pumps) {
			GraphicViewer pr=new GraphicViewer();
			plo.add(pr);
			p.assignViewer(pr);			
		}
	}

	public Panel panel() {
		return pan;
	}

	@Override
	public synchronized void addCar(Car a) throws InterruptedException {
		super.addCar(a);
		if(!running || num==line.length) return;
		waiting.add(Integer.toString(a.Id()) + "\n");
	}

	@Override
	public synchronized Car takeCar() throws InterruptedException {
		Car a=super.takeCar();
		waiting.remove(0);
		return a;
	}

	@Override
	public synchronized void close() {
		super.close();
		waiting.removeAll();
	}

	@Override
	public synchronized void open() {
		super.open();
		waiting.setEnabled(true);
	}
	

}
