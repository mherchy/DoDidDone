package com.example.dodiddone.metier;

import com.example.dodiddone.metier.typedValues.DataType;
import com.example.dodiddone.metier.typedValues.TypesEnum;

public class Ligne extends Entity {
    /* à revoir */
    private TypesEnum type;
    private DataType valeur;
    private Page page;
    private Regle regle;

    /**
     * Used by the UI
     * Generate an empty Line
     * An empty DataType value is generated also
     * @param page Page
     * @param regle Regle
     */
    public Ligne(Page page, Regle regle) {
        this.type = regle.getType();
        this.page = page;
        this.regle = regle;
        this.valeur = this.type.newDataType();
    }

    /**
     * Used by the LigneDAO
     * An DataType value is generated with the value in the stored format
     * @param id long
     * @param type String
     * @param valeur String
     * @param page Page
     * @param regle Regle
     */
    public Ligne(long id, String type, String valeur, Page page, Regle regle) {
        this.id = id;
        this.type = TypesEnum.valueOf(type);
        this.page = page;
        this.regle = regle;
        this.valeur = this.type.newDataType(valeur);
    }


    public DataType getValeur() {
        return valeur;
    }

    public TypesEnum getType() {
        return type;
    }

    public Regle getRegle() {
        return regle;
    }

    public void setRegle(Regle regle) {
        this.regle = regle;
    }
}
