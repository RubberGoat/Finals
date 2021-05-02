/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Finals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;

import java.awt.image.BufferedImage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 *
 * @author Aldo
 */
public class MemeGeneratorController {
    
    @FXML
    private ListView MemeList; 
    
    @FXML
    private ImageView Meme;
    
    @FXML
    private ProgressBar pb;
    
//    private Stage stage; 
    
//    private BufferedImage image;
    
    public void initialize() throws FileNotFoundException, IOException, SQLException{     
      
       try {
      
        
        Connection conn = DriverManager.getConnection("jdbc:sqlite:FinalsDatabase.db");
        Statement st = conn.createStatement();
        
        //get memes from database
        String query = "SELECT attachment_name, attachment_data FROM Attachment WHERE attachment_name LIKE '%.jpg' OR attachment_name LIKE '%.gif' OR attachment_name LIKE '%.png' ";
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
    @FXML
    public void selectMemes() throws SQLException, FileNotFoundException, IOException{
        //get name of selected file
        String selectedFile = String.valueOf(MemeList.getSelectionModel().getSelectedItem());
        
        try {
            
            Connection conn = DriverManager.getConnection("jdbc:sqlite:FinalsDatabase.db");
            Statement st = conn.createStatement();

//            show files w the selceted file name from sql
            String query = "SELECT * FROM Attachment WHERE attachment_name = '" + selectedFile + "'";

            ResultSet rs = st.executeQuery(query);
            
            while(rs.next()){
                
                //get inputstream from sql database
                InputStream input = rs.getBinaryStream(3);
                
                //if its gif
                if(selectedFile.contains (".gif")){
                    
                    FileOutputStream os =  new FileOutputStream("src/main/resources/memes/" + selectedFile);      
                    
                    //download file
                    int b = 0;
                   
                    while ((b = input.read()) != -1)
                    {

                        os.write(b); 
                        
                        
                    }
                    
                    try{
                        Image imge = new Image(new FileInputStream("src/main/resources/memes/" + selectedFile));
                        
        
                        Meme.setImage(imge);
                        
//                        BorderPane borderPane = new BorderPane(Meme);
                        

                        Meme.setFitHeight(300);
                        Meme.setFitWidth(300);
                        Meme.setPreserveRatio(true); 
                        
//                        Group root = new Group(Meme);  
                        
//                        Scene scene = new Scene(root, 600, 500);  
//                        
//                        stage.setTitle("Loading an image"); 
//                        
//                        stage.setScene(scene);
//                        
//                        stage.show(); 
                    } catch (NullPointerException e){
//                     
//                        
                    }
                    
                    
                    
                //if its image
                }else{
                    
                    Image imge = new Image(input);
                
                    Meme.setImage(imge);

                    Meme.setFitHeight(300);
                    Meme.setPreserveRatio(true); 
                }
                
  
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
    
    @FXML
    public void GenerateMemes() throws SQLException, FileNotFoundException, IOException{
        
    }
    
    public void HuiJia(ActionEvent event) throws IOException{
        App.setRoot("HomePage");
    }
    
}
