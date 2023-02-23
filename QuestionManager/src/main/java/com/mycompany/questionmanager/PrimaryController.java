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
     @FXML private TextField choiceC;
     @FXML private TextField choiceD;
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
            getStmt = connection.prepareStatement(GetSQL);
        } catch(Exception ex) {
            outputMsg.setText("Problem opening database connection.");
            outputMsg.setTextFill(Color.RED);
            System.out.println("Problem opening database connection.");
            ex.printStackTrace();
        }
      
    } 
    
    private String InsertSQL = "INSERT into survey_ques(question_name, opt_a, opt_b, opt_c, opt_d, date) values(?,?,?,?,?,?)";
    private PreparedStatement insertStmt;
   
    @FXML
    private void addSurvey() throws IOException {
        int lastId = 0;
        int dummyVal = 0;
        String ques = questionTxt.getText();
        String opta = choiceA.getText();
        String optb = choiceB.getText();
        String optc = choiceC.getText();
        String optd = choiceD.getText();
        
        LocalDate dates = datePick.getValue();
        
        if(ques !="" && opta !="" && optb !="" && optc !="" && optd !="" && dates!= null){
        
         try {
            insertStmt.setString(1, ques);
            insertStmt.setString(2, opta);
            insertStmt.setString(3, optb);
            insertStmt.setString(4, optc);
            insertStmt.setString(5, optd);
            insertStmt.setDate(6, java.sql.Date.valueOf(dates));
            insertStmt.execute();
           
          lastId = getId();
          
          if(lastId>0){
          
          outputMsg.setText("Question added for: " + java.sql.Date.valueOf(dates) );
          outputMsg.setTextFill(Color.GREEN);

          }
          
          
           questionTxt.setText("");
           choiceA.setText("");
           choiceB.setText("");
           choiceC.setText("");
           choiceD.setText("");
           datePick.setValue(null);
            
         } catch(SQLException ex) {
            ex.printStackTrace();
            outputMsg.setText("Problem in insert Statement.");
            outputMsg.setTextFill(Color.RED);
         }
        
        } else{
        
            outputMsg.setText("All Fields Are Required!");
            outputMsg.setTextFill(Color.RED);
        
        }
        
        
    }
    
    //========== GET THE LAST INSERTED ROW ID FOR QUESTION ============
    
    private String GetSQL = "SELECT last_insert_id()";
    private PreparedStatement getStmt;
 
    private int getId()throws IOException {
        
    int id = 0;
     try {
    ResultSet rs= getStmt.executeQuery();
    
    if(rs.next()){
        id = rs.getInt(1);
    }
     }catch(SQLException ex) {
            ex.printStackTrace();
            outputMsg.setText("Problem in getting the last insert id");
            outputMsg.setTextFill(Color.RED);
            }
    return id;
    
    }
    
}
