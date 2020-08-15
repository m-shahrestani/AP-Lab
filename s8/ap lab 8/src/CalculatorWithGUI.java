import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;

/**
 * A class to design calculator GUI.
 *
 * @author Mohammadreza Shahrestani
 * @version 1.0
 */
public class CalculatorWithGUI {
    //standard calculator panel
    private JPanel standard;
    //scientific calculator panel
    private JPanel scientific;
    //calculate fields:
    private boolean validForDisplayOperators;
    private HashMap<String,JButton> buttonsKeyboard;
    private HashMap<String,JButton> buttonsKeyboard2;
    private KeyboardHandler keyboardHandler;
    private KeyboardHandler2 keyboardHandler2;
    private JMenuItem copyItem;
    private boolean shiftPressed = false;

    /**
     * Create a new CalculatorGUI.
     *
     */
    public CalculatorWithGUI() {
        //look and feel
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look and feel.
        }
        buttonsKeyboard = new HashMap<>();
        buttonsKeyboard2 = new HashMap<>();
        keyboardHandler = new KeyboardHandler();
        keyboardHandler2 = new KeyboardHandler2();
        //frame
        JFrame calcFrame = new JFrame();
        calcFrame.setTitle("AUT Calculator");
        calcFrame.setSize(370, 500);
        calcFrame.setLocation(100, 200);
        calcFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //menu
        JMenuBar jMenuBar = new JMenuBar();
        jMenuBar.setToolTipText("This is menu bar.");
        JMenu menu = new JMenu("File");
        menu.setToolTipText("This is menu.");
        JMenuItem exitItem = new JMenuItem("Exit");
        menu.add(exitItem);
        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, ActionEvent.ALT_MASK));
        exitItem.addActionListener(e -> System.exit(0));
        copyItem = new JMenuItem("Copy to clipboard");
        copyItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.ALT_MASK));
        menu.add(copyItem);
        copyItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.ALT_MASK));
        jMenuBar.add(menu);
        calcFrame.setJMenuBar(jMenuBar);
        makeStandardPanel();
        makeScientificPanel();
        //tab
        JTabbedPane calculatorTabs = new JTabbedPane();
        calculatorTabs.setToolTipText("This is tab.");
        ImageIcon iconStandard = new ImageIcon(getClass().getResource("Standard.gif"));
        ImageIcon iconScientific = new ImageIcon(getClass().getResource("Scientific.gif"));
        ImageIcon iconCalculator = new ImageIcon(getClass().getResource("Calculator.gif"));
        calculatorTabs.addTab("Standard ",iconStandard, standard);
        calculatorTabs.addTab("Scientific ",iconScientific,scientific);
        calcFrame.setIconImage(iconCalculator.getImage());
        calcFrame.add(calculatorTabs);
        calcFrame.setVisible(true);
    }

    /**
     * make standard calculator panel.
     *
     * The method makes standard calculator panel for standard tab.
     */
    private void makeStandardPanel() {
        standard = new JPanel();
        standard.setLayout(new BorderLayout());
        //textarea
        JTextArea display = new JTextArea(2,10);
        display.setToolTipText("This is text area.");
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.BOLD, 36));
        Border border = BorderFactory.createLineBorder(Color.gray,1);
        display.setBorder(border);
        //Copy item handler
        copyItem.addActionListener(arg -> {
            StringSelection selection = new StringSelection(display.getText());
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(selection, selection);
        });
        validForDisplayOperators = false;
        display.addKeyListener(keyboardHandler);
        //keyboard
        JPanel keyboardPanel = new JPanel();
        keyboardPanel.setToolTipText("This is keyboard.");
        keyboardPanel.setLayout(new GridLayout(5, 4));
        keyboardPanel.setBorder(border);
        //button
        //numbers
        JButton[] numberButton = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButton[i] = new JButton();
            numberButton[i].setToolTipText("This is number button.");
            numberButton[i].setText("" + i);
            buttonsKeyboard.put("" + i,numberButton[i]);
            numberButton[i].addKeyListener(keyboardHandler);
            numberButton[i].setBorder(BorderFactory.createLineBorder(Color.pink,1));
            numberButton[i].addActionListener(e -> {
                JButton button = (JButton) e.getSource();
                display.append(button.getText());
                validForDisplayOperators = true;
            });
        }
        //operators
        JButton[] operatorButton = new JButton[8];
        for (int i = 0; i < 8; i++) {
            operatorButton[i] = new JButton();
            operatorButton[i].setToolTipText("This is operator button.");
            operatorButton[i].addKeyListener(keyboardHandler);
            operatorButton[i].addActionListener(e -> {
                JButton button = (JButton) e.getSource();
                if (button.getText().equals("+")) {
                    actionOnTextArea("+", display);
                }
                if (button.getText().equals("-")) {
                    actionOnTextArea("-", display);
                }
                if (button.getText().equals("*")) {
                    actionOnTextArea("*", display);
                }
                if (button.getText().equals("/")) {
                    actionOnTextArea("/", display);
                }
                if (button.getText().equals("=")) {
                    try{
                        ScriptEngineManager mgr = new ScriptEngineManager();
                        ScriptEngine engine = mgr.getEngineByName("JavaScript");
                        String result = display.getText();
                        display.setText(engine.eval(result).toString());
                    }catch (Exception ignored){display.setText("Wrong input.");}
                }
                if(button.getText().equals(".")) {
                    if(validForDisplayOperators ) {
                        try {
                            String lastFloat = display.getText().substring(display.getText().lastIndexOf(" "));
                            if (lastFloat.contains("."))
                                return;
                            display.append(".");
                            validForDisplayOperators = false;
                        }catch (StringIndexOutOfBoundsException exception){
                            if(display.getText().contains("."))
                                return;
                            display.append(".");
                            validForDisplayOperators = false;}
                    }
                }
                if (button.getText().equals("+/-")) {
                    if (display.getText().charAt(0) == '-') {
                        display.setText(display.getText().substring(1));
                    }
                    else if (display.getText().charAt(0) != '-') {
                        display.setText("-" + display.getText());
                    }
                }
                if (button.getText().equals("%")) {
                    actionOnTextArea("%",display);
                }
            });
        }
        operatorButton[0].setText("+");
        operatorButton[0].addKeyListener(keyboardHandler);
        buttonsKeyboard.put(operatorButton[0].getText(),operatorButton[0]);
        operatorButton[1].addKeyListener(keyboardHandler);
        operatorButton[1].setText("-");
        buttonsKeyboard.put(operatorButton[1].getText(),operatorButton[1]);
        operatorButton[2].addKeyListener(keyboardHandler);
        operatorButton[2].setText("*");
        buttonsKeyboard.put(operatorButton[2].getText(),operatorButton[2]);
        operatorButton[3].addKeyListener(keyboardHandler);
        operatorButton[3].setText("/");
        buttonsKeyboard.put(operatorButton[3].getText(),operatorButton[3]);
        operatorButton[4].addKeyListener(keyboardHandler);
        operatorButton[4].setText("=");
        buttonsKeyboard.put(operatorButton[4].getText(),operatorButton[4]);
        operatorButton[5].addKeyListener(keyboardHandler);
        operatorButton[5].setText(".");
        buttonsKeyboard.put(operatorButton[5].getText(),operatorButton[5]);
        operatorButton[6].setText("+/-");
        operatorButton[7].addKeyListener(keyboardHandler);
        operatorButton[7].setText("%");
        buttonsKeyboard.put(operatorButton[7].getText(),operatorButton[7]);
        //C&CE
        JButton cButton = new JButton();
        cButton.setText("C");
        cButton.setBackground(Color.gray);
        cButton.addActionListener(e -> {
            display.setText("");
            validForDisplayOperators = false;
        });
        JButton ceButton = new JButton();
        ceButton.setBackground(Color.gray);
        ceButton.setText("CE");
        ceButton.addActionListener(e -> {
            try {
                if(display.getText().endsWith(" ")) {
                    display.setText(display.getText().substring(0, display.getText().length() - 3));
                    validForDisplayOperators = true;
                }
                else
                    display.setText(display.getText().substring(0, display.getText().length() - 1));
            }catch (StringIndexOutOfBoundsException ignored){}
        });
        //Add
        keyboardPanel.add(ceButton);
        keyboardPanel.add(cButton);
        keyboardPanel.add(operatorButton[7]);
        keyboardPanel.add(operatorButton[0]);
        keyboardPanel.add(numberButton[7]);
        keyboardPanel.add(numberButton[8]);
        keyboardPanel.add(numberButton[9]);
        keyboardPanel.add(operatorButton[1]);
        keyboardPanel.add(numberButton[4]);
        keyboardPanel.add(numberButton[5]);
        keyboardPanel.add(numberButton[6]);
        keyboardPanel.add(operatorButton[2]);
        keyboardPanel.add(numberButton[1]);
        keyboardPanel.add(numberButton[2]);
        keyboardPanel.add(numberButton[3]);
        keyboardPanel.add(operatorButton[3]);
        keyboardPanel.add(operatorButton[6]);
        keyboardPanel.add(numberButton[0]);
        keyboardPanel.add(operatorButton[5]);
        keyboardPanel.add(operatorButton[4]);

        //scroll
        JScrollPane scrollPane = new JScrollPane(display);
        scrollPane.setPreferredSize(new Dimension(50, 100));

        standard.add(scrollPane, BorderLayout.NORTH);
        standard.add(keyboardPanel, BorderLayout.CENTER);
    }

    /**
     * make scientific calculator panel.
     *
     * The method makes scientific calculator panel for scientific tab.
     */
    private void makeScientificPanel() {
        scientific = new JPanel();
        scientific.setLayout(new BorderLayout());

        //textarea
        JTextArea display2 = new JTextArea(2, 5);
        display2.setToolTipText("This is text area.");
        display2.setEditable(false);
        display2.setFont(new Font("Arial", Font.BOLD, 36));
        Border border = BorderFactory.createLineBorder(Color.gray,1);
        display2.setBorder(border);
        display2.addKeyListener(keyboardHandler2);

        //keyboard
        JPanel keyboardPanel = new JPanel();
        keyboardPanel.setToolTipText("This is keyboard.");
        keyboardPanel.setLayout(new GridLayout(7, 5));
        keyboardPanel.setBorder(border);

        //button:
        //numbers
        JButton[] numberButton = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButton[i] = new JButton();
            numberButton[i].setToolTipText("This is number button.");
            numberButton[i].setText("" + i);
            buttonsKeyboard2.put("" + i,numberButton[i]);
            numberButton[i].addKeyListener(keyboardHandler2);
            numberButton[i].setBorder(BorderFactory.createLineBorder(Color.pink,1));
            numberButton[i].addActionListener(e -> {
                JButton button = (JButton) e.getSource();
                display2.append(button.getText());
                validForDisplayOperators = true;
            });
        }
        //operators
        JButton[] operatorButton = new JButton[20];
        for (int i = 0; i < 20; i++) {
            operatorButton[i] = new JButton();
            operatorButton[i].setToolTipText("This is operator button.");
            operatorButton[i].addKeyListener(keyboardHandler);
            operatorButton[i].addActionListener(e -> {
                JButton button = (JButton) e.getSource();
                if (button.getText().equals("+")) {
                    actionOnTextArea("+", display2);
                }
                if (button.getText().equals("-")) {
                    actionOnTextArea("-", display2);
                }
                if (button.getText().equals("*")) {
                    actionOnTextArea("*", display2);
                }
                if (button.getText().equals("/")) {
                    actionOnTextArea("/", display2);
                }
                if (button.getText().equals("=")) {
                    try{
                        ScriptEngineManager mgr = new ScriptEngineManager();
                        ScriptEngine engine = mgr.getEngineByName("JavaScript");
                        String result = display2.getText();
                        result = result.replaceAll("cos", "Math.cos");
                        result = result.replaceAll("cot", "Math.cot");
                        result = result.replaceAll("sin", "Math.sin");
                        result = result.replaceAll("tan", "Math.tan");
                        display2.setText(engine.eval(result).toString());
                    }catch (Exception ignored){display2.setText("Wrong input.");}
                }
                if(button.getText().equals(".")) {
                    if(validForDisplayOperators ) {
                        try {
                            String lastFloat = display2.getText().substring(display2.getText().lastIndexOf(" "));
                            if (lastFloat.contains("."))
                                return;
                            display2.append(".");
                            validForDisplayOperators = false;
                        }catch (StringIndexOutOfBoundsException exception){
                            if(display2.getText().contains("."))
                                return;
                            display2.append(".");
                            validForDisplayOperators = false;}
                    }
                }
                if (button.getText().equals("+/-")) {
                    if (display2.getText().charAt(0) == '-') {
                        display2.setText(display2.getText().substring(1));
                    }
                    else if (display2.getText().charAt(0) != '-') {
                        display2.setText("-" + display2.getText());
                    }
                }
                if (button.getText().equals("%")) {
                    validForDisplayOperators = true;
                    actionOnTextArea("%",display2);
                }
                if (button.getText().equals("tan")) {
                    validForDisplayOperators = true;
                    actionOnTextArea("tan(",display2);
                }
                if (button.getText().equals("sin")) {
                    validForDisplayOperators = true;
                    actionOnTextArea("sin(",display2);
                }
                if (button.getText().equals("cot")) {
                    validForDisplayOperators = true;
                    actionOnTextArea("cot(",display2);
                }
                if (button.getText().equals("cos")) {
                    validForDisplayOperators = true;
                    actionOnTextArea("cos(",display2);
                }
            });
        }
        operatorButton[0].setText("+");
        operatorButton[0].addKeyListener(keyboardHandler2);
        buttonsKeyboard2.put(operatorButton[0].getText(),operatorButton[0]);
        operatorButton[1].addKeyListener(keyboardHandler2);
        operatorButton[1].setText("-");
        buttonsKeyboard2.put(operatorButton[1].getText(),operatorButton[1]);
        operatorButton[2].addKeyListener(keyboardHandler2);
        operatorButton[2].setText("*");
        buttonsKeyboard2.put(operatorButton[2].getText(),operatorButton[2]);
        operatorButton[3].addKeyListener(keyboardHandler2);
        operatorButton[3].setText("/");
        buttonsKeyboard2.put(operatorButton[3].getText(),operatorButton[3]);
        operatorButton[4].addKeyListener(keyboardHandler2);
        operatorButton[4].setText("=");
        buttonsKeyboard2.put(operatorButton[4].getText(),operatorButton[4]);
        operatorButton[5].addKeyListener(keyboardHandler2);
        operatorButton[5].setText(".");
        buttonsKeyboard2.put(operatorButton[5].getText(),operatorButton[5]);
        operatorButton[6].setText("+/-");
        operatorButton[7].addKeyListener(keyboardHandler2);
        operatorButton[7].setText("%");
        buttonsKeyboard2.put(operatorButton[7].getText(),operatorButton[7]);
        operatorButton[8].setText("sin");
        operatorButton[9].setText("cos");
        operatorButton[10].setText("log");
        operatorButton[11].setText("exp");
        operatorButton[12].setText("e");
        operatorButton[13].setText("Pi");
        operatorButton[14].setText("x^y");
        operatorButton[15].setText("ln");
        operatorButton[16].setText("abs");
        operatorButton[17].setText("x^2");
        operatorButton[18].setText("x^1/2");
        operatorButton[19].setText("1/x");
        //C&CE&shift&)&(
        JButton cButton = new JButton();
        cButton.setText("C");
        cButton.setBackground(Color.gray);
        cButton.addActionListener(e -> {
            display2.setText("");
            validForDisplayOperators = false;
        });
        JButton ceButton = new JButton();
        ceButton.setBackground(Color.gray);
        ceButton.setText("CE");
        ceButton.addActionListener(e -> {
            try {
                if(display2.getText().endsWith(" ")) {
                    display2.setText(display2.getText().substring(0, display2.getText().length() - 3));
                    validForDisplayOperators = true;
                }
                else
                    display2.setText(display2.getText().substring(0, display2.getText().length() - 1));
            }catch (StringIndexOutOfBoundsException ignored){}
        });
        //Shift
        JButton shiftButton = new JButton();
        shiftButton.setText("Shift");
        shiftButton.setBackground(Color.gray);
        buttonsKeyboard2.put(shiftButton.getText(), shiftButton);
        shiftButton.addActionListener(e -> {
            shiftPressed = !shiftPressed;
            if (shiftPressed) {
                operatorButton[8].setText("tan");
                operatorButton[9].setText("cot");
            }
            else {
                operatorButton[8].setText("sin");
                operatorButton[9].setText("cos");
            }
        });
        JButton parentheses1 = new JButton();
        parentheses1.setText("(");
        parentheses1.addActionListener(e -> {
            display2.append("(");
            validForDisplayOperators = true;
        });
        parentheses1.setBackground(Color.gray);
        buttonsKeyboard2.put(parentheses1.getText(),parentheses1);
        JButton parentheses2 = new JButton();
        parentheses2.setText(")");
        parentheses2.addActionListener(e -> {
            display2.append(")");
            validForDisplayOperators = true;
        });
        parentheses2.setBackground(Color.gray);
        buttonsKeyboard2.put(parentheses2.getText(),parentheses2);
        //Add
        keyboardPanel.add(shiftButton);
        keyboardPanel.add(parentheses1);
        keyboardPanel.add(parentheses2);
        keyboardPanel.add(ceButton);
        keyboardPanel.add(cButton);
        keyboardPanel.add(operatorButton[8]);
        keyboardPanel.add(operatorButton[17]);
        keyboardPanel.add(operatorButton[18]);
        keyboardPanel.add(operatorButton[19]);
        keyboardPanel.add(operatorButton[16]);
        keyboardPanel.add(operatorButton[9]);
        keyboardPanel.add(operatorButton[12]);
        keyboardPanel.add(operatorButton[13]);
        keyboardPanel.add(operatorButton[7]);
        keyboardPanel.add(operatorButton[0]);
        keyboardPanel.add(operatorButton[11]);
        keyboardPanel.add(numberButton[7]);
        keyboardPanel.add(numberButton[8]);
        keyboardPanel.add(numberButton[9]);
        keyboardPanel.add(operatorButton[1]);
        keyboardPanel.add(operatorButton[15]);
        keyboardPanel.add(numberButton[4]);
        keyboardPanel.add(numberButton[5]);
        keyboardPanel.add(numberButton[6]);
        keyboardPanel.add(operatorButton[2]);
        keyboardPanel.add(operatorButton[14]);
        keyboardPanel.add(numberButton[1]);
        keyboardPanel.add(numberButton[2]);
        keyboardPanel.add(numberButton[3]);
        keyboardPanel.add(operatorButton[3]);
        keyboardPanel.add(operatorButton[10]);
        keyboardPanel.add(operatorButton[6]);
        keyboardPanel.add(numberButton[0]);
        keyboardPanel.add(operatorButton[5]);
        keyboardPanel.add(operatorButton[4]);

        //scroll
        JScrollPane scrollPane = new JScrollPane(display2);
        scrollPane.setPreferredSize(new Dimension(50, 100));

        scientific.add(scrollPane, BorderLayout.NORTH);
        scientific.add(keyboardPanel, BorderLayout.CENTER);
    }

    /**
     * make actionOnTextArea.
     *
     * The method makes action for TextArea.
     *
     * @param string text
     * @param textArea text area
     */
    private void actionOnTextArea(String string , JTextArea textArea)
    {
        if(validForDisplayOperators)
            textArea.append(string);
        validForDisplayOperators = false;
    }

    /**
     * make KeyboardHandler.
     *
     * The method handel keyboard key.
     */
    private class KeyboardHandler extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            System.out.println(KeyEvent.getKeyText(e.getKeyCode()));
            if(buttonsKeyboard.containsKey("" + e.getKeyChar())) {
                buttonsKeyboard.get("" + e.getKeyChar()).doClick();
                return;
            }
            if(buttonsKeyboard.containsKey("" + KeyEvent.getKeyText(e.getKeyCode())))
                buttonsKeyboard.get( "" + KeyEvent.getKeyText(e.getKeyCode())).doClick();
        }
    }

    /**
     * make KeyboardHandler.
     *
     * The method handel keyboard key.
     */
    private class KeyboardHandler2 extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            System.out.println(KeyEvent.getKeyText(e.getKeyCode()));
            if(buttonsKeyboard2.containsKey("" + e.getKeyChar())) {
                buttonsKeyboard2.get("" + e.getKeyChar()).doClick();
                return;
            }
            if(buttonsKeyboard2.containsKey("" + KeyEvent.getKeyText(e.getKeyCode())))
                buttonsKeyboard2.get( "" + KeyEvent.getKeyText(e.getKeyCode())).doClick();
        }
    }
}