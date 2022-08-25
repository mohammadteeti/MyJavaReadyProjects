package snake;

import javax.lang.model.element.Element;
import javax.swing.JPanel;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

public class MainPanel extends JPanel implements KeyListener {
    Snake snake;
    Main parent;
    boolean eggExisit = false;
    public MainPanel(Main parent) {
        this.parent=parent;
        this.setLayout(null);
        this.setSize(parent.getWidth(),parent.getHeight()-70);
        this.setPreferredSize(this.getSize());
        this.setBackground(Color.BLACK);
        this.setVisible(true);
        this.setForeground(Color.cyan);
        this.setFont(new Font("Times New Romans",2,48));
        snake = new Snake(new Point(this.getWidth() / 2, this.getHeight() / 2), 50, 0, this);
        System.out.println("Snake: "+snake.getLocation().getX()+" " 
        +snake.getLocation().getY());
        snake.moveSnake.setPriority(Thread.MAX_PRIORITY-1);
        snake.moveSnake.start();
        this.addKeyListener(this);
        Thread addEgg = new Thread (()->{
            while(true){
                for(Component  c : this.getComponents()){
                    if(c instanceof Egg){
                        eggExisit=true;
                        break;
                    }
                }
                if(!eggExisit)
                    Egg.generateEgg(this);
            }

            
        });
        addEgg.start();
    }

    public Snake getSanke() {
        return this.snake;
    }
    
    public Main getParent() {
        return this.parent;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
       if(e.getKeyCode()==KeyEvent.VK_SHIFT)
                for(Component c : this.getComponents()){
                    // if(c instanceof Egg){
                        this.remove(c);
                       // break;
                    // }

                }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }
}
