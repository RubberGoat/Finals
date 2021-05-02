/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Finals;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Aldo
 */
public class Database {
    
        final private String database= "jdbc:sqlite:FinalsDatabase.db";
        
        //setup database
        public static void setupDatabase() throws ClassNotFoundException, SQLException{
            Class.forName("org.sqlite.JDBC");
            
            //create connection
            Connection conn = DriverManager.getConnection("jdbc:sqlite:FinalsDatabase.db");

            //create statement
            Statement st = conn.createStatement();

            // Create  Table, with id, name, species, colour, and owner fields
            String createStuff= "CREATE TABLE IF NOT EXISTS Stuff ("
                    + "stuff_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT"
                    + ", stuff_name VARCHAR (255) NOT NULL)";
            
             // Close connections and statements
            st.execute(createStuff);
            
            String createAttachment = "CREATE TABLE IF NOT EXISTS Attachment ("
                    + "attachment_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT"
                    + ", attachment_name VARCHAR (255) NOT NULL"
                    + ", attachment_data LONGBLOB NOT NULL)";
            
             // Close connections and statements
            st.execute(createAttachment);
        }
        
        //insert data
        
        //print attachment
         public static void printAll() throws SQLException{
            Connection conn = DriverManager.getConnection("JDBC:sqlite:FinalsDatabase.db");

            //create statement
            Statement st = conn.createStatement ();

            
            System.out.println("AttachmentList");

            
            //print attachment

            String selectAT = "SELECT * FROM Attachment ";

            ResultSet sc = st.executeQuery(selectAT);

            while(sc.next()){
                    System.out.println(sc.getString(1) + ", " 
                    + sc.getString(2) + ", " 
                    + sc.getString(3));
            }
             
         }
        
        //get attachment
        public static ObservableList<Attachment> getAtch() throws SQLException {
        // Get ResultSet
        Connection conn = DriverManager.getConnection("jdbc:sqlite:FinalsDatabase.db");
        Statement st = conn.createStatement();
        
        String query = "SELECT attachment_id, attachment_name FROM Attachment";
        ResultSet rs = st.executeQuery(query);
        
        ObservableList<Attachment> AtchList = FXCollections.observableArrayList();
        
        // Add each row in the ResultSet
        
        while(rs.next()){
            AtchList.add(new Attachment(rs.getInt(1),
                    rs.getString(2), (Blob) rs.getBinaryStream(3)));
        }     
        

  
        // Close 
        st.close();
        conn.close();
        return AtchList;
    }
        
        //login boolea
        
        //whatever boolean

    
}
