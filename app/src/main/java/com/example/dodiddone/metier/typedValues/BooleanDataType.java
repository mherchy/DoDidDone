package com.example.dodiddone.metier.typedValues;

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
}
