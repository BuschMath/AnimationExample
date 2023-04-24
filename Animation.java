/* In this example, we create a JFrame to display the animation and implement the 
    Runnable interface to run the animation in a separate thread. We use a while 
    loop to continuously update the state of the animation by incrementing the x 
    and y coordinates of a circle and calling the repaint() method to render the 
    updated frame. We also use the Thread.sleep() method to control the speed of 
    the animation.

   To start the animation, we call the startAnimation() method, which creates a 
    new thread and starts it. To stop the animation, we call the stopAnimation() 
    method, which sets the running flag to false and waits for the animation thread 
    to finish using the join() method.

   By using concurrent programming techniques in this way, we can create complex 
    and responsive animations that are able to run smoothly alongside other threads 
    in our program. */

import javax.swing.*;
import java.awt.*;

public class Animation extends JFrame implements Runnable {
    
    private Thread animationThread;
    private volatile boolean running;
    private int x, y;
    
    public Animation() {
        setTitle("Animation Example");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        x = 50;
        y = 50;
        startAnimation();
    }
    
    private void startAnimation() {
        running = true;
        animationThread = new Thread(this);
        animationThread.start();
    }
    
    private void stopAnimation() {
        running = false;
        try {
            animationThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void run() {
        while (running) {
            x++;
            y++;
            repaint();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawOval(x, y, 50, 50);
    }
    
    public static void main(String[] args) {
        new Animation();
    }
}
