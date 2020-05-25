package com.example.dodiddone.metier.typedValues.input;

import com.example.dodiddone.metier.Regle;

public interface DataTypeInput<T> {
    public void setLabel(String label);
    public void setValue(T value);
    public T getValue();
    public void setRegle(Regle regle);
    public Regle getRegle();
}
