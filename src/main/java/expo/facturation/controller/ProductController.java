package expo.facturation.controller;

import java.io.IOException;

import expo.facturation.App;
import expo.facturation.dao.ProductDao;
import expo.facturation.model.Product;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.util.Callback;

public class ProductController {

    private ProductDao productDao = new ProductDao();
    private Product selectedProduct = null;

    @FXML
    private TextField nom;
    @FXML
    private TextField qte;
    @FXML
    private TextField prix;

    @FXML
    private TableView<Product> tableProduit;

    @FXML
    private TableColumn<Product, Integer> colId;
    @FXML
    private TableColumn<Product, String> colNom;
    @FXML
    private TableColumn<Product, Integer> colQte;
    @FXML
    private TableColumn<Product, Float> colPrix;

    @FXML
    private TableColumn<Product, Void> colAction;

    @FXML
    private Button validEdit;

    @FXML
    private void switchToClient() throws IOException {
        App.setRoot("client");
    }

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colQte.setCellValueFactory(new PropertyValueFactory<>("qte"));
        colPrix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        Callback<TableColumn<Product, Void>, TableCell<Product, Void>> cellFactory = params -> new TableCell<>() {
            private final Button btnSupr = new Button("Supprimer");
            private final Button btnEdit = new Button("Modifier");
            private final HBox box = new HBox(5, btnEdit, btnSupr);
            {

                box.setAlignment(Pos.CENTER);

                btnSupr.setStyle("-fx-background-color: #ff4444; -fx-text-fill: white;");
                btnEdit.setStyle("-fx-background-color: #4444ff; -fx-text-fill: white;");

                btnSupr.setOnAction(event -> {
                    Product product = getTableView().getItems().get(getIndex());
                    deleteProduct(product.getId());
                });
                btnEdit.setOnAction(event -> {
                    Product product = getTableView().getItems().get(getIndex());
                    putDataInFormField(product);
                });

            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : box);
            }

        };
        tableProduit.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        colAction.setCellFactory(cellFactory);

        loadProduct();
    }

    @FXML
    private void clearFormField() {
        nom.clear();
        qte.clear();
        prix.clear();
    }

    @FXML
    private void saveProduct() throws IOException {
        try {
            Product product = new Product();

            product.setNom(nom.getText());
            product.setQte(Integer.valueOf(qte.getText()));
            product.setPrix(Float.valueOf(prix.getText()));

            productDao.createProduct(product);

            clearFormField();

            loadProduct();
        } catch (Exception e) {
            System.out.println("Erreur lors de l'ajout du produit : " + e);
        }
    }

    @FXML
    private void putDataInFormField(Product produit) {
        selectedProduct = produit;
        nom.setText(produit.getNom());
        qte.setText(String.valueOf(produit.getQte()));
        prix.setText(String.valueOf(produit.getPrix()));
    }

    @FXML
    private void validEditProduct() throws IOException {
        try {
            if (selectedProduct != null) {

                selectedProduct.setNom(nom.getText());
                selectedProduct.setQte(Integer.valueOf(qte.getText()));
                selectedProduct.setPrix(Float.valueOf(prix.getText()));

                productDao.editProduct(selectedProduct);

                selectedProduct = null;
                clearFormField();
                loadProduct();
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de l'edition du prosuit : " + e);
        }
    }

    @FXML
    private void deleteProduct(int id) {
        try {
            productDao.deleteProduct(id);
            loadProduct();
        } catch (Exception e) {
            System.out.println("Erreur lors de la suppression du produit : " + e);
        }
    }

    @FXML
    private void loadProduct() {
        try {
            tableProduit.setItems(FXCollections.observableArrayList(productDao.getAllProducts()));
        } catch (Exception e) {
            System.out.println("Erreur lors du chargement de la liste des produits : " + e);
        }
    }

}
