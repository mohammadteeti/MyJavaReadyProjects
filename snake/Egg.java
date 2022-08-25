package snake;

import java.util.Random;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JLabel;

public class Egg extends JLabel{
        private Point location;
        public static String ico =new String(Character.toChars(0x1F95A));
        private Egg(String s){
            super(s);
        }
    public static void generateEgg(MainPanel parent){
        Random r = new Random();
        Egg egg = new Egg(ico);
        
        Rectangle rec = parent.getBounds();
        egg.location = new Point(r.nextInt(rec.width) ,r.nextInt(rec.height));
        System.out.println("Egg: " + egg.getLocation().getX() + " "
        +egg.getLocation().getY());
        egg.setBackground(new Color (r.nextInt(254),r.nextInt(254),r.nextInt(254)));
        egg.setFont(new Font("Times New Romans",2,48));
        egg.setForeground(Color.red);
        egg.setSize(80, 30);
        egg.setLocation(egg.location);

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
