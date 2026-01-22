package expo.facturation.model;

public class Facture {
    private int id;
    private String numFac;
    private int client;
    private String clientNom;
    private float montantPayer;
    private float montantTotal;
    private float reste;

    public Facture() {
    }
  public Facture(int id, String numFac, int client, String clientNom, float montantPayer, float montantTotal,
            float reste) {
        this.id = id;
        this.numFac = numFac;
        this.client = client;
        this.clientNom = clientNom;
        this.montantPayer = montantPayer;
        this.montantTotal = montantTotal;
        this.reste = reste;
    }

    public int getId() {
        return id;
    }

    public String getNumFac() {
        return numFac;
    }

    public int getClient() {
        return client;
    }

    public String getClientNom() {
        return clientNom;
    }

    public float getMontantPayer() {
        return montantPayer;
    }

    public float getMontantTotal() {
        return montantTotal;
    }

    public float getReste() {
        return reste;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNumFac(String numFac) {
        this.numFac = numFac;
    }

    public void setClient(int client) {
        this.client = client;
    }

    public void setClientNom(String clientNom) {
        this.clientNom = clientNom;
    } // nouveau setter

    public void setMontantPayer(float montantPayer) {
        this.montantPayer = montantPayer;
    }

    public void setMontantTotal(float montantTotal) {
        this.montantTotal = montantTotal;
    }

    public void setReste(float reste) {
        this.reste = reste;
    }
}
