package expo.facturation.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import expo.facturation.db.DbConnection;

public class ProdFactureDao {
     Connection conn = DbConnection.getConnection();
    public void create(int idFac, int idProd, int qte) {
    String sql = "INSERT INTO prod_factures (id_fac, id_prod, qte) VALUES (?, ?, ?)";
    try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, idFac);
        ps.setInt(2, idProd);
        ps.setInt(3, qte);
        ps.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    }
}

}
