module com.mycompany.surveyserver {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.surveyserver to javafx.fxml;
    exports com.mycompany.surveyserver;
}
