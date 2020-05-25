package com.example.dodiddone.metier.typedValues.input;

import android.content.Context;
import android.widget.CheckBox;

import com.example.dodiddone.metier.Regle;

public class BooleanDataTypeInput extends androidx.appcompat.widget.AppCompatCheckBox implements DataTypeInput<Boolean> {
    public BooleanDataTypeInput(Context context) {
        super(context);
    }

    @Override
    public void setLabel(String label) {
        this.setText(label);
    }

    @Override
    public void setValue(Boolean value) {
        this.setChecked(value);
    }

    @Override
    public Boolean getValue() {
        return this.isChecked();
    }

    @Override
    public void setRegle(Regle regle) {
        this.setTag(regle);
    }

    @Override
    public Regle getRegle() {
        return (Regle) getTag();
    }
}
