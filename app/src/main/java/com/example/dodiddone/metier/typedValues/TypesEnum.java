package com.example.dodiddone.metier.typedValues;

import android.util.Log;

public enum TypesEnum {
    TITRE(StringDataType.class),
    TXTLONG(StringDataType.class),
    TXTCOURT(StringDataType.class),
    NB(NumericDataType.class),
    BOOL(BooleanDataType.class),
    DATE(DateDataType.class);

    private Class associatedClass;

    TypesEnum(Class associatedClass) {
        this.associatedClass = associatedClass;
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
     * @return DataType
     */
    public DataType newDataType(String storableValue) {
        DataType d = this.newDataType();
        d.setFromStorable(storableValue);
        return d;
    }
}
