package testCalculator;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionListener;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 * Created by keen on 9/19/14.
 */
public class CalculatorView extends JFrame{

    private JTextField calcResult= new JTextField(20);
    private JButton clearButton=new JButton("C");
    private JButton divide=new JButton("/");
    private JButton multiply=new JButton("*");
    private JButton subtract=new JButton("-");
    private JButton add=new JButton("+");
    private JButton[] button=new JButton[10];
    private JButton dot=new JButton(".");
    private JButton enter=new JButton("=");
    private JButton fullClearButton=new JButton("AC");

    public CalculatorView(CalculatorListener listener){
        setGUI(listener);
        this.setVisible(true);
    }

    public void setGUI(ActionListener listener){
        JPanel mainPanel=new JPanel(new BorderLayout(2,2));
        JPanel allButtonsPanel=new JPanel(new GridLayout(1,2));
        JPanel buttonsPanel=new JPanel(new GridLayout(3,3,4,4));
        buttonsPanel.setBorder(new EmptyBorder(5,5,5,5));
        JPanel numbersPanel=new JPanel(new GridLayout(3,3,4,4));
        numbersPanel.setBorder(new EmptyBorder(5,5,5,5));
        this.setTitle("Calculator");
        this.setSize(370, 250);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        calcResult.setEditable(false);
        mainPanel.add(calcResult,BorderLayout.NORTH);
        calcResult.setHorizontalAlignment(SwingConstants.RIGHT);
        mainPanel.add(allButtonsPanel);

        mainPanel.setBorder(new EmptyBorder(5,5,5,5));
        allButtonsPanel.add(numbersPanel);
        allButtonsPanel.add(buttonsPanel);


        buttonsPanel.add(clearButton);
        buttonsPanel.add(fullClearButton);
        buttonsPanel.add(divide);
        buttonsPanel.add(multiply);
        buttonsPanel.add(subtract);
        buttonsPanel.add(add);
        for(int i=0;i<10;i++){
            button[i] = new JButton(String.valueOf(i));
            button[i].addActionListener(listener);
            if (i!=0)
                numbersPanel.add(button[i]);
        }
        buttonsPanel.add(button[0]);
        buttonsPanel.add(dot);
        buttonsPanel.add(enter);

        clearButton.addActionListener(listener);
        fullClearButton.addActionListener(listener);
        divide.addActionListener(listener);
        multiply.addActionListener(listener);
        subtract.addActionListener(listener);
        add.addActionListener(listener);
        dot.addActionListener(listener);
        enter.addActionListener(listener);

        this.add(mainPanel);
    }

    public void setCalcResult(String s){
        this.calcResult.setText(s);
    }

}
