package com.example.linebreaker;

import javafx.fxml.FXML;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private TextField textField;

    @FXML
    private TextField lengthWords;

    private Stage primaryStage;

    private Dialog dialog;


    @FXML
    protected void onLineBreakButtonClick() throws IOException {

        try {
            Integer.parseInt(lengthWords.getText());

            welcomeText.setText("The Lines are Break !");

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save Text File");

            // 파일 확장자 필터 설정 (예: .txt)
            FileChooser.ExtensionFilter extFilter =
                    new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
            fileChooser.getExtensionFilters().add(extFilter);

            // 저장할 파일 선택
            File file = fileChooser.showSaveDialog(primaryStage);
            String txt = "";
            txt = lineBreak(textField.getText(), Integer.parseInt(lengthWords.getText()));

            if (file != null) {
                saveTextToFile(txt, file);
            }
        } catch (NumberFormatException e) {
            dialog = new Dialog();
            dialog.setContentText("숫자만 입력해주세요");
        }
    }

    private void saveTextToFile(String txt, File file) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(txt);
        writer.flush();
        writer.close();
    }

    private String lineBreak(String input, int length) {
        StringTokenizer st = new StringTokenizer(input);
        String output = "";
        Loop1 : while (st.hasMoreTokens()) {
            for (int i=0; i<length; i++) {
                if(!st.hasMoreTokens()) {break Loop1;}
                output += st.nextToken() + " ";
            }
            output += "\n";
        }
        return output;
    }
}