package expo.facturation.controller;

import java.io.IOException;

import expo.facturation.App;
import javafx.fxml.FXML;

public class ProductController {

    @FXML
    private void switchToClient() throws IOException {
        App.setRoot("client");
    }
}
