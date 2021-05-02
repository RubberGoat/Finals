module Finals {
    requires javafx.baseEmpty;
    requires javafx.base;
    requires javafx.fxmlEmpty;
    requires javafx.fxml;
    requires javafx.controlsEmpty;
    requires javafx.controls;
    requires javafx.graphicsEmpty;
    requires javafx.graphics;
    
    //for bufferedimage
    requires java.desktop;
    
    //for mediaplayer
    requires javafx.media;
    
    //for sql
    requires java.sql;
    
    //for pdf reader
    requires org.apache.pdfbox;

    opens Finals to javafx.fxml;
    exports Finals;
}