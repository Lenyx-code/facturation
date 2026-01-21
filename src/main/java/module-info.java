module expo.facturation {
    requires javafx.controls;
    requires javafx.fxml;

    opens expo.facturation to javafx.fxml;
    exports expo.facturation;
}
