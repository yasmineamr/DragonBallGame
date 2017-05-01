package dragonball.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import dragonball.model.dragon.DragonWish;

public class DragonView extends JLabel{
	
	public DragonView() {
		super(new ImageIcon("eye.jpg"));
		setLayout(null);
		setVisible(true);
		setSize(1000,1000);
		validate();
		JLabel j = new JLabel(new ImageIcon("db1.gif"));
		j.setBounds(900, 500, 150, 150);
		add(j);
//		JTextArea d = new JTextArea("CHOOSE WISELY");
//		d.setSelectedTextColor(Color.WHITE);
	}

		
		public static void main(String[]args) {
		JFrame x = new JFrame();		
		x.setVisible(true);
		x.setSize(1000, 1000);
    	DragonView d = new DragonView();
    	x.add(d);
//    	JLabel d1 = new JLabel();
//		d1.setText("CHOOSE WISELY..");
//		Font f1 = new Font("Atmosphere",Font.BOLD,40);
//        d1.setFont(f1);
//        d1.setForeground(Color.WHITE);
//        d1.setBounds(200,200,500,27);
//        d.add(d1);
//        JButton dd = new JButton();
//        dd.setText(">>CHOOSE WISH<<");
//        dd.setFont(new Font("Atmosphere",Font.BOLD,25));
//        dd.setBounds(230,400,300,50);
//        dd.setForeground(Color.BLACK);
////        dd.setSize(300,70);
//        d.add(dd);
//        x.add(d);
        
        
        
	}
		
	
	
}
