package com.example.dodiddone.metier.typedValues.calcul;


import com.example.dodiddone.metier.Cahier;
import com.example.dodiddone.metier.Regle;
import com.example.dodiddone.metier.typedValues.TypesEnum;

public class Calcul {
    private String name;
    private String desc;
    private TypesEnum relatedType;
    private Calculator calculator;

    Calcul(String name, String desc, TypesEnum relatedType, Calculator calculator) {
        this.name = name;
        this.desc = desc;
        this.relatedType = relatedType;
        this.calculator = calculator;
    }

    public interface Calculator {
        public String compute(Cahier cahier, Regle regle);
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public TypesEnum getRelatedType() {
        return relatedType;
    }

    public String compute(Cahier cahier, Regle regle) {
        return calculator.compute(cahier, regle);
    }
}
