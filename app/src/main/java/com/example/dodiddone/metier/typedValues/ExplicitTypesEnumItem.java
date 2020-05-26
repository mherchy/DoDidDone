package com.example.dodiddone.metier.typedValues;

import androidx.annotation.NonNull;

public class ExplicitTypesEnumItem {

    private TypesEnum item;

    public ExplicitTypesEnumItem(TypesEnum item) {
        this.item = item;
    }

    public TypesEnum getItem() {
        return item;
    }

    @NonNull
    @Override
    public String toString() {
        return item.getNom();
    }

}
