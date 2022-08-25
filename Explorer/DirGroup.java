package Explorer;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class DirGroup extends JPanel{

    private int width,height;
    private int folderCounts = 1;
    private JFrame parent ;
    public DirGroup(JFrame parent ){
        GridLayout grid =  new GridLayout(folderCounts,1);
        setLayout(grid);

        this.width=150;
        this.height=50;
        this.parent=parent;

        addMouseListener(new mouseInterface(){

            @Override 
            public void mousePressed (MouseEvent e){
                // add the logic here 
                grid.setRows(++folderCounts);
                setLayout(grid);
                try {
                add(new Dirlbl("FOlder"+folderCounts, "folderPath"));
                parent.revalidate();
                parent.repaint();
                
            }

                catch (IOException ioException){
                    JOptionPane.showMessageDialog(null, "Icon Error","Error",
                    JOptionPane.ERROR_MESSAGE);

                }
            }
        });
        setBorder(BorderFactory.createLineBorder(Color.BLUE));
        repaint();
        setVisible(true);
        
    }

}
