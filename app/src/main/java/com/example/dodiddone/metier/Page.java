package com.example.dodiddone.metier;

import java.util.HashMap;

public class Page {
    public HashMap<Structure, Champ> champs;

    public Page(HashMap<Structure, Champ> champs) {
        this.champs = champs;
    }
}
