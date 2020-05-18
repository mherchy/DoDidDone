package com.example.dodiddone.metier;

public abstract class Entity {

    protected long id = -1;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean hasId() {
        return id > 0;
    }
}
