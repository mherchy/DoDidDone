package com.example.dodiddone.metier;

public class Regle extends Entity {

    private String nom;
    private String unite;
    private TypesEnum type;
    private long cahierRef;

    public Regle(long id, String nom, String unite, String type, long cahierRef) {
        this.id = id;
        this.nom = nom;
        this.unite = unite;
        this.type = TypesEnum.valueOf(type);
        this.cahierRef = cahierRef;
    }

    public String getNom() {
        return nom;
    }

    public String getUnite() {
        return unite;
    }

    public TypesEnum getType() {
        return type;
    }

    public long getCahierRef() {
        return cahierRef;
    }
}
