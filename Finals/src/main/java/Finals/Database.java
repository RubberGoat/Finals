/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Finals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Aldo
 */
public class Database {
    
        final private String database= "jdbc:sqlite:CBDatabase.db";
        
        //setup database
        public static void setupDatabase() throws ClassNotFoundException, SQLException{
            Class.forName("org.sqlite.JDBC");
            
            //create connection
            Connection conn = DriverManager.getConnection("jdbc:sqlite:CBDatabase.db");

            //create statement
            Statement st = conn.createStatement();

            // Create  Table, with id, name, species, colour, and owner fields
            String createStuff= "CREATE TABLE IF NOT EXISTS Stuff ("
                    + "stuff_id NUMBER (255) NOT NULL autoincrement"
                    + ", stuff_name VARCHAR (255) NOT NULL"
                    + ", PRIMARY KEY (stuff_id))";
            
             // Close connections and statements
            st.execute(createStuff);
            
            String createAttachment = "CREATE TABLE IF NOT EXISTS Attachment ("
                    + "attachment_id NUMBER (255) NOT NULL autoincrement"
                    + ", attachment_name VARCHAR (255) NOT NULL"
                    + ", attachment_data LONGBLOB NOT NULL"
                    + ", PRIMARY KEY (attachment_id))";
            
             // Close connections and statements
            st.execute(createAttachment);
        }
        
        //insert data
        
        //get database
        
        //login boolea
        
        //whatever boolean

    
}
