package com.example.dodiddone.metier.typedValues;

public class StringDataType extends DataType<String> {

    @Override
    public String toStorable() {
        return this.getTyped();
    }

    @Override
    public void setFromStorable(String storableValue) {
        this.setTyped(storableValue);
    }
}
