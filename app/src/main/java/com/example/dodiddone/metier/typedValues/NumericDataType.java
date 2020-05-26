package com.example.dodiddone.metier.typedValues;

import androidx.annotation.NonNull;

import java.math.BigDecimal;

public class NumericDataType extends DataType<BigDecimal> {

    @Override
    public String toStorable() {
        return this.getTyped().toString();
    }

    @Override
    public void setFromStorable(String storableValue) {
        this.setTyped(new BigDecimal(storableValue));
    }

    @NonNull
    @Override
    public String toString() {
        return this.getTyped().toString();
    }


}
