package com.example.dodiddone.metier.typedValues;

public abstract class DataType<T> {

    private T value;

    public DataType() {
    }

    public abstract String toStorable();

    public abstract void setFromStorable(String storableValue);

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
