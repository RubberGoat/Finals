/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Finals;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;

/**
 *
 * @author Aldo
 */




public class TextWrapperController {
    
    @FXML
    private ListView PDFList; 
     
     
    public void initialize() throws FileNotFoundException, IOException, SQLException{     
      //get pdf from database
      
      //show pdf into list view
      
    }
    
    public void NewPdf(ActionEvent event) throws FileNotFoundException, IOException, SQLException, ClassNotFoundException{     
        //open file chooser
        FileChooser fc = new FileChooser();
        java.io.File selectedFile = fc.showOpenDialog(null);
        
        
        //insert file to database
        if(selectedFile != null){
            
                //change file into readable file
            try {

                Class.forName("org.sqlite.JDBC");
                Connection conn = DriverManager.getConnection("jdbc:sqlite:FinalsDatabase.db"); 


                PreparedStatement pStF = conn.prepareStatement(
                    "INSERT OR IGNORE INTO Attachment (attachment_name, attachment_data) VALUES (?,?)"
                );

         
                pStF.setString(1, selectedFile.getName());


                FileInputStream mn = new FileInputStream(selectedFile);

                pStF.setBinaryStream(2, mn, (int) selectedFile.length());

                pStF.execute();

                PDFList.getItems().add(selectedFile.getName());

                conn.close();

                } catch (SQLException e){
                    System.out.println(e.getErrorCode());
                    System.out.println(e.getSQLState());
                    System.out.println(e.getMessage());
                    System.out.println(e.getCause());
                    System.out.println(e.getNextException());

            }
        }else{
//                warning1.setText("Please select a file");
            }
        
      
    }
    
    public void WrapPdf(ActionEvent event) throws FileNotFoundException, IOException, SQLException{     
        //
        
        // 
        
      
    }
    
    
}
