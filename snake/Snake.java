package snake;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Component;
import javax.swing.JComponent;

public class Snake extends JComponent implements KeyListener {
    private MainPanel parent;
    private int speed = 10;
    private int score;
    private char dir;
    private Segment s;
    final int FIELD_TOP;
    final int FIELD_BUTTOM;
    final int FIELD_LEFT;
    final int FIELD_RIGHT;

    Thread moveSnake;
    volatile boolean exitMoveThread = false;

    public Snake(Point location, int speed, int score, MainPanel parent) {
        this.setOpaque(false);
        this.setLocation(location);
        this.speed = speed;
        this.score = score;
        this.setOpaque(false);
        this.dir = 'r';
        this.FIELD_TOP = 0; // we may modify this when we add Score Label
        this.FIELD_BUTTOM = parent.getHeight(); // we may modify this when we add Score Label
        this.FIELD_LEFT = 0;
        this.FIELD_RIGHT = parent.getWidth();
        this.parent = parent;
        initializeSegments(parent);
        this.addKeyListener(this);

        
        moveSnake = new Thread(() -> {
            while (!exitMoveThread) {

                    moveSnake();
                    eat();
                try {
                    Thread.sleep(speed);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
            

        });

    }


    public void incLength() {

    }

    public void decLength() {
   
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }


    public void eat() {
        
        for (Component c : this.parent.getComponents()){
            if (c instanceof Egg){
                if(this.getLocation()==c.getLocation()){
                        parent.remove(c);
                        break;
                }
            }
        }
}


    public boolean checkCrash() {
       return false; 
    }

    public void initializeSegments(MainPanel parent) {
        Random rndColor = new Random();
        s= new Segment(this, new Color(rndColor.nextInt(255),rndColor.nextInt(128),rndColor.nextInt(255)));
        int xLoc  = (int) getLocation().getX();//x coordinate of the snake
        int yLoc  = (int) getLocation().getY();//y coordinate of the snake

        s.setVisible(true);
        s.setBounds(xLoc +  s.getWidth()
                    ,yLoc, s.getWidth()
                    ,s.getHeight());

        parent.add(s);
        parent.repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_UP)
            if (this.s.getDir()=='l' || this.s.getDir()=='r')
                this.s.changeDir('u');

        if (e.getKeyCode() == KeyEvent.VK_DOWN)
            if (this.s.getDir()=='l' || this.s.getDir()=='r')
                this.s.changeDir('d');

        if (e.getKeyCode() == KeyEvent.VK_LEFT)
            if (this.s.getDir()=='d' || this.s.getDir()=='u')
                this.s.changeDir('l');

        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            if (this.s.getDir()=='d' || this.s.getDir()=='u')
                this.s.changeDir('r');
        
        
    
    }
    

    public void moveSnake(){
        this.s.move();
    }


    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    public MainPanel getParent() {
        return this.parent;
    }

}