package src;


// import java.awt.FileDialog;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.swing.JFileChooser;

public class BarFunctions {

    GUI gui;

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

}