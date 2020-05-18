package com.example.dodiddone.metier;

import com.example.dodiddone.metier.typedValues.TypesEnum;

public class Regle extends Entity {

    private String nom;
    private String unite;
    private TypesEnum type;
    private Cahier cahier;

    /**
     * Used by DB
     * @param id long
     * @param nom String
     * @param unite String
     * @param type String
     * @param cahier Cahier
     */
    public Regle(long id, String nom, String unite, String type, Cahier cahier) {
        this.id = id;
        this.nom = nom;
        this.unite = unite;
        this.type = TypesEnum.valueOf(type);
        this.cahier = cahier;
    }
    /**
     * Used by the UI
     * @param nom String
     * @param unite String
     * @param type String
     * @param cahier Cahier
     */
    public Regle(String nom, String unite, TypesEnum type, Cahier cahier) {
        this.nom = nom;
        this.unite = unite;
        this.type = type;
        this.cahier = cahier;
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

    public Cahier getCahier() {
        return cahier;
    }
}
