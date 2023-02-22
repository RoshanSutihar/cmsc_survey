module com.mycompany.questionmanager {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    opens com.mycompany.questionmanager to javafx.fxml;
    exports com.mycompany.questionmanager;
}
