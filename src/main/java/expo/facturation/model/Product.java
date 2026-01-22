package expo.facturation.model;

public class Product {
    private int id;
    private String nom;
    private int qte;
    private float prix;


    public Product(){}

    public Product(int id, String nom, int qte, float prix){
        this.id = id;
        this.nom = nom;
        this.qte = qte;
        this.prix = prix;
    }

    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }
    public String getNom(){
        return this.nom;
    }

    public void setNom(String nom){
        this.nom = nom;
    }
    public int getQte(){
        return this.qte;
    }

    public void setQte(int qte){
        this.qte = qte;
    }
    public float getPrix(){
        return this.prix;
    }

    public void setPrix(float prix){
        this.prix = prix;
    }
}
