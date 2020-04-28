package com.example.dodiddone.metier;

import com.example.dodiddone.metier.typedValues.TypesEnum;

public class Ligne extends Entity {
    /* à revoir */
    private TypesEnum type;
    private String valeur;
    protected long refPage;
    protected long refRegle;

    public Ligne(long id, String type, String valeur, long refPage, long refRegle) {
        this.id = id;
        this.type = TypesEnum.valueOf(type);
        this.valeur = valeur;
        this.refPage = refPage;
        this.refRegle = refRegle;
    }

    public String getValeur() {
        return valeur;
    }

    public long getRefPage() {
        return refPage;
    }

    public long getRefRegle() {
        return refRegle;
    }

    public TypesEnum getType() {
        return type;
    }
}
