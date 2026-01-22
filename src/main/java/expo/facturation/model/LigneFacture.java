package expo.facturation.model;

public class LigneFacture {

    private Product product;
    private int qte;
    private float total;

    public LigneFacture(Product product, int qte) {
        this.product = product;
        this.qte = qte;
        this.total = product.getPrix() * qte;
    }

    public Product getProduct() {
        return product;
    }

    public String getNomProduit() {
        return product.getNom();
    }

    public float getPrix() {
        return product.getPrix();
    }

    public int getQte() {
        return qte;
    }

    public float getTotal() {
        return total;
    }

}
