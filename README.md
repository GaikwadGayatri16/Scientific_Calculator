🧮 Java Scientific Calculator

A GUI-based Scientific Calculator built using Java Swing and AWT.
This calculator performs basic arithmetic operations along with scientific functions like trigonometric calculations, logarithms, square root, reciprocal, and memory operations.


📌 Features
🔢 Basic Operations

Addition (+)
Subtraction (−)
Multiplication (*)
Division (/)
Decimal support
Clear (C)


📊 Scientific Functions

√ (Square Root)
1/x (Reciprocal)
Log (Natural Logarithm)
Sin
Cos
Tan
Exp


🧠 Memory Functions

MC (Memory Clear)
MR (Memory Recall)
M+ (Memory Add)
M− (Memory Subtract)


🖥️ GUI Features

Built using Java Swing
Grid layout button arrangement
Right-aligned display
Error handling
Special case handling (e.g., Tan(90°) undefined)
Result chaining (use result in next operation)


🛠️ Technologies Used

Java
Swing (javax.swing)
AWT (java.awt)
Event Handling (ActionListener)


📂 Project Structure
Calculator.java


🚀 How to Run
Step 1: Compile
javac Calculator.java
Step 2: Run
java Calculator


The calculator window will open.
🧮 How It Works
Uses JFrame for the main window
Uses JTextField for display
Uses JButton for input buttons


Implements ActionListener to handle button events
Stores:
str → First number
str1 → Operator
str2 → Second number
Uses Math class for scientific calculations
Handles invalid operations using try-catch block
