package controller;

import view.View;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Open implements ActionListener, Controller {

    final JFileChooser jFileChooser = new JFileChooser();
    private View view;
    private JDialog jDialog = new JDialog();
    private FileNameExtensionFilter filter = null;

    public Open(View view) {
        this.view = view;
        connectButtonToMethod();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String buttonText = ((JButton) e.getSource()).getActionCommand();
        Scanner scanner;
        String fileContent = "";

        if(buttonText.equals("Open .CSV file"))
            filter = new FileNameExtensionFilter("CSV FILES", "csv", "csv");
        else
            filter = new FileNameExtensionFilter("JSON FILES", "json", "json");

        jFileChooser.setFileFilter(filter);
        int result = jFileChooser.showOpenDialog(jDialog);
        if(result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = jFileChooser.getSelectedFile();

            try {
                scanner = new Scanner(selectedFile);
                while(scanner.hasNextLine()) {
                    fileContent += scanner.nextLine() + "\n";
                }
                updateView(fileContent);

            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void connectButtonToMethod() {
        view.openCSVFile.addActionListener(this);
        view.openJSONFile.addActionListener(this);
    }

    public void updateView(String text) {
        if(filter.getExtensions()[0].equals("csv"))
            view.csvTextArea.setText(text);
        else
            view.jsonTextArea.setText(text);
    }
}
