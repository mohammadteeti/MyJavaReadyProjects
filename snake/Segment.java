package snake;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Dimension;


public class Segment extends JLabel  implements Movable{

    private  int width = 70;
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

    public void  changeDir(char dir){ // we need to change Location as well 
        /* What i Don't get till know is that replacing width and height in
         * Dimension () dosnt work , so I had to swap them as below :
         */

        int xLoc =  (int)  this.getLocation().getX();
        int yLoc =  (int)  this.getLocation().getY();

        int w =this.width;
        int h = this.height;

        /* swap width and height */
        int temp = width;
        width=height;
        height=temp;



        switch(dir){

        //setBounds() changes the Size and Location of the top-left (x,y) corner
        //of the object.
         
            case 'l':
                if(this.getDir()=='u')
                    this.setBounds(xLoc-(h-w),yLoc+(h-w),w,h);
                if(this.getDir()=='d')
                    this.setBounds(xLoc-(h-w),yLoc,w,h);
            break;
            case 'r':
                if(this.getDir()=='u')
                    this.setBounds(xLoc,yLoc+(h-w),w,h);
                if(this.getDir()=='d')
                    this.setBounds(xLoc,yLoc,w,h);
                
            break;

            case 'u':
                if(this.getDir()=='l') 
                    this.setBounds(xLoc+(w-h),yLoc-(w-h),w,h); 
                if(this.getDir()=='r') 
                    this.setBounds(xLoc,yLoc-(w-h),w,h);
            break;
            case 'd':
                if(this.getDir()=='l') 
                    this.setBounds(xLoc+(w-h),yLoc,w,h); 
                if(this.getDir()=='r') 
                    this.setBounds(xLoc,yLoc,w,h);
            break;
           
        }

        this.setDir(dir);
        parent.repaint();
        parent.getParent().repaint();

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
        this.setBounds(xLoc - 10, yLoc,this.getWidth(),this.getHeight());
    }

    @Override
    public  void moveRight(){
        int xLoc = (int) this.getLocation().getX();
        int yLoc = (int) this.getLocation().getY();
        this.setBounds(xLoc + 10, yLoc,this.getWidth(),this.getHeight());
    }

    @Override
    public  void moveUp(){
        int xLoc = (int) this.getLocation().getX();
        int yLoc = (int) this.getLocation().getY();
        this.setBounds(xLoc,yLoc-10,this.getWidth(),this.getHeight());
    }

    @Override
    public  void moveDown(){
        int xLoc = (int) this.getLocation().getX();
        int yLoc = (int) this.getLocation().getY();
        this.setBounds(xLoc,yLoc+10,this.getWidth(),this.getHeight());
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
        parent.getParent().repaint();
        
    }
    
  
}
