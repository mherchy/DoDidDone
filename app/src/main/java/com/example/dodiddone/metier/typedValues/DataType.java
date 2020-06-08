package com.example.dodiddone.metier.typedValues;

import androidx.annotation.NonNull;

import java.io.Serializable;

public abstract class DataType<T> implements Serializable {

    private T value;

    public DataType() {
    }

    public abstract String toStorable();

    public abstract void setFromStorable(String storableValue);

    @NonNull
    public abstract String toString();

    public T getTyped() {
        return this.value;
    }

    public void setTyped(T value) {
        this.value = value;
    }

    public final boolean isEmpty() {
        return this.value == null;
    }
}
