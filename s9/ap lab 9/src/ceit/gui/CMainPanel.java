package ceit.gui;

import ceit.utils.FileUtils;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

/**
 * A class to manage Panels.
 *
 * @author Mohammadreza Shahrestani
 * @version 1.0
 */

public class CMainPanel extends JPanel {

    //tabbedPane
    private JTabbedPane tabbedPane;
    //directoryList
    private JList<File> directoryList;

    /**
     * Create a CMainPanel.
     *
     */
    public CMainPanel() {

        setLayout(new BorderLayout());

        initDirectoryList(); // add JList to main Panel

        initTabbedPane(); // add TabbedPane to main panel

        addNewTab(); // open new empty tab when user open the application
    }

    /**
     * make TabbedPane.
     *
     * The method makes TabbedPane for iNote.
     */
    private void initTabbedPane() {
        tabbedPane = new JTabbedPane();
        add(tabbedPane, BorderLayout.CENTER);
    }

    /**
     * make DirectoryList.
     *
     * The method makes DirectoryList for iNote.
     */
    private void initDirectoryList() {
        File[] files = FileUtils.getFilesInDirectory();
        directoryList = new JList<>(files);

        directoryList.setBackground(new Color(211, 211, 211));
        directoryList.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        directoryList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        directoryList.setVisibleRowCount(-1);
        directoryList.setMinimumSize(new Dimension(130, 100));
        directoryList.setMaximumSize(new Dimension(130, 100));
        directoryList.setFixedCellWidth(130);
        directoryList.setCellRenderer(new MyCellRenderer());
        directoryList.addMouseListener(new MyMouseAdapter());

        add(new JScrollPane(directoryList), BorderLayout.WEST);
    }

    /**
     * add new Tab.
     *
     * The method adds new Tab and textarea to iNote.
     */
    public void addNewTab() {
        JTextArea textPanel = createTextPanel();
        textPanel.setText("Write Something here...");
        tabbedPane.addTab("Tab " + (tabbedPane.getTabCount() + 1), textPanel);
    }

    /**
     * open notes.
     * The method opens and loads Tab and textarea
     *
     * @param content a String.
     */
    public void openExistingNote(String content) {
        JTextArea existPanel = createTextPanel();
        existPanel.setText(content);

        int tabIndex = tabbedPane.getTabCount() + 1;
        tabbedPane.addTab("Tab " + tabIndex, existPanel);
        tabbedPane.setSelectedIndex(tabIndex - 1);
    }

    /**
     * save note.
     *
     * The method saves note.
     */
    public void saveNote() {
        JTextArea textPanel = (JTextArea) tabbedPane.getSelectedComponent();
        String note = textPanel.getText();
        if (!note.isEmpty()) {
            FileUtils.writeObject(note);
        }
        updateListGUI();
    }

    /**
     * save all notes.
     *
     * The method saves all of the notes.
     */
    public void saveAllNotes() {
        for (int i = 0; i < tabbedPane.getComponentCount(); i++)
        {
            tabbedPane.setSelectedIndex(i);
            saveNote();
        }
    }

    /**
     * save date note.
     *
     * The method saves date in note.
     */
    public void saveDateNote() {
        JTextArea textPanel = (JTextArea) tabbedPane.getSelectedComponent();
        String note = textPanel.getText();
        if (!note.isEmpty()) {
            FileUtils.writeObject(note);
        }
        updateListGUI();
    }

    /**
     * create TextPanel.
     *
     * The method creates TextPanel.
     */
    private JTextArea createTextPanel() {
        JTextArea textPanel = new JTextArea();
        textPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        return textPanel;
    }

    /**
     *  update ListGUI.
     *
     * The method  updates ListGUI.
     */
    private void updateListGUI() {
        File[] newFiles = FileUtils.getFilesInDirectory();
        directoryList.setListData(newFiles);
    }

    /**
     * an innerclass that implements MouseAdapter interface.
     *
     */
    private class MyMouseAdapter extends MouseAdapter {
        /**
         * an Override method
         * Handel mouseClicked for save.
         *
         * @param eve mouse event.
         */
        @Override
        public void mouseClicked(MouseEvent eve) {
            // Double-click detected
            if (eve.getClickCount() == 2) {
                int index = directoryList.locationToIndex(eve.getPoint());
                System.out.println("Item " + index + " is clicked...");
                File[] curr = FileUtils.getFilesInDirectory();
                File fileToRead = curr[index];
                String content;
                if (fileToRead.getName().substring(fileToRead.getName().length() - 4).equals(".txt"))
                    content = FileUtils.fileReader(fileToRead);
                else
                    content = FileUtils.readObject(fileToRead);
                openExistingNote(content);
            }
        }
    }

    /**
     * an innerclass that implements DefaultListCellRenderer interface.
     *
     */
    private static class MyCellRenderer extends DefaultListCellRenderer {
        /**
         * an Override method
         * Handel ListCellRenderer for list.
         *
         * @param list list of notes.
         * @param object object.
         * @param index index of note.
         * @param isSelected condition of select.
         * @param cellHasFocus condition of focus.
         * @return component.
         */
        @Override
        public Component getListCellRendererComponent(JList list, Object object, int index, boolean isSelected, boolean cellHasFocus) {
            if (object instanceof File) {
                File file = (File) object;
                setText(file.getName());
                setIcon(FileSystemView.getFileSystemView().getSystemIcon(file));
                if (isSelected) {
                    setBackground(list.getSelectionBackground());
                    setForeground(list.getSelectionForeground());
                } else {
                    setBackground(list.getBackground());
                    setForeground(list.getForeground());
                }
                setEnabled(list.isEnabled());
            }
            return this;
        }
    }
}
