package expo.facturation.dao;

import expo.facturation.model.Client;
import expo.facturation.db.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDao {

    private Connection conn = DbConnection.getConnection();

    public void createClient(Client client) {
        String query = "INSERT INTO clients (nom, adresse, telephone) VALUES (?, ?, ?)";

        try {
            if (conn != null) {
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, client.getNom());
                stmt.setString(2, client.getAdresse());
                stmt.setString(3, client.getTelephone());
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Client> getAllClients() {
        List<Client> clients = new ArrayList<>();
        String query = "SELECT * FROM clients";

        try {
            if (conn != null) {
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next()) {
                    Client client = new Client(
                            rs.getInt("id"),
                            rs.getString("nom"),
                            rs.getString("adresse"),
                            rs.getString("telephone")
                    );
                    clients.add(client);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clients;
    }

    public void editClient(Client client) {
        String query = "UPDATE clients SET nom=?, adresse=?, telephone=? WHERE id=?";

        try {
            if (conn != null) {
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, client.getNom());
                stmt.setString(2, client.getAdresse());
                stmt.setString(3, client.getTelephone());
                stmt.setInt(4, client.getId());
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteClient(int id) {
        String query = "DELETE FROM clients WHERE id=?";

        try {
            if (conn != null) {
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setInt(1, id);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
