module ku.cs.hellofx {
    requires javafx.controls;
    requires javafx.fxml;


    opens ku.cs.hellofx to javafx.fxml;
    exports ku.cs.hellofx;
    exports ku.cs.controllers;
    opens ku.cs.controllers to javafx.fxml;
    exports ku.cs.models;
    opens ku.cs.models to javafx.base;
}