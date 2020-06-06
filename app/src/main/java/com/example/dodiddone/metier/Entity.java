package com.example.dodiddone.metier;

import java.io.Serializable;

public abstract class Entity implements Serializable {

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
