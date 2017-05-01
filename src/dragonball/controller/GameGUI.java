package dragonball.controller;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.Timer;
import javax.swing.UIManager;

import dragonball.model.attack.PhysicalAttack;
import dragonball.model.attack.SuperAttack;
import dragonball.model.attack.SuperSaiyan;
import dragonball.model.attack.UltimateAttack;
import dragonball.model.battle.Battle;
import dragonball.model.battle.BattleEvent;
import dragonball.model.battle.BattleEventType;
import dragonball.model.cell.Collectible;
import dragonball.model.character.fighter.Fighter;
import dragonball.model.character.fighter.NonPlayableFighter;
import dragonball.model.character.fighter.Saiyan;
import dragonball.model.dragon.Dragon;
import dragonball.model.dragon.DragonWish;
import dragonball.model.dragon.DragonWishType;
import dragonball.model.exceptions.DuplicateAttackException;
import dragonball.model.exceptions.MapIndexOutOfBoundsException;
import dragonball.model.exceptions.MaximumAttacksLearnedException;
import dragonball.model.exceptions.MissingFieldException;
import dragonball.model.exceptions.NotASaiyanException;
import dragonball.model.exceptions.NotEnoughAbilityPointsException;
import dragonball.model.exceptions.NotEnoughKiException;
import dragonball.model.exceptions.NotEnoughSenzuBeansException;
import dragonball.model.exceptions.UnknownAttackTypeException;
import dragonball.model.game.Game;
import dragonball.model.game.GameListener;
import dragonball.view.BackgroundView;
import dragonball.view.BattleView;
import dragonball.view.ChooseWish;
import dragonball.view.DragonView;
import dragonball.view.GameView;
import dragonball.view.MainFrame;
import dragonball.view.StartGame;
import dragonball.view.StartMenu;
import dragonball.view.WorldView;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;


public class GameGUI implements GameListener, ActionListener, KeyListener{

	private transient ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	
	private transient GameView gameView;
	private transient Game game;
	private transient WorldView worldView;
	private transient MainFrame mainFrame;
	private transient BattleView battleView;
	JTextArea area2;
	JTextArea areaF2;

	transient StartMenu startMenu;
	transient StartGame startGame;
	JButton newGame;
	JButton loadGame;
	JButton startBattle;
	JButton newGame2;
	transient BackgroundView sideInfo;

	Object selected;


	final JLabel label1 = new JLabel("GAME OVER");

	transient JLabel[] f;
	JButton next;
	int ind;
	JLabel[] info;
	char[] fighters;
	JButton start;
	JButton options;
	JButton exit;

	JButton save;
	JButton switchFighter;
	JButton upgrade;
	JButton createFighter;
	JButton assignAttack;
	JButton assignUAttack;
	JButton back;
	JButton backO;
	JLabel playerInfo;
	JLabel fighterInfo;
	JLabel transformed;

	JLabel saved;
	JComboBox nsattacks; 
	JComboBox osattacks;
	JComboBox nuattacks;
	JComboBox ouattacks;
	String typeAttack;

	JComboBox typeF;
	JTextArea tn;
	JButton backF;

	JComboBox sf;
	JButton backSF;

	JButton yes;
	JButton no;
	
	JButton rtrn2;
	
	int level;

	JLabel pl = new JLabel();
	JLabel al = new JLabel();
	JLabel bl = new JLabel();
	JLabel fs = new JLabel();
	JLabel fp = new JLabel();
	
	String blocked = "";

	JComboBox uf;
	JButton backUF;
	JButton ok;
	JFrame exception;

	transient BackgroundView optionsView;
	transient BackgroundView attacksView;
	transient BackgroundView fighterView;
	transient BackgroundView upgradeView;
	transient BackgroundView switchView;

	transient BackgroundView battle1;
	transient BackgroundView actionView;

	JButton block;
	JButton use;
	JButton attack;

	JTextArea pInfo;
	JTextArea fInfo;
	JFrame close;
	
	Battle battle;
	JLabel fi;
	JLabel pi;

	JButton superA = new JButton("Super");
	JButton ultimateA = new JButton("Ultimate");
	JButton physicalA = new JButton("Physical");

	JButton s1 = new JButton();
	JButton s2 = new JButton();
	JButton s3 = new JButton();
	JButton s4 = new JButton();

	JButton u1 = new JButton();
	JButton u2 = new JButton();

	private transient DragonView dragonView;
	private transient ChooseWish cw;
	private JButton chooseWish;
	private JButton nextWish;
	private JButton selectWish;
	private int indW;
	private ArrayList<JLabel> wishesOptions;
	private ArrayList<JLabel> wishInfo;
	private Dragon dragon;
	private DragonWish wish;

	JButton w1;
	JButton w2;
	JButton w3;
	JButton w4;
	JLabel l1;
	JLabel l2;
	JLabel l3;
	JLabel l4;

	JLabel g1;

	JButton newGame3;

	JButton instructions; 
	JButton aboutGame;
	JButton aboutFighters;
	JButton backI;
	JButton next2;
	JTextArea DBText;
	JTextArea [] aboutF;
	JTextArea ins;

	JButton exitwindow;
	JLabel foesturn;
	JLabel yourturn;
	transient JLabel fl;
	transient JLabel pla;
	transient JLabel foeplay;
	int i;
	int delay;
	JButton rtrn;
	transient JLabel blocking;
	transient JLabel attacking;

