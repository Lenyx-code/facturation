package expo.facturation.dao;

import java.sql.*;

import expo.facturation.db.DbConnection;
import expo.facturation.model.Facture;

public class FactureDao {
    Connection conn = DbConnection.getConnection();

    public void createInvoice(Facture fac){
        try {
            String query = "INSERT INTO factures (num_fac, id_cli, montant_payer, montant_total, reste_a_payer) VALUES (?, ?, ?, ?, ?)";
            if(conn != null){
                PreparedStatement stmt = conn.prepareStatement(query);

                stmt.setString(1, fac.getNumFac());
                stmt.setInt(2, fac.getClient());
                stmt.setFloat(3, fac.getMontantPayer());
                stmt.setFloat(4, fac.getMontantTotal());
                stmt.setFloat(5, fac.getReste());

                stmt.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
