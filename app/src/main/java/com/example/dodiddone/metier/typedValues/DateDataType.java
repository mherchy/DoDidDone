package com.example.dodiddone.metier.typedValues;

import java.util.Date;

public class DateDataType extends DataType<Date> {

    @Override
    public String toStorable() {
        return ""+this.getTyped().getTime();
    }

    @Override
    public void setFromStorable(String storableValue) {
        this.setTyped(new Date(Long.parseLong(storableValue)));
    }
}
