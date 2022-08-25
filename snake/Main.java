package snake;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.JFrame;

public class Main extends JFrame  {
    private MainPanel mainPanel;
    private ScorePanel scorePanel;
    public Main(){

        this.setSize(new Dimension (800,600));
        this.setPreferredSize(this.getSize());
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        mainPanel = new MainPanel(this);
        scorePanel = new ScorePanel(this);
        GridBagConstraints gc = new GridBagConstraints();

        gc.gridx=0;
        gc.gridy=0;
        gc.weighty=0.3;
        gc.fill=GridBagConstraints.BOTH;
        gc.weighty=0.1;

        this.add(scorePanel,gc);
        

        gc.gridy=GridBagConstraints.RELATIVE;
        gc.weightx=GridBagConstraints.HORIZONTAL;
        gc.weighty=0.9;
        this.add(mainPanel,gc);

        this.setVisible(true);
        this.addKeyListener(mainPanel.getSanke());
        //this.setResizable(false);
       
    }
    public static void main(String[] args) {
        Main m = new Main();
    }
  

 
}