	public GameGUI() throws MissingFieldException, UnknownAttackTypeException {
		game = new Game();
		game.setListener(this);
		mainFrame = new MainFrame();
		gameView = new GameView();

		startMenu = new StartMenu();
		newGame = new JButton("NEW GAME");
		Font f = new Font("Atmosphere",Font.BOLD,40);
		newGame.setBounds(700, 300, 300, 50);
		newGame.setFont(f);
		newGame.setBorder(BorderFactory.createEmptyBorder());
		newGame.setContentAreaFilled(false);
		loadGame = new JButton("LOAD GAME");
		Font f1 = new Font("Atmpsphere",Font.BOLD,40);
		loadGame.setBounds(700, 350, 300, 50);
		loadGame.setFont(f);
		loadGame.setBorder(BorderFactory.createEmptyBorder());
		loadGame.setContentAreaFilled(false);

		instructions = new JButton("ABOUT"); 
		instructions.setFont(f);
		instructions.setBounds(700,400,300,50);
		instructions.setBorder(BorderFactory.createEmptyBorder()); 
		instructions.setContentAreaFilled(false);

		exitwindow = new JButton("Exit");
		exitwindow.setFont(f);
		exitwindow.setBounds(700, 450, 300, 50);
		exitwindow.setBorder(BorderFactory.createEmptyBorder());
		exitwindow.setContentAreaFilled(false);

		newGame.addActionListener(this);
		loadGame.addActionListener(this);
		instructions.addActionListener(this);
		exitwindow.addActionListener(this);
		startMenu.add(newGame);
		startMenu.add(loadGame);
		startMenu.add(instructions);
		mainFrame.add(startMenu);
		mainFrame.repaint();
		mainFrame.revalidate();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JComboBox) {
			selected = ((JComboBox)e.getSource()).getSelectedItem();	
		}
		else {
			final JButton btn = (JButton) e.getSource();
			Toolkit tk = Toolkit.getDefaultToolkit();
			tk.beep();

			if(btn == exitwindow)
			{
				mainFrame.dispose();
			}

			if(btn == instructions){ 
				startGame = new StartGame();
				aboutFighters = new JButton(); 
				aboutGame = new JButton();
				backI = new JButton();

				aboutFighters.setBounds(250,600,200,50);
				aboutGame.setBounds(500,600,150,50);
				backI.setBounds(700,600,100,50);

				aboutFighters.setText("About Fighters");
				aboutGame.setText("HOW TO PLAY");
				backI.setText("BACK");

				aboutFighters.setBorder(BorderFactory.createEmptyBorder());
				aboutGame.setBorder(BorderFactory.createEmptyBorder()); 
				backI.setBorder(BorderFactory.createEmptyBorder()); 


				aboutGame.addActionListener(this);
				aboutFighters.addActionListener(this);
				backI.addActionListener(this);

				startGame.add(backI);
				startGame.add(aboutFighters);
				startGame.add(aboutGame);

				DBText = new JTextArea("Long ago, legends were told that 7 magical orbs are scattered around the earth."+"\n"+ "Anyone brave enough to travel the earth looking for them,"+"\n"+ " will have his bravery rewarded by obtaining anything he could ever wish for."+"\n"+ " Recently, these magical orbs were found to be more than just old legends"+"\n"+ " and some of them were actually located."+"\n"+ " It was also revealed that these orbs are called the dragon balls and were created by Kami,"+"\n"+ " The Guardian of Earth, who came from the planet Namek."+"\n"+ " When you gather all the seven dragon balls together, The dragon will appear"+"\n"+ " and will grant any wish made by the one who gathered the"+"\n"+ " Dragon Balls. The main character of the dragon ball"+"\n"+ " universe is Goku, who had multiple adventures searching for"+"\n"+ " the dragon balls with his friends saving the world from "+"\n"+ "numerous threats in the process. Goku was later "+"\n"+ "revealed to be a Saiyan sent from planet Vegeta as a baby"+"\n"+ " to earth in order to destroy it. Instead, Goku hit his"+"\n"+ " head and loses his memory resulting in him being more"+"\n"+ " peaceful and saving the planet instead "+"\n"+ "of destroying it.");
				DBText.setBounds(100,100,800,500);
				DBText.setEditable(false);
				DBText.setOpaque(false);
				Font f = new Font("Atmosphere",Font.BOLD,20);
				DBText.setFont(new Font("Serif",Font.PLAIN,25));
				DBText.setForeground(Color.WHITE);
				DBText.setVisible(true);
				startGame.add(DBText);
				aboutFighters.setFont(f);
				aboutGame.setFont(f);
				backI.setFont(f);
				aboutFighters.setForeground(Color.WHITE);
				aboutGame.setForeground(Color.WHITE);
				backI.setForeground(Color.WHITE);
				mainFrame.setContentPane(startGame);
				mainFrame.repaint();
				mainFrame.revalidate();
			}
			if(btn == backI) 
			{
				mainFrame.setContentPane(startMenu);
			}

			if(btn==aboutGame){
				ins = new JTextArea();
				ins.setText("This is a brief description of how to play the game."+"\n"+"You start by choosing your player’s name, then create a fighter by choosing its name and race."+"\n"+"There is a 10x10 map with the Boss(the strongest foe) in the top left cell. There are 15 weak foes randomly hidden in the cells."+"\n"+"There is also, one Dragon Ball and 3-5 Senzu Beans randomly placed."+"\n"+"You should navigate through the map and try to collect the Senzu Beans and Dragon Ball before meeting the Boss. "+"\n"+"Once you meet a foe, the game will automatically switch to Battle mode. In each turn you choose one of three moves."+"\n"+"You can either block the foe’s next attack, or you can use a Senzu Bean to restore your health points and stamina to maximum."+"\n"+"Or you can attack the foe. There are three types of attacks; physical attack, super attack and physical attack."+"\n"+" Once the battle ends, you will receive a message notifying you with the winner of the game."+"\n"+"If you won you will continue navigating through the map,"+"\n"+" unless the you won the boss, you will level up and a new map will be generated. If you lost a new map will be generated as well."+"\n"+"When you collect 7 Dragon Balls the game will switch to Dragon mode automatically."+"\n"+"A random dragon will be chosen for you. Each dragon has maximum four wishes, where you can choose one of these wishes.");
				DBText.setVisible(false);
				ins.setBounds(30, 100, 1000, 400);
				ins.setOpaque(false);
				ins.setEditable(false);
				Font f = new Font("Serif", Font.PLAIN, 18);
				ins.setFont(f);
				ins.setForeground(Color.WHITE);
				startGame.removeAll();
				startGame.add(aboutFighters);
				startGame.add(aboutGame);
				startGame.add(backI);
				startGame.add(ins);
				mainFrame.repaint();
				mainFrame.revalidate();

			}

			if(btn == aboutFighters) { 

				DBText.setVisible(false);
				startGame.removeAll();
				startGame.add(aboutFighters);
				startGame.add(aboutGame);
				startGame.add(backI);
				createFighters();
				aboutFighters();
				next2 = new JButton("Next");
				Font font = new Font("Atmosphere", Font.BOLD,25);
				next2.setFont(font);
				next2.setBounds(700,400,100, 50);
				next2.setVisible(true);
				startGame.add(next2);
				ind=0;
				next2.addActionListener(this);
				mainFrame.repaint();
				mainFrame.revalidate();


			}
			if(btn == next2) { 

				if(ind==0) {
					f[0].setVisible(false);
					f[1].setVisible(true);
					f[2].setVisible(false);
					f[3].setVisible(false);
					f[4].setVisible(false);

					aboutF[0].setVisible(false);
					aboutF[1].setVisible(true);
					aboutF[2].setVisible(false);
					aboutF[3].setVisible(false);
					aboutF[4].setVisible(false);

					ind++;
				}
				else if(ind==1) {
					f[0].setVisible(false);
					f[1].setVisible(false);
					f[2].setVisible(true);
					f[3].setVisible(false);
					f[4].setVisible(false);

					aboutF[0].setVisible(false);
					aboutF[1].setVisible(false);
					aboutF[2].setVisible(true);
					aboutF[3].setVisible(false);
					aboutF[4].setVisible(false);

					ind++;
				}
				else if(ind==2) {
					f[0].setVisible(false);
					f[1].setVisible(false);
					f[2].setVisible(false);
					f[3].setVisible(true);
					f[4].setVisible(false);

					aboutF[0].setVisible(false);
					aboutF[1].setVisible(false);
					aboutF[2].setVisible(false);
					aboutF[3].setVisible(true);
					aboutF[4].setVisible(false);

					ind++;
				}
				else if(ind==3) {
					f[0].setVisible(false);
					f[1].setVisible(false);
					f[2].setVisible(false);
					f[3].setVisible(false);
					f[4].setVisible(true);

					aboutF[0].setVisible(false);
					aboutF[1].setVisible(false);
					aboutF[2].setVisible(false);
					aboutF[3].setVisible(false);
					aboutF[4].setVisible(true);

					ind++;
				}
				else if(ind==4) {
					f[0].setVisible(true);
					f[1].setVisible(false);
					f[2].setVisible(false);
					f[3].setVisible(false);
					f[4].setVisible(false);

					aboutF[0].setVisible(true);
					aboutF[1].setVisible(false);
					aboutF[2].setVisible(false);
					aboutF[3].setVisible(false);
					aboutF[4].setVisible(false);
					ind=0;
				}
			}
			if(btn==newGame3) {
				try {
					sideInfo = new BackgroundView("grey1.jpg",200,1000);
				} catch (IOException e1) {

				}
				
				if(startGame!=null) startGame.removeAll();
				if(area2!=null) game.getPlayer().setName(area2.getText());
				if(areaF2!=null) game.getPlayer().createFighter(fighters[ind], areaF2.getText());
				createGrid();
				createInfo();
				newGame3.setVisible(false);
				label1.setVisible(false);
				sideInfo.setBounds(800,0,200,1000);
				worldView.addKeyListener(this);
				worldView.setBounds(0,0,800,710);
				mainFrame.add(worldView);
				mainFrame.add(sideInfo);
				worldView.setFocusable(true);
				worldView.requestFocusInWindow();
				worldView.repaint();
				worldView.revalidate();
				mainFrame.repaint();
				mainFrame.revalidate();
//				System.out.println(game.getWorld().toString());

			}

			if(btn==newGame || btn==newGame2) {
				startGame = new StartGame();
				createFighters();
				next = new JButton("NEXT");
				Font font = new Font("Atmosphere", Font.BOLD,25);
				next.setFont(font);
				next.setBounds(720,400,100, 50);
				next.setVisible(true);
				startGame.add(next);
				ind=0;
				next.addActionListener(this);
				JLabel area1 = new JLabel("Enter your name");
				Font f = new Font("Atmosphere",Font.BOLD,25);
				area1.setForeground(Color.WHITE);
				area1.setFont(f);
				area1.setBounds(30, 10, 300, 100);
				area2 = new JTextArea();
				area2.setBounds(30, 90, 200, 20);
				area2.setEditable(true);
				area2.setVisible(true);
				area2.setBackground(Color.WHITE);
				startGame.add(area1);
				startGame.add(area2);

				JLabel areaF = new JLabel("Enter your active fighter's name");
				Font f1 = new Font("Atmosphere",Font.BOLD,25);
				areaF.setForeground(Color.WHITE);
				areaF.setFont(f1);
				areaF.setBounds(450, 10, 500, 100);
				areaF2 = new JTextArea();
				areaF2.setBounds(450,90,200,20);
				areaF2.setEditable(true);
				areaF2.setVisible(true);
				areaF2.setBackground(Color.WHITE);
				startGame.add(areaF);
				startGame.add(areaF2);
				fightersInfo();

				start = new JButton("START");
				start.setFont(font);
				start.setBounds(830, 600, 150, 50);
				start.setVisible(true);
				startGame.add(start);
				mainFrame.add(startGame);
				mainFrame.setContentPane(startGame);
				start.addActionListener(this);
				mainFrame.repaint();
				mainFrame.validate();


			}

			if(btn==nextWish) {

				if (indW == wishesOptions.size())
					indW = 0;

				cw.add(wishesOptions.get(indW));
				wishesOptions.get(indW).setVisible(true);

				for(int i = 0 ; i<wishesOptions.size();i++)
					if(i != indW)
					{
						wishesOptions.get(i).setVisible(false);
						wishInfo.get(i).setVisible(false);
					}

				wishInfo.get(indW).setVisible(true);
				cw.add(wishInfo.get(indW));

				indW++;

			}

			if(btn == chooseWish){
				cw = new ChooseWish();
				createWish1();
				mainFrame.setContentPane(cw);
				mainFrame.repaint();
				mainFrame.validate();
			}

			if(btn==next) {
				if(ind==0) {
					f[0].setVisible(false);
					f[1].setVisible(true);
					f[2].setVisible(false);
					f[3].setVisible(false);
					f[4].setVisible(false);

					info[0].setVisible(false);
					info[1].setVisible(true);
					info[2].setVisible(false);
					info[3].setVisible(false);
					info[4].setVisible(false);

					ind++;
				}
				else if(ind==1) {
					f[0].setVisible(false);
					f[1].setVisible(false);
					f[2].setVisible(true);
					f[3].setVisible(false);
					f[4].setVisible(false);

					info[0].setVisible(false);
					info[1].setVisible(false);
					info[2].setVisible(true);
					info[3].setVisible(false);
					info[4].setVisible(false);

					ind++;
				}
				else if(ind==2) {
					f[0].setVisible(false);
					f[1].setVisible(false);
					f[2].setVisible(false);
					f[3].setVisible(true);
					f[4].setVisible(false);

					info[0].setVisible(false);
					info[1].setVisible(false);
					info[2].setVisible(false);
					info[3].setVisible(true);
					info[4].setVisible(false);

					ind++;
				}
				else if(ind==3) {
					f[0].setVisible(false);
					f[1].setVisible(false);
					f[2].setVisible(false);
					f[3].setVisible(false);
					f[4].setVisible(true);

					info[0].setVisible(false);
					info[1].setVisible(false);
					info[2].setVisible(false);
					info[3].setVisible(false);
					info[4].setVisible(true);

					ind++;
				}
				else if(ind==4) {
					f[0].setVisible(true);
					f[1].setVisible(false);
					f[2].setVisible(false);
					f[3].setVisible(false);
					f[4].setVisible(false);

					info[0].setVisible(true);
					info[1].setVisible(false);
					info[2].setVisible(false);
					info[3].setVisible(false);
					info[4].setVisible(false);
					ind=0;
				}
			}

			if(btn==start)
			{
				try 
				{
					sideInfo = new BackgroundView("grey1.jpg",200,1000);
				} 
				catch (IOException e2)
				{
				}
				startGame.removeAll();
				if(area2.getText().equals("")){
					game.getPlayer().setName("Player1");
				}
				else 
					game.getPlayer().setName(area2.getText());
				if(areaF2.getText().equals("")){
					game.getPlayer().createFighter(fighters[ind], "Active Fighter1");
				}
				else 
					game.getPlayer().createFighter(fighters[ind], areaF2.getText());
//				game.getPlayer().setDragonBalls(6);
				createGrid();
				createInfo();
				sideInfo.setBounds(800,0, 200,1000);
				worldView.addKeyListener(this);
				worldView.setBounds(0, 0, 800, 710);
				mainFrame.add(worldView);
				mainFrame.add(sideInfo);
				worldView.setFocusable(true);
				worldView.requestFocusInWindow();
				worldView.repaint();
				worldView.revalidate();
				mainFrame.repaint();
				mainFrame.revalidate();
//				System.out.println(game.getWorld().toString());
			}
			if(btn==loadGame)
			{
				try
				{
					game.load("yasmine.ser");
					startMenu.setVisible(false);
					mainFrame.repaint();
					mainFrame.revalidate();
					game.setListener(this);
					createGrid();
					try 
					{
						sideInfo = new BackgroundView("grey1.jpg",200,1000);
					} 
					catch (IOException e2) 
					{
					}
					createInfo();
					sideInfo.setBounds(800,0, 200,1000);
					worldView.addKeyListener(this);
					worldView.setBounds(0, 0, 800, 710);
					mainFrame.add(worldView);
					mainFrame.add(sideInfo);
					worldView.setFocusable(true);
					worldView.requestFocusInWindow();
					worldView.repaint();
					worldView.revalidate();
					mainFrame.repaint();
					mainFrame.revalidate();
				} 
				catch (ClassNotFoundException e1) 
				{
					JLabel message = new JLabel("Oops!!"+"\n"+ "No previously saved game");
					JLabel message2 = new JLabel("Start a new game?");
					newGame2 = new JButton("New Game");
					newGame2.addActionListener(this);
					message.setBounds(300,50,700, 50);
					message2.setBounds(350,150,700, 50);
					newGame2.setBounds(400,600,250,50);
					Font f = new Font("Serif",Font.BOLD,20);
					Font f1 = new Font("Atmosphere",Font.BOLD,20);
					message.setFont(f);
					message.setForeground(Color.WHITE);
					message2.setFont(f);
					message2.setForeground(Color.WHITE);
					newGame2.setFont(f1);
					newGame2.setContentAreaFilled(false);
					try
					{
						BackgroundView backLoad = new BackgroundView("wallpaper.jpg",1000,1000);
						backLoad.add(message);
						backLoad.add(message2);
						backLoad.add(newGame2);
						mainFrame.setContentPane(backLoad);
					}
					catch (IOException e2)
					{
						e2.printStackTrace();
					}

				} 
				catch (IOException e1) 
				{
					JLabel message = new JLabel("Oops!!"+"\n"+ "No previously saved game");
					JLabel message2 = new JLabel("Start a new game?");
					newGame2 = new JButton("New Game");
					newGame2.addActionListener(this);
					newGame2.setSize(1000, 1000);
					message.setBounds(300,50,700, 50);
					message2.setBounds(350,150,700, 50);
					newGame2.setBounds(400,600,250,50);
					Font f = new Font("Serif",Font.BOLD,20);
					Font f1 = new Font("Atmosphere",Font.BOLD,20);
					message.setFont(f);
					message.setForeground(Color.WHITE);
					message2.setFont(f);
					message2.setForeground(Color.WHITE);
					newGame2.setFont(f1);
					newGame2.setContentAreaFilled(false);
					try 
					{
						BackgroundView backLoad = new BackgroundView("wallpaper.jpg",1000,1000);
						backLoad.add(message);
						backLoad.add(message2);
						backLoad.add(newGame2);
						mainFrame.setContentPane(backLoad);
					} 
					catch (IOException e2)
					{

					}
				}
			}

			if(btn == startBattle) {
				try {
					battleView = new BattleView("battlemode.jpg");
					Font f1 = new Font("Atmosphere",Font.PLAIN,15);
					Fighter y = ((Fighter)battle.getFoe());
					fi = new JLabel("<html>Name: <html>"+y.getName()+"<html><br/>Level: <html>"+y.getLevel()+"<html><br/> Health Points: <html>"+y.getHealthPoints()+"<html><br/>Max Health Points: <html>"+y.getMaxHealthPoints()+"<html> <br/> Ki: <html>"+y.getKi()+"<html><br/> Max Ki: <html>"+y.getMaxKi()+"<html><br/>Stamina: <html>"+y.getStamina()+"<hmtl><br/> Max Stamina: <html>"+y.getMaxStamina());
					fi.setBounds(10, 10, 300, 130);
					fi.setFont(f1);
					fi.setForeground(Color.WHITE);
					battleView.add(fi);
					
					pla = new JLabel(new ImageIcon("standing.png"));
					pla.setBounds(550,300, 170, 170);
					fl = new JLabel(new ImageIcon("foestanding.png"));
					fl.setBounds(350,300,170, 170);
					fl.setVisible(true);
					battleView.add(fl);
					battleView.add(pla);
					foesturn = new JLabel("FOE'S TURN");
					yourturn = new JLabel("YOUR TURN");
					foesturn.setFont(new Font("Atmosphere",Font.PLAIN,40));
					yourturn.setFont(new Font("Atmosphere",Font.PLAIN,40));
					foesturn.setBounds(350, 10, 300, 50);
					yourturn.setBounds(350, 10, 300, 50);
					foesturn.setVisible(false);
					yourturn.setVisible(false);
					battleView.add(yourturn);
					battleView.add(foesturn);
					attacking = new JLabel(new ImageIcon("attack.gif"));
					attacking.setBounds(500, 300, 170, 170);
					attacking.setVisible(false);
					scheduler = Executors.newScheduledThreadPool(1);

					Fighter x = ((Fighter)battle.getMe());

					pi = new JLabel("<html>Name: <html>"+x.getName()+"<html><br/>Level: <html>"+x.getLevel()+"<html><br/> Health Points: <html>"+x.getHealthPoints()+"<html><br/>Max Health Points: <html>"+x.getMaxHealthPoints()+"<html> <br/> Ki: <html>"+x.getKi()+"<html><br/> Max Ki: <html>"+x.getMaxKi()+"<html><br/>Stamina: <html>"+x.getStamina()+"<hmtl><br/> Max Stamina: <html>"+x.getMaxStamina());
					pi.setBounds(790, 10, 300, 130);
					pi.setFont(f1);
					pi.setForeground(Color.WHITE);
					battleView.add(pi);

					transformed = new JLabel("SAIYAN TRANSFORMED");
					transformed.setBounds(790, 140, 300, 10);
					transformed.setFont(f1);
					transformed.setForeground(Color.WHITE);
					transformed.setVisible(false); 
					battleView.add(transformed);
					blocking = new JLabel(new ImageIcon("block.gif"));
					blocking.setBounds(500, 300, 170, 170);
					blocking.setVisible(false);
					battleView.add(blocking);
					
					foeplay = new JLabel(new ImageIcon("foegif.gif"));
					foeplay.setBounds(400, 300, 170, 170);
					foeplay.setVisible(false);
					battleView.add(foeplay);
					i = 0;

					Font f = new Font("Atmosphere",Font.PLAIN,20);
					block = new JButton("BLOCK");
					block.setBounds(850, 630, 100, 50);;
					block.setFont(f);
					block.addActionListener(this);
					block.setVisible(false);
					use = new JButton("USE");
					use.setBounds(750, 630, 100, 50);
					use.setFont(f);
					use.addActionListener(this);
					use.setVisible(false);
					attack = new JButton("ATTACK");
					attack.setBounds(650, 630, 100, 50);
					attack.setFont(f);
					attack.addActionListener(this);
					attack.setVisible(false);
					battleView.add(use);
					battleView.add(block);
					battleView.add(attack);
					battle.start();
					mainFrame.setContentPane(battleView);
					mainFrame.repaint();
					mainFrame.revalidate();
				} 
				catch (IOException e1) {
				}

			}

			if(btn==options)
			{
				if(optionsView!=null && cw==null &&battle1 == null ) 
				{
					optionsView.setVisible(true);
					sideInfo.setVisible(false);
					mainFrame.removeKeyListener(this);
					worldView.removeKeyListener(this);
					optionsView.setFocusable(true);
					optionsView.requestFocusInWindow();
					optionsView.revalidate();
					worldView.repaint();
					worldView.revalidate();
					mainFrame.repaint();
					mainFrame.revalidate();
				}
				else
				{
					try
					{
						optionsView = new BackgroundView("grey1.jpg",200,1000);
					} 
					catch (IOException e2) 
					{
						e2.printStackTrace();
					}
					mainFrame.removeKeyListener(this);
					worldView.removeKeyListener(this);
					sideInfo.setVisible(false);
					save = new JButton("Save");
					save.setBounds(20, 570, 150, 50);
					Font f = new Font("Atmosphere",Font.PLAIN, 15);
					save.setFont(f);
					save.addActionListener(this);
					optionsView.add(save);
					switchFighter = new JButton("Switch Fighter");
					switchFighter.setBounds(7, 30, 190, 50);
					switchFighter.setFont(f);
					switchFighter.addActionListener(this);
					optionsView.add(switchFighter);

					upgrade = new JButton("Upgrade Fighter");
					upgrade.setBounds(7, 80, 190, 50);
					upgrade.setFont(f);
					upgrade.addActionListener(this);
					optionsView.add(upgrade);

					createFighter = new JButton("<html>Create<br/> New Fighter<html>");
					createFighter.setBounds(7, 130, 190, 50);
					createFighter.setFont(f);
					createFighter.addActionListener(this);
					optionsView.add(createFighter);

					assignAttack = new JButton("<html>Assign<br/> Super Attack<html>");
					assignAttack.setBounds(7, 180, 190, 50);
					assignAttack.setFont(f);
					assignAttack.addActionListener(this);
					optionsView.add(assignAttack);

					assignUAttack = new JButton("<html>Assign<br/> Ultimate Attack<html>");
					assignUAttack.setBounds(7, 230, 190, 50);
					assignUAttack.setFont(f);
					assignUAttack.addActionListener(this);
					optionsView.add(assignUAttack);

					back = new JButton("Back");
					back.setBounds(20, 620, 150, 50);
					back.setFont(f);
					back.addActionListener(this);
					optionsView.add(back);
					optionsView.setBounds(800,0, 200,1000);

					saved = new JLabel("<html> The Game Has<br/> Been Saved<html>");
					saved.setFont(f);
					saved.setVisible(false);
					saved.setBounds(40, 450, 150, 100);
					saved.setForeground(Color.WHITE);
					optionsView.add(saved);
					optionsView.repaint();
					optionsView.revalidate();
					mainFrame.add(optionsView);
					mainFrame.repaint();
					mainFrame.revalidate();
				}
			}

			if(btn==assignAttack)
			{
				typeAttack = "Super";
				int size = game.getPlayer().getActiveFighter().getSuperAttacks().size();
				String [] old = new String[size+1];
				old[0] = "Choose nothing";

				for(int i=0; i<size; i++)
				{
					old[i+1] = game.getPlayer().getActiveFighter().getSuperAttacks().get(i).getName();
				}
				osattacks = new JComboBox(old);
				osattacks.addActionListener(this);
				int size1 = game.getPlayer().getSuperAttacks().size();
				String [] newattacks = new String[size1+1];
				newattacks[0] = "Choose nothing";

				for(int i=0; i<size1; i++)
				{
					newattacks[i+1] = game.getPlayer().getSuperAttacks().get(i).getName();
				}
				nsattacks = new JComboBox(newattacks);
				nsattacks.addActionListener(this);
				optionsView.setVisible(false);
				try 
				{
					attacksView = new BackgroundView("grey1.jpg",200,1000);
				}
				catch (IOException e1) 
				{
					e1.printStackTrace();
				}
				Font f = new Font("Atmosphere",Font.PLAIN, 15);
				JLabel t1 = new JLabel("Pick an old attack");
				JLabel t2 = new JLabel("Pick a new attack");

				t1.setFont(f);
				t2.setFont(f);
				t1.setForeground(Color.WHITE);
				t2.setForeground(Color.WHITE);
				t1.setBounds(10,30,200,30);
				osattacks.setBounds(2,60, 200, 50);
				t2.setBounds(10,120,200,30);
				nsattacks.setBounds(2,150, 200, 50);
				attacksView.setBounds(800, 0, 200, 1000);
				backO = new JButton("Back");
				backO.setBounds(20, 620, 150, 50);
				backO.setFont(f);
				backO.addActionListener(this);
				attacksView.add(backO);
				attacksView.add(osattacks);
				attacksView.add(nsattacks);
				attacksView.add(t1);
				attacksView.add(t2);
				mainFrame.add(attacksView);
			}
			if(btn==assignUAttack)
			{
				typeAttack = "Ultimate";
				int size = game.getPlayer().getActiveFighter().getUltimateAttacks().size();
				String [] old = new String[size+1];
				old[0] = "Choose nothing";

				for(int i=0; i<size; i++)
				{
					old[i+1] = game.getPlayer().getActiveFighter().getUltimateAttacks().get(i).getName();
				}
				ouattacks = new JComboBox(old);
				ouattacks.addActionListener(this);
				int size1 = game.getPlayer().getUltimateAttacks().size();
				String [] newattacks = new String[size1+1];
				newattacks[0] = "Choose nothing";

				for(int i=0; i<size1; i++)
				{
					newattacks[i+1] = game.getPlayer().getUltimateAttacks().get(i).getName();
				}
				nuattacks = new JComboBox(newattacks);
				nuattacks.addActionListener(this);
				optionsView.setVisible(false);
				try 
				{
					attacksView = new BackgroundView("grey1.jpg",200,1000);
				}
				catch (IOException e1) 
				{
					e1.printStackTrace();
				}
				Font f = new Font("Atmosphere",Font.PLAIN, 15);
				JLabel t1 = new JLabel("Pick an old attack");
				JLabel t2 = new JLabel("Pick a new attack");

				t1.setFont(f);
				t2.setFont(f);
				t1.setForeground(Color.WHITE);
				t2.setForeground(Color.WHITE);
				t1.setBounds(10,30,200,30);
				ouattacks.setBounds(2,60, 200, 50);
				t2.setBounds(10,120,200,30);
				nuattacks.setBounds(2,150, 200, 50);
				attacksView.setBounds(800, 0, 200, 1000);
				backO = new JButton("Back");
				backO.setBounds(20, 620, 150, 50);
				backO.setFont(f);
				backO.addActionListener(this);
				attacksView.add(backO);
				attacksView.add(ouattacks);
				attacksView.add(nuattacks);
				attacksView.add(t1);
				attacksView.add(t2);
				mainFrame.add(attacksView);
			}

			if(btn==backO)

			{   
				attacksView.setVisible(false);
				optionsView.setVisible(true);
				SuperAttack oldAttack = null;
				SuperAttack newAttack = null;
				UltimateAttack oldUAttack = null;
				UltimateAttack newUAttack = null;
				if(typeAttack.equals("Super")){
					if(nsattacks.getSelectedIndex()==0){
						return;
					}
					String x = (String) osattacks.getSelectedItem();
					for(int i =0 ; i<game.getPlayer().getActiveFighter().getSuperAttacks().size() ; i++)
					{
						if(x.equals(game.getPlayer().getActiveFighter().getSuperAttacks().get(i).getName()))
						{
							oldAttack = game.getPlayer().getActiveFighter().getSuperAttacks().get(i);
							break;
						}
					}
					String y = (String) selected;
				
					for(int i =0 ; i<game.getPlayer().getSuperAttacks().size(); i++)
					{
						if(y.equals(game.getPlayer().getSuperAttacks().get(i).getName()))
						{
							newAttack = game.getPlayer().getSuperAttacks().get(i);
							break;
						}
					}
					
					try 
					{
						game.getPlayer().assignAttack(game.getPlayer().getActiveFighter(), newAttack, oldAttack);

					}
					catch (MaximumAttacksLearnedException e1) 
					{
						JFrame error = new JFrame();
						JLabel e2 = new JLabel(e1.getMessage());
						error.setSize(400, 400);
						error.add(e2);
						error.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						error.setVisible(true);
						error.repaint();
						error.revalidate();
						System.out.println("error");
					} 
					catch (DuplicateAttackException e1) 
					{
						JFrame error = new JFrame();
						JLabel e2 = new JLabel(e1.getMessage());
						error.setSize(400, 400);
						error.add(e2);
						error.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						error.setVisible(true);
						error.repaint();
						error.revalidate();
					} 
					catch (NotASaiyanException e1) 
					{
						JFrame error = new JFrame();
						JLabel e2 = new JLabel(e1.getMessage());
						error.setSize(400, 400);
						error.add(e2);
						error.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						error.setVisible(true);
						error.repaint();
						error.revalidate();
					}

				}
				else
				{
					if(nuattacks.getSelectedIndex()==0)
						return;
					String x = (String) ouattacks.getSelectedItem();
					for(int i =0 ; i<game.getPlayer().getActiveFighter().getUltimateAttacks().size(); i++)
					{
						if(x.equals(game.getPlayer().getActiveFighter().getUltimateAttacks().get(i).getName()))
						{
							oldUAttack = game.getPlayer().getActiveFighter().getUltimateAttacks().get(i);
							break;
						}
					}
					String y = (String) selected;
					for(int i =0 ; i<game.getPlayer().getUltimateAttacks().size(); i++)
					{
						if(y.equals(game.getPlayer().getUltimateAttacks().get(i).getName()))
						{
							newUAttack = game.getPlayer().getUltimateAttacks().get(i);
							break;
						}
					}
					try 
					{
						game.getPlayer().assignAttack(game.getPlayer().getActiveFighter(), newUAttack, oldUAttack);
					} 
					catch (MaximumAttacksLearnedException e1) 
					{
						JFrame error = new JFrame();
						JLabel e2 = new JLabel(e1.getMessage());
						error.setSize(400, 400);
						error.add(e2);
						error.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						error.setVisible(true);
						error.repaint();
						error.revalidate();
						
					}
					catch (DuplicateAttackException e1)
					{
						JFrame error = new JFrame();
						JLabel e2 = new JLabel(e1.getMessage());
						error.setSize(400, 400);
						error.add(e2);
						error.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						error.setVisible(true);
						error.repaint();
						error.revalidate();
						
					}
					catch (NotASaiyanException e1)
					{
						JFrame error = new JFrame();
						JLabel e2 = new JLabel(e1.getMessage());
						error.setSize(400, 400);
						error.add(e2);
						error.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						error.setVisible(true);
						error.repaint();
						error.revalidate();
						
					}

				}	
			}
			if(btn==createFighter)
			{
				String [] t = {"Namekian","Majin","Frieza","Saiyan","Earthling"};
				typeF = new JComboBox(t);
				optionsView.setVisible(false);
				try
				{
					fighterView = new BackgroundView("grey1.jpg",200,1000);
				} catch (IOException e1)
				{
					e1.printStackTrace();
				}
				JLabel n = new JLabel("Fighter's name:");
				JLabel r = new JLabel("Choose race:");
				tn = new JTextArea();
				n.setBounds(5,10,150,50);
				r.setBounds(5,100,150,50);
				tn.setBounds(5,60,150,20);
				typeF.setBounds(1,130,150,50);
				Font f = new Font("Atmosphere",Font.PLAIN, 15);
				backF = new JButton("Back");
				backF.addActionListener(this);
				backF.setBounds(20, 620, 150, 50);
				backF.setFont(f);
				n.setFont(f);
				r.setFont(f);
				n.setForeground(Color.WHITE);
				r.setForeground(Color.WHITE);
				fighterView.add(n);
				fighterView.add(r);
				fighterView.add(tn);
				fighterView.add(typeF);
				fighterView.add(backF);
				fighterView.setVisible(true);
				fighterView.setBounds(800,0,200,1000);
				mainFrame.add(fighterView);
				mainFrame.repaint();
				mainFrame.revalidate();
			}

			if(btn==backF)
			{
				optionsView.setVisible(true);
				fighterView.setVisible(false);
				if(tn.getText().equals("")){
					return;
				}
				char c = ((String)typeF.getSelectedItem()).charAt(0);
				game.getPlayer().createFighter(c, tn.getText());
			}
			if(btn==exit)
			{
				if(close!=null)
					close.setVisible(true);
				else
				{
					close = new JFrame();
					close.setLocationRelativeTo(mainFrame);
					close.setSize(500, 300);
					close.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					close.setVisible(true);
					close.setLayout(null);
					JLabel x = new JLabel("Are you sure you want to exit?");
					Font f = new Font("Atmosphere",Font.PLAIN, 20);
					x.setFont(f);
					x.setBounds(50,20,400,50);
					close.add(x);
					yes = new JButton("YES");
					yes.setBounds(100, 150, 100, 50);
					yes.setFont(f);
					yes.addActionListener(this);
					no = new JButton("NO");
					no.setBounds(300, 150, 100, 50);
					no.setFont(f);
					no.addActionListener(this);
					close.add(yes);
					close.add(no);
					close.repaint();
					close.revalidate();
				}
			}

			if(btn==yes)
			{
				//			game.save("yasmine");
				close.dispose();
				mainFrame.dispose();
			}
			if(btn==no)
			{	
				close.dispose();
				worldView.setFocusable(true);
				worldView.requestFocusInWindow();

				mainFrame.repaint();
				mainFrame.revalidate();
			}

			if(btn==switchFighter)
			{
				try 
				{
					switchView = new BackgroundView("grey1.jpg",200,1000);
				} catch (IOException e1) 
				{
					e1.printStackTrace();
				}
				int size = game.getPlayer().getFighters().size();
				ArrayList <String> f = new ArrayList();
				for(int i=0; i<size; i++){
					if(!(f.contains(game.getPlayer().getFighters().get(i).getName()))) 
						f.add(game.getPlayer().getFighters().get(i).getName());
				}
				String [] f2 = new String[f.size()];
				for(int i = 0;i<f.size();i++){
					f2[i]=f.get(i);
				}

				sf = new JComboBox(f2);
				sf.setBounds(5,100,150,50);
				JLabel i = new JLabel("Choose Fighter");
				Font font = new Font("Atmosphere",Font.PLAIN, 15);
				i.setFont(font);
				i.setForeground(Color.WHITE);
				i.setBounds(5,50,150,50);
				optionsView.setVisible(false);
				backSF = new JButton("back");
				backSF.addActionListener(this);
				backSF.setFont(font);
				backSF.setBounds(20, 620, 150, 50);
				switchView.add(backSF);
				switchView.add(sf);
				switchView.add(i);
				switchView.setBounds(800,0,200,1000);
				mainFrame.add(switchView);
			}
			if(btn==backSF)
			{
				optionsView.setVisible(true);
				switchView.setVisible(false);
				String x = (String)sf.getSelectedItem();
				for(int i =0; i<game.getPlayer().getFighters().size(); i++)
				{
					if(x.equals(game.getPlayer().getFighters().get(i).getName()))
					{
						game.getPlayer().setActiveFighter(game.getPlayer().getFighters().get(i));
						break;
					}
				}
			}

			if(btn==upgrade) 
			{
			
				try 
				{
					upgradeView = new BackgroundView("grey1.jpg",200,1000);
				} 
				catch (IOException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				String [] x ={"Choose nothing","Health Points "+game.getPlayer().getActiveFighter().getMaxHealthPoints(),"Blast Damage "+game.getPlayer().getActiveFighter().getBlastDamage(), "Ki "+game.getPlayer().getActiveFighter().getMaxKi(),"Physical Damage "+game.getPlayer().getActiveFighter().getPhysicalDamage() ,"Stamina "+game.getPlayer().getActiveFighter().getMaxStamina()};
				uf = new JComboBox(x);
				uf.setBounds(5,130,170,50);
				JLabel i = new JLabel("Pick one");
				Font font = new Font("Atmosphere",Font.PLAIN, 15);
				i.setFont(font);
				i.setForeground(Color.WHITE);
				i.setBounds(15,100,150,50);
				JLabel h = new JLabel("<html>Ability Points:<html>"+"<html><br/><html>"+game.getPlayer().getActiveFighter().getAbilityPoints());
				h.setBounds(5,50,150,50);
				h.setOpaque(false);
				h.setFont(font);
				h.setForeground(Color.WHITE);
				optionsView.setVisible(false);
				backUF = new JButton("back");
				backUF.addActionListener(this);
				backUF.setFont(font);
				backUF.setBounds(20, 620, 150, 50);
				upgradeView.add(backUF);
				upgradeView.add(uf);
				upgradeView.add(i);
				upgradeView.add(h);
				upgradeView.setBounds(800,0,200,1000);
				mainFrame.add(upgradeView);
			
			}

			if(btn == backUF)
			{
				optionsView.setVisible(true);
				upgradeView.setVisible(false);
				char c = ((String)uf.getSelectedItem()).charAt(0);
				if(uf.getSelectedIndex()==0)
					return;

				try 
				{
					game.getPlayer().upgradeFighter(game.getPlayer().getActiveFighter(), c);
				}
				catch (NotEnoughAbilityPointsException e1) 
				{
					exception = new JFrame();
					exception.setVisible(true);
					exception.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					exception.setBounds(400,400,650,200);
					JLabel message = new JLabel(e1.getMessage());
					message.setFont(new Font("Atmosphere",Font.PLAIN, 16));
					message.setBounds(20,0,600,50);
					ok = new JButton("OK");
					ok.setBounds(200, 50, 100, 50);
					ok.setFont(new Font("Atmosphere",Font.PLAIN, 16));
					ok.addActionListener(this);

					exception.add(message);
					exception.add(ok);
				}
			}
			if(btn == ok)
			{
				exception.dispose();
			}

			if (btn==save)
			{
//							try 
//							{
//								game.save("yasmine.ser");
//							}
//							catch (IOException e1) 
//							{
//								// TODO Auto-generated catch block
//								e1.printStackTrace();
//							}
				saved.setVisible(true);

				int delay = 5000; //milliseconds
				ActionListener taskPerformer = new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						saved.setVisible(false);
					}
				};
				new Timer(delay, taskPerformer).start();
			}
			if (btn == back) 
			{
				sideInfo.removeAll();
				optionsView.setVisible(false);
				createInfo();
				sideInfo.setVisible(true);
				sideInfo.setBounds(800, 0, 200, 1000);
				mainFrame.add(sideInfo);
				mainFrame.repaint();
				mainFrame.revalidate();
				worldView.addKeyListener(this);
				mainFrame.addKeyListener(this);
			}

