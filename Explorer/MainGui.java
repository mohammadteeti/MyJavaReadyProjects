package Explorer;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.GridLayout;
import java.awt.GridBagLayout; 
import java.awt.GridBagConstraints;

public class MainGui extends JFrame{
    
    private int width = 800,height=600;
    
    public MainGui(){

        GridBagLayout gb =  new GridBagLayout ();
        GridBagConstraints gc =  new GridBagConstraints();
         
        /* Layout should be changed to GridBagLayout */
        setLayout(gb); // on column for folder explorer
                                                   //the other column for folder content
        gc.gridx=0;
        gc.gridy=0;
        gc.gridwidth=1;
        gc.gridheight=1;
      
        add(new DirGroup(this),gc);

        gc.gridx=1;
        gc.gridy=0;
        gc.gridwidth=2;
        gc.gridheight=1;

        add(new JLabel("Content Here!"),gc);
        repaint();
        setVisible(true);
        setSize(new Dimension(width, height));
        setTitle("Explorer");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
    }   

    public static void main(String[] args) {
        new MainGui();

    }
}
