module com.example.tipcalculatorapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tipcalculatorapp to javafx.fxml;
    exports com.example.tipcalculatorapp;
}