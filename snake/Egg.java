package snake;

import java.util.Random;
import java.awt.Color;
import java.awt.Point;
import javax.swing.JLabel;

public class Egg extends JLabel{
        private Point location;
    
    public void generateEgg(MainPanel parent){
        Random r = new Random();
        Egg egg = new Egg();
   
        location = new Point(r.nextInt(parent.getWidth()-5),r.nextInt(parent.getHeight()-5));
        egg.setBackground(new Color (r.nextInt(254),r.nextInt(254),r.nextInt(254)));

        egg.setSize(5, 5);
        egg.setLocation(location);

        parent.add(egg);
        parent.revalidate();


    }

    public void setLocation(Point p){
        this.location=p;
    }
    public Point getLocation(){
        return this.location;
    }

    
}
