package com.example.dodiddone.metier;

import java.util.ArrayList;
import java.util.HashMap;

public class Cahier {

    private long id;
    private String nom;
    private ArrayList<Page> pages;
    private HashMap<String, Structure> structure;

    public Cahier(String nom) {
        this.nom = nom;
    }
    public Cahier(long id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public long getID() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public boolean ajoutPage(Page p)
    {
        return this.pages.add(p);
    }
}
