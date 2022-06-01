package Calculator;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;

public class TitlePanel extends JPanel {

    private JLabel titLabel;

    public TitlePanel(Component parent){
        
        this.setPreferredSize(new Dimension(parent.getWidth(), 50));

        titLabel =  new JLabel();
        titLabel.setForeground(Color.black);
        titLabel.setSize(this.getWidth(), this.getHeight());
        titLabel.setText("Calculator");
        titLabel.setForeground(Color.cyan);
        titLabel.setOpaque(true);
        titLabel.setBackground(Color.black);
        titLabel.setFont(new Font("Courier",1,18));
        this.add(titLabel);
        this.setBorder(BorderFactory.createLineBorder(Color.red,1));
        this.revalidate();

    }


}