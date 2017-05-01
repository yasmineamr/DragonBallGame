package dragonball.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dragonball.model.exceptions.MissingFieldException;
import dragonball.model.exceptions.UnknownAttackTypeException;
import dragonball.model.game.Game;

public class WorldView extends JLabel{
	private JPanel map;
	private JPanel status;
	private BackgroundView [][] array;
	
	
	public WorldView(){
		setLayout(new GridLayout(10,10));
		setVisible(true);
		setSize(1000,700);
		validate();
	}
	
	public BackgroundView[][] getArray() {
		return array;
	}
	
	public void setArray(BackgroundView[][] array) {
		this.array = array;
	}
	public static void main(String[]args){
		WorldView test=new WorldView();
	}
	}