package com.example.dodiddone.metier;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Page extends Entity {
    private Map<Regle, Ligne> lignes;
    private Date creation;
    private Cahier cahier;


    /**
     * Base Constructor
     */
    private Page() {
        this.lignes = new HashMap<>();
    }
    /**
     * Used by DB
     * Generate an empty Page
     * @param id long
     * @param creationTimestamp long
     * @param cahier cahier
     */
    public Page(long id, long creationTimestamp, Cahier cahier) {
        this();
        this.id = id;
        this.creation = new Date(creationTimestamp);
        this.cahier = cahier;
    }
    /**
     * Used by the UI
     * Generate an empty Page
     * @param cahier Cahier
     */
    public Page(Cahier cahier, Date date) {
        this();
        this.creation = date;
        this.cahier = cahier;
    }

    public Date getCreation() {
        return creation;
    }

    public void setCreation(Date creation) {
        this.creation = creation;
    }

    public Cahier getCahier() {
        return cahier;
    }

    public void setCahier(Cahier cahier) {
        this.cahier = cahier;
    }

    public void addLigne(Ligne l) {
        this.lignes.put(l.getRegle(), l);
    }
    public void addLigne(Iterable<Ligne> itr) {
        for (Ligne ligne : itr) {
            this.addLigne(ligne);
        }
    }
}
