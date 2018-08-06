package gasstation;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Program extends Frame {
	
	private GraphicStation bp=new GraphicStation();
	private Highway a=new Highway(bp);
	private Button open=new Button("Open"),
			close=new Button("Close");
	
	public Program() {
		super("Petrol Station");
		setSize(300, 200);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				new Stop().start();
			}
		});
		pop();
		endis(true);
		setVisible(true);
	}
	
	private class Stop extends Thread {
		@Override
		public void run() {
			a.abort();
			dispose();
		}
	}
	
	private class Close extends Thread {

		@Override
		public void run() {
			endis(true);
			bp.close();
		}
		
	}
	
	private void endis(boolean go) {
		open.setEnabled(go);
		close.setEnabled(!go);
	}
	
	private void pop() {
		add(bp.panel(),BorderLayout.CENTER);
		
		Panel plo=new Panel();
		add(plo,BorderLayout.SOUTH);
		
		plo.setBackground(Color.DARK_GRAY);
		
		plo.add(open);
		open.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
					endis(false);
					bp.open();
			}
		});
		plo.add(close);
		close.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new Close().start();
			}
		});
		
		
		}

	public static void main(String[] args) {
		new Program();
	}

}