			if(btn==block) 
			{
				blocked = "yes";
				block.setVisible(false);
				use.setVisible(false);
				attack.setVisible(false);
				s1.setVisible(false);
				s2.setVisible(false);
				s3.setVisible(false);
				s4.setVisible(false);
				u1.setVisible(false);
				u2.setVisible(false);
				superA.setVisible(false);
				ultimateA.setVisible(false);
				physicalA.setVisible(false);
				pla.setVisible(false);
				battleView.repaint();
				battleView.revalidate();
				blocking.setVisible(true);
				
				final Runnable stop = new Runnable() {
					public void run() {

						battle.block();
					}
				};
				scheduler.schedule(stop, 1, SECONDS);

				//fighters visible true
			}

			if(btn==use)
			{
				try {
					battle.use(game.getPlayer(), Collectible.SENZU_BEAN);
					block.setVisible(false);
					use.setVisible(false);
					attack.setVisible(false);
					s1.setVisible(false);
					s2.setVisible(false);
					s3.setVisible(false);
					s4.setVisible(false);
					u1.setVisible(false);
					u2.setVisible(false);
					superA.setVisible(false);
					ultimateA.setVisible(false);
					physicalA.setVisible(false);
					battleView.repaint();
					battleView.revalidate();
					//fighters visible true
				} catch (NotEnoughSenzuBeansException e1) {
					//handled in setEnable(false)
				}
			}

