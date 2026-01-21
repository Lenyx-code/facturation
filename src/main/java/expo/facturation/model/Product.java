package expo.facturation.model;

public class Product {
    private int id;
    private String nom;
    private int qte;


    public Product(){}

    public Product(int id, String nom, int qte){
        this.id = id;
        this.nom = nom;
        this.qte = qte;
    }
}
