package Calculator;

import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Color;

public class MainPanle extends JPanel {

    private TitlePanel     titlePanel;
    private TextFieldPanel txtFieldPanel;
    private ButtonPanel  firstRowPanel;


    public MainPanle(Component parent){

        super(new GridBagLayout());
        this.setSize(parent.getSize());
        titlePanel    =  new TitlePanel(this);
        txtFieldPanel =  new TextFieldPanel(this);
        firstRowPanel =  new ButtonPanel(this,(JTextField)txtFieldPanel.getComponent(0));
        GridBagConstraints c  = new GridBagConstraints();
       
        c.gridx = 0;
        c.gridy=0;
        c.weighty=0.15;
        c.weightx=1;
        c.gridwidth=GridBagConstraints.RELATIVE;
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(titlePanel,c);

        c.gridx = 0;
        c.gridy=1;
        c.weighty=0.15;
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(txtFieldPanel,c);

        c.gridx = 0;
        c.gridy=2;
        c.weighty=0.3;
        c.gridwidth = GridBagConstraints.RELATIVE;
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(firstRowPanel,c);
        this.setBackground(Color.gray);
        this.revalidate();


    }

    public TextFieldPanel getTextFieldPanel(){
        return txtFieldPanel;
    }

}