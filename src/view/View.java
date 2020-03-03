package view;

import javax.swing.*;

public class View extends JFrame {

    public JScrollPane csvScrollPane, jsonScrollPane;
    public JButton openCSVFile, openJSONFile, convertCSV2JSON, convertJSON2CSV;
    public JTextArea csvTextArea = new JTextArea(), jsonTextArea = new JTextArea();

    public View() {
        showGUI();
    }

    public void showGUI() {
        // Left side of View
        openCSVFile = new JButton("Open .CSV file");
        openCSVFile.setSize(150, 30);
        openCSVFile.setLocation(10, 10);

        csvScrollPane = new JScrollPane(csvTextArea);
        csvScrollPane.setSize(500,500);
        csvScrollPane.setLocation(10, 50);

        convertCSV2JSON = new JButton("Convert to .JSON");
        convertCSV2JSON.setSize(150, 30);
        convertCSV2JSON.setLocation(10, 555);

        // Right side of View
        openJSONFile = new JButton("Open .JSON file");
        openJSONFile.setSize(150, 30);
        openJSONFile.setLocation(870, 10);

        jsonScrollPane = new JScrollPane(jsonTextArea);
        jsonScrollPane.setSize(500, 500);
        jsonScrollPane.setLocation(520, 50);

        convertJSON2CSV = new JButton("Convert to .CSV");
        convertJSON2CSV.setSize(150, 30);
        convertJSON2CSV.setLocation(870, 555);

        getContentPane().add(openCSVFile);
        getContentPane().add(csvScrollPane);
        getContentPane().add(convertCSV2JSON);

        getContentPane().add(openJSONFile);
        getContentPane().add(jsonScrollPane);
        getContentPane().add(convertJSON2CSV);

        setSize(1040, 630);
        setLayout(null);
        setVisible(true);
    }

    public void showError(String text) {
        JOptionPane.showMessageDialog(this, text);
    }
}
