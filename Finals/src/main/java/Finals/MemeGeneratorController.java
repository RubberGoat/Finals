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
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

/**
 *
 * @author Aldo
 */
public class MemeGeneratorController {
    
    @FXML
    private ListView MemeList; 
    
    @FXML
    private ImageView Meme;
    
    public void initialize() throws FileNotFoundException, IOException, SQLException{     
      
       try {
      
        
        Connection conn = DriverManager.getConnection("jdbc:sqlite:FinalsDatabase.db");
        Statement st = conn.createStatement();
        
        //get memes from database
        String query = "SELECT attachment_name, attachment_data FROM Attachment WHERE attachment_name LIKE '%.jpg' ";
        ResultSet rs = st.executeQuery(query);
        
        //show memes into list view
        while(rs.next()){

            MemeList.getItems().add(rs.getString(1)

            );
        }     
  
        st.close();
        conn.close();
       
            
        } catch (SQLException e){
                    System.out.println(e.getErrorCode());
                    System.out.println(e.getSQLState());
                    System.out.println(e.getMessage());
                    System.out.println(e.getCause());
                    System.out.println(e.getNextException());

                
        }
      
    }
    
    //add local meme
    public void LocalMeme(ActionEvent event) throws FileNotFoundException, IOException, SQLException, ClassNotFoundException{     
        //open file chooser
        FileChooser fc = new FileChooser();
        
        //add memes extension filter
        FileChooser.ExtensionFilter fcFilter = new FileChooser.ExtensionFilter("Sex files ", "*.jpg", "*.png" , "*.gif");
        
        //add filter into current chooser
        fc.getExtensionFilters().add(fcFilter);
        
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

                MemeList.getItems().add(selectedFile.getName());

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
    
    //change selection 
    
}
