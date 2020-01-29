package models;

import com.google.gson.JsonElement;

public class Champ {
    private JsonElement valeur;
    private TypesEnum type;

    public Champ(JsonElement valeur, TypesEnum type) {
        this.valeur = valeur;
        this.type = type;
    }
}
