package dragonball.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class invalidLoadView extends JPanel{
	
	public invalidLoadView(){
		setVisible(true);
		Dimension d = new Dimension(1000,1000);
		this.setPreferredSize(d);
		
		
	}
	
	public static void main(String[] args) {
		//Just for testing 
		JFrame x1 = new JFrame();
		x1.setVisible(true);
		x1.setSize(1000, 1000);
		invalidLoadView x = new invalidLoadView();
		x1.add(x);
	}

}
