package dragonball.view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class BattleView extends JPanel {
	
	private BufferedImage backgroundImage;
	
	public BattleView (String name) throws IOException {
		backgroundImage = ImageIO.read(new File(name));
		backgroundImage = resizeImage(backgroundImage, 1000,1000);
		setLayout(null);
		setVisible(true);
		setSize(1000,1000);
	}
	
	public static BufferedImage resizeImage(BufferedImage image, int width, int height) {
		
		BufferedImage image1 = new BufferedImage(width, height,image.getType());
		Graphics2D g = image1.createGraphics();
		g.drawImage(image, 0, 0, width, height, null);
		return image1;
	}
	
	 public BufferedImage getBackgroundImage() {
		return backgroundImage;
	}

	public void setBackgroundImage(BufferedImage backgroundImage) {
		this.backgroundImage = backgroundImage;
	}

	public void paintComponent(Graphics g) {
		    super.paintComponent(g);
		    g.drawImage(backgroundImage, 0, 0, null);
	 }
	
	public BattleView() {
		setVisible(true);
		setSize(1000,1000);
	}
	
	public static void main(String[] args) throws IOException {
		JFrame x = new JFrame();
		x.setSize(1000,1000);
		x.setVisible(true);
		x.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BattleView b = new BattleView("battle.png");
		x.add(b);	
	}

}
