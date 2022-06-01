
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicTreeUI.ComponentHandler;
import javax.tools.DocumentationTool.Location;

import org.w3c.dom.events.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;


import java.time.chrono.HijrahChronology;
import java.time.chrono.HijrahDate;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.MouseInputListener;
import static java.awt.geom.RoundRectangle2D.Float;


public class HijriForm extends JDialog{
    static Point location;
    HijriForm parent =this;
    private MyMenu menu ;
    private myLabel label;
    public HijriForm(){
        HijrahChronology hijriClaendar  = HijrahChronology.INSTANCE;
        String date = hijriClaendar.dateNow().toString();
        Toolkit t = Toolkit.getDefaultToolkit();
        Dimension d =  t.getScreenSize();

        double screenWidth  =  d.getWidth();
        double screenHeight  =  d.getHeight();

        int myWidth =  300;
        int myHeight =  30;
        this.setBackground(Color.green);
  
        
        this.setSize(new Dimension (myWidth,myHeight));
        this.setResizable(false);
        JPanel  panel = new JPanel();
        panel.setSize(this.getSize());
        label = new myLabel();
        label.setSize(this.getSize());
        
        label.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        panel.add(label);
        this.add(panel);

        Taskbar t1=  Taskbar.getTaskbar();

              double maxHeight =  GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getHeight();
              double taskbarHeight  =  screenHeight-maxHeight;

            
           
            this.setAlwaysOnTop(true);
            //this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            this.setUndecorated(true);
            this.setVisible(true);
           
            label.setFont(new Font("Times New Romans",Font.BOLD,15));
            label.setText(getDateFormat(date));
            label.setSize(label.getPreferredSize());
            myWidth= label.getWidth();
            myHeight =label.getHeight();
            location =  new Point((int) (screenWidth - myWidth), (int)(screenHeight-taskbarHeight-myHeight)-10);
            this.setLocation(location);
            panel.setSize(panel.getPreferredSize());
            this.setSize(this.getPreferredSize());
            label.repaint();
            panel.repaint();
            this.repaint();
            Point p = new Point((int)location.getX(),(int)location.getY()-100);
            menu = new MyMenu(p,parent);
            label.addMouseListener(new MouseListener() {
            
        @Override
        public void mouseClicked(java.awt.event.MouseEvent e) {

            System.out.println("Moused Clicked");
            menu.setVisible(menu.isVisible()?false:true);


            
        }

        @Override
        public void mousePressed(java.awt.event.MouseEvent e) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void mouseReleased(java.awt.event.MouseEvent e) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void mouseEntered(java.awt.event.MouseEvent e) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void mouseExited(java.awt.event.MouseEvent e) {
            // TODO Auto-generated method stub
            
        }
    });
    label.addMouseMotionListener(new MouseInputAdapter() {
        

        @Override 

        public void mouseMoved(java.awt.event.MouseEvent e) {
            if (menu.getLock().isSelected()){
                //menu.setVisible(false);
                
                for (int i = (int)parent.getX();i>=location.getX();i--){
                    parent.setLocation(i, (int)location.getY());
                    try {
                        Thread.sleep(3);
                    } catch (InterruptedException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
                menu.getLock().setSelected(false);
               }
            
        }
     
        
    });
        //    int i =  JOptionPane.showConfirmDialog(null,"Exit?","Confirm",JOptionPane.OK_CANCEL_OPTION);
        //    if (i==0)
        //        System.exit(0);
      }
      public myLabel getLabel (){
          return label;
      }

    public static void main(String[] args) {
        HijriForm hijri  =  new HijriForm();
   
    }

    private String getDateFormat(String date ){
        String months []  = {"محرم","صفر","ربيع اول","ربيع ثاني"
                            ,"جمادى اولى","جمادى اخرة","رجب","شعبان"
                            ,"رمضان","شوال","ذو القعدة","ذو الحجة"};
        
        String days [] = {"الاول","الثاني","الثالث","الرابع","الخامس","السادس","السابع","الثامن","التاسع","العاشر",
                         "الحادي عشر","الثاني عشر","الثالث عشر","الرابع  عشر","الخامس عشر","السادس عشر","السابع عشر","الثامن عشر","التاسع عشر","العشرون",  
                          "الحادي والعشرون","الثاني والعشرون","الثالث والعشرون","الرابع والعشرون","الخامس والعشرون","السادس والعشرون",
                          "السابع والعشرون","الثامن والعشرون","التاسع والعشرون","الثلاثون"};
                        
        date = date.split(" ")[2];
        int monthNumber = Integer.parseInt(date.split("-")[1]);
        int dayNumber   = Integer.parseInt(date.split("-")[2]);

        String month    =  months[monthNumber-1];
        
        
        String haram = (monthNumber==12 || monthNumber==11 || monthNumber==1 || monthNumber==7)?"   شهر حرام   ":"";
        
        
        String day      =   days[dayNumber-1];
        String year     =  date.split("-")[0];
        
       // JOptionPane.showMessageDialog(this, "هـــ"+ year  +  " لعام  " +  month +" من "+ day + "اليوه هو ", "التاريخ", JOptionPane.INFORMATION_MESSAGE);
        return day+  "  من " +  month+ haram +"  لعام "+ year + " هـــ  ";

    }

 
}