package snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScorePanel extends JPanel{
    private JLabel scroeLabel ;
    private String heart  = new String(Character.toChars(0x1F49B));
    public ScorePanel(JFrame parent){
        this.setSize(parent.getWidth(),70);
        this.setPreferredSize(this.getSize());
        this.setOpaque(true);
        this.setBackground(Color.gray);
        setLayout(new GridLayout(1,2));
        scroeLabel= new JLabel(heart+heart+heart+heart+heart);
        scroeLabel.setSize(100, 50);
        scroeLabel.setOpaque(false);
        scroeLabel.setFont(new Font("Times New Romans",2,48));
        scroeLabel.setForeground(Color.red);
        scroeLabel.setLocation(0, 0);
        this.add(scroeLabel);
        this.setVisible(true);

        
    }
    public JLabel getScoreLabel(){
        return this.scroeLabel;
    }

    
}
