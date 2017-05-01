import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;


public class Animation extends JPanel{

    private int ballX = 250;
    private int ballY = 250;
    private int ballDiameter = 50;

    private double ballXSpeed = 5;
    private double ballYSpeed = 10;


    public Animation(){
        setBackground(Color.BLACK);
    }

    //call this method from a Timer to move the ball!
    public void step(){

        //move the ball on the X axis
        ballX += ballXSpeed;
        //if the ball goes off the edge, bounce it by reversing its speed
        if(ballX < 0 || ballX > getWidth()-ballDiameter){
            ballXSpeed *= -1;
        }

        //move the ball on the Y axis
        ballY += ballYSpeed;
        //if the ball goes off the edge, bounce it by reversing its speed
        if(ballY < 0 || ballY > getHeight()-ballDiameter){
            ballYSpeed *= -1;
        }

        //tell this JPanel to repaint itself since the ball has moved
        repaint();
    }

    //paint the ball
    public void paintComponent(Graphics g){

        super.paintComponent(g);

        g.setColor(Color.BLUE);
        g.fillOval(ballX, ballY, ballDiameter, ballDiameter);
    }

}