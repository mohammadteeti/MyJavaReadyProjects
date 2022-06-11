package snake;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

public class Segment extends JLabel  implements Movable{

    private  int width = 80;
    private  int height = 10;
    private Snake parent ;
    private char dir ;

    public Segment(Snake parent,Color color){

        this.setOpaque(true);
        this.setSize(new Dimension(width, height));
        this.setBackground(color);
        this.setBorder(BorderFactory.createEtchedBorder(1));
        this.setVisible(true);
        this.parent=parent;
        this.dir = 'r'; // Initially ,each Segment is directed to Right
    }

    public void changeDir(char dir){ // we need to change Location as well 
        /* What i Don't get till know is that replacing width and heigh in
         * Dimension () dosnt work , so I had to swap them as below :
         */
        int temp = width;
        width=height;
        height=temp;

        switch(dir){
            case 'l': 
            case 'r':
                this.setPreferredSize(new Dimension(this.width,this.height));
            break;

            case 'u': 
            case 'd':
                this.setPreferredSize(new Dimension(this.height,this.width));
            break;
        }

        this.setDir(dir);
    }


    public int getWidth(){
        return this.width;
    }

    public int getHeight(){
        return this.height;
    }

    @Override
    public  void moveLeft(){
        int xLoc = (int) this.getLocation().getX();
        int yLoc = (int) this.getLocation().getY();
        this.setLocation(new Point(xLoc - this.getWidth(), yLoc));
    }

    @Override
    public  void moveRight(){
        int xLoc = (int) this.getLocation().getX();
        int yLoc = (int) this.getLocation().getY();
        this.setLocation(new Point(xLoc + this.getWidth(),yLoc));
    }

    @Override
    public  void moveUp(){
        int xLoc = (int) this.getLocation().getX();
        int yLoc = (int) this.getLocation().getY();
        this.setLocation(new Point(xLoc,yLoc-this.getHeight()));
    }

    @Override
    public  void moveDown(){
        int xLoc = (int) this.getLocation().getX();
        int yLoc = (int) this.getLocation().getY();
        this.setLocation(new Point(xLoc, yLoc+this.getHeight()));
    }

    public void setDir(char dir){
        this.dir = dir;
    }

    public char getDir(){
        return this.dir;
    }

    @Override
    public  void move(){
        
        switch (getDir()){
            case 'u':
                moveUp();
            break;

            case 'd':
                moveDown();
            break;

            case 'l':
                moveLeft();
            break;

            case 'r':
                moveRight();
            break;
        }
    }
}
