
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SpringLayout;
import javax.swing.UIManager;

/**
 *
 * @author moham
 */
public class Hijri_Installer extends JFrame {

    static String startUpPath;

    public static void main(String[] args) throws InterruptedException, IOException {
        Hijri_Installer h = new Hijri_Installer();
        h.setLocation(new Point(500, 200));
        h.setSize(300, 300);
        h.setResizable(false);
        h.getContentPane().setBackground(Color.BLACK);
        h.setTitle("Hijri Installer");
        File iconFile = new File (System.getProperty("user.dir")+"\\HijriForwindows\\ico.jpg");
        if (iconFile.exists()){
            byte []  ico =  new FileInputStream(iconFile).readAllBytes();
            h.setIconImage(new ImageIcon(ico).getImage());
        }
      
        startUpPath = System.getProperty("user.home") + "\\AppData\\Roaming\\Microsoft\\Windows\\Start Menu\\Programs\\Startup";
        
        JPanel panel = new JPanel(new GridLayout(2,1));
        JPanel barPanel = new JPanel(new GridLayout(3,1));
        barPanel.add(new JPanel());
     
       
        JPanel labelPanel =  new JPanel(new GridLayout(3,1));
        labelPanel.add(new JPanel());
        JLabel label  = new JLabel();
        JProgressBar bar = new JProgressBar();
        
        panel.setSize(h.getSize());
        panel.setLocation(h.getLocation());
        barPanel.setSize(panel.getWidth(),70);
        labelPanel.setSize(panel.getWidth(),100);
        labelPanel.setBorder(BorderFactory.createLineBorder(Color.yellow, 2));
        barPanel.setBorder(BorderFactory.createLineBorder(Color.yellow, 2));
        panel.add(labelPanel);
        panel.add(barPanel);

        
        bar.setForeground(Color.red);
        bar.setSize(80, 10);
        bar.setValue(0);
        bar.setVisible(true);
        label.setSize(50, 10);
        label.setVisible(true);
        bar.repaint();
        barPanel.add(bar);
        labelPanel.add(label);
        panel.repaint();
        barPanel.repaint();
        labelPanel.repaint();
        h.add(panel);
        h.repaint();
        h.setVisible(true);
        try {
            File file = new File(System.getProperty("user.dir") + "\\HijriForwindows");
            ArrayList<File> allFiles = new ArrayList<>();
            ArrayList<File> allFolders = new ArrayList<>();
            h.getFiles(allFiles, allFolders, file);
            System.out.println("Number of Files = " + allFiles.size());
            bar.setMaximum(allFiles.size());
            new File("C:\\Program Files\\HijriForwindows").mkdir();
            for (File f : allFolders) {
                new File("C:\\Program Files\\HijriForwindows\\" + f.getAbsolutePath().split("HijriForwindows")[1]).mkdir();
            }
            FileInputStream fis = null;
            FileOutputStream fos = null;
            for (File f : allFiles) {
                fos = new FileOutputStream("C:\\Program Files\\HijriForwindows" + f.getAbsolutePath().split("HijriForwindows")[1]);
                fis = new FileInputStream(f);
                byte[] data = fis.readAllBytes();
                fos.write(data);
                label.setText("Installing Files: "+ f.getName());
                bar.setValue(bar.getValue() + 1);
                
            }
            File shortcut = new File("C:\\Program Files\\HijriForwindows\\Hijri - Shortcut.lnk");
            fis = new FileInputStream(shortcut);
            fos = new FileOutputStream(new File(startUpPath + "\\Hijri - Shortcut.lnk"));
            fos.write(fis.readAllBytes());
            fis.close();
            fos.close();
            
            
            panel.removeAll();
            panel.setLayout(new GridLayout(3,1));
            JPanel empty  = new JPanel();
            empty.setSize(panel.getWidth(),100);
            panel.add(empty);
            label.setText("Launching Hijri Date Tool ...");
            label.setLocation(new Point(70, 101));
            panel.add(label);
            panel.repaint();
       
            while(!(new File (startUpPath + "\\Hijri - Shortcut.lnk").exists()));
            Runtime r = Runtime.getRuntime();
            Process p = r.exec("C:\\Program Files\\HijriForwindows\\Hijri.exe");
            if (p.isAlive()) {
                r.exit(0);
                System.exit(0);
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    private void getFiles(ArrayList<File> array, ArrayList<File> array2, File file) {
        
        if (file.listFiles() == null) {
            return;
        }
        
        for (File f : file.listFiles()) {
            if (f.isFile()) {
                array.add(f);
            }
            if (f.isDirectory()) {
                array2.add(f);
            }
            getFiles(array, array2, f);
            System.out.println(f.getAbsolutePath().split("HijriForwindows")[1]);
        }
    }
    
}
