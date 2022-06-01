package Calculator;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import java.awt.Component;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Color;

public class TextFieldPanel extends JPanel{
    private JTextField textField;

    public TextFieldPanel(Component parent){
        textField =  new JTextField("0");
        textField.setFont(new Font("Symbole",1,18));
        
        this.setSize(parent.getWidth(),100);
        textField.setPreferredSize(new Dimension(this.getWidth()-20,30));
        textField.setEditable(true);
        textField.setEnabled(true);
        textField.setForeground(Color.cyan);
        textField.setBackground(Color.black);
        textField.revalidate();
        this.add(textField);
        this.setBorder(BorderFactory.createLineBorder(Color.red,1));

        this.revalidate();
    }
    public JTextField getTextField(){
        return textField;
    }
}