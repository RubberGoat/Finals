package MemeGenerator;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javafx.fxml.FXML;

public class HomePageController {

   
    
    //initialize
    @FXML
    public void initialize() {
        System.out.println("Local Date " + LocalDateTime.now());
    }
    
    @FXML
    private void TextWrapper() throws IOException{
                App.setRoot("TextWrapper");
    }
    
    @FXML
    private void MemeGenerator() throws IOException{
                App.setRoot("MemeGenerator");
    }
   
    
}
