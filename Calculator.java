import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame implements ActionListener {
    static JFrame frame;
    static JTextField field;
    String str, str1, str2;
    boolean isResultDisplayed; // Track if the result is displayed

    Calculator() {
        str = str1 = str2 = "";
        isResultDisplayed = false; // Initially, no result is displayed
    }

    public static void main(String args[]) {
        frame = new JFrame("Calculator");
        Calculator c = new Calculator();
        
        field = new JTextField(16);
        field.setEditable(false);
        field.setFont(new Font("Arial", Font.BOLD, 24));
        field.setHorizontalAlignment(JTextField.RIGHT);
        field.setPreferredSize(new Dimension(300, 50));

        JButton b0 = new JButton("0");
        JButton b1 = new JButton("1");
        JButton b2 = new JButton("2");
        JButton b3 = new JButton("3");
        JButton b4 = new JButton("4");
        JButton b5 = new JButton("5");
        JButton b6 = new JButton("6");
        JButton b7 = new JButton("7");
        JButton b8 = new JButton("8");
        JButton b9 = new JButton("9");

        JButton beq = new JButton("=");
        JButton ba = new JButton("+");
        JButton bs = new JButton("-");
        JButton bd = new JButton("/");
        JButton bm = new JButton("*");
        JButton bc = new JButton("C");
        JButton be = new JButton(".");
        JButton bsq = new JButton("√");
        JButton binv = new JButton("1/x");
        JButton blog = new JButton("Log");
        JButton bsin = new JButton("Sin");
        JButton bcos = new JButton("cos");
        JButton btan = new JButton("Tan");
        JButton bexp = new JButton("Exp");

        JButton bMC = new JButton("MC");
        JButton bMR = new JButton("MR");
        JButton bm1 = new JButton("M+");
        JButton bm2 = new JButton("M-");

        Font buttonFont = new Font("Arial", Font.PLAIN, 18);
        JButton[] buttons = {b0, b1, b2, b3, b4, b5, b6, b7, b8, b9, beq, ba, bs, bd, bm, bc, be, bsq, binv, blog, bsin, bcos, btan, bexp, bMC, bMR, bm1, bm2};
        
        for (JButton button : buttons) {
            button.setFont(buttonFont);
            button.addActionListener(c);
        }

        JPanel p = new JPanel();
        p.setLayout(new GridLayout(7, 4, 5, 5));

        // Add buttons to the panel
        p.add(bMR); p.add(bMC); p.add(bm1); p.add(bm2);
        p.add(b1); p.add(b2); p.add(b3); p.add(ba);
        p.add(b4); p.add(b5); p.add(b6); p.add(bs);
        p.add(b7); p.add(b8); p.add(b9); p.add(bm);
        p.add(b0); p.add(bsq); p.add(bexp); p.add(bd);
        p.add(bsin); p.add(btan); p.add(bcos); p.add(be);
        p.add(blog); p.add(bc); p.add(beq);

        frame.setLayout(new BorderLayout());
        frame.add(field, BorderLayout.NORTH);
        frame.add(p, BorderLayout.CENTER);

        p.setBackground(Color.lightGray);
        frame.setSize(400, 500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();

        if ((s.charAt(0) >= '0' && s.charAt(0) <= '9') || s.charAt(0) == '.') {
            if (isResultDisplayed) {
                str = "";  // Reset the first number if result was displayed
                str2 = ""; // Clear second number
                isResultDisplayed = false; // Reset the flag
            }
            if (!str1.equals("")) {
                str2 += s;  // Append to second number if operator is selected
            } else {
                str += s;   // Append to first number
            }
            field.setText(str + str1 + str2);
        } else if (s.charAt(0) == 'C') {
            str = str1 = str2 = "";
            field.setText("");
            isResultDisplayed = false; // Reset the flag
        } else if (s.charAt(0) == '=') {
            double te = 0;
            try {
                // Calculate based on operator
                if (str1.equals("+")) {
                    te = Double.parseDouble(str) + Double.parseDouble(str2);
                } else if (str1.equals("-")) {
                    te = Double.parseDouble(str) - Double.parseDouble(str2);
                } else if (str1.equals("/")) {
                    te = Double.parseDouble(str) / Double.parseDouble(str2);
                } else if (str1.equals("*")) {
                    te = Double.parseDouble(str) * Double.parseDouble(str2);
                } else if (!str1.equals("")) {
                    double input = str2.isEmpty() ? Double.parseDouble(str) : Double.parseDouble(str2);
                    if (str1.equals("Sin")) {
                        te = Math.sin(Math.toRadians(input));
                    } else if (str1.equals("cos")) {
                        // Handle cos(90) specifically
                        if (input == 90) {
                            te = 0; // cos(90) = 0
                        } else {
                            te = Math.cos(Math.toRadians(input));
                        }
                    } else if (str1.equals("Tan")) {
                        // Handle tan(90) specifically
                        if (input == 90) {
                            field.setText("Tan(90°) = undefined");
                            str = str1 = str2 = ""; // Reset for new input
                            return;
                        }
                        te = Math.tan(Math.toRadians(input));
                    } else if (str1.equals("Log")) {
                        te = Math.log(input);
                    } else if (str1.equals("√")) {
                        te = Math.sqrt(input);
                    } else if (str1.equals("1/x")) {
                        te = 1 / input;
                    }
                }
            } catch (Exception ex) {
                field.setText("Error");
                str = str1 = str2 = "";
                return;
            }

            // Display result and prepare for further operations
            field.setText(str + str1 + str2 + "=" + te);
            str = Double.toString(te); // Store result for further operations
            str1 = ""; // Clear operator
            str2 = ""; // Clear second number
            isResultDisplayed = true; // Set flag that result is displayed
        } else {
            // Handle function buttons
            if ("√ 1/x Log Sin cos Tan Exp".contains(s)) {
                if (!str.isEmpty()) {
                    actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "="));
                }
                str1 = s;  // Set the function as the current operation
                str2 = "";  // Clear the second number
                field.setText(str + str1);  // Update the display
                isResultDisplayed = false; // Reset the flag for further input
            } else {
                // Handle arithmetic operations
                if (str1.equals("") || str2.equals("")) {
                    str1 = s;
                } else {
                    double te = 0;
                    if (str1.equals("+")) {
                        te = Double.parseDouble(str) + Double.parseDouble(str2);
                    } else if (str1.equals("-")) {
                        te = Double.parseDouble(str) - Double.parseDouble(str2);
                    } else if (str1.equals("/")) {
                        te = Double.parseDouble(str) / Double.parseDouble(str2);
                    } else if (str1.equals("*")) {
                        te = Double.parseDouble(str) * Double.parseDouble(str2);
                    }
                    str = Double.toString(te);
                    str1 = s;
                    str2 = "";
                }
                field.setText(str + str1 + str2);
                isResultDisplayed = false; // Reset the flag for further input
            }
        }
    }
}
