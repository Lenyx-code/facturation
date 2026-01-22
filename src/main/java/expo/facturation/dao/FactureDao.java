package expo.facturation.dao;

import java.sql.*;

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
}


