package com.example.dodiddone.metier.typedValues.input;

import android.content.Context;
import android.view.inputmethod.EditorInfo;

import com.example.dodiddone.metier.Regle;

public class StringDataTypeInput extends androidx.appcompat.widget.AppCompatEditText implements DataTypeInput<String> {

    public StringDataTypeInput(Context context) {
        super(context);
        this.setInputType(EditorInfo.TYPE_CLASS_TEXT);
    }

    @Override
    public void setLabel(String label) {
        this.setHint(label);
    }

    @Override
    public void setValue(String value) {
        this.setText(value);
    }

    @Override
    public String getValue() {
        return this.getText().toString();
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
