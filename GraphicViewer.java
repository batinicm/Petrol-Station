package gasstation;

import java.awt.*;

public class GraphicViewer extends Canvas implements Viewer {

	private Car myCar;
	
	@Override
	public void paint(Graphics g) {
		int x=getWidth();
		int y=getHeight();
		if(myCar!=null) {
		g.setColor(Color.LIGHT_GRAY);
		x=(x*myCar.currFill())/myCar.tankCapacity();	
		g.fillRect(0,0, x, y);
		g.setColor(Color.black);
		g.drawString(Integer.toString(myCar.Id()), 10, 15);
		}
		x=getWidth();
		y=getHeight();
		g.setColor(Color.black);
		g.drawRect(0,0, x, y);
	}



	@Override
	public void view(Car a) {
		myCar=a;
		repaint();
	}

}
