package ScreenRecord;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.Robot;
import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
public class Main extends JFrame {
    
    public Main() {
    }
    public static void main(String[] args) throws AWTException, IOException  {
       //Mp4Format m = new Mp4Format(new Dimension(500,500));

       Robot  r  =  new Robot();
       Rectangle rec =  new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());

       BufferedImage img  = r.createScreenCapture(rec);
       boolean found = ImageIO.write(img,"jpg",new File(System.console().readLine()+".jpg"));
        System.out.println(found);
       
    


    }
    
}