			if(btn==attack)
			{
				superA.setVisible(true);
				ultimateA.setVisible(true);
				physicalA.setVisible(true);
				s1.setVisible(false);
				s2.setVisible(false);
				s3.setVisible(false);
				s4.setVisible(false);
				u1.setVisible(false);
				u2.setVisible(false);
				Font f = new Font("Atmosphere",Font.PLAIN,15);
				physicalA.setFont(f);
				superA.setFont(f);
				ultimateA.setFont(f);
				physicalA.addActionListener(this);
				superA.addActionListener(this);
				ultimateA.addActionListener(this);
				superA.setBounds(650,480,100,50);
				ultimateA.setBounds(650,530,100,50);
				physicalA.setBounds(650,580,100,50);
				if(game.getPlayer().getActiveFighter().getSuperAttacks().size()==0 || game.getPlayer().getActiveFighter().getKi()<1){
					superA.setEnabled(false);
				}
				else{
					superA.setEnabled(true);
				}
				if(game.getPlayer().getActiveFighter().getUltimateAttacks().size()==0 || game.getPlayer().getActiveFighter().getKi()<3){
					ultimateA.setEnabled(false);
				}
				else{
					ultimateA.setEnabled(true);
				}

				battleView.add(superA);
				battleView.add(ultimateA);
				battleView.add(physicalA);
				battleView.repaint();
				battleView.revalidate();
				mainFrame.repaint();
				mainFrame.revalidate();
			}
			if(btn==superA){
				superA.setVisible(false);
				ultimateA.setVisible(false);
				physicalA.setVisible(false);
				superAttackButtons();
			}
			if(btn == s1 || btn == s2 || btn == s3 || btn == s4){
				s1.setVisible(false);
				s2.setVisible(false);
				s3.setVisible(false);
				s4.setVisible(false);		
				block.setVisible(false);
				use.setVisible(false);
				attack.setVisible(false);
				pla.setVisible(false);
				attacking.setVisible(true);
				battleView.add(attacking);
				battleView.repaint();
				battleView.revalidate();
				final Runnable stop = new Runnable() {
					public void run() {
						pla.setVisible(true);
						attacking.setVisible(false);
						String SA = btn.getText();
						for(int i = 0 ; i<game.getPlayer().getActiveFighter().getSuperAttacks().size();i++)
						{
							if (SA.equals(game.getPlayer().getActiveFighter().getSuperAttacks().get(i).getName()))
								try {
									battle.attack(game.getPlayer().getActiveFighter().getSuperAttacks().get(i));
								} catch (NotEnoughKiException e1) { }
						}
					}
				};
				scheduler.schedule(stop, 5, SECONDS);
			}
			if(btn==ultimateA){
				superA.setVisible(false);
				ultimateA.setVisible(false);
				physicalA.setVisible(false);
				ultimateAttackButtons();
			}
			if(btn == u1 || btn == u2 ){ 
				u1.setVisible(false);
				u2.setVisible(false);
				attack.setVisible(false);
				use.setVisible(false);
				block.setVisible(false);
				pla.setVisible(false);
				attacking.setVisible(true);
				battleView.add(attacking);
				final Runnable stop = new Runnable() {
					public void run() {
						String UA = btn.getText();
						pla.setVisible(true);
						attacking.setVisible(false);
						for(int i = 0 ; i<game.getPlayer().getActiveFighter().getUltimateAttacks().size();i++)
						{
							if (UA.equals(game.getPlayer().getActiveFighter().getUltimateAttacks().get(i).getName()))
								try {
									battle.attack(game.getPlayer().getActiveFighter().getUltimateAttacks().get(i));
								} catch (NotEnoughKiException e1) {
									
								}
						}
					}
				};
				scheduler.schedule(stop, 5, SECONDS);		

			}
			if(btn==physicalA) {
				superA.setVisible(false);
				ultimateA.setVisible(false);
				physicalA.setVisible(false);
				block.setVisible(false);
				use.setVisible(false);
				attack.setVisible(false);
				pla.setVisible(false);
				battleView.add(attacking);
				attacking.setVisible(true);
				battleView.repaint();
				battleView.revalidate();
				
				final Runnable stop = new Runnable() {
					public void run() {
						pla.setVisible(true);
						attacking.setVisible(false);
						try {
							battle.attack(new PhysicalAttack());
						} catch (NotEnoughKiException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				};
				scheduler.schedule(stop, 3, SECONDS);		
				updateInfo();
			}

			if(btn==w1)
			{
				int s = 0;
				for(int i = 0; i < dragon.getWishes().length; i++)
				{
					if(dragon.getWishes()[i].getType()==DragonWishType.SENZU_BEANS)
					{
						game.getPlayer().chooseWish(dragon.getWishes()[i]);
						break;
					}
				}


				dragonView.removeAll();
				cw.removeAll();

				try {
					sideInfo = new BackgroundView("grey1.jpg",200,1000);
				} catch (IOException e1) {

				}
				sideInfo.setBounds(800,0,200,1000);
				createInfo();
				mainFrame.add(sideInfo);
				mainFrame.add(worldView);
				worldView.setFocusable(true);
				worldView.requestFocusInWindow();
				sideInfo.repaint();
				sideInfo.revalidate();
				mainFrame.repaint();
				mainFrame.validate();
			}
			if(btn==w2)
			{
				int s = 0;
				for(int i = 0; i < dragon.getWishes().length; i++)
				{
					if(dragon.getWishes()[i].getType()==DragonWishType.ABILITY_POINTS)
					{
						game.getPlayer().chooseWish(dragon.getWishes()[i]);
						break;
					}
				}

				//timer 

				dragonView.removeAll();
				cw.removeAll();
				try {
					sideInfo = new BackgroundView("grey1.jpg",200,1000);
				} catch (IOException e1) {

				}
				sideInfo.setBounds(800,0,200,1000);
				createInfo();
				mainFrame.add(sideInfo);
				mainFrame.add(worldView);
				worldView.setFocusable(true);
				worldView.requestFocusInWindow();
				sideInfo.repaint();
				sideInfo.revalidate();
				mainFrame.repaint();
				mainFrame.validate();
			}

			if(btn==w3)
			{
				SuperAttack s = null;
				for(int i = 0; i < dragon.getWishes().length; i++)
				{
					if(dragon.getWishes()[i].getType()==DragonWishType.SUPER_ATTACK)
					{
						game.getPlayer().chooseWish(dragon.getWishes()[i]);
						break;
					}
				}

				//timer 

				dragonView.removeAll();
				cw.removeAll();
				try {
					sideInfo = new BackgroundView("grey1.jpg",200,1000);
				} catch (IOException e1) {

				}

				sideInfo.setBounds(800,0,200,1000);
				createInfo();

				mainFrame.add(sideInfo);
				mainFrame.add(worldView);
				worldView.setFocusable(true);
				worldView.requestFocusInWindow();
				sideInfo.repaint();
				sideInfo.revalidate();
				mainFrame.repaint();
				mainFrame.validate();
			}

			if(btn==w4)
			{
				UltimateAttack s = null;
				for(int i = 0; i < dragon.getWishes().length; i++)
				{
					if(dragon.getWishes()[i].getType()==DragonWishType.ULTIMATE_ATTACK)
					{
						game.getPlayer().chooseWish(dragon.getWishes()[i]);
						break;
					}
				}
			
				dragonView.removeAll();
				cw.removeAll();
				try {
					sideInfo = new BackgroundView("grey1.jpg",200,1000);
				} catch (IOException e1) {

				}
				sideInfo.setBounds(800,0,200,1000);
				createInfo();
				mainFrame.add(sideInfo);
				mainFrame.add(worldView);
				worldView.setFocusable(true);
				worldView.requestFocusInWindow();
				sideInfo.repaint();
				sideInfo.revalidate();
				mainFrame.repaint();
				mainFrame.validate();
			}
			if(btn==rtrn)
			{
				
				battleView.removeAll();
				battle1.removeAll();
				sideInfo.removeAll();
				createInfo();
				mainFrame.add(sideInfo);
				mainFrame.add(worldView);
				worldView.setFocusable(true);
				worldView.requestFocusInWindow();
				mainFrame.repaint();
				mainFrame.revalidate();	
			}
			
			if(btn==rtrn2){
				battleView.removeAll();
				battle1.removeAll();
				sideInfo.removeAll();
				createInfo();
				createGrid();
				worldView.addKeyListener(this);
				worldView.setBounds(0,0,800,710);
				sideInfo.setBounds(800,0,200,1000);
				mainFrame.add(sideInfo);
				mainFrame.add(worldView);
				worldView.setFocusable(true);
				worldView.requestFocusInWindow();
				worldView.repaint();
				worldView.revalidate();
				mainFrame.repaint();
				mainFrame.revalidate();	
			}
		}
	}

