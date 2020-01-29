package models;

import java.util.ArrayList;
import java.util.HashMap;

public class Cahier {

    private String nom;
    private ArrayList<Page> pages;
    private HashMap<String, Structure> structure;

    public Cahier(String nom) {
        this.nom = nom;
    }

    public boolean AjoutPage(Page p)
    {
        return this.pages.add(p);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
