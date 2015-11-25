package com.krod.database;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class Table {

    public String name;
    public HashMap<String, Column> columns = new HashMap<String, Column>();
    private int newInVersion;

    public Class<?> tableClass;

    public String createTable;

    private Column id;//主键

    private boolean isAutoIncrement;


    public Table setmNewInVersion(int mNewInVersion) {
        this.newInVersion = mNewInVersion;
        return this;
    }

    public int getmNewInVersion() {
        return newInVersion;
    }

    public Table(Class<?> c, Column id) {
        tableClass = c;
        this.name = c.getSimpleName();
        this.id = id;
        this.isAutoIncrement = id.isAutoIncrement();
        initColumn();
    }

    private void initColumn() {
        ArrayList<Field> list = new ArrayList<Field>();
        ObjectUtil.getDeclaredFieldList(list, tableClass);
        int len = list.size();
        for (int i = 0; i < len; i++) {
            Field f = list.get(i);
            if (id.getName().equals(f.getName())) {
                columns.put(id.getName(), id.setField(f));
            } else {
                columns.put(f.getName(), new Column(f.getName()).setType(Column.Type.Text).setField(f));
            }
        }
    }

    public void removeColums(String name) {
        columns.remove(name);
    }

    public Table(Class<?> c, String createTable, boolean isAutoIncrement) {
        tableClass = c;
        this.name = c.getSimpleName();
        this.createTable = createTable;
        this.isAutoIncrement = isAutoIncrement;
    }

    public void addField(Column c) {
        columns.put(c.getName(), c);
    }

    public String getSQLAddField(Column field) {
        if (field != null) {
            String out = "";
            out = "ALTER TABLE " + name + " ADD " + field.toString();
            return out;
        }
        return null;
    }

    public String createTable() {
        if (!SQLUtil.isEmpty(createTable)) {
            return createTable;
        }
        int len = columns.size();
        if (len <= 0) {
            return "字段列表不能为空";
        }
        StringBuffer sb = new StringBuffer("CREATE TABLE IF NOT EXISTS '" + name + "' (");
        sb.append(id.toString());
        HashMap<String, Column> map = new HashMap<String, Column>(columns);
        map.remove(id.getName());
        if (len > 0) {
            for (Entry<String, Column> e : map.entrySet()) {
                sb.append(" , ").append(e.getValue().toString());
            }
            sb.append(");");
        }
        map.clear();
        return sb.toString();
    }

    public String deleteTable() {
        String out = "DROP TABLE IF EXISTS " + name;
        return out;
    }

    public String[] getFieldToArray() {
        int len = columns.size();
        String[] out = new String[len];
        int i = 0;
        for (Entry<String, Column> e : columns.entrySet()) {
            out[i] = e.getKey();
            i++;
        }
        return out;
    }

    /*public HashMap<String, Integer> getFieldTypeToArray() {
        int len = columns.size();
        HashMap<String, Integer> out = new HashMap<String, Integer>();
        for (int i = 0; i < len; i ++) {
            Column f = columns.get(i);
            out.put(f.getName(), f.getType().ordinal());
        }
        return out;
    }
    */
    public boolean isAutoIncrement() {
        return isAutoIncrement;
    }

    public Column getId() {
        return id;
    }
}
