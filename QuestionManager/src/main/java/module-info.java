module com.mycompany.questionmanager {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.questionmanager to javafx.fxml;
    exports com.mycompany.questionmanager;
}
