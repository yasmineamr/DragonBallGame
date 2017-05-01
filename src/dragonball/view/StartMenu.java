package dragonball.view;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class StartMenu extends JLabel{
	
	public StartMenu() {
		super(new ImageIcon("start menu.jpg"));
		setLayout(null);
		setVisible(true);
		setSize(1000,1000);
		validate();
	}
}
