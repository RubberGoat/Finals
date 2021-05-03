/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Finals;

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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

/**
 *
 * @author Aldo
 */


public class TextWrapperController {
    
    @FXML
    private ListView PDFList; 
    
    @FXML
    private TextArea WrappedText;

     
    public void initialize() throws FileNotFoundException, IOException, SQLException{     
      
       try {
      
        
        Connection conn = DriverManager.getConnection("jdbc:sqlite:FinalsDatabase.db");
        Statement st = conn.createStatement();
        
        //get pdf from database
        String query = "SELECT attachment_name, attachment_data FROM Attachment WHERE attachment_name LIKE '%.pdf' ";
        ResultSet rs = st.executeQuery(query);
        
        //show pdf into list view
        while(rs.next()){

            PDFList.getItems().add(rs.getString(1)

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
    
    public void NewPdf(ActionEvent event) throws FileNotFoundException, IOException, SQLException, ClassNotFoundException{     
        //open file chooser
        FileChooser fc = new FileChooser();
        
        //add pdf extension filter
        FileChooser.ExtensionFilter pdfFilter = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
        
        //add filter into current chooser
        fc.getExtensionFilters().add(pdfFilter);
        
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
        //Get name of selected file list
        String selectedFile = String.valueOf(PDFList.getSelectionModel().getSelectedItem());
        
        try {
            
                    Connection conn = DriverManager.getConnection("jdbc:sqlite:FinalsDatabase.db");
                    Statement st = conn.createStatement();

//                  show files w the selceted file name from sql
                    String query = "SELECT * FROM Attachment WHERE attachment_name = '" + selectedFile + "'";

                    ResultSet rs = st.executeQuery(query);
                    
                    
                    while(rs.next()){
                        
                        //download file into path
                        InputStream input = rs.getBinaryStream(3);

                        FileOutputStream os =  new FileOutputStream("src/main/resources/pdf/" + selectedFile);

                        int b = 0;
                        while ((b = input.read()) != -1)
                        {
                            os.write(b); 
                        }
                        //load pdf into PDdoc
                        PDDocument inputPDF = PDDocument.load(new java.io.File("src/main/resources/pdf/" + selectedFile));
                        
                        //get text from pdf
                        String text = new PDFTextStripper().getText(inputPDF);
          
                        //wrap pdf into string
                        WrappedText.setText(text);
                        
             
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
    
    public void HuiJia(ActionEvent event) throws IOException{
        App.setRoot("HomePage");
    }
    
     @FXML
    private void extractText() throws IOException,InterruptedException {
        
        
        //Get name of selected file list
        String selectedFile = String.valueOf(PDFList.getSelectionModel().getSelectedItem());
        
        try {
            
                    Connection conn = DriverManager.getConnection("jdbc:sqlite:FinalsDatabase.db");
                    Statement st = conn.createStatement();

//                  show files w the selceted file name from sql
                    String query = "SELECT * FROM Attachment WHERE attachment_name = '" + selectedFile + "'";

                    ResultSet rs = st.executeQuery(query);
                    
                    
                    while(rs.next()){
                        
                        //download file into path
                        InputStream input = rs.getBinaryStream(3);

                        FileOutputStream os =  new FileOutputStream("src/main/resources/pdf/" + selectedFile);

                        int b = 0;
                        while ((b = input.read()) != -1)
                        {
                            os.write(b); 
                        }
                        //load pdf into PDdoc
                        PDDocument inputPDF = PDDocument.load(new java.io.File("src/main/resources/pdf/" + selectedFile));
                        
                        //get text from pdf
                        String text = new PDFTextStripper().getText(inputPDF);
          
                        //extract text into string
                        Thread threadOne = new MessageLoop("class1.txt",text,1);
                        Thread threadTwo = new MessageLoop("class2.txt","I will finish my homework",15);
                        Thread threadThree = new MessageLoop("class3.txt","I will listen to my teacher",20);
                        
                         try{
                            threadOne.start();
                            threadTwo.start();
                            threadThree.start();

                        } catch (Exception e ){
                            System.out.println("Bae Suzy");
                        }

                        threadOne.join();
                        threadTwo.join();
                        threadThree.join();
                        System.out.println("Finished");
                        
             
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
        
        
       
     
        
        //Checking the number of lines in each file
        int result = -1;
        for (int i = 1; i < 4; i++) {
            result = ThreadHelper.countLines("class" + i + ".txt");
            if (result == -1) {
                System.out.println("class" + i + ".txt does not exist, please double check your file names");
            } else {
                System.out.println("class" + i + ".txt has " + result + " lines");
            }
        }
    }
    
    
}
