/*by Mohammad J Teeti
*/
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.table.JTableHeader;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.awt.Color;
import java.awt.Component;
public class MyMenu extends JPopupMenu{

    private JButton exitItem =  new JButton("خروج");
    private JCheckBox  lock =  new JCheckBox("إخفاء/إظهار");
    private JCheckBox  changeColor = new JCheckBox("عكس اللون");
    MyMenu thisMenu =this;
    private double parentX ;
    private double parentY;

    public MyMenu(Point p , HijriForm parent){
        this.setLocation(p);
        parentX =parent.getLocation().getX();
        parentY =parent.getLocation().getY();
        exitItem.setPreferredSize(new Dimension(80,50));
        exitItem.setBorderPainted(false);

        lock.setPreferredSize(new Dimension(180,50));
        lock.setBorderPainted(false);
        lock.setSelected(false);

        changeColor.setPreferredSize(new Dimension(180,50));
        changeColor.setBorderPainted(false);
        changeColor.setSelected(false);

        this.setPopupSize(lock.getPreferredSize().width,100);


        
        this.add(lock);
        this.add(changeColor);
        this.add(exitItem);
        
        exitItem.addActionListener((e) -> {
            int i =  JOptionPane.showConfirmDialog(null,"هل تريد الخروج؟","تأكيد",JOptionPane.OK_CANCEL_OPTION);
            if (i==0)
                System.exit(0);
         

        });

        lock.addActionListener((e)->{

            if (lock.isSelected()){
                thisMenu.setVisible(false);
                for (int i = (int)parentX;i<=parent.getWidth()+parentX-30;i++){
                    parent.setLocation(i, (int)parentY);
                    try {
                        Thread.sleep(3);
                    } catch (InterruptedException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
            }
        });

        changeColor.addActionListener((e)->{

            if (changeColor.isSelected()){
            
                parent.getLabel().setForeground(Color.black);
                parent.getLabel().setBackground(Color.white);
            }
            else{
                parent.getLabel().setBackground(Color.black);
                parent.getLabel().setForeground(Color.white);
            }
            thisMenu.setVisible(false);
        });
    }

    public JCheckBox getLock(){
        return lock;
    }

 
    
}