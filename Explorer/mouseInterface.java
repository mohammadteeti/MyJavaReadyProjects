package Explorer;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
  
    /*  I created this abstract method to limit the implementation on
     * the mouse pressed event only 
     * as the MouseListener interface contains alot of methods to implement
     * on a regualr cioncrete class 
     * 
     */
public class mouseInterface implements  MouseListener {
  
    @Override 
    public void mousePressed(MouseEvent  e){
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
}
