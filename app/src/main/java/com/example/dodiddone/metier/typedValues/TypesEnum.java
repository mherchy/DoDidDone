package com.example.dodiddone.metier.typedValues;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.dodiddone.metier.typedValues.input.BooleanDataTypeInput;
import com.example.dodiddone.metier.typedValues.input.DataTypeInput;
import com.example.dodiddone.metier.typedValues.input.NumericDataTypeInput;
import com.example.dodiddone.metier.typedValues.input.StringDataTypeInput;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public enum TypesEnum {

    TITRE("Titre", StringDataType.class, StringDataTypeInput.class),
    TXTLONG("Texte", StringDataType.class, StringDataTypeInput.class),
    TXTCOURT("Texte court", StringDataType.class, StringDataTypeInput.class),
    NB("Nombre", NumericDataType.class, NumericDataTypeInput.class),
    BOOL("Oui/Non", BooleanDataType.class, BooleanDataTypeInput.class);
    //DATE(DateDataType.class, DateDataTypeInput.class),

    private String nom;
    private Class associatedClass;
    private Class associatedInput;

    TypesEnum(String nom, Class associatedClass, Class associatedInput) {
        this.nom = nom;
        this.associatedClass = associatedClass;
        this.associatedInput = associatedInput;
    }

    public String getNom() {
        return nom;
    }

    public Class getAssociatedClass() {
        return associatedClass;
    }

    /**
     * @return DataType
     */
    public DataType newDataType() {
        try {
            return (DataType) associatedClass.newInstance();
        } catch (IllegalAccessException iaE) {
            Log.println(Log.ERROR, "DataType","com.example.dodiddone.metier.typedValues.TypesEnum.newEmptyDataType IllegalAccessException");
            System.exit(2);
        } catch (InstantiationException instE) {
            Log.println(Log.ERROR, "DataType", "com.example.dodiddone.metier.typedValues.TypesEnum.newEmptyDataType InstantiationException");
            System.exit(2);
        }
        return null;
    }
    /**
     * @return DataTypeInput
     */
    public DataTypeInput newInput(Context context) {
        try {
            return (DataTypeInput) associatedInput.getConstructor(Context.class).newInstance(context);
        } catch (IllegalAccessException iaE) {
            Log.println(Log.ERROR, "DataTypeInput","com.example.dodiddone.metier.typedValues.TypesEnum.newEmptyDataType IllegalAccessException");
            System.exit(2);
        } catch (InstantiationException instE) {
            Log.println(Log.ERROR, "DataTypeInput", "com.example.dodiddone.metier.typedValues.TypesEnum.newEmptyDataType InstantiationException");
            System.exit(2);
        } catch (NoSuchMethodException nsmE) {
            Log.println(Log.ERROR, "DataTypeInput", "com.example.dodiddone.metier.typedValues.TypesEnum.newEmptyDataType NoSuchMethodException");
            System.exit(2);
        } catch (InvocationTargetException e) {
            Log.println(Log.ERROR, "DataTypeInput", "com.example.dodiddone.metier.typedValues.TypesEnum.newEmptyDataType InvocationTargetException");
            System.exit(2);
        }
        return null;
    }
    /**
     * @return DataType
     */
    public DataType newDataType(String storableValue) {
        DataType d = this.newDataType();
        d.setFromStorable(storableValue);
        return d;
    }

    public static List<ExplicitTypesEnumItem> explicitValues() {
        ArrayList<ExplicitTypesEnumItem> array = new ArrayList<>();
        for (TypesEnum item : TypesEnum.values()) {
            array.add(new ExplicitTypesEnumItem(item));
        }
        return array;
    }


}
