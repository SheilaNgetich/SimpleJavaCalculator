/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Calculator;

/**
 *
 * @author Sheila
 */
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class Calculator {

    //frame dimensions
    int wWidth = 360;
    int wHeight = 450;

    //creating the frame, display label and panels
    JFrame frame = new JFrame("Calculator");
    JLabel displayLabel = new JLabel();
    JPanel displayPanel = new JPanel();
    JPanel buttonsPanel = new JPanel();

    //creating a string array of numbers
    String[] buttonValues = {
        "AC", "+/-", "%", "÷",
        "7", "8", "9", "×",
        "4", "5", "6", "-",
        "1", "2", "3", "+",
        "0", ".", "√", "="
    };

    //variables to keep track of the numbers
    String a = "0";
    String b = null;
    String operator = null;

    String[] rightSymbols = {"÷", "×", "-", "+", "="};
    String[] topSymbols = {"AC", "+/-", "%"};

    //colors calculator will use
    Color blushPink = new Color(255, 160, 180);
    Color white = new Color(245, 245, 245);
    Color softPink = new Color(255, 170, 185);
    Color black = new Color(0, 0, 0);
    Color lightGray = new Color(211, 211, 211);
    Color gray = new Color(50, 50, 50);

    //constructor for calculator class
    public Calculator() {

        frame.setSize(wWidth, wHeight);
        //censors the window
        frame.setLocationRelativeTo(null);
        //prevents the user from resizing the frame
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        //styling for the label
        displayLabel.setBackground(black);
        displayLabel.setForeground(white);
        displayLabel.setFont(new Font("Arial", Font.PLAIN, 80));
        displayLabel.setHorizontalAlignment(JLabel.RIGHT);
        displayLabel.setText("0");
        displayLabel.setOpaque(true);

        //adding the label into the panel
        //and adding the panel into the frame
        displayPanel.setLayout(new BorderLayout());
        frame.add(displayLabel, BorderLayout.NORTH);
        frame.add(displayPanel);

        //customizing the buttons panel
        //and adding the panel into the frame
        buttonsPanel.setLayout(new GridLayout(5, 4));
        buttonsPanel.setBackground(blushPink);
        frame.add(buttonsPanel);

        //adding buttons
        for (int i = 0; i < buttonValues.length; i++) {
            JButton button = new JButton();
            String buttonValue = buttonValues[i];
            //setting the font of the button
            button.setFont(new Font("Arial", Font.PLAIN, 30));
            button.setText(buttonValue);
            button.setFocusable(false);
            button.setBorder(new LineBorder(black));
            if (Arrays.asList(topSymbols).contains(buttonValue)) {
                button.setBackground(softPink);
                button.setForeground(black);
            } else if (Arrays.asList(rightSymbols).contains(buttonValue)) {
                button.setBackground(blushPink);
                button.setForeground(white);
            } else {
                button.setBackground(lightGray);
                button.setForeground(gray);
            }
            buttonsPanel.add(button);
            //adding an actionlistener
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //identifying the button that was clicked
                    JButton button = (JButton) e.getSource();
                    //Retrieving the Button's Text
                    String buttonValue = button.getText();
                    if (Arrays.asList(topSymbols).contains(buttonValue)) {// Handling AC, +/-, and % 

                        if (buttonValue.equals("AC")) {//clears all values
                            clearAll();
                            displayLabel.setText("0");
                        } else if (buttonValue.equals("+/-")) {//multiplies a number by -1
                            double displayNum = Double.parseDouble(displayLabel.getText());
                            displayNum *= -1;
                            displayLabel.setText(removeZeroDecimal(displayNum));
                        } else if (buttonValue.equals("%")) {//finds modulus
                            double displayNum = Double.parseDouble(displayLabel.getText());
                            displayNum /= 100;
                            displayLabel.setText(removeZeroDecimal(displayNum));
                        }
                    } else if (Arrays.asList(rightSymbols).contains(buttonValue)) {// Handle ÷, ×, -, +, and = 
                        if (buttonValue.equals("=")) {//returns result
                            if ( a != null) {
                                b = displayLabel.getText();//sets the value of b
                                double numA = Double.parseDouble(a);
                                double numB = Double.parseDouble(b);

                                if (operator.equals("+")) {
                                    displayLabel.setText(removeZeroDecimal(numA + numB));
                                } else if (operator.equals("-")) {
                                    displayLabel.setText(removeZeroDecimal(numA - numB));
                                } else if (operator.equals("×")) {
                                    displayLabel.setText(removeZeroDecimal(numA * numB));
                                } else if (operator.equals("÷")) {
                                    displayLabel.setText(removeZeroDecimal(numA / numB));
                                }
                                clearAll();
                            }
                        } else if ("÷×-+".contains(buttonValue)) {
                            if (operator == null) {
                                a =  displayLabel.getText();//sets value of a
                                displayLabel.setText("0");
                                b = "0";
                            }
                            operator = buttonValue;//sets value of operator 
                        }
                    } else {
                        if (buttonValue.equals(".")) {
                            if (!displayLabel.getText().contains(buttonValue)) {//Only allow one decimal point
                                displayLabel.setText(displayLabel.getText() + buttonValue);//allows you to write text after it
                            }
                        } else if ("0123456789".contains(buttonValue)) {// Check's if it's a number
                            if (displayLabel.getText().equals("0")) {//Check's if its equal to zero
                                displayLabel.setText(buttonValue);//displays zero
                            } else {//if not it allows you to add numbers after each other
                                displayLabel.setText(displayLabel.getText() + buttonValue);
                            }
                        } else if (buttonValue.equals("√")) {//if you press a squareroot
                            displayLabel.setText(displayLabel.getText() + buttonValue);//allows you to write text after it
                        }
                    }

                }

            });
            frame.setVisible(true);
        }

    }

    //clear method
    void clearAll() {
        a = "0";
        b = null;
        operator = null;
    }

    //removeZeroDecimal method
    String removeZeroDecimal(double displayNum) {
        if (displayNum % 1 == 0) {
            return String.valueOf((int) displayNum);
        }
        return String.valueOf(displayNum);
    }

}
