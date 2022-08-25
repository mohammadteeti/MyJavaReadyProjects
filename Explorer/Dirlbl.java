package Explorer;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;



public class Dirlbl extends JPanel  {
    private int width ,height;
    private  JLabel lbl,icon ;
    private String  folderName , folderPath ;

    public Dirlbl(String folderName , String path ) throws IOException {
        lbl =new JLabel();
        icon = new JLabel();

        GridBagLayout gb =  new GridBagLayout();
        GridBagConstraints gc = new GridBagConstraints();


        this.width=150;
        this.height=50;
        this.folderName = folderName;
        this.folderPath = path;

        setLayout(gb);

        lbl.setSize(new Dimension (width,height));
        lbl.setText(folderName);

        icon.setSize(new Dimension (50,50));
        
        File f ;
        FileInputStream fis=null;
        try{
        
            f=new File (System.getProperty("user.dir")
            + "\\resourses\\icons\\folder.png");
        
            fis= new FileInputStream(f);

            icon.setIcon(new ImageIcon(fis.readAllBytes()));
        }catch (IOException e){
            
            JOptionPane.showMessageDialog(null, 
            "Icon Not Found ","Error",JOptionPane.ERROR_MESSAGE);

        }finally{
            if (fis!=null)
                fis.close();
        }

        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        lbl.setVisible(true);
        lbl.setVisible(true);
        
  
        gc.gridx=0;
        gc.gridy=0;
        gc.gridwidth=1;
        gc.gridheight=1;

        add(icon,gc);
    
        gc.gridx=1;
        gc.gridy=0;
        gc.gridwidth=2;
        gc.gridheight=1;
        add(lbl,gc);
        

        repaint();
        setVisible(true);
    }






}
