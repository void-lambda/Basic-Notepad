package src;


// import java.awt.FileDialog;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class BarFunctions {

    GUI gui;
    private File currentFile = null;

    public BarFunctions(GUI gui) {
        this.gui = gui;
    }

    public void newFile() {
        gui.textArea.setText("");
        gui.window.setTitle("New");
    }

    public void open() {
        JFileChooser chooser = new JFileChooser();
        int result = chooser.showOpenDialog(gui.window);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selected = chooser.getSelectedFile();
            try {
                String content = Files.readString(selected.toPath());
                gui.textArea.setText(content);
                gui.window.setTitle(selected.getName() + " - Your Notepad");
            } catch (IOException ex) {
                System.err.println("Can not read file: " + ex.getMessage());
            }
        }
    }

    public void save() {
        if (currentFile == null) {
            saveAs();
        } else {
            writeToFile(currentFile);
        }
    }

    public void saveAs() {
        JFileChooser chooser = new JFileChooser();
        // "Save" Button in the Dialog
        chooser.setDialogTitle("Save as");
        int result = chooser.showSaveDialog(gui.window);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selected = chooser.getSelectedFile();
            // If already exists
            if (selected.exists()) {
                int overwrite = JOptionPane.showConfirmDialog(gui.window,
                    "File already exists. Overwrite?",
                    "Confirm Overwrite", JOptionPane.YES_NO_OPTION);
                if (overwrite != JOptionPane.YES_OPTION) {
                    return;
                }
            }
            writeToFile(selected);
        }
    }

    public void writeToFile(File file) {
        try {
            String content = gui.textArea.getText();  // Getting text from the text field
            Files.writeString(file.toPath(), content);  // writing

            gui.window.setTitle(file.getName() + " - Your Notepad");
            currentFile = file;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(gui.window,
                "Could not save file: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void exit() {
        int confirm = JOptionPane.showConfirmDialog(
            gui.window,
            "Are you sure you want to exit?",
            "Exit",
            JOptionPane.YES_NO_OPTION
        );
        if (confirm == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
}