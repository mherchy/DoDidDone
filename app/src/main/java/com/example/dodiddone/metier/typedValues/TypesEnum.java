package com.example.dodiddone.metier.typedValues;

import android.content.Context;
import android.util.Log;

import com.example.dodiddone.metier.typedValues.input.BooleanDataTypeInput;
import com.example.dodiddone.metier.typedValues.input.DataTypeInput;
import com.example.dodiddone.metier.typedValues.input.NumericDataTypeInput;
import com.example.dodiddone.metier.typedValues.input.StringDataTypeInput;

import java.lang.reflect.InvocationTargetException;

public enum TypesEnum {
    TITRE(StringDataType.class, StringDataTypeInput.class),
    TXTLONG(StringDataType.class, StringDataTypeInput.class),
    TXTCOURT(StringDataType.class, StringDataTypeInput.class),
    NB(NumericDataType.class, NumericDataTypeInput.class),
    //DATE(DateDataType.class, DateDataTypeInput.class),
    BOOL(BooleanDataType.class, BooleanDataTypeInput.class);


    private Class associatedClass;
    private Class associatedInput;

    TypesEnum(Class associatedClass, Class associatedInput) {
        this.associatedClass = associatedClass;
        this.associatedInput = associatedInput;
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
}
