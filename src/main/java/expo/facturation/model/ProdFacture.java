package expo.facturation.model;

public class ProdFacture {
    private int id;
    private int idFac;
    private int idPro;
    private int qte;

    public ProdFacture() {
    }

    public ProdFacture(int id,
            int idFac,
            int idPro,
            int qte) {
        this.id = id;
        this.idFac = idFac;
        this.idPro = idPro;
        this.qte = qte;
    }

    public int getId() {
        return this.id;
    }

    public int getIdFac() {
        return this.idFac;
    }

    public int getIdProd() {
        return this.idPro;
    }

    public int getQte() {
        return this.qte;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdFac(int id) {
        this.idFac = id;
    }

    public void setIdProd(int idPro) {
        this.idPro = idPro;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }
}
