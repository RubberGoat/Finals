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
    private void switchToSecondary() throws IOException,InterruptedException {
        
//        App.setRoot("secondary");
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
