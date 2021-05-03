package Finals;

import java.io.IOException;
import java.time.LocalDate;
import javafx.fxml.FXML;

public class HomePageController {

   
    
    //initialize
    @FXML
    public void initialize() {
        System.out.println("Local Date " + LocalDate.now());
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
