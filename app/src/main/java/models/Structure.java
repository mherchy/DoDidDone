package models;

public class Structure {
    private String nom;
    private String unite;
    private TypesEnum type;

    public Structure(String nom, String unite, TypesEnum type) {
        this.nom = nom;
        this.unite = unite;
        this.type = type;
    }
}
