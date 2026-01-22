package expo.facturation.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import expo.facturation.db.DbConnection;
import expo.facturation.model.Product;

public class ProductDao {
    Connection conn = DbConnection.getConnection();

    public void createProduct(Product prod) {
        try {
            String query = "INSERT INTO produits (nom, qte, prix) VALUES(?, ?, ?)";
            if (conn != null) {
                PreparedStatement stmt = conn.prepareStatement(query);

                stmt.setString(1, prod.getNom());
                stmt.setInt(2, prod.getQte());
                stmt.setFloat(3, prod.getPrix());

                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Product> getAllProducts() {
        String query = "SELECT * FROM produits";
        List<Product> produits = new ArrayList<>();
        try {
            if (conn != null) {
                Statement stmt = conn.createStatement();
                ResultSet result = stmt.executeQuery(query);

                while (result.next()) {
                    Product p = new Product(
                            result.getInt("id"),
                            result.getString("nom"),
                            result.getInt("qte"),
                            result.getFloat("prix"));
                    produits.add(p);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return produits;
    }

    public void editProduct(Product prod) {
        String query = "UPDATE produits SET nom=?, qte=?, prix=? WHERE id=?";

        try {
            if (conn != null) {
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, prod.getNom());
                stmt.setInt(2, prod.getQte());
                stmt.setFloat(3, prod.getPrix());
                stmt.setInt(4, prod.getId());
                stmt.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteProduct(int id) {
        String query = "DELETE FROM produits WHERE id=?";

        try {
            if (conn != null) {

                PreparedStatement stmt = conn.prepareStatement(query);

                stmt.setInt(1, id);
                stmt.executeUpdate();
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
