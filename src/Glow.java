import java.awt.Color;
import java.awt.event.MouseListener;

import javax.swing.*;

import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class Glow {

	private static JFrame f = new JFrame("Glowing Button");
	private JButton b = new JButton("Glow");

	Glow(){

        f.setSize(600,600);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(null);
        b.setBounds(200,150,200,200);
        b.setOpaque(true);
        f.add(b);
        f.setVisible(true);
        
//        b.addMouseListener(new java.awt.event.MouseAdapter());
    }

	public static void main(String args[]) {
		
//		new Glow();
//		JLabel j = new JLabel("yasmine");
//		j.setForeground(Color.BLUE);
//	    j.setBounds(10,10,100,100);
//	    j.setVisible(true);
//	    f.add(j);
//	    
//		j.addMouseListener(new java.awt.event.MouseAdapter() {
//		    public void mouseEntered(java.awt.event.MouseEvent evt) {
//	//	        j.setBackground(Color.GREEN);
//		        j.setText("sdsads");        
//		    }
//
//		    public void mouseExited(java.awt.event.MouseEvent evt) {
//		        j.setBackground(UIManager.getColor("control"));
//		        j.setText("yasmine");
//		    }
//		});	    
//	    
//		b.addMouseListener(new java.awt.event.MouseAdapter() {
//		    public void mouseEntered(java.awt.event.MouseEvent evt) {
//		        b.setBackground(Color.GREEN);
//		        b.setText("yasmine");
//		        j.setVisible(false);
//		    }
//
//		    public void mouseExited(java.awt.event.MouseEvent evt) {
//		        b.setBackground(UIManager.getColor("control"));
//		        b.setText("Glow");
//		        j.setVisible(true);
//		    }
//		});
		
		
		
		
		

		try 
		{
			UIManager.setLookAndFeel(new NimbusLookAndFeel());
			SwingUtilities.updateComponentTreeUI(f);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		new Glow();

	}


	}
