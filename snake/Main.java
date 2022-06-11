package snake;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Main extends JFrame  {
    private MainPanel mainPanel;
    public Main(){

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(new Dimension (800,600));
        mainPanel = new MainPanel(this);
        this.add(mainPanel);
        this.setVisible(true);
        this.addKeyListener(mainPanel.getSanke());
        //this.setResizable(false);
       
    }
    public static void main(String[] args) {
        Main m = new Main();
    }
  

 
}
