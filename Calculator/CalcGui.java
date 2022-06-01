package Calculator;

import javax.swing.*;
import java.awt.Point;
import java.awt.GridLayout;
import java.awt.Component;
import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


public class CalcGui extends JFrame {

    private MainPanle       mainPanel;
    public static String Op1="";
    public static String Op2="";
    public static String Opr="";

    public static Queue<String> Op1Queue = new ArrayDeque<>();
    public static Queue<String> OprQueue = new ArrayDeque<>();
    public static Queue<String> Op2Queue = new ArrayDeque<>();

    public CalcGui() throws FileNotFoundException, IOException{
            this.setLayout(new GridLayout(1,1));
            this.setSize(300,500);
            this.setTitle("Calculator");
       
            //Fix Application Path Using System.getProperty("user.dir");
            byte []  f =  new FileInputStream(new File(System.getProperty("user.dir")+"\\Resources\\calcIco.png")).readAllBytes();
           
            this.setIconImage((new ImageIcon(f)).getImage());
            this.setLocation(new Point(400,50));
            mainPanel =  new MainPanle(this);
            this.add(mainPanel);
            this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            this.revalidate();
            this.setVisible(true);
        }

    public static void main(String[] args) throws FileNotFoundException , IOException {
        CalcGui calculator =  new CalcGui();
        
        
    }

    public static void calculationForQueue(MainPanle output){

        System.out.println(Op1Queue.peek() + OprQueue.peek()+Op2Queue.peek()+"\r");
        Opr =  OprQueue.remove();
        System.out.println(Opr);
        switch(Opr){

            case "+":   
                Op1=String.format("%.4f", (Double.parseDouble(Op1Queue.remove())+Double.parseDouble(Op2Queue.remove())));

            break;
            case "-":   
                Op1=String.format("%.4f", (Double.parseDouble(Op1Queue.remove())-Double.parseDouble(Op2Queue.remove())));

            break;
            case "x":   
                Op1=String.format("%.4f", (Double.parseDouble(Op1Queue.remove())*Double.parseDouble(Op2Queue.remove())));
  
            break;
            case "\\":   
                Op1=String.format("%.4f", (Double.parseDouble(Op1Queue.remove())/Double.parseDouble(Op2Queue.remove())));

            break;


    }
    Op1Queue.add(Op1);
    System.out.println(Op1);
    output.getTextFieldPanel().getTextField().setText(Op1);
    }

    public static strictfp void calculation(MainPanle output){
        try{
        switch(Opr){
           
            case "+":   
                Op1=String.format("%.4f", (Double.parseDouble(Op1)+Double.parseDouble(Op2)));

                System.out.println(Op1);

               
            break;
            case "-":   
                Op1=String.format("%.4f", (Double.parseDouble(Op1)-Double.parseDouble(Op2)));
                System.out.println(Op1);
            break;
            case "x":   
                Op1=String.format("%.4f", (Double.parseDouble(Op1)*Double.parseDouble(Op2)));
                System.out.println(Op1);
            break;
            case "\\":   
                Op1=String.format("%.4f", (Double.parseDouble(Op1)/Double.parseDouble(Op2)));
                System.out.println(Op1);
            break;

        }
        output.getTextFieldPanel().getTextField().setText(Op1);
        Op2="";
        Opr="";
    }
    catch (Exception e){
        output.getTextFieldPanel().getTextField().setText("#Nan");
    }
        


    }
}






