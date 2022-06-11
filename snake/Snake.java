package snake;


import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JComponent;

public class Snake extends JComponent implements KeyListener {
    private MainPanel parent;
    private Queue<Segment> queue = new LinkedList<>();

   
    private int numberOfSegments;
    private int speed = 10;
    private int score;
    private char dir;

    final int FIELD_TOP;
    final int FIELD_BUTTOM;
    final int FIELD_LEFT;
    final int FIELD_RIGHT;

    Thread moveSnake ;
    volatile boolean  exitMoveThread =false;
    public Snake(Point location, int speed, int score, MainPanel parent) {

        this.setLocation(location);
        this.numberOfSegments = 3;
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
        
        moveSnake = new Thread(()->{
            while(!exitMoveThread){
                int numberOFSegToRem =0;
                for(Segment s : queue){
                        s.move();
                        numberOFSegToRem+= checkCrash(s);
                }
                for (int i=0;i<numberOFSegToRem;i++)
                        queue.remove();

        
                if (queue.isEmpty())
                    exitMoveThread=true;

                try{Thread.sleep(speed);}catch (InterruptedException e){
                    System.out.println(e.getMessage());
                }
           }
        
        
        });
    
        moveSnake.setDaemon(true); // background motion all the day
        
       

    }

    public Queue<Segment> getQueue() {
        return this.queue;
    }

    public void incLength() {
        if (queue.isEmpty())
            return;
        Random r =  new Random();
        Segment s = new Segment(this, 
        new Color(r.nextInt(254),
                  r.nextInt(254),
                  r.nextInt(128)));
        
        Segment lastSeg=null;
        Iterator<Segment> itr =  queue.iterator();
        /* Get The Last seg in the Queu to add after it  */
        while(itr.hasNext())
                lastSeg=itr.next();
        
        int xLoc  = (int) lastSeg.getLocation().getX();//x coordinate of the last seg
        int yLoc  = (int) lastSeg.getLocation().getY();//y coordinate of the last seg

        switch (lastSeg.getDir()){ // Switch according to last seg dimension
            case 'u':
                s.setDir('u');
                s.setBounds(xLoc , yLoc-s.getHeight(), s.getWidth(),
                s.getHeight());
            break;

            case 'd':
                s.setDir('d');
                s.setBounds(xLoc , yLoc+s.getHeight(), s.getWidth(),
                s.getHeight());
            break;

            case 'r':
                s.setDir('r');
                s.setBounds(xLoc-s.getWidth() , yLoc, s.getWidth(),
                s.getHeight());
            break;

            case 'l':
                s.setDir('l');
                s.setBounds(xLoc+s.getWidth() ,yLoc, s.getWidth(),
                s.getHeight());
            break;
        }

        s.setVisible(true);
        parent.add(s);
        queue.add(s);
        numberOfSegments++;
        parent.repaint();
    }

    public  void decLength()  {
            if (queue.isEmpty())
                return;
        
            parent.remove(queue.remove());
            if(!queue.isEmpty()){
                queue.peek().setBackground(Color.red);
                numberOfSegments--;
            }
            else{
                numberOfSegments--;
            }
            parent.repaint();
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

    public void setNumberOfSegment(int numberOfSegments) {
        this.numberOfSegments = numberOfSegments;
    }

    public int getNumberOfSegment() {
        return this.numberOfSegments;
    }





    public void eat(){

    }


/**
 * 
 * @param s - segment
 * @return numberOFSegToRem - number Of segments to be removed - the moveSnake Thread then removes queue peeks
 *          as many as numberOFSegToRem
 */
    public int checkCrash(Segment s) {
        boolean remove =false;
        int numberOFSegToRem=0;
        switch (s.getDir()){
            case 'r':
                if (s == queue.peek() && s.getLocation().getX() ==FIELD_RIGHT)
                    remove=true;

                break;
            case 'l':
                if (s == queue.peek() && s.getLocation().getX() ==FIELD_LEFT)
                    remove=true;

                break;
            case 'd':
                if (s == queue.peek() && s.getLocation().getY() ==FIELD_BUTTOM)
                    remove=true;

                break;
            case 'u':
                if (s == queue.peek() && s.getLocation().getY() ==FIELD_TOP)
                    remove=true;

                break;
            }
            if(remove){
                parent.remove(s);
                numberOFSegToRem++;
                numberOfSegments--;
            }
            return numberOFSegToRem;
        }

    public void initializeSegments(MainPanel parent){
        Random r = new Random();
        for (int i = 0; i < getNumberOfSegment(); i++){
            Segment s = new Segment(this, 
            new Color(r.nextInt(254),
                      r.nextInt(254),
                      r.nextInt(254))); // Direction is set in Segment Constructor

            int xLoc  = (int) getLocation().getX();//x coordinate of the snake
            int yLoc  = (int) getLocation().getY();//y coordinate of the snake

            s.setVisible(true);
            s.setBounds(xLoc + (i * s.getWidth())
                        ,yLoc, s.getWidth()
                        ,s.getHeight());

            parent.add(s);
            parent.repaint();
            queue.add(s);
        }
        queue.peek().setBackground(Color.red);
    }

 


    @Override
    public  void keyPressed(KeyEvent e){
        if(e.getKeyCode() == e.VK_LEFT){
            for (Segment s : queue) {
                if(s.getDir() != 'l' && s.getDir()!='r')
                    s.changeDir('l');
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
            }
        }

        if(e.getKeyCode() == e.VK_RIGHT){
            for (Segment s : queue) {
                if(s.getDir() != 'l' && s.getDir()!='r')
                    s.changeDir('r');
                try {
                        Thread.sleep(50);
                    } catch (InterruptedException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
            }
       
        }

        if(e.getKeyCode() == e.VK_UP){
            for (Segment s : queue) {
                if(s.getDir() != 'u' && s.getDir()!='d')
                    s.changeDir('u');
                try {
                        Thread.sleep(50);
                    } catch (InterruptedException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
            }
       
        }

        if(e.getKeyCode() == e.VK_DOWN){
            for (Segment s : queue) {
                if(s.getDir() != 'u' && s.getDir()!='d')
                    s.changeDir('d');
                try {
                        Thread.sleep(50);
                    } catch (InterruptedException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
            }
       
        }
    }
    

    public void move(){
        synchronized (queue){ 
        for (Segment s : queue){
           
                    s.move();
            }
        }
        
    }



    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }
    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }

}
