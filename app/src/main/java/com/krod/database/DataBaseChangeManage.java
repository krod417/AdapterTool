package com.krod.database;

import java.util.HashMap;

public class DataBaseChangeManage {
    private HashMap<String, Object> map = new HashMap<>();

    public DataBaseChangeManage() {

    }

    public void insert(String table) {
        if (!map.containsKey(table)) {
            return;
        }
        onChange(table);
    }

    public void update(String table) {
        if (!map.containsKey(table)) {
            return;
        }
        onChange(table);
    }

    public void delete(String table) {
        if (!map.containsKey(table)) {
            return;
        }
        onChange(table);
    }

    public void onChange(String table) {

    }

    public DataBaseChangeManage addTableChange(Object o) {
        map.put(o.getClass().getSimpleName(), o);
        return this;
    }

    public DataBaseChangeManage addTableChange(String table, Object o) {
        map.put(table, o);
        return this;
    }
}
