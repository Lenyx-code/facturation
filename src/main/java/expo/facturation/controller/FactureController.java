package expo.facturation.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import expo.facturation.App;
import expo.facturation.dao.ClientDao;
import expo.facturation.dao.FactureDao;
import expo.facturation.dao.ProductDao;
import expo.facturation.dao.ProdFactureDao;
import expo.facturation.model.Client;
import expo.facturation.model.Facture;
import expo.facturation.model.LigneFacture;
import expo.facturation.model.Product;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

public class FactureController {

    // DAO
    private ClientDao clientDao = new ClientDao();
    private ProductDao productDao = new ProductDao();
    private FactureDao factureDao = new FactureDao();
    private ProdFactureDao prodFactureDao = new ProdFactureDao();

    private List<LigneFacture> panier = new ArrayList<>();
    @FXML
    private ComboBox<Client> choiceClient;
    @FXML
    private ComboBox<Product> choiceProduit;
    @FXML
    private TextField txtQte;
    @FXML
    private TextField txtNumeroFacture;
    @FXML
    private TextField txtTotal;
    @FXML
    private TextField txtPaye;
    @FXML
    private TextField txtReste;

    @FXML
    private TableView<LigneFacture> tableProduits;
    @FXML
    private TableColumn<LigneFacture, String> colProduit;
    @FXML
    private TableColumn<LigneFacture, Float> colPrix;
    @FXML
    private TableColumn<LigneFacture, Integer> colQte;
    @FXML
    private TableColumn<LigneFacture, Float> colTotal;
    @FXML
    private TableColumn<LigneFacture, Void> colAction;

    @FXML
    public void initialize() {

        choiceClient.setItems(FXCollections.observableArrayList(clientDao.getAllClients()));
        choiceProduit.setItems(FXCollections.observableArrayList(productDao.getAllProducts()));

        colProduit.setCellValueFactory(
                data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNomProduit()));
        colPrix.setCellValueFactory(
                data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getPrix()));
        colQte.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getQte()));
        colTotal.setCellValueFactory(
                data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getTotal()));
        Callback<TableColumn<LigneFacture, Void>, TableCell<LigneFacture, Void>> cellFactory = params -> new TableCell<>() {
            private final Button btnSupr = new Button("Supprimer");
            private final HBox box = new HBox(5, btnSupr);
            {

                box.setAlignment(Pos.CENTER);

                btnSupr.setStyle("-fx-background-color: #ff4444; -fx-text-fill: white;");

                btnSupr.setOnAction(event -> {
                    LigneFacture product = getTableView().getItems().get(getIndex());
                    suppLineProduit(product.getProduct().getId());
                });

            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : box);
            }

        };
        colAction.setCellFactory(cellFactory);
    }

    @FXML
    private void ajouterProduit() {

        Product produit = choiceProduit.getValue();
        int qte = Integer.parseInt(txtQte.getText());

        LigneFacture ligne = new LigneFacture(produit, qte);
        panier.add(ligne);

        tableProduits.setItems(FXCollections.observableArrayList(panier));

        calculerTotal();
        txtQte.clear();
    }

    private void calculerTotal() {
        float total = 0;
        for (LigneFacture lf : panier) {
            total += lf.getTotal();
        }
        txtTotal.setText(String.valueOf(total));

        if (!txtPaye.getText().isEmpty()) {
            float paye = Float.valueOf(txtPaye.getText());
            txtReste.setText(String.valueOf(total - paye));
        }
    }

    @FXML
    private void enregistrerFacture() {

        Facture facture = new Facture();
        facture.setNumFac(txtNumeroFacture.getText());
        facture.setClient(choiceClient.getValue().getId());

        float total = txtTotal.getText().isEmpty() ? 0 : Float.parseFloat(txtTotal.getText());
        float paye = txtPaye.getText().isEmpty() ? 0 : Float.parseFloat(txtPaye.getText());
        float reste = txtReste.getText().isEmpty() ? total - paye : Float.parseFloat(txtReste.getText());

        facture.setMontantTotal(total);
        facture.setMontantPayer(paye);
        facture.setReste(reste);

        int idFacture = factureDao.createInvoiceAndReturnId(facture);

        panier.forEach(ligne -> {
            prodFactureDao.create(
                    idFacture,
                    ligne.getProduct().getId(),
                    ligne.getQte());
        });

        panier.clear();
        tableProduits.setItems(FXCollections.observableArrayList(panier));

        txtTotal.clear();
        txtPaye.clear();
        txtReste.clear();
        txtNumeroFacture.clear();

    }

    @FXML
    private void suppLineProduit(int productId) {
        panier.removeIf(ligne -> ligne.getProduct().getId() == productId);
        tableProduits.setItems(FXCollections.observableArrayList(panier));
    }

    @FXML
    private void switchToClient() throws IOException {
        App.setRoot("client");
    }
}
