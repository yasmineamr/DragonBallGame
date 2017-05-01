import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;

public class MyListener implements ActionListener{

    private Animation ballPanel;

    public MyListener(Animation ballPanel){
        this.ballPanel = ballPanel;
    }

    public void actionPerformed(ActionEvent e) {
        ballPanel.step();
    }
    
    public static void main(String[] args) {

        JFrame frame = new JFrame("Counter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        Animation ballPanel = new Animation();
        frame.add(ballPanel, BorderLayout.CENTER);

        //pass the BallPanel instance into the MyListener constructor
        MyListener listener = new MyListener(ballPanel);

        //the timer fires 30 times a second
        Timer timer = new Timer(1000/30, listener);

        //start the timer
        timer.start();

        frame.setSize(500, 500);
        frame.setVisible(true);
    }
}