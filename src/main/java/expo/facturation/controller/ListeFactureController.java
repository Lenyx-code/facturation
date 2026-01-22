package expo.facturation.controller;

import java.io.IOException;

import expo.facturation.App;
import expo.facturation.dao.FactureDao;
import expo.facturation.model.Facture;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ListeFactureController {

    private FactureDao factureDao = new FactureDao();

    @FXML
    private TableView<Facture> tableProduit;
    @FXML
    private TableColumn<Facture, Integer> colId;
    @FXML
    private TableColumn<Facture, String> colNum;
    @FXML
    private TableColumn<Facture, String> colClient;
    @FXML
    private TableColumn<Facture, Float> colMontantTotal;
    @FXML
    private TableColumn<Facture, Float> colMontantPayer;

    @FXML
    public void initialize() {
        colId.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getId()));
        colNum.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNumFac()));
        colClient.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getClientNom()));
        colMontantTotal.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getMontantTotal()));
        colMontantPayer.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getMontantPayer()));

        tableProduit.setItems(FXCollections.observableArrayList(factureDao.getAllFactures()));
    }

       @FXML
    private void switchToInvoice() throws IOException {
        App.setRoot("facture");
    }

    @FXML
    private void switchToClient() throws IOException {
        App.setRoot("client");
    }
}
