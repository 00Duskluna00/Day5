import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class BasicFramework extends JFrame {

    //final variables for window size and refresh rate
    private final int MAX_FPS;
    private final int WIDTH;
    private final int HEIGHT;

    //double buffer
    private BufferStrategy strategy;

    public BasicFramework(int width, int height, int fps){
        super("JFrame Demo");
        this.MAX_FPS = fps;
        this.WIDTH = width;
        this.HEIGHT = height;
    }

    //initialize variables, set up the window
    void init(){
        //initialize window size
        setBounds(0, 0, WIDTH, HEIGHT);
        setResizable(false);

        //set visible
        setVisible(true);

        //set close operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //create double buffer strategy
        createBufferStrategy(2);
        strategy = getBufferStrategy();

        //set the background color
        setBackground(Color.GREEN);
    }

    //draw all elements on screen
    private void draw(){
        //get canvas
        Graphics2D g = (Graphics2D) strategy.getDrawGraphics();

        //clear screen
        g.clearRect(0,0,WIDTH, HEIGHT);

        //release resources, show the buffer
        g.dispose();
        strategy.show();
    }

    //main looping method
    public void run(){

        //initialize variables
        init();

        //loop
        while(true){

            //draw every frame
            draw();

            //sleep between frames to cap the framerate
            try{ Thread.sleep(1000/MAX_FPS); }
            catch (InterruptedException e){ e.printStackTrace(); }

        }

    }

    //entry point for application
    public static void main(String[] args){
        BasicFramework game = new BasicFramework(400, 300, 60);
        game.run();
    }
}
