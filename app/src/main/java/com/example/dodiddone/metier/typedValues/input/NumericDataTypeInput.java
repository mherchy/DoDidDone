package com.example.dodiddone.metier.typedValues.input;

import android.content.Context;
import android.view.inputmethod.EditorInfo;

import com.example.dodiddone.metier.Regle;

import java.math.BigDecimal;

public class NumericDataTypeInput extends androidx.appcompat.widget.AppCompatEditText implements DataTypeInput<BigDecimal> {

    public NumericDataTypeInput(Context context) {
        super(context);
        this.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
    }

    @Override
    public void setLabel(String label) {
        this.setHint(label);
    }

    @Override
    public void setValue(BigDecimal value) {
        this.setText(value.toString());
    }

    @Override
    public BigDecimal getValue() {
        return new BigDecimal(this.getText().toString());
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
