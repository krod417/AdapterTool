package com.krod.database;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBHelper {

    private SQLiteDatabase db;
    private DataFrameworkCore mDataFramework;
    private HashMap<String, Table> tables = new HashMap<String, Table>();
    private int mOpenInstances = 0;
    private DataBaseChangeManage changeManage;

    private final static class DBHelperHolder {
        private static DBHelper INSTANCE = new DBHelper();
    }

    private DBHelper() {
        mDataFramework = new DataFrameworkCore();
    }

    public static DBHelper getInstance() {
        return DBHelperHolder.INSTANCE;
    }

    public void open(Context c, String name, HashMap<String, Table> table, int version) {
        tables = table;
        if (mOpenInstances == 0) {
            mOpenInstances++;
            mDataFramework.open(c, name, table, version);
        }
        db = mDataFramework.getmDb();
    }

    public void setDataBaseChangeManage(DataBaseChangeManage dataBaseChangeManage) {
        this.changeManage = dataBaseChangeManage;
    }

    public void close() {
        if (mOpenInstances == 1) {
            mDataFramework.close();
            mOpenInstances--;
        }
    }

    private void checkAutoIncrement(String tableName, ContentValues values) {
        Table table = getTable(tableName);
        if (table.isAutoIncrement()) {
            values.remove(table.getId().getName());
        }
    }

    public boolean insert(String table, ContentValues values) {
        long id = 0;
        try {
            checkAutoIncrement(table, values);
            id = db.insert(table, null, values);
            if (id > 0 && changeManage != null) {
                changeManage.insert(table);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (id > 0);
    }

    public boolean insert(Object o) {
        return insert(o.getClass().getSimpleName(), getContentValues(o));
    }

    public boolean insert(String table, HashMap<String, String> o) {
        return insert(table, getContentValues(o));
    }

    public boolean update(String table, ContentValues values, String where) {
        int id = 0;
        try {
            checkAutoIncrement(table, values);
            id = db.update(table, values, where, null);
            if (id > 0 && changeManage != null) {
                changeManage.update(table);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (id > 0);
    }

    public boolean update(Object o, String where) {
        return update(o.getClass().getSimpleName(), getContentValues(o), where);
    }

    public boolean update(Object o) {
        try {
            ContentValues values = getContentValues(o);
            String table = o.getClass().getSimpleName();
            Column column = getTable(table).getId();
            if (column.getType() == Column.Type.Integer) {
                return update(table, values, column.getName() + "=" + values.get(column.getName()));
            } else if (column.getType() == Column.Type.Text) {
                return update(table, values, column.getName() + "='" + values.get(column.getName()) + "'");
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ContentValues getContentValues(Object o) {
        ContentValues values = new ContentValues();
        HashMap<String, Column> fields = tables.get(o.getClass().getSimpleName()).columns;
        int size = fields.size();
        if (size <= 0) {
            return values;
        }
        for (Entry<String, Column> entry : fields.entrySet()) {
            try {
                Field f = entry.getValue().getField();
                f.setAccessible(true);
                Object obj = f.get(o);
                if (obj != null) {
                    values.put(f.getName(), obj.toString());
                }
            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return values;
    }

    public ContentValues getContentValues(HashMap<String, Object> map) {
        ContentValues values = new ContentValues();
        for (Entry<String, Object> e : map.entrySet()) {
            if (e.getValue() != null) {
                values.put(e.getKey(), e.getValue().toString());
            }
        }
        return values;
    }

    public boolean delete(Object o) {
        try {
            String table = o.getClass().getSimpleName();
            Column column = getTable(table).getId();
            if (column.getType() == Column.Type.Integer) {
                return delete(table, column.getName() + "=" + column.getField().get(o));
            } else if (column.getType() == Column.Type.Text) {
                return delete(table, column.getName() + "='" + column.getField().get(o) + "'");
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(String table, String where) {
        boolean result = false;
        try {
            result = db.delete(table, where, null) > 0;
            if (result && changeManage != null) {
                changeManage.delete(table);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public <T> ArrayList<T> list(Class<T> c) {
        return list(c, null, null, null);
    }

    public <T> ArrayList<T> list(Class<T> c, String where) {
        return list(c, where, null, null);
    }

    public <T> ArrayList<T> list(Class<T> c, String where, String orderby) {
        return list(c, where, orderby, null);
    }

    public <T> ArrayList<T> list(Class<T> c, String where, String orderby, String limit) {
        String tableName = c.getSimpleName();
        Table t = getTable(tableName);
        String[] columns = t.getFieldToArray();
        Cursor cur = db.query(tableName, columns, where, null, null, null, orderby, limit);
        ArrayList<T> list = new ArrayList<T>();
        if (cur != null && cur.moveToFirst()) {
            while (!cur.isAfterLast()) {
                list.add(curToObj(cur, c));
                cur.moveToNext();
            }
        }
        if (cur != null) {
            cur.close();
        }
        return list;
    }

    public Cursor listCursor(Class c) {
        String tableName = c.getSimpleName();
        Table t = getTable(tableName);
        String[] columns = t.getFieldToArray();
        Cursor cur = db.query(tableName, columns, null, null, null, null, null, null);
        return cur;
    }

    public <T> T curToObj(Cursor cur, Class<T> c) {
        T obj = null;
        try {
            obj = c.newInstance();
            HashMap<String, Column> fields = tables.get(c.getSimpleName()).columns;
            int size = fields.size();
            if (size > 0) {
                for (Entry<String, Column> entry : fields.entrySet()) {
                    Field f = entry.getValue().getField();
                    f.setAccessible(true);
                    int index = cur.getColumnIndex(f.getName());
                    if (index < 0) {
                        continue;
                    }
                    String str = cur.getString(index);
                    String typename = f.getType().getName();
                    if (typename.equalsIgnoreCase("int")) {
                        f.set(obj, Integer.parseInt(str));
                    } else if (typename.equalsIgnoreCase("float")) {
                        f.set(obj, Float.parseFloat(str));
                    } else if (typename.equalsIgnoreCase("double")) {
                        f.set(obj, Double.parseDouble(str));
                    } else if (typename.equals(String.class.getName())) {
                        f.set(obj, str);
                    } else {
                        String name = f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1);
                        Method m = null;
                        try {
                            m = c.getMethod("setValue" + name, String.class);
                        } catch (NoSuchMethodException e) {
                            continue;
                        }
                        if (str != null) {
                            m.invoke(obj, str);
                        } else {
                            m.invoke(obj, "");
                        }
                    }
                }
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return obj;
    }


    public Table getTable(String table) {
        Table res = tables.get(table);
        if (res == null) {
            throw new NullPointerException("No existe la tabla '" + table + "'");
        }
        return res;
    }


    /**
     * Crea las tablas de la base de datos
     */
    public void createTables() {
        for (Entry<String, Table> e : tables.entrySet()) {
            db.execSQL(e.getValue().createTable());
        }
    }

    /**
     * Borra todas las tablas de la base de datos
     */

    public void deleteTables() {
        for (Entry<String, Table> e : tables.entrySet()) {
            db.execSQL(e.getValue().deleteTable());
        }
    }

    /**
     * Borra la tabla pasada como parametro de la base de datos
     *
     * @param table tabla
     */
    public void deleteTable(String table) {
        Table t = getTable(table);
        if (t != null) {
            db.execSQL(t.deleteTable());
        }
    }

    /**
     * Vacia todas las tablas de la base de datos
     */
    public void emptyTables() {
        for (Entry<String, Table> e : tables.entrySet()) {
            db.delete(e.getValue().name, null, null);
        }
    }

    /**
     * Vacia la tabla pasada como parametro de la base de datos
     *
     * @param table tabla
     */
    public void emptyTable(String table) {
        db.delete(table, null, null);
    }

    /**
     * Inicia una transaccion
     *
     * @param void
     * @return void.
     */
    public void startTransaction() {
        db.beginTransaction();
    }

    /**
     * Finaliza una transaccion
     *
     * @param void
     * @return void.
     */
    public void endTransaction() {
        db.endTransaction();
    }

    /**
     * Indica si estamos en una transaccion
     *
     * @param void
     * @return Boolean True si hay una transaccion en curso, false en caso contrario.
     */
    public boolean inTransaction() {
        return db.inTransaction();
    }

    /**
     * Confirma la ejecucion correcta de una transaccion
     *
     * @param void
     * @return void.
     */
    public void successfulTransaction() {
        db.setTransactionSuccessful();
    }

    public void execSQL(String sql) {
        db.execSQL(sql);
    }

    public Cursor rawQuery(String sql, String[] selectionArgs) {
        Cursor c = db.rawQuery(sql, selectionArgs);
        if (c.getCount() > 0) {
            c.moveToFirst();
        }
        return c;
    }
}