	public void createFighters() {
		f = new JLabel[5];
		f[0] = new JLabel(new ImageIcon("Frieza.png"));
		f[0].setBounds(200, 200, 700, 450);
		f[0].setVisible(true);
		startGame.add(f[0]);
		f[1] = new JLabel(new ImageIcon("Namekian.png"));
		f[1].setBounds(200, 200, 700, 450);
		f[1].setVisible(false);
		startGame.add(f[1]);
		f[2] = new JLabel(new ImageIcon("Saiyan.png"));
		f[2].setBounds(200, 200, 700, 450);
		f[2].setVisible(false);
		startGame.add(f[2]);
		f[3] = new JLabel(new ImageIcon("earthling.png"));
		f[3].setBounds(200, 200, 700, 450);
		f[3].setVisible(false);
		startGame.add(f[3]);
		f[4] = new JLabel(new ImageIcon("majin.png"));
		f[4].setBounds(250, 200, 700, 450);
		f[4].setVisible(false);
		startGame.add(f[4]);
	}

	public void fightersInfo() {
		info = new JLabel[5];
		fighters = new char[5];

		info[0] = new JLabel();
		fighters[0] = 'F';
		String sText = "<html>Name: Frieza <br/> Max Health Points: 1100 <br/> Blast Damage: 75 <br/> Physical Damage: 75 <br/> Max Ki: 4 <br/> max Stamina: 4 </html>";                      
		info[0].setText(sText);
		info[0].setBounds(200, 400, 300, 200);
		info[0].setVisible(true);

		Font f = new Font("Atmosphere",Font.PLAIN,20);
		info[0].setFont(f);
		info[0].setForeground(Color.WHITE);

		startGame.add(info[0]);

		info[1] = new JLabel();
		fighters[1] = 'N';
		sText ="<html>Name: Namekian <br/> Max Health Points: 1350 <br/> Blast Damage: 0 <br/> Physical Damage: 50 <br/> Max Ki: 3 <br/> max Stamina: 5 </html>";  
		info[1].setText(sText);
		info[1].setText(sText);
		info[1].setBounds(200, 400, 300, 200);
		info[1].setVisible(false);
		f = new Font("Atmosphere",Font.PLAIN,20);
		info[1].setFont(f);
		info[1].setForeground(Color.WHITE);
		startGame.add(info[1]);

		info[2] = new JLabel();
		fighters[2] = 'S';
		sText = "<html>Name: Saiyan <br/> Max Health Points: 1000 <br/> Blast Damage: 150 <br/> Physical Damage: 100 <br/> Max Ki: 5 <br/> max Stamina: 3 </html>";  
		info[2].setText(sText);
		info[2].setText(sText);
		info[2].setText(sText);
		info[2].setBounds(200, 400, 300, 200);
		info[2].setVisible(false);
		f = new Font("Atmosphere",Font.PLAIN,20);
		info[2].setFont(f);
		info[2].setForeground(Color.WHITE);
		startGame.add(info[2]);

		info[3] = new JLabel();
		fighters[3] = 'E';
		sText = "<html>Name: Earthling <br/> Max Health Points: 1250 <br/> Blast Damage: 50 <br/> Physical Damage: 50 <br/> Max Ki: 4 <br/> max Stamina: 4 </html>";  
		info[3].setText(sText);
		info[3].setText(sText);
		info[3].setBounds(200, 400, 300, 200);
		info[3].setVisible(false);
		f = new Font("Atmosphere",Font.PLAIN,20);
		info[3].setFont(f);
		info[3].setForeground(Color.WHITE);
		startGame.add(info[3]);

		info[4] = new JLabel();
		fighters[4] = 'M';
		sText = "<html>Name: Majin <br/> Max Health Points: 1500 <br/> Blast Damage: 50 <br/> Physical Damage: 50 <br/> Max Ki: 3 <br/> max Stamina: 6 </html>";  
		info[4].setText(sText);
		info[4].setText(sText);
		info[4].setBounds(200, 400, 300, 200);
		info[4].setVisible(false);
		f = new Font("Atmosphere",Font.PLAIN,20);
		info[4].setFont(f);
		info[4].setForeground(Color.WHITE);
		startGame.add(info[4]);
	}

