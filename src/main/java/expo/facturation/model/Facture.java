package expo.facturation.model;

public class Facture {
    private int id;
    private String numFac;
    private int client;
    private float montantPayer;
    private float montantTotal;
    private float reste;

    public Facture() {
    }

    public Facture(int id, String numFac, int client, float montantPayer, float montantTotal, float reste) {
        this.id = id;
        this.numFac = numFac;
        this.client = client;
        this.montantPayer = montantPayer;
        this.montantTotal = montantTotal;
        this.reste = reste;
    }

    public int getId(){
        return this.id;
    }
    public String getNumFac(){
        return this.numFac;
    }
    public int getClient(){
        return this.client;
    }
    public float getMontantPayer(){
        return this.montantPayer;
    }
    public float getMontantTotal(){
        return this.montantTotal;
    }
    public float getReste(){
        return this.reste;
    }

    public void setId(int id){
        this.id = id;
    }
    public void setNumFac(String num){
        this.numFac = num;
    }
    public void setClient(int cli){
        this.client = cli;
    }
    public void setMontantPayer(float mp){
        this.montantPayer = mp;
    }
    public void setMontantTotal(float mt){
        this.montantTotal = mt;
    }
    public void setReste(float r){
        this.reste = r;
    }


}
