package ceit.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A class to design GUI and Frame.
 *
 * @author Mohammadreza Shahrestani
 * @version 1.0
 */
public class CFrame extends JFrame implements ActionListener {
    //main panel
    private CMainPanel mainPanel;
    //new menu item
    private JMenuItem newItem;
    //new save item
    private JMenuItem saveItem;
    //new save date item
    private JMenuItem saveDateItem;
    //new exit item
    private JMenuItem exitItem;

    /**
     * Create a CFrame.
     *
     * @param title title of frame.
     */
    public CFrame(String title) {
        super(title);

        setIcon();//set an Icon

        initMenuBar(); //create menuBar

        initMainPanel(); //create main panel
    }

    /**
     * set application icon.
     *
     * The method set an icon for iNote.
     */
    private void setIcon() {
        ImageIcon iconCalculator = new ImageIcon("./images/iNote.png");
        setIconImage(iconCalculator.getImage());
    }

    /**
     * make MenuBar.
     *
     * The method makes MenuBar for iNote.
     */
    private void initMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu jmenu = new JMenu("File");

        newItem = new JMenuItem("New");
        saveItem = new JMenuItem("Save");
        saveDateItem = new JMenuItem("Save Date");
        exitItem = new JMenuItem("Exit");

        newItem.addActionListener(this);
        saveItem.addActionListener(this);
        saveDateItem.addActionListener(this);
        exitItem.addActionListener(this);

        jmenu.add(newItem);
        jmenu.add(saveItem);
        jmenu.add(saveDateItem);
        jmenu.add(exitItem);

        menuBar.add(jmenu);
        setJMenuBar(menuBar);
    }

    /**
     * make main panel.
     *
     * The method makes mainPanel for iNote.
     */
    private void initMainPanel() {
        mainPanel = new CMainPanel();
        add(mainPanel);
    }

    /**
     * an Override method
     * Handel action performed for MenuBar.
     *
     * @param e action event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newItem) {
            mainPanel.addNewTab();
        } else if (e.getSource() == saveItem) {
            mainPanel.saveNote();
        } else if (e.getSource() == saveDateItem) {
            mainPanel.saveDateNote();
        } else if (e.getSource() == exitItem) {
            //TODO: Phase1: check all tabs saved ...
            mainPanel.saveAllNotes();
            System.exit(0);
        } else {
            System.out.println("Nothing detected...");
        }
    }
}