	public void onDragonCalled(Dragon dragon) {


		dragonView = new DragonView();
		this.dragon = dragon;

		JLabel d1 = new JLabel();
		d1.setText("<html>Well, you collected 7 dragon balls, so you can choose one wish.<br/>choose wisely..<html>");
		Font f1 = new Font("Atmosphere",Font.BOLD,25);
		d1.setFont(f1);
		d1.setForeground(Color.YELLOW);
		d1.setBounds(10,10,1000,100);

		dragonView.add(d1);
		chooseWish = new JButton();
		chooseWish.addActionListener(this);
		chooseWish.setText("CHOOSE WISH");
		chooseWish.setFont(new Font("Atmosphere",Font.BOLD,25));
		chooseWish.setBounds(750,600,250,50);
		chooseWish.setForeground(Color.BLACK);
		chooseWish.setOpaque(true);
		dragonView.add(chooseWish);

		chooseWish.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				chooseWish.setBackground(Color.ORANGE);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				chooseWish.setBackground(UIManager.getColor("control"));
			}
		});

		mainFrame.setContentPane(dragonView);
	}

	public void createWish1() {

		DragonWish [] wishes = dragon.getWishes();
		Font f = new Font("Atmosphere",Font.PLAIN, 20);

		w1 = new JButton("SENZU BEANS");
		w1.setBounds(20,600,200,50);
		w1.addActionListener(this);
		w1.setFont(f);
		w1.setVisible(false);
		w1.setOpaque(true);
		cw.add(w1);

		w2 = new JButton("ABILITY POINTS");
		w2.setBounds(240,600,230,50);
		w2.addActionListener(this);
		w2.setFont(f);
		w2.setVisible(false);
		w2.setOpaque(true);
		cw.add(w2);

		w3 = new JButton("SUPER ATTACKS");
		w3.setBounds(480,600,200,50);
		w3.addActionListener(this);
		w3.setFont(f);
		w3.setVisible(false);
		w3.setOpaque(true);
		cw.add(w3);

		w4 = new JButton("ULTIAMTE ATTACKS");
		w4.setBounds(700,600,250,50);
		w4.addActionListener(this);
		w4.setFont(f);
		w4.setVisible(false);
		w4.setOpaque(true);
		cw.add(w4);

		Font f1 = new Font("Atmosphere",Font.BOLD,30);

		l1 = new JLabel(); //senzu beans
		l1.setForeground(Color.YELLOW);
		l1.setVisible(false);
		l1.setFont(f1);
		l1.setBounds(50,50,550,50); 
		cw.add(l1);

		l2 = new JLabel(); //ultimate attacks
		l2.setForeground(Color.YELLOW);
		l2.setVisible(false);
		l2.setFont(f1);
		l2.setBounds(50,50,500,50);
		cw.add(l2);

		l3 = new JLabel(); //super attacks
		l3.setForeground(Color.YELLOW);
		l3.setVisible(false);
		l3.setFont(f1);
		l3.setBounds(50,50,1000,50);
		cw.add(l3);

		l4 = new JLabel();
		l4.setForeground(Color.YELLOW);
		l4.setVisible(false);
		l4.setFont(f1);
		l4.setBounds(50,50,1000,50);
		cw.add(l4);

		for(int i = 0; i < wishes.length; i++)
		{
			if(wishes[i].getType()==DragonWishType.SENZU_BEANS)
			{
				l1.setText("Will it be "+ wishes[i].getSenzuBeans()+"  SenzuBean(s)?");
				w1.setVisible(true);
				w1.addMouseListener(new java.awt.event.MouseAdapter() {
					public void mouseEntered(java.awt.event.MouseEvent evt) {
						w1.setBackground(Color.YELLOW);
						l1.setVisible(true);
					}

					public void mouseExited(java.awt.event.MouseEvent evt) {
						w1.setBackground(UIManager.getColor("control"));
						l1.setVisible(false);
					}
				});
				continue;
			}
			if(wishes[i].getType()==DragonWishType.ABILITY_POINTS)
			{
				l2.setText("Or " + wishes[i].getAbilityPoints()+"  AbilityPoints?");
				w2.setVisible(true);
				w2.addMouseListener(new java.awt.event.MouseAdapter() {
					public void mouseEntered(java.awt.event.MouseEvent evt) {
						w2.setBackground(Color.YELLOW);
						l2.setVisible(true);
					}

					public void mouseExited(java.awt.event.MouseEvent evt) {
						w2.setBackground(UIManager.getColor("control"));
						l2.setVisible(false);
					}
				});
				continue;
			}
			if(wishes[i].getType()==DragonWishType.SUPER_ATTACK)
			{
				l3.setText("Or a "+ wishes[i].getSuperAttack().getName()+"  SUPER ATTACK?");
				w3.setVisible(true);
				w3.addMouseListener(new java.awt.event.MouseAdapter() {
					public void mouseEntered(java.awt.event.MouseEvent evt) {
						w3.setBackground(Color.YELLOW);
						l3.setVisible(true);
					}

					public void mouseExited(java.awt.event.MouseEvent evt) {
						w3.setBackground(UIManager.getColor("control"));
						l3.setVisible(false);
					}
				});
				continue;
			}
			if(wishes[i].getType()==DragonWishType.ULTIMATE_ATTACK)
			{
				l4.setText("Or a "+ wishes[i].getUltimateAttack().getName()+"  ULTIMATE ATTACK?");
				w4.setVisible(true);
				w4.addMouseListener(new java.awt.event.MouseAdapter() {
					public void mouseEntered(java.awt.event.MouseEvent evt) {
						w4.setBackground(Color.YELLOW);
						l4.setVisible(true);
					}

					public void mouseExited(java.awt.event.MouseEvent evt) {
						w4.setBackground(UIManager.getColor("control"));
						l4.setVisible(false);
					}
				});
				continue;
			}
		}
	}

	public void aboutFighters(){ 
		aboutF = new JTextArea[5];


		aboutF[0] = new JTextArea("Frieza was probably considered the strongest in the universe"+"\n"+"for quite so long. His hunger for power grew each and everyday."+"\n"+"Frieza had a lot of warriors and fighters(including the Saiyans)"+"\n"+ "invading planets for him expanding his empire by each passing day."+"\n"+"Thanks to his incredible powers he managed to keep his subordinates" +"\n"+"from defying him."+"\n"+"However, he feared that the Saiyans might unlock their hidden potential"+"\n"+"and transform into Super Saiyans and defeat him as by then, none of the"+"\n"+"Saiyans did achieve this transformation that was known only from legends."+"\n"+ "So, Frieza decided to exterminate the Saiyans and destroy planet Vegeta."+"\n"+"In doing so he angered prince Vegeta who vowed to get revenge for his race."+"\n"+"Later on Frieza travelled to planet Namek in order to search for the original"+"\n"+"dragon balls and wish for eternal life. After a fierce battle with Goku,"+"\n"+"Vegeta and others on planet Namek, Goku finally transformed into a Super"+"\n"+ "Saiyan and was the first one able to overpower Frieza and defeated him"+"\n"+"Even Though Frieza is now dead, others from his race continue to live on."+"\n"+"Fighters of this race are known for their incredible speed and strength."+"\n"+ "Famous Characters of this race : Frieza and his father King Cold.");
		aboutF[0].setBounds(20, 300, 470, 300);
		aboutF[0].setVisible(true);
		aboutF[0].setOpaque(false);
		aboutF[0].setEditable(false);

		Font f = new Font("Atmosphere",Font.PLAIN,20);
		aboutF[0].setForeground(Color.WHITE);

		startGame.add(aboutF[0]);


		aboutF[1] = new JTextArea("The inhabitants of planet Namek are strong yet peaceful people."+"\n"+"They have a lot of magical abilities and special powers."+"\n"+"A Namekian can create a set of Dragon Balls, among other things."+"\n"+ "They are known for their regenerative abilities and their cunning"+"\n"+ "and wisdom. Famous Namekians include : Piccolo and Kami.");
		aboutF[1].setBounds(20, 300, 470, 300);
		aboutF[1].setVisible(false);
		aboutF[1].setOpaque(false);
		aboutF[1].setEditable(false);


		aboutF[1].setForeground(Color.WHITE);

		startGame.add(aboutF[1]);

		aboutF[2] = new JTextArea("The Saiyans are naturally born to be a warrior race. They are known for"+"\n"+"their amazing strength and skills in battle. The Saiyans were an evil race,"+"\n"+"invading other planets, destroy their inhabitants and sell the planets to"+"\n"+"the highest bidder. Their planet was later on destroyed by the evil lord"+"\n"+"Frieza destroying most of the Saiyan race leaving only few Saiyans alive,"+"\n"+"those fortunate enough to be away from planet Vegeta including Goku"+"\n"+"and prince Vegeta himself. After Vegetaís battle with Goku on earth while"+"\n"+ "he was searching for the dragon balls on earth, Vegeta was defeated and"+"\n"+ "little by little he knew the error of his evil ways and started to join the"+"\n"+ "Z-Fighters in defending the universe. The Saiyan race has a special"+"\n"+ "transformation called Super Saiyan this transformation increases the"+"\n"+"power and strength of the Saiyans. Famous Saiyans include : Goku and"+"\n"+"Vegeta as well as their half Saiyans sons and daughters.");
		aboutF[2].setBounds(20, 300, 470, 300);
		aboutF[2].setVisible(false);
		aboutF[2].setOpaque(false);
		aboutF[2].setEditable(false);



		aboutF[2].setForeground(Color.WHITE);

		startGame.add(aboutF[2]);

		aboutF[3] = new JTextArea("The inhabitants of the planet earth are weak by nature."+"\n"+ "However, some trained really hard and became in league with other"+"\n"+ "warrior races. The Human race is the most balanced"+"\n"+ "of all the racesin terms of strength and defence."+"\n"+ "Famous Earthlings include : Krillin, Yamcha and Tien");
		aboutF[3].setBounds(20, 300, 470, 300);
		aboutF[3].setVisible(false);
		aboutF[3].setOpaque(false);
		aboutF[3].setEditable(false);

		aboutF[3].setForeground(Color.WHITE);

		startGame.add(aboutF[3]);

		aboutF[4] = new JTextArea("Unleashed by the evil wizard Bibidi, Majin Buu in his original (Kid Buu) form"+"\n"+"went on a killing and destruction spree obliterating entire civilizations"+"\n"+"with just his breath. Thanks to the Grand Supreme Kai intervention,"+"\n"+"he infused his own goodness into Majin Buu making him less evil."+"\n"+ "However, the monster still remained and after a while good Buu and evil"+"\n"+"Buu separated resulting in the toughest battle the Z-Fighters had yet to"+"\n"+"encounter. Eventually, the evil Kid Buu was defeated and the good Buu"+"\n"+"lived on starting his own race. Majin fighters are known for their great"+"\n"+ "health and stamina. Famous Majin fighters : Buu in all his forms"+"\n"+"Majin Buu, Super Buu and Kid Buu.");
		aboutF[4].setBounds(20, 300, 470, 300);
		aboutF[4].setVisible(false);
		aboutF[4].setOpaque(false);
		aboutF[4].setEditable(false);

		aboutF[4].setForeground(Color.WHITE);

		startGame.add(aboutF[4]);



	}


	@Override
	public void onCollectibleFound(Collectible collectible) {

		final JLabel z = new JLabel();

		if(collectible==Collectible.SENZU_BEAN) 
		{
			z.setText("+1 SENZU BEAN");
		}
		else z.setText("+1 DRAGON BALL");
		z.setBounds(3,120,300,50);
		Font f = new Font("Atmosphere",Font.PLAIN,20);
		z.setFont(f);
		z.setForeground(Color.YELLOW);
		mainFrame.repaint();
		mainFrame.revalidate();
		int delay = 3000; //milliseconds
		ActionListener taskPerformer = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				z.setVisible(false);
			}
		};
		new Timer(delay, taskPerformer).start();

		sideInfo.removeAll();
		createInfo();
		sideInfo.add(z);

		if(collectible==Collectible.DRAGON_BALL && game.getPlayer().getDragonBalls()==7)
			game.getPlayer().callDragon();


	}

	@Override
	public void onBattleEvent(BattleEvent e) {

		battle = (Battle)e.getSource();


		if(e.getType()==BattleEventType.STARTED)
		{	
			level = game.getPlayer().getActiveFighter().getLevel();
			try
			{
				battle1 = new BackgroundView("battlemode.jpg",1000,1000);
			}
			catch(IOException exp)
			{
				System.out.println("no image background");
			}
			JLabel notify = new JLabel();
			notify.setText("YELLEEHHWWYYYYY");
			Font f = new Font("Atmosphere",Font.PLAIN,20);
			Font x = new Font("Atmosphere",Font.PLAIN,40); 
			notify.setFont(x);
			notify.setForeground(Color.WHITE);
			notify.setBounds(350, 40, 400, 27);
			notify.setVisible(true);
			startBattle = new JButton("START BATTLE");
			startBattle.setFont(f);
			startBattle.addActionListener(this);
			startBattle.setBounds(420,600,200,50);
			startBattle.setVisible(true);
			battle1.add(notify);
			battle1.add(startBattle);
			JLabel fo = new JLabel("Are you ready to face:");
			fo.setFont(f);
			fo.setForeground(Color.WHITE);
			fo.setBounds(400,350,300,100);
			fo.setVisible(true);
			JLabel foename = new JLabel(((Fighter)battle.getFoe()).getName());
			foename.setFont(new Font("Atmosphere",Font.BOLD,40));
			foename.setForeground(Color.WHITE);
			foename.setBounds(435,420,300,100);
			battle1.add(foename);
			battle1.add(fo);
			battle1.repaint();
			battle1.validate();

			mainFrame.setContentPane(battle1);
			mainFrame.repaint();
			mainFrame.revalidate();
		}

		if(battle.getAttacker()==battle.getMe() && battleView!=null)
		{
			
			
			if(i==0) {
				delay = 0;
				i++;

			}
			else {
				fl.setVisible(false);
				foeplay.setVisible(true);
				mainFrame.repaint();
				mainFrame.revalidate();

			}
			if(blocking.equals("yes"))
			{
				pla.setVisible(false);
				blocking.setVisible(true);
			}
			
			
			final Runnable stop = new Runnable() {
				
				public void run() {
					block.setVisible(true);
					use.setVisible(true);
					attack.setVisible(true);
					foesturn.setVisible(false);
					yourturn.setVisible(true);
					if(delay!=0)
					foeplay.setVisible(false);
					pla.setVisible(true);
					fl.setVisible(true);
					mainFrame.repaint();
					mainFrame.revalidate();
					battleView.repaint();
					battleView.revalidate();
					if(delay==0)
						delay = 3;
					if(game.getPlayer().getSenzuBeans()==0) 
						use.setEnabled(false);
					else
						use.setEnabled(true);
					
					if(blocked.equals("yes"))
					{
						blocked = "";
						pla.setVisible(true);
						blocking.setVisible(false);
					}

				}
			};
			scheduler.schedule(stop, delay, SECONDS);
		}

		if(battle.getAttacker()==battle.getFoe() && e.getType()==BattleEventType.NEW_TURN)
		{

			use.setVisible(false);
			block.setVisible(false);
			attack.setVisible(false);
			superA.setVisible(false);
			ultimateA.setVisible(false);
			physicalA.setVisible(false);
			
			

					foesturn.setVisible(true);
					yourturn.setVisible(false);
				try 
				{			
					battle.play();
				} catch (NotEnoughKiException e1) { }
					
					battleView.repaint();
					battleView.revalidate();
		}

		if(e.getType()==BattleEventType.USE)
		{

			if((Fighter)battle.getMe() instanceof Saiyan && ((Saiyan)battle.getMe()).isTransformed())
				transformed.setVisible(true);
			else if((Fighter)battle.getMe()instanceof Saiyan && !((Saiyan)battle.getMe()).isTransformed())
				transformed.setVisible(false);

			updateInfo();
		}

		if(e.getType()==BattleEventType.BLOCK)
		{

			if((Fighter)battle.getMe() instanceof Saiyan && ((Saiyan)battle.getMe()).isTransformed())
				transformed.setVisible(true);
			else if((Fighter)battle.getMe()instanceof Saiyan && !((Saiyan)battle.getMe()).isTransformed())
				transformed.setVisible(false);

			updateInfo();
		}

		if(e.getType()==BattleEventType.ATTACK)
		{
			if((Fighter)battle.getMe() instanceof Saiyan && ((Saiyan)battle.getMe()).isTransformed())
				transformed.setVisible(true);
			else if((Fighter)battle.getMe()instanceof Saiyan && !((Saiyan)battle.getMe()).isTransformed())
				transformed.setVisible(false);

			updateInfo();
		}

		if(e.getType()==BattleEventType.ENDED)
		{
			if(e.getWinner()==battle.getFoe())
			{
				battleView.removeAll();
				battle1.removeAll();
				
				label1.setVisible(true);
				Font f = new Font("Atmosphere",Font.PLAIN,80);
				label1.setFont(f);
				label1.setForeground(Color.WHITE);
				label1.setBounds(250,130,600,80);
				battleView.add(label1);
				battleView.repaint();


				newGame3 = new JButton("NEW GAME"); 
				Font f2 = new Font("Atmosphere",Font.PLAIN,20);
				newGame3.setFont(f2);
				newGame3.setBounds(400,600,150,50);
				newGame3.addActionListener(this);
				battleView.add(newGame3);
				battleView.repaint();
				battleView.revalidate();
				

			}

			if(e.getWinner()==battle.getMe() && !(((NonPlayableFighter)battle.getFoe()).isStrong())) 
			{	
				battleView.removeAll();
				battle1.removeAll();
				sideInfo.removeAll();
				
				final JLabel label = new JLabel("LOLOLOLOLEEYYY");
				Font f = new Font("Atmosphere",Font.PLAIN,40);
				label.setFont(f);
				label.setForeground(Color.WHITE);
				label.setBounds(300,40,400,27);
				battleView.add(label);
				
				JLabel gainedxp = new JLabel("Gained XP: "+game.getPlayer().getActiveFighter().getXp());
				gainedxp.setFont(new Font("Atmosphere",Font.PLAIN,20));
				gainedxp.setForeground(Color.WHITE);
				gainedxp.setBounds(300, 250, 200, 27);
				battleView.add(gainedxp);
				if(game.getPlayer().getActiveFighter().getLevel()>level)
				{
					
					JLabel levelup = new JLabel("LEVEL UP: "+game.getPlayer().getActiveFighter().getLevel());
					levelup.setFont(new Font("Atmosphere",Font.PLAIN,20));
					levelup.setForeground(Color.WHITE);
					levelup.setBounds(300,300,200,40);
					battleView.add(levelup);
					
					JLabel apoints = new JLabel("Updated Ability Points: "+game.getPlayer().getActiveFighter().getAbilityPoints());
					apoints.setFont(new Font("Atmosphere",Font.PLAIN,20));
					apoints.setForeground(Color.WHITE);
					apoints.setBounds(300,350,350,40);
					battleView.add(apoints);
					
					JLabel txp= new JLabel("Target xp: "+game.getPlayer().getActiveFighter().getTargetXp());
					txp.setFont(new Font("Atmosphere",Font.PLAIN,20));
					txp.setForeground(Color.WHITE);
					txp.setBounds(300,400,200,40);
					battleView.add(txp);
				}
				String s = "Super Attacks unlocked: ";
				Fighter ftrs = ((Fighter) battle.getFoe());
				if(ftrs.getSuperAttacks().size()==0) s="";
				else
				{
				for(int i = 0; i < ftrs.getSuperAttacks().size(); i++)
					s+="\n" + ftrs.getSuperAttacks().get(i).getName();
				s+="\n";
				}
				String u = "Ultimate Attacks unlockeD: ";
				if(ftrs.getUltimateAttacks().size()==0) u="";
				else
				{
				for(int i = 0; i < ftrs.getUltimateAttacks().size(); i++)
					u+="\n" + ftrs.getUltimateAttacks().get(i).getName();
				u+="\n";
				}
				JTextArea gainedskills = new JTextArea("Gained Skills:"+"\n" + s + u);
				gainedskills.setOpaque(false);
				gainedskills.setFont(new Font("Atmosphere",Font.PLAIN,20));
				gainedskills.setBounds(300,450,400,300);
				gainedskills.setForeground(Color.WHITE);
				gainedskills.setEditable(false);
				battleView.add(gainedskills);
				rtrn = new JButton("Back to world map");
				rtrn.setFont(new Font("Atmosphere",Font.PLAIN,20));
				rtrn.setBounds(600, 600, 250, 50);
				rtrn.addActionListener(this);
				battleView.add(rtrn);
				battleView.repaint();
				battleView.revalidate();
			}
			else if(e.getWinner()==battle.getMe() && ((NonPlayableFighter)battle.getFoe()).isStrong()) 
			{
				battleView.removeAll();
				battle1.removeAll();
				sideInfo.removeAll();

				final JLabel label = new JLabel("WELL DONE "+game.getPlayer().getName());
				JLabel label3 = new JLabel("YOU CRUSHED THE BOSS");
				Font f = new Font("Atmosphere",Font.PLAIN,40);
				label.setFont(f);
				label.setForeground(Color.YELLOW);
				label.setBounds(300,40,800,27);
				battleView.add(label);
				label3.setFont(f);
				label3.setForeground(Color.YELLOW);
				label3.setBounds(280,70,800,27);
				battleView.add(label3);
				
				JLabel gainedxp = new JLabel("Gained XP: "+game.getPlayer().getActiveFighter().getXp());
				gainedxp.setFont(new Font("Atmosphere",Font.PLAIN,20));
				gainedxp.setForeground(Color.WHITE);
				gainedxp.setBounds(300, 250, 200, 27);
				battleView.add(gainedxp);
				if(game.getPlayer().getActiveFighter().getXp() >= game.getPlayer().getActiveFighter().getTargetXp())
				{
					JLabel levelup = new JLabel("LEVEL UP: "+game.getPlayer().getActiveFighter().getLevel());
					levelup.setFont(f);
					levelup.setForeground(Color.WHITE);
					levelup.setBounds(300,300,200,27);
					battleView.add(gainedxp);
				}
				String s = "Super Attacks unlocked: ";
				Fighter ftrs = ((Fighter) battle.getFoe());
				if(ftrs.getSuperAttacks().size()==0) s="";
				else
				{
				for(int i = 0; i < ftrs.getSuperAttacks().size(); i++)
					s+="\n" + ftrs.getSuperAttacks().get(i).getName();
				s+="\n";
				}
				String u = "Ultimate Attacks unlocked: ";
				if(ftrs.getUltimateAttacks().size()==0) u="";
				else
				{
				for(int i = 0; i < ftrs.getUltimateAttacks().size(); i++)
					u+="\n" + ftrs.getUltimateAttacks().get(i).getName();
				u+="\n";
				}
				JTextArea gainedskills = new JTextArea("Gained Skills:"+"\n" + s + u);
				gainedskills.setOpaque(false);
				gainedskills.setFont(new Font("Atmosphere",Font.PLAIN,20));
				gainedskills.setBounds(300,350,350,300);
				gainedskills.setForeground(Color.WHITE);
				gainedskills.setEditable(false);
				battleView.add(gainedskills);
				rtrn2 = new JButton("Back to world map");
				rtrn2.setFont(new Font("Atmosphere",Font.PLAIN,20));
				rtrn2.setBounds(600, 600, 250, 50);
				rtrn2.addActionListener(this);
				battleView.add(rtrn2);
				battleView.repaint();
				battleView.revalidate();

			}
		}
	}

	public void updateInfo() {
		Fighter y = ((Fighter)battle.getFoe());

		fi.setText("<html>Name: <html>"+y.getName()+"<html><br/>Level: <html>"+y.getLevel()+"<html><br/> Health Points: <html>"+y.getHealthPoints()+"<html><br/>Max Health Points: <html>"+y.getMaxHealthPoints()+"<html> <br/> Ki: <html>"+y.getKi()+"<html><br/> Max Ki: <html>"+y.getMaxKi()+"<html><br/>Stamina: <html>"+y.getStamina()+"<hmtl><br/> Max Stamina: <html>"+y.getMaxStamina());

		Fighter x = ((Fighter)battle.getMe());
		pi.setText("<html>Name: <html>"+x.getName()+"<html><br/>Level: <html>"+x.getLevel()+"<html><br/> Health Points: <html>"+x.getHealthPoints()+"<html><br/>Max Health Points: <html>"+x.getMaxHealthPoints()+"<html> <br/> Ki: <html>"+x.getKi()+"<html><br/> Max Ki: <html>"+x.getMaxKi()+"<html><br/>Stamina: <html>"+x.getStamina()+"<hmtl><br/> Max Stamina: <html>"+x.getMaxStamina());
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		mainFrame.repaint();
		mainFrame.revalidate();
		worldView.repaint();
		worldView.revalidate();

		if(e.getKeyCode()==KeyEvent.VK_UP) {
			try{
				int row = game.getWorld().getPlayerRow();
				int col = game.getWorld().getPlayerColumn();
				BackgroundView [][] arr= worldView.getArray();
				game.getWorld().moveUp();
				arr[row][col].changeBackground("grid.jpg");
				row = game.getWorld().getPlayerRow();
				col = game.getWorld().getPlayerColumn();
				arr[row][col].changeBackground("gokocloud1.png");
				mainFrame.repaint();
				mainFrame.revalidate();
				worldView.repaint();
				worldView.revalidate();
			}
			catch(MapIndexOutOfBoundsException e1){		

				String gongFile = "error.wav";
				InputStream in = null;
				try {
					in = new FileInputStream(gongFile);
				} catch (FileNotFoundException e2) {

				}
				AudioStream audioStream = null;
				try {
					audioStream = new AudioStream(in);
				} catch (IOException e2) {

				}
				AudioPlayer.player.start(audioStream);
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_DOWN){
			try{
				int row = game.getWorld().getPlayerRow();
				int col = game.getWorld().getPlayerColumn();
				BackgroundView [][] arr= worldView.getArray();
				game.getWorld().moveDown();
				arr[row][col].changeBackground("grid.jpg");
				row = game.getWorld().getPlayerRow();
				col = game.getWorld().getPlayerColumn();
				arr[row][col].changeBackground("gokocloud1.png");
				mainFrame.repaint();
				mainFrame.revalidate();
			}
			catch(MapIndexOutOfBoundsException e1){

				String gongFile = "error.wav";
				InputStream in = null;
				try {
					in = new FileInputStream(gongFile);
				} catch (FileNotFoundException e2) {

				}
				AudioStream audioStream = null;
				try {
					audioStream = new AudioStream(in);
				} catch (IOException e2) {

				}
				AudioPlayer.player.start(audioStream);

			}
		}
		if(e.getKeyCode()==KeyEvent.VK_LEFT){
			try{
				int row = game.getWorld().getPlayerRow();
				int col = game.getWorld().getPlayerColumn();
				BackgroundView [][] arr= worldView.getArray();
				game.getWorld().moveLeft();
				arr[row][col].changeBackground("grid.jpg");
				row = game.getWorld().getPlayerRow();
				col = game.getWorld().getPlayerColumn();
				arr[row][col].changeBackground("gokocloud1.png");
				mainFrame.repaint();
				mainFrame.revalidate();
			}
			catch(MapIndexOutOfBoundsException e1) {

				String gongFile = "error.wav";
				InputStream in = null;
				try {
					in = new FileInputStream(gongFile);
				} catch (FileNotFoundException e2) {

				}
				AudioStream audioStream = null;
				try {
					audioStream = new AudioStream(in);
				} catch (IOException e2) {

				}
				AudioPlayer.player.start(audioStream);
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
			try
			{
				int row = game.getWorld().getPlayerRow();
				int col = game.getWorld().getPlayerColumn();
				BackgroundView [][] arr= worldView.getArray();
				game.getWorld().moveRight();
				arr[row][col].changeBackground("grid.jpg");
				row = game.getWorld().getPlayerRow();
				col = game.getWorld().getPlayerColumn();
				arr[row][col].changeBackground("gokocloud1.png");
				mainFrame.repaint();
				mainFrame.revalidate();
			}
			catch(MapIndexOutOfBoundsException e1) 
			{	
				String gongFile = "error.wav";
				InputStream in = null;
				try {
					in = new FileInputStream(gongFile);
				} catch (FileNotFoundException e2) {

				}
				AudioStream audioStream = null;
				try {
					audioStream = new AudioStream(in);
				} catch (IOException e2) {

				}
				AudioPlayer.player.start(audioStream);
			}
		}
	}

	public void createGrid() {
		worldView = new WorldView();
		BackgroundView [][] array = new BackgroundView[10][10];
		BufferedImage backgroundImage;
		try 
		{
			backgroundImage = ImageIO.read(new File("grid.jpg"));
			backgroundImage = BackgroundView.resizeImage(backgroundImage,100,100);
			for(int i=0;i<10;i++)
			{
				for(int j=0; j<10; j++)
				{
					try
					{
						BackgroundView tmp;  
						if(i==0&& j==0) //boss
						{	
							tmp = new BackgroundView("abd.png",100,100);
							worldView.add(tmp);
							tmp.setBorder(BorderFactory.createLineBorder(Color.black));
							array[i][j] = tmp;
							continue;
						}
						if(i==game.getWorld().getPlayerRow() && j==game.getWorld().getPlayerColumn()){
							tmp = new BackgroundView("gokocloud1.png",100,100);
							worldView.add(tmp);
							tmp.setBorder(BorderFactory.createLineBorder(Color.black));
							array[i][j] = tmp;
							continue;
						}
						else{ 
							tmp = new BackgroundView(backgroundImage,100,100);
							tmp.setSize(100, 100);
							tmp.setBorder(BorderFactory.createLineBorder(Color.black));
							worldView.add(tmp);
							array[i][j] = tmp;
						}
					}
					catch(IOException exp)
					{
						System.out.println("no image");
					}
				}

			}
			worldView.setArray(array);


		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public void superAttackButtons() {

		Font f = new Font("Atmosphere", Font.PLAIN,13);
		s1.setBounds(650, 590, 200, 40);
		s2.setBounds(650, 550, 200, 40);
		s3.setBounds(650, 510, 200, 40);
		s4.setBounds(650, 470, 200, 40);
		s1.addActionListener(this);
		s2.addActionListener(this);
		s3.addActionListener(this);
		s4.addActionListener(this);
		s1.setFont(f);
		s2.setFont(f);
		s3.setFont(f);
		s4.setFont(f);
		s1.setVisible(false);
		s2.setVisible(false);
		s3.setVisible(false);
		s4.setVisible(false);
		battleView.add(s1);
		battleView.add(s2);
		battleView.add(s3);
		battleView.add(s4);
		for(int i=0; i<game.getPlayer().getActiveFighter().getSuperAttacks().size(); i++){
			switch(i){
			case 0: s1.setText(game.getPlayer().getActiveFighter().getSuperAttacks().get(i).getName());
			s1.setVisible(true);
			break;
			case 1: s2.setText(game.getPlayer().getActiveFighter().getSuperAttacks().get(i).getName());
			s2.setVisible(true);
			break;
			case 2: s3.setText(game.getPlayer().getActiveFighter().getSuperAttacks().get(i).getName());
			s3.setVisible(true);		
			break;
			case 3: s4.setText(game.getPlayer().getActiveFighter().getSuperAttacks().get(i).getName());
			s4.setVisible(true);		
			break;
			}
		}



	}
	public void ultimateAttackButtons() {

		Font f = new Font("Atmosphere", Font.PLAIN,13);
		u1.setBounds(650,590 ,200, 40);
		u2.setBounds(650,550 ,200, 40);
		u1.addActionListener(this);
		u2.addActionListener(this);
		u1.setFont(f);
		u2.setFont(f);
		u1.setVisible(false);
		u2.setVisible(false);
		battleView.add(u1);
		battleView.add(u2);
		for(int i=0; i<game.getPlayer().getActiveFighter().getUltimateAttacks().size(); i++){
			switch(i){
			case 0: u1.setText(game.getPlayer().getActiveFighter().getUltimateAttacks().get(i).getName());
			u1.setVisible(true);
			break;
			case 1: u2.setText(game.getPlayer().getActiveFighter().getUltimateAttacks().get(i).getName());
			u2.setVisible(true);
			break;
			}
		}
	}

	public void createInfo() {

		String namePlayer = game.getPlayer().getName()+"";
		String nameFighter = game.getPlayer().getActiveFighter().getName()+"";
		String level = game.getPlayer().getActiveFighter().getLevel()+"";
		int sb = game.getPlayer().getSenzuBeans();
		int db = game.getPlayer().getDragonBalls();
		int mhp = game.getPlayer().getActiveFighter().getMaxHealthPoints();
		int pd = game.getPlayer().getActiveFighter().getPhysicalDamage();
		int bd = game.getPlayer().getActiveFighter().getBlastDamage();
		int mk = game.getPlayer().getActiveFighter().getMaxKi();
		int ms = game.getPlayer().getActiveFighter().getMaxStamina();
		int ap = game.getPlayer().getActiveFighter().getAbilityPoints();
		playerInfo = new JLabel("<html> Player's name:<br/><html>"+namePlayer+"<html> <br/> Senzu Beans:<br/><html>"+ sb+"<html><br/> Dragon Balls:<br/><html>"+db);                            
		playerInfo.setBounds(3, 0, 300, 150);
		Font f = new Font("Atmosphere",Font.PLAIN,18);
		playerInfo.setFont(f);
		playerInfo.setForeground(Color.WHITE);
		fighterInfo = new JLabel("<html> <br/> Fighter's name:<br/><html>"+nameFighter+"<html> <br/> Level:<br/><html>"+level+"<html> <br/> Max Health Points:<br/><html>"+mhp + "<html> <br/> Physical Damage:<br/><html>" + pd + "<html> <br/> Blast Damage:<br/><html>" + bd + "<html> <br/> Max Ki:<br/><html>" + mk + "<html> <br/> Max Stamina:<br/><html>" + ms + "<html> <br/> Ability Points:<br/><html>"+ap);
		fighterInfo.setFont(f);
		fighterInfo.setForeground(Color.WHITE);
		fighterInfo.setBounds(3, 100, 300, 450);
		sideInfo.add(fighterInfo);
		sideInfo.add(playerInfo);

		options = new JButton("Options");
		options.setBounds(20, 550, 150, 50);
		options.setFont(f);
		options.addActionListener(this);
		sideInfo.add(options);

	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	public static void main(String [] args) throws MissingFieldException, UnknownAttackTypeException {

		GameGUI a = new GameGUI();


	}
}
