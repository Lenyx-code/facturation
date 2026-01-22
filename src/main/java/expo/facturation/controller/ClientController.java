package expo.facturation.controller;

import java.io.IOException;

import expo.facturation.App;
import expo.facturation.dao.ClientDao;
import expo.facturation.model.Client;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class ClientController {

    private ClientDao clientDao = new ClientDao();
    private Client selectedClient = null;

    @FXML
    private TextField nom;

    @FXML
    private TextField adresse;

    @FXML
    private TextField telephone;

    // ===================== TABLE =====================
    @FXML
    private TableView<Client> tableClient;

    @FXML
    private TableColumn<Client, Integer> colId;

    @FXML
    private TableColumn<Client, String> colNom;

    @FXML
    private TableColumn<Client, String> colAdresse;

    @FXML
    private TableColumn<Client, String> colTelephone;

    @FXML
    private TableColumn<Client, Void> colAction;

    @FXML
    private Button validEdit;

       @FXML
    private void switchToProduct() throws IOException {
        App.setRoot("product");
    }
       @FXML
    private void switchToInvoice() throws IOException {
        App.setRoot("facture");
    }
       @FXML
    private void switchToInvoiceList() throws IOException {
        App.setRoot("listefacture");
    }
  
    @FXML
    public void initialize() {

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colAdresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        colTelephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));

        Callback<TableColumn<Client, Void>, TableCell<Client, Void>> cellFactory = params -> new TableCell<>() {

            private final Button btnEdit = new Button("Modifier");
            private final Button btnDelete = new Button("Supprimer");

            {
                btnEdit.setStyle("-fx-background-color: #ffc444; -fx-text-fill: white;");
                btnDelete.setStyle("-fx-background-color: #ff4444; -fx-text-fill: white;");

                btnEdit.setOnAction(event -> {
                    Client client = getTableView().getItems().get(getIndex());
                    putDataInFormField(client);
                });

                btnDelete.setOnAction(event -> {
                    Client client = getTableView().getItems().get(getIndex());
                    deleteClient(client.getId());
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(new javafx.scene.layout.HBox(10, btnEdit, btnDelete));
                }
            }
        };

        colAction.setCellFactory(cellFactory);

        loadClients();
    }

    @FXML
    private void clearFormField() {
        nom.clear();
        adresse.clear();
        telephone.clear();
    }

    @FXML
    private void saveClient() throws IOException {
        try {
            Client client = new Client();
            client.setNom(nom.getText());
            client.setAdresse(adresse.getText());
            client.setTelephone(telephone.getText());

            clientDao.createClient(client);

            clearFormField();
            loadClients();
        } catch (Exception e) {
            System.out.println("Erreur lors de l'ajout du client : " + e);
        }
    }

    @FXML
    private void putDataInFormField(Client client) {
        selectedClient = client;
        nom.setText(client.getNom());
        adresse.setText(client.getAdresse());
        telephone.setText(client.getTelephone());
    }

    @FXML
    private void validEditClient() throws IOException {
        try {
            if (selectedClient != null) {
                selectedClient.setNom(nom.getText());
                selectedClient.setAdresse(adresse.getText());
                selectedClient.setTelephone(telephone.getText());

                clientDao.editClient(selectedClient);

                selectedClient = null;
                clearFormField();
                loadClients();
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de la modification du client : " + e);
        }
    }

    private void deleteClient(int id) {
        try {
            clientDao.deleteClient(id);
            loadClients();
        } catch (Exception e) {
            System.out.println("Erreur lors de la suppression du client : " + e);
        }
    }

    private void loadClients() {
        try {
            tableClient.setItems(
                FXCollections.observableArrayList(clientDao.getAllClients())
            );
        } catch (Exception e) {
            System.out.println("Erreur lors du chargement des clients : " + e);
        }
    }
}
