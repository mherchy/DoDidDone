package com.example.dodiddone.metier;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class Cahier extends Entity {

    protected String nom;
    protected HashSet<Page> pages;
    protected HashMap<Long, Regle> regles;


    private Cahier() {
        this.pages = new HashSet<>();
        this.regles = new HashMap<>();
    }
    public Cahier(String nom) {
        this();
        this.nom = nom;
    }
    public Cahier(long id, String nom) {
        this();
        this.id = id;
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public boolean addPage(Page p)
    {
        return this.pages.add(p);
    }
    public boolean addPage(Collection<Page> pages)
    {
        return this.pages.addAll(pages);
    }

    public HashSet<Page> getPages() {
        return pages;
    }

    public HashMap<Long, Regle> getRegles() {
        return regles;
    }
    public void addRegle(Regle r) {
        this.regles.put(r.getId(), r);
    }
    public void addRegle(Iterable<Regle> itr) {
        for (Regle regle : itr) {
            this.addRegle(regle);
        }
    }
}
