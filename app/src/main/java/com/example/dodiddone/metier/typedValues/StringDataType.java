package com.example.dodiddone.metier.typedValues;

import androidx.annotation.NonNull;

public class StringDataType extends DataType<String> {

    @Override
    public String toStorable() {
        return this.getTyped();
    }

    @Override
    public void setFromStorable(String storableValue) {
        this.setTyped(storableValue);
    }

    @NonNull
    @Override
    public String toString() {
        return this.getTyped();
    }
}
