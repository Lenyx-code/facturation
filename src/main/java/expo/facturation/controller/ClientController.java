package expo.facturation.controller;

import java.io.IOException;

import expo.facturation.App;
import javafx.fxml.FXML;

public class ClientController {

    @FXML
    private void switchToProduct() throws IOException {
        App.setRoot("product");
    }
}