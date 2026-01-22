package expo.facturation.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import expo.facturation.db.DbConnection;
import expo.facturation.model.Facture;

public class FactureDao {
    Connection conn = DbConnection.getConnection();

    public int createInvoiceAndReturnId(Facture fac) {

        int generatedId = -1;

        String sql = "INSERT INTO factures " +
                     "(num_fac, id_cli, montant_payer, montant_total, reste_a_payer) " +
                     "VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = conn.prepareStatement(
                sql,
                Statement.RETURN_GENERATED_KEYS
            );

            ps.setString(1, fac.getNumFac());
            ps.setInt(2, fac.getClient());
            ps.setFloat(3, fac.getMontantPayer());
            ps.setFloat(4, fac.getMontantTotal());
            ps.setFloat(5, fac.getReste());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                generatedId = rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return generatedId;
    }

    public List<Facture> getAllFactures() {
    String query = "SELECT f.id, f.num_fac, f.id_cli, c.nom AS clientNom, f.montant_total, f.montant_payer, f.reste_a_payer FROM factures f JOIN clients c ON f.id_cli = c.id";

    
    List<Facture> factures = new ArrayList<>();
    try {
        if (conn != null) {
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(query);

            while (result.next()) {
                Facture f = new Facture(
                        result.getInt("id"),
                        result.getString("num_fac"),
                        result.getInt("id_cli"),
                        result.getString("clientNom"),
                        result.getFloat("montant_payer"),
                        result.getFloat("montant_total"),
                        result.getFloat("reste_a_payer")
                );
                factures.add(f);
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return factures;
}



}


