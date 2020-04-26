package com.example.dodiddone.metier;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Page extends Entity {
    protected Map<Structure, Champ> champs;
    protected Date creation;
    protected long cahierRef;

    public Page() {
        this.champs = new HashMap<>();
    }
    public Page(long id, long creationTimestamp, long cahierRef) {
        this();
        this.id = id;
        this.creation = new Date(creationTimestamp);
        this.cahierRef = cahierRef;
    }

    public Page(long cahierRef) {
        this.creation = new Date();
        this.cahierRef = cahierRef;
    }

    public Date getCreation() {
        return creation;
    }

    public void setCreation(Date creation) {
        this.creation = creation;
    }

    public long getCahierRef() {
        return cahierRef;
    }

    public void setCahierRef(long cahierRef) {
        this.cahierRef = cahierRef;
    }
}
