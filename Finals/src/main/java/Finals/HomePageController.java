package Finals;

import java.io.IOException;
import javafx.fxml.FXML;

public class HomePageController {

    
    MessageLoop threadOne;
    MessageLoop threadTwo;
    MessageLoop threadThree;
    
    //initialize
    @FXML
    public void initialize() {
        MessageLoop threadOne = new MessageLoop("class1.txt","I will behave",10);
        MessageLoop threadTwo = new MessageLoop("class2.txt","I will finish my homework",15);
        MessageLoop threadThree = new MessageLoop("class3.txt","I will listen to my teacher",20);
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
