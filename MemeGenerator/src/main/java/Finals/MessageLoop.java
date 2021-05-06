/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Finals;

/**
 *
 * @author Aldo
 */
public class MessageLoop extends Thread {
    private String filename;
    private String message;
    private int numberTimes;

    //Write a constructor for the MessageLoop class

    public MessageLoop (String filename, String message, int numberTimes){
        this.filename = filename;
        this.message = message;
        this.numberTimes = numberTimes;

    }


    //Write the method that is used to begin the thread - hint: what interface was implemented in this MessageLoop class
    public void run(){
        try{
          

            for(int i = 0; i < this.numberTimes ; i ++){
                ThreadHelper.writeToFile(this.filename,this.message);
                 Thread.sleep(20);
            }

        } catch (Exception e){
            System.out.println("NEIIIIIIIIIIGH");
        }
    }


    
}
