package com.example.dodiddone.metier.typedValues;

import java.math.BigDecimal;

public class LineNumericValue extends LineStringValue {


    public LineNumericValue(String value) {
        super(value);
    }
    public LineNumericValue(BigDecimal value) {
        super(value.toString());
    }

    public BigDecimal getNumericValue() {
        return new BigDecimal(value);
    }

}
