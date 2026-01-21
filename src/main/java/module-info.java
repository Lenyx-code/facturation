module expo.facturation {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens expo.facturation to javafx.fxml;
    exports expo.facturation;
}
