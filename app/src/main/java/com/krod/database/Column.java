package com.krod.database;


import java.lang.reflect.Field;

public class Column {
    public enum Type {
        Integer {
            public String getType() {
                return "INTEGER";
            }
        },
        Text {
            public String getType() {
                return "text";
            }
        };

        public abstract String getType();
    }

    private String name;
    private Type type = Type.Text;
    private int length = 0;
    private boolean isNull = true;
    private boolean isAutoIncrement = false;
    private boolean isPrimaryKey = false;
    private String normal = null;
    private int mNewInVersion;
    private Field field;
    //[_id] INTEGER PRIMARY KEY AUTOINCREMENT,

    public Column setField(Field field) {
        this.field = field;
        return this;
    }

    public Field getField() {
        return field;
    }

    public void setmNewInVersion(int mNewInVersion) {
        this.mNewInVersion = mNewInVersion;
    }

    public int getmNewInVersion() {
        return mNewInVersion;
    }

    public Type getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public Column(String name) {
        super();
        this.name = name;
        mNewInVersion = 1;
    }

    public Column setType(Type type) {
        this.type = type;
        return this;
    }

    public Column setNull(boolean isNull) {
        this.isNull = isNull;
        return this;
    }

    public Column setPrimaryKey(boolean isPrimaryKey) {
        this.isPrimaryKey = isPrimaryKey;
        return this;
    }

    public Column setAutoIncrement(boolean isAutoIncrement) {
        this.isAutoIncrement = isAutoIncrement;
        return this;
    }

    public Column setNormal(String normal) {
        this.normal = normal;
        return this;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer("'" + name + "' ");
        sb.append(getTypeString());
        sb.append(getAutoinCrement());
        sb.append(isNull ? "" : " NOT NULL ");
        if (normal != null) {
            sb.append(" DEFAULT '" + normal + "' ");
        }
        return sb.toString();
    }

    private String getTypeString() {
        if (type.ordinal() <= 1 && length > 0) {
            return type.getType().concat("(" + length + ")");
        }
        return type.getType();
    }

    private String getAutoinCrement() {
        if (isAutoIncrement) {
            return " PRIMARY KEY AUTOINCREMENT ";
        }
        if (isPrimaryKey) {
            return " PRIMARY KEY ";
        }
        return "";
    }

    public boolean isAutoIncrement() {
        return isAutoIncrement;
    }
}
