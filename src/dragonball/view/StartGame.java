package dragonball.view;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class StartGame extends JLabel {
	

	public StartGame() {
		
		super(new ImageIcon("wallpaper.jpg"));
		setLayout(null);
		setVisible(true);
		setSize(1000,1000);
		validate();
	}
}
