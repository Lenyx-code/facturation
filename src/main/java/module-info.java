module expo.facturation {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens expo.facturation.controller to javafx.fxml;
    opens expo.facturation.model to javafx.base;

    exports expo.facturation;
}
