package dragonball.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ChooseWish extends JLabel {
	
	public ChooseWish() {
		
		super(new ImageIcon("1.jpg"));
		setLayout(null);
		setVisible(true);
		setSize(1000,1000);
		validate();
	}
	
	public static void main(String[] args) {
		JFrame x = new JFrame();
		x.setVisible(true);
		x.setSize(1000, 1000);
		x.setVisible(true);
		
	//	Font f1 = new Font("Atmosphere",Font.BOLD,40);
		Font f1 = new Font("Atmosphere",Font.BOLD,48); //option 3

		ChooseWish cw = new ChooseWish();
		x.add(cw);
		
//		JLabel sb = new JLabel(new ImageIcon("SenzuBean.gif"));        
//		sb.setBounds(650,300,500,500); //option 1 with chooseWish
////        sb.setBounds(530,300,500,500); //option 2 with chooseWish2
////       sb.setBounds(650,370,500,500); //option 3
//
//        cw.add(sb);
//        JButton nextWish = new JButton(">>NEXT WISH<<");
//        nextWish.setBounds(1300,500,330,100); //option1
////        nextWish.setBounds(530,300,330,100);
//        nextWish.setForeground(Color.BLACK);
//        nextWish.setSize(300,70);
//        JButton selectWish = new JButton(">>SELECT<<");
// //       selectWish.setBounds(200,500,330,100); //option 1
////        selectWish.setBounds(530,750,330,100); //option 2 with chooseWish2
//        selectWish.setBounds(200,500,330,100); //option 3
//
//        selectWish.setForeground(Color.BLACK);
//        selectWish.setSize(300,70);
//        cw.add(nextWish);
//        cw.add(selectWish);
//        JLabel wishInfo = new JLabel();
//        wishInfo.setText("Will it be 5 SenzuBean(s)?");
//       // wishInfo.setForeground(Color.BLACK);
//        wishInfo.setForeground(Color.WHITE); //option 3
//
//        wishInfo.setFont(f1);
// //       wishInfo.setBounds(660,200,500,50); //option 1
////        wishInfo.setBounds(530,200,500,50); //option 2 with chooseWish2
//          wishInfo.setBounds(660,150,600,50); //option 3
//        
//	
//        cw.add(wishInfo);
//        x.setContentPane(cw);
	
	}

}
