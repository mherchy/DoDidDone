package com.example.dodiddone.metier.typedValues;

import androidx.annotation.NonNull;

import java.math.BigDecimal;

public class BooleanDataType extends DataType<Boolean> {

    @Override
    public String toStorable() {
        return this.getTyped() ? "1" : "0";
    }

    @Override
    public void setFromStorable(String storableValue) {
        this.setTyped(storableValue.equals("1"));
    }

    @NonNull
    @Override
    public String toString() {
        return this.getTyped() ? "Oui" : "Non";
    }
}
