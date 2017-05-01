package dragonball.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class BackgroundView extends JLabel {
	
	private BufferedImage backgroundImage;
	
	public BackgroundView (String name,int width,int height) throws IOException {
		backgroundImage = ImageIO.read(new File(name));
		backgroundImage = resizeImage(backgroundImage,width,height);
		setVisible(true);
		setSize(width,height);
		setLayout(null);
		//2560 × 1600
	}
	
	public BackgroundView(BufferedImage image,int width,int height){
		this.backgroundImage=image;
		this.setSize(width, height);
		setVisible(true);
		setLayout(new FlowLayout());
		
		
	}
	
	public static BufferedImage resizeImage(BufferedImage image, int width, int height) {
		
		BufferedImage image1 = new BufferedImage(width, height,image.getType());
		Graphics2D g = image1.createGraphics();
		g.drawImage(image, 0, 0, width, height, null);
		return image1;
	}
	
	public void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    g.drawImage(backgroundImage, 0, 0, null);
	}
	// (If you have any trouble seeing the image, you might need to call setOpaque(false)
	// on some other components so that you can see through to the background.)
	public static void main(String[] args) throws IOException {

		JFrame x = new JFrame();
		x.setLayout(new BorderLayout());
		x.setSize(1000,1000);
		x.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		x.setVisible(true);
		BackgroundView b = new BackgroundView("battle.png",1000,1000);
		x.setContentPane(b);
		b.changeBackground("Player.jpg");

//		JLabel l1=new JLabel("Here is a button");
//		JButton b1=new JButton("I am a button");
//	    b.add(l1);
//	    b.add(b1);
//	    JTextArea c = new JTextArea("Ama nshoof!");
//	    b.add(c);
		
//		BackgroundView bck = null;
//		try{
//			mainFrame.setLayout(new BorderLayout());
//			bck = new BackgroundView("battle.png");
//			mainFrame.add(bck);
//			bck.setLayout(new FlowLayout());
//		}catch(IOException exp)
//		{
//			System.out.println("no background");
//		}
	}
	
//	mainFrame.validate();
//	game.getPlayer().setName(area2.getText());
//	if(info[2].equals("Earthling")){
//		Earthling a = new Earthling(info[1]);
//	}
//	if(info[2].equals("Frieza")){
//		Frieza a = new Frieza(info[1]);
//		game.getPlayer().setActiveFighter(a);
//	}
//	if(info[2].equals("Majin")){
//		Majin a = new Majin(info[1]);
//		game.getPlayer().setActiveFighter(a);
//	}
//	if(info[2].equals("Namekian")){
//		Namekian a = new Namekian(info[1]);
//		game.getPlayer().setActiveFighter(a);
//	}
//	if(info[2].equals("Saiyan")){
//		Saiyan a = new Saiyan(info[1]);
//		game.getPlayer().setActiveFighter(a);
//	}
	public void changeBackground(String filePath){
		try {
			backgroundImage = ImageIO.read(new File(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		backgroundImage = resizeImage(backgroundImage,100,100);
		revalidate();
		
	}
	

}
