package ScreenRecord;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.MouseInputAdapter;
import javax.swing.filechooser.FileNameExtensionFilter;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Screen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.awt.Dimension;
import java.lang.Thread;

public class Mp4Format extends JFrame {

    private JPanel mainPanel;
    private JScrollPane formatPanel;
    private JPanel buttonsPanel;
    private JPanel barPanel;
    private JTextArea formatArea;

    private JButton btnOpen;
    private JButton btnExit;
    private JButton btnSave;
    private JLabel fileDetails = new JLabel();
    private JProgressBar bar;
    private File openedFile;
    private byte[] fileData;

    public Mp4Format(Dimension d) {
        this.setSize(d);
        mainPanel = new JPanel(new GridLayout(3, 1));
        buttonsPanel = new JPanel(new GridLayout(3, 5));
        barPanel = new JPanel(new GridLayout(3, 1));
        btnExit = new JButton("Exit!");
        btnOpen = new JButton("Open File");
        btnSave = new JButton("Save File");

        formatArea = new JTextArea();
        formatArea.setWrapStyleWord(true);
        formatPanel = new JScrollPane(formatArea);
        formatPanel.setAutoscrolls(true);
        mainPanel.setPreferredSize(this.getPreferredSize());
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.red, 2));

        int w = (int) mainPanel.getPreferredSize().getWidth();
        int h = (int) (mainPanel.getPreferredSize().getHeight() * 0.7);

        formatPanel.setPreferredSize(new Dimension(w, h));
        formatPanel.setBorder(BorderFactory.createLineBorder(Color.red, 2));
        formatArea.setPreferredSize(formatPanel.getSize());

        mainPanel.add(formatPanel);

        h = (int) (mainPanel.getPreferredSize().getHeight() * 0.15);
        buttonsPanel.setPreferredSize(new Dimension(w, h));
        btnExit.setVisible(true);
        btnOpen.setVisible(true);
        btnSave.setVisible(true);
        JButton b1 = new JButton();
        JButton b2 = new JButton();
        JButton b3 = new JButton();
        JButton b4 = new JButton();
        JButton b5 = new JButton();
        JButton b6 = new JButton();
        JButton b7 = new JButton();
        JButton b8 = new JButton();
        JButton b9 = new JButton();
        JButton b10 = new JButton();

        b1.setVisible(false);
        b2.setVisible(false);
        b3.setVisible(false);
        b4.setVisible(false);
        b5.setVisible(false);
        b6.setVisible(false);
        b7.setVisible(false);
        b8.setVisible(false);
        b9.setVisible(false);
        b10.setVisible(false);
        bar = new JProgressBar();
        bar.setMinimum(0);
        bar.setForeground(Color.GREEN);
        bar.setBackground(Color.WHITE);

        bar.setVisible(true);
        bar.setValue(0);
        bar.setStringPainted(true);

        buttonsPanel.add(b1);
        buttonsPanel.add(b2);
        buttonsPanel.add(b3);
        buttonsPanel.add(b4);
        buttonsPanel.add(b5);
        buttonsPanel.add(btnExit);
        buttonsPanel.add(b6);
        buttonsPanel.add(btnSave);
        buttonsPanel.add(b7);
        buttonsPanel.add(btnOpen);
        // buttonsPanel.add(bar);
        buttonsPanel.add(b8);
        buttonsPanel.add(b9);
        buttonsPanel.add(b10);

        mainPanel.add(buttonsPanel);
        barPanel.setPreferredSize(new Dimension(w, h));
        barPanel.add(fileDetails);
        barPanel.add(bar);
        barPanel.add(new JLabel(""));
        mainPanel.add(barPanel);

        this.setLocation(new Point(500, 200));
        this.add(mainPanel);
        this.revalidate();
        this.setVisible(true);

        addExitEvent();
        addOpenEvent();

    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JScrollPane getFormatPanel() {
        return formatPanel;
    }

    public JPanel getButtonPanel() {
        return buttonsPanel;
    }

    public JTextArea getTextArea() {
        return formatArea;
    }

    public JButton getBtnOpen() {
        return btnOpen;
    }

    public JButton getBtnSave() {
        return btnSave;
    }

    public JButton getBtnExit() {
        return btnExit;
    }

    private void addExitEvent() {
        getBtnExit().addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                System.exit(0);
            }
        });
    }

    private void addOpenEvent() {
        getBtnOpen().addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                JFileChooser open = new JFileChooser(System.getProperty("user.home"));
                FileNameExtensionFilter filter = new FileNameExtensionFilter("MP4  Files", "mp4");
                open.setAcceptAllFileFilterUsed(false);
                open.setFileSelectionMode(JFileChooser.FILES_ONLY);
                open.setMultiSelectionEnabled(false);
                open.setFileFilter(filter);

                open.setVisible(true);
                int state = open.showOpenDialog(getMainPanel());
                if (state == JFileChooser.APPROVE_OPTION) {
                    openedFile = open.getSelectedFile();
                    System.out.println(openedFile.getAbsolutePath());
                    getVideo(openedFile);
                    // Thread t = new Thread(new Runnable() {

                    //     @Override
                    //     public void run() {
                    //         // TODO Auto-generated method stub
                    //         try {

                    //             FileInputStream fs = new FileInputStream(openedFile);
                    //             fileData = fs.readAllBytes();
                    //             // bar.setMaximum(fileData.length);
                    //             double size = fileData.length < 1000000 ? fileData.length / 1000.0
                    //                     : fileData.length / 1000000.0;
                    //             fileDetails.setText("File: " + openedFile.getAbsolutePath() + " \t " + "Size: " + size
                    //                     + (fileData.length < 1000000 ? "KB" : "MB"));
                    //             showFileDump(fileData);
                    //             fs.close();
                    //         } catch (IOException erro) {
                    //             System.err.println(erro.getStackTrace());
                    //         }
                    //     }

                    // });
                    // t.start();

                }
            }
        });
    }

    // Views HEX data in TextArea
    private void showFileDump(byte[] data) {
        int col = 0;
        int rows = 0;
        StringBuilder result = new StringBuilder("");
        for (int i = 0; i < data.length; i++) {

            bar.setString(String.format("%s%.2f%s", "Reading File ... ", (100f * i * 1f) / data.length, "%"));
            bar.setValue((int) ((100f * i * 1f) / data.length));
            result.append(String.format("%H", data[i] & 0xff) + "\t");
            col++;
            if (col > 15) {
                result.append("\n");
                col = 0;
                rows++;
            }

        }

        formatArea.setText(result.toString());
        formatArea.setColumns(16);
        formatArea.setRows(rows + 1);
        bar.setString("Done!");
        result = null;
        System.gc();
        
    }

    private void getVideo(File video_source){
        JFXPanel videoPanel = new JFXPanel();
        videoPanel.setPreferredSize(formatArea.getPreferredSize());
        final JFXPanel VFXPanel = new JFXPanel();
    
        
        Media m = new Media(video_source.toURI().toString());
        MediaPlayer player = new MediaPlayer(m);
        MediaView viewer = new MediaView(player);
    
        StackPane root = new StackPane();
        Scene scene = new Scene(root);
    
        // center video position
        javafx.geometry.Rectangle2D screen = Screen.getPrimary().getVisualBounds();
        viewer.setX((screen.getWidth() - videoPanel.getWidth()) / 2);
        viewer.setY((screen.getHeight() - videoPanel.getHeight()) / 2);
    
        // resize video based on screen size
        DoubleProperty width = viewer.fitWidthProperty();
        DoubleProperty height = viewer.fitHeightProperty();
        width.bind(Bindings.selectDouble(viewer.sceneProperty(), "width"));
        height.bind(Bindings.selectDouble(viewer.sceneProperty(), "height"));
        viewer.setPreserveRatio(true);
    
        // add video to stackpane
        root.getChildren().add(viewer);
    
        VFXPanel.setScene(scene);
        //player.play();
        videoPanel.setLayout(new BorderLayout());
        videoPanel.add(VFXPanel, BorderLayout.CENTER);
        mainPanel.add(videoPanel);
    }

}
