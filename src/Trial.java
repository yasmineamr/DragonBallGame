import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.Timer;

import dragonball.model.battle.Battle;
import dragonball.model.character.fighter.Fighter;
import dragonball.view.BackgroundView;
import dragonball.view.BattleView;

public class Trial {
	static BackgroundView actionView;
	static BattleView battleView;
	static Battle battle;
	JTextArea foeInfo;
	JTextArea playerInfo;
	static JButton w1;
	static JButton w2;
	static JButton w3;
	static JButton w4;
	
	public static void main(String[] args) {
		final JFrame j = new JFrame();
		j.setVisible(true);
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.setSize(1000, 1000);
		w1 = new JButton("Super Big Bang Kamehameha");
		Font f = new Font("Atmpshpere",Font.PLAIN, 13);
		w1.setBounds(0,0 ,300, 40);
		j.add(w1);
		
//		j.addWindowListener(new java.awt.event.WindowAdapter() {
//		    @Override
//		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
//		        if (JOptionPane.showConfirmDialog(j, 
//		            "Are you sure to close this window?", "Really Closing?", 
//		            JOptionPane.YES_NO_OPTION,
//		            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
//		            System.exit(0);
//		        }
//		    }
//		});
		
//		   final JLabel label = new JLabel("m");
//		   
//		   j.add(label);
//		   int delay = 3000; //milliseconds
//		   ActionListener taskPerformer = new ActionListener() {
//		       public void actionPerformed(ActionEvent evt) {
//		           label.setText("my");
//		       }
//		   };
//		   new Timer(delay, taskPerformer).start();
		   
//		   delay = 3000; //milliseconds
//		   taskPerformer = new ActionListener() {
//		       public void actionPerformed(ActionEvent evt) {
//		           label.setText("my l");
//		       }
//		   };
//		   new Timer(delay, taskPerformer).start();
//		   
//		   
//		   delay = 4000; //milliseconds
//		   taskPerformer = new ActionListener() {
//		       public void actionPerformed(ActionEvent evt) {
//		           label.setText("my la");
//		       }
//		   };
//		   new Timer(delay, taskPerformer).start();
		   
		   

//		try {
//			battleView = new BattleView("battlemode.jpg");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		Fighter y = ((Fighter)battle.getFoe());
//		JTextArea foeInfo = new JTextArea("Name: "+y.getName()+"\n"+"Level: "+y.getLevel()+"\n"+"Health Points: "+y.getHealthPoints()+"\n"+"Max Health Points: "+y.getMaxHealthPoints()+"\n"+"Ki: "+y.getKi()+"\n"+"Max Ki: "+y.getMaxKi()+"\n"+"Stamina: "+y.getStamina()+"\n"+"Max Stamina: "+y.getMaxStamina());
//		foeInfo.setOpaque(false);
//		foeInfo.setBounds(10, 10, 200, 200);
//		foeInfo.setVisible(true);
//		foeInfo.setEditable(false);
//		battleView.add(foeInfo);
//		Fighter x = ((Fighter)battle.getMe());
//		JTextArea playerInfo = new JTextArea("Name: "+x.getName()+"\n"+"Level: "+x.getLevel()+"\n"+"Health Points: "+x.getHealthPoints()+"\n"+"Max Health Points: "+x.getMaxHealthPoints()+"\n"+"Ki: "+x.getKi()+"\n"+"Max Ki: "+x.getMaxKi()+"\n"+"Stamina: "+x.getStamina()+"\n"+"Max Stamina: "+x.getMaxStamina());
//		playerInfo.setOpaque(false);
//		playerInfo.setBounds(800, 10, 200, 200);
//		playerInfo.setVisible(true);
//		playerInfo.setEditable(false);
//		battleView.add(playerInfo);
	}
	
	public void updateInfo() {
		Fighter y = ((Fighter)battle.getFoe());
		foeInfo.setText("Name: "+y.getName()+"\n"+"Level: "+y.getLevel()+"\n"+"Health Points: "+y.getHealthPoints()+"\n"+"Max Health Points: "+y.getMaxHealthPoints()+"\n"+"Ki: "+y.getKi()+"\n"+"Max Ki: "+y.getMaxKi()+"\n"+"Stamina: "+y.getStamina()+"\n"+"Max Stamina: "+y.getMaxStamina());
	
		Fighter x = ((Fighter)battle.getMe());
		foeInfo.setText("Name: "+x.getName()+"\n"+"Level: "+x.getLevel()+"\n"+"Health Points: "+x.getHealthPoints()+"\n"+"Max Health Points: "+x.getMaxHealthPoints()+"\n"+"Ki: "+x.getKi()+"\n"+"Max Ki: "+x.getMaxKi()+"\n"+"Stamina: "+x.getStamina()+"\n"+"Max Stamina: "+x.getMaxStamina());
	}
	
	public void chooseAction() {
		try 
		{
			actionView = new BackgroundView("battlemode.jpg",1000,1000);
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//battle1.setVisible(false);
		
		Font f = new Font("Atmosphere",Font.PLAIN,40);
		JButton block = new JButton("BLOCK");
		block.setBounds(130, 500, 200, 100);
		block.setFont(f);
//		block.addActionListener(this);
		
		JButton use = new JButton("USE");
		use.setBounds(380, 500, 200, 100);
		use.setFont(f);
//		use.addActionListener(this);
		
		JButton attack = new JButton("ATTACK");
		attack.setBounds(630, 500, 200, 100);
		attack.setFont(f);
//		attack.addActionListener(this);
		
		actionView.add(block);
		actionView.add(use);
		actionView.add(attack);
//		actionView.add(fInfo);
//		actionView.add(pInfo);
	}
	
	
	
}
