package com.mycompany.questionmanager;

import java.sql.*;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class PrimaryController implements Initializable{

    
    private Statement stmt;
    @FXML private TextField questionTxt;
    @FXML private DatePicker datePick;
     @FXML private TextField choiceA;
     @FXML private TextField choiceB;
      @FXML private Label outputMsg;
    
    
     @Override
    public void initialize(URL url, ResourceBundle rb) {
         
        Scanner input = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection
              ("jdbc:mysql://localhost:3306/survey?user=student&password=Cmsc250!");
            stmt = connection.createStatement();
            insertStmt = connection.prepareStatement(InsertSQL);
        } catch(Exception ex) {
            outputMsg.setText("Problem opening database connection.");
            outputMsg.setTextFill(Color.RED);
            System.out.println("Problem opening database connection.");
            ex.printStackTrace();
        }
      
    } 
    
    private String InsertSQL = "INSERT into question(question_name, option_a, option_b, opta_cnt, optb_cnt, date) values(?,?,?,?,?,?)";
    private PreparedStatement insertStmt;
    @FXML
    private void addSurvey() throws IOException {
        
        int dummyVal = 0;
        String ques = questionTxt.getText();
        String opta = choiceA.getText();
        String optb = choiceB.getText();
        LocalDate dates = datePick.getValue();
         try {
            insertStmt.setString(1, ques);
            insertStmt.setString(2, opta);
            insertStmt.setString(3, optb);
            insertStmt.setInt(4, dummyVal);
            insertStmt.setInt(5, dummyVal);
            insertStmt.setDate(6, java.sql.Date.valueOf(dates));
            insertStmt.execute();
            
            questionTxt.setText("");
           choiceA.setText("");
           choiceB.setText("");
           datePick.setValue(null);
         
         } catch(SQLException ex) {
            ex.printStackTrace();
            outputMsg.setText("Problem in insert Statement.");
            outputMsg.setTextFill(Color.RED);
            }
        
        
        
        
    }
}
