module Finals {
    requires javafx.baseEmpty;
    requires javafx.base;
    requires javafx.fxmlEmpty;
    requires javafx.fxml;
    requires javafx.controlsEmpty;
    requires javafx.controls;
    requires javafx.graphicsEmpty;
    requires javafx.graphics;
    
    requires java.sql;
    
    requires org.apache.pdfbox;

    opens Finals to javafx.fxml;
    exports Finals;
}