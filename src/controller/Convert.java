package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import view.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class Convert implements ActionListener, Controller {

    private View view;

    public Convert(View view ) {
        this.view = view;
        connectButtonToMethod();
    }

    public String convertToJSON(String text) {
        int i;
        String jsonString = "[";
        List<String> fileContent = new ArrayList<String>(Arrays.asList(text.split("\n")));
        String[] columns = fileContent.get(0).split(",");
        fileContent.remove(0);

        for(String line : fileContent) {
            i = 0;
            jsonString += "{";
            String[] data = line.split(",");
            String comma;
            while(i < data.length) {
                jsonString += "\"" + columns[i] + "\":\"" + data[i] + "\",";
                i++;
            }
            jsonString = jsonString.substring(0, jsonString.length() - 1) + "},\n";
        }

        jsonString = jsonString.substring(0, jsonString.length() - 2) + "]";
        return jsonString;
    }

    private String convertToCSV(String text) throws JsonProcessingException {
        String csvString = "";
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode json = objectMapper.readTree(text);

        for (Iterator<String> it = json.get(0).fieldNames(); it.hasNext(); ) {
            String string = it.next();
            csvString += string + ",";
        }
        csvString = csvString.substring(0, csvString.length() - 1) + "\n";

        for (JsonNode jsonNode:json) {
            for(JsonNode field: jsonNode) {
                csvString += field.toString().replace("\"", "") + ",";
            }
            csvString = csvString.substring(0, csvString.length() - 1) + "\n";
        }
        return csvString;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String buttonText = ((JButton) e.getSource()).getActionCommand();

        if(buttonText.equals("Convert to .JSON")) {
            if(view.csvTextArea.getText().isEmpty())
                view.showError("CSV text field is empty!!!");
            else
                try {
                    view.jsonTextArea.setText(convertToJSON(view.csvTextArea.getText()));
                } catch (Exception ex) {
                    view.showError("CSV format is wrong!!!");
                }
        }
        else {
            try {
                if(view.jsonTextArea.getText().isEmpty())
                    view.showError("JSON text field is empty!!!");
                else
                view.csvTextArea.setText(convertToCSV(view.jsonTextArea.getText()));
            } catch (JsonProcessingException ex) {
                // ex.printStackTrace();
                view.showError("JSON format is wrong!!!");
            }
        }
    }

    @Override
    public void connectButtonToMethod() {
        view.convertCSV2JSON.addActionListener(this);
        view.convertJSON2CSV.addActionListener(this);
    }
}
