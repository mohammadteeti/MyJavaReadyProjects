package Calculator;


import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;


public class ButtonPanel extends JPanel{
    private JButton btnOne =    new JButton("1");
    private JButton btnTwo =    new JButton("2");
    private JButton btnThree =  new JButton("3");
    private JButton btnPlus =   new JButton("+");
    private JButton btnFour =   new JButton("4");
    private JButton btnFive =   new JButton("5");
    private JButton btnSix =    new JButton("6");
    private JButton btnMinus =  new JButton("-");
    private JButton btnSeven =  new JButton("7");
    private JButton btnEight =  new JButton("8");
    private JButton btnNine =   new JButton("9");
    private JButton btnDivision = new JButton("\\");
    private JButton btnZero =     new JButton("0");
    private JButton btnMultpy =   new JButton("x");
    private JButton btnEqual =   new JButton("=");
    private JButton btnOpenBracket =   new JButton("(");
    private JButton btnCloseBracket =   new JButton(")");
    private JButton btnBack =   new JButton("<");
    private JButton btnReset =   new JButton("C");
    private JButton btnPoint =   new JButton(".");

    private String input ;
    public ButtonPanel (Component parent,JTextField textField){

        this.setLayout(new GridLayout(4,5));
        this.setSize(parent.getWidth(),200);
        this.add(btnOne);
        this.add(btnTwo);
        this.add(btnThree);
        this.add(btnBack);
        this.add(btnReset);
        this.add(btnFour);
        this.add(btnFive);
        this.add(btnSix);
        this.add(btnPlus);
        this.add(btnOpenBracket);
        this.add(btnSeven);
        this.add(btnEight);
        this.add(btnNine);
        this.add(btnMinus);
        this.add(btnCloseBracket);
        this.add(btnZero);
        this.add(btnEqual);
        this.add(btnPoint);
        this.add(btnMultpy);
        this.add(btnDivision);

        for (Component b : this.getComponents()){
                if (b instanceof JButton){
                   JButton button = (JButton)b;
                   button.setSize(30,30);
                   button.setBackground(Color.black);
                   button.setForeground(Color.cyan);
                   button.setFont(new Font("Courier",0,18));
                   button.addActionListener(new ActionListener(){

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        input=button.getText();
                        switch (input)
                        {
                        case "1": case "2": case "3": case "4":case "5":
                        case "6": case "7": case "8": case "9": case "0":
                        if(CalcGui.Op1Queue.isEmpty())
                            CalcGui.Op1+=input;
                        else 
                            CalcGui.Op2+=input;
                        // if(CalcGui.Opr.equals(""))
                        //         CalcGui.Op1+=input;
                        // else 
                        //         CalcGui.Op2+=input;
                        textField.setText(textField.getText()=="0"?input:textField.getText()+input);


                        break;

                        case "+": case "-": case "x": case "\\": 
                          
                        if (CalcGui.Op1Queue.isEmpty())
                            CalcGui.Op1Queue.add(CalcGui.Op1);
                        else if(!CalcGui.Op2.isEmpty())
                            CalcGui.Op2Queue.add(CalcGui.Op2);
                        
                            CalcGui.OprQueue.add(input);
                        if (!CalcGui.Op2Queue.isEmpty()){
                            CalcGui.calculationForQueue((MainPanle) parent);
                            CalcGui.Op1="";
                            CalcGui.Op2="";

                        }else if (CalcGui.Op2Queue.isEmpty() && CalcGui.OprQueue.size()>1){
                            CalcGui.OprQueue.remove();
                            CalcGui.OprQueue.remove();
                            CalcGui.OprQueue.add(input);
                            textField.setText(CalcGui.Op1Queue.peek()+input);
                            break;
                        }
                        
                        // if (CalcGui.Opr.equals(""))
                        //     CalcGui.Opr=input;
                        // else
                        //     if(!CalcGui.Op2.equals("")){
                        //         CalcGui.calculation((MainPanle)parent);
                        //         CalcGui.Opr=input;
                        //     }

                            textField.setText(textField.getText()+input);
                            
                        break;

                        case "=":
                            if(!CalcGui.Op1.equals("") 
                                && !CalcGui.Op2.equals("") && !CalcGui.Opr.equals("")){
                                    CalcGui.calculation((MainPanle)(parent));
                                }
                            break;

                        case "(": case ")":
                            textField.setText(textField.getText()+button.getText());
                      
                        break;

                        case "C":
                            textField.setText("0");
                            CalcGui.Op1="";
                            CalcGui.Op2="";
                            CalcGui.Opr="";

                        break;

                        case  "<":
                        if(textField.getText().length()==1)
                            textField.setText("0");
                        else
                            if(textField.getText().length()>0){
                                String s =  textField.getText();
                                int l = s.length();
                                s=s.substring(0, l-1);
                                textField.setText(s);
                        }
                        break;

                        case ".":
                            if(CalcGui.Opr.equals("")){
                                if(!CalcGui.Op1.contains(".")){
                                    CalcGui.Op1+=".";
                                    textField.setText(textField.getText()+".");
                            }}
                            else {
                                if(!CalcGui.Op2.contains(".")){
                                    CalcGui.Op2+=".";
                                    textField.setText(textField.getText()+".");
                                }
                            }
                            break;
                    }
                    
                        
                    }});

                    this.revalidate();
                  
                    
                }
        }
    }
    

}
