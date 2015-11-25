package com.com.example.testsql;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import com.krod.database.Column;
import com.krod.database.DBHelper;
import com.krod.database.DataBaseChangeManage;
import com.krod.database.Table;

import java.util.ArrayList;
import java.util.HashMap;


public class HermesSqlHelper {
    private final static String DatabaseName = "hermes_";
    private static HermesSqlHelper mHelper;
    public static int SqlVersion = 5;

    private HermesSqlHelper() {

    }

    public static HermesSqlHelper getInstance() {
        if (mHelper == null) {
            mHelper = new HermesSqlHelper();
        }
        return mHelper;
    }

    // 创建数据表,根据用户名创建数据库
    public static void createDatabase(Context context, String userName) {
        HashMap<String, Table> tables = new HashMap<String, Table>();
        Table group = GroupMode.createTable().setmNewInVersion(SqlVersion);
        tables.put(group.name, group);
        DBHelper.getInstance().open(context, DatabaseName + userName, tables, SqlVersion);
        DataBaseChangeManage dataBaseChange = new DataBaseChangeManage() {
            public void onChange(String table) {
                Log.e("TAG", "11111" + table);
            }

            @Override
            public void insert(String table) {
                super.insert(table);

            }
        };
        dataBaseChange.addTableChange(new GroupMode(0));
        DBHelper.getInstance().setDataBaseChangeManage(dataBaseChange);
    }

    public boolean insert(Object o) {
        ContentValues values = DBHelper.getInstance().getContentValues(o);
        values.remove("id");
        boolean result = DBHelper.getInstance().insert(o.getClass().getSimpleName(), values);
        return result;
    }

    public boolean insertList(ArrayList list) {
        DBHelper dbHelper = DBHelper.getInstance();
        int len;
        try {
            if (list != null && (len = list.size()) > 0) {
                dbHelper.startTransaction();
                for (int i = 0; i < len; i++) {
                    insert(list.get(i));
                }
                dbHelper.successfulTransaction();
                return true;
            }
            return true;
        } catch (Exception e) {
            Log.e("Insert", "insert data fail");
            return false;
        } finally {
            dbHelper.endTransaction();
        }
    }

    public <T> boolean insert(Class<T> c, HashMap<String, String> value) {
        ContentValues values = DBHelper.getInstance().getContentValues(value);
        values.remove("id");
        return DBHelper.getInstance().insert(c.getSimpleName(), values);
    }

    public <T> boolean deleteByWhere(Class<T> c, String where) {
        try {
            return DBHelper.getInstance().delete(c.getSimpleName(), where);
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(Object o) {
        try {
            String table = o.getClass().getSimpleName();
            Column column = DBHelper.getInstance().getTable(table).getId();
            if (column.getType() == Column.Type.Integer) {
                return DBHelper.getInstance().delete(table, column.getName() + "=" + column.getField().get(o) + "");
            } else if (column.getType() == Column.Type.Text) {
                return DBHelper.getInstance().delete(table, column.getName() + "='" + column.getField().get(o) + "'");
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(Object o) {
        return DBHelper.getInstance().update(o);
    }

    public boolean updateList(ArrayList list, String where) {
        DBHelper dbHelper = DBHelper.getInstance();
        try {
            dbHelper.startTransaction();
            int len;
            if (list != null && (len = list.size()) > 0) {
                for (int i = 0; i < len; i++) {
                    dbHelper.update(list.get(i), where);
                }
                dbHelper.successfulTransaction();
                return true;
            }
            return true;
        } catch (Exception e) {
            Log.e("update", "update Data fail");
            return false;
        } finally {
            dbHelper.endTransaction();
        }
    }

    public boolean update(Object o, String where) {
        ContentValues values = DBHelper.getInstance().getContentValues(o);
        values.remove("id");
        return DBHelper.getInstance().update(o.getClass().getSimpleName(), values, where);
    }

    public <T> boolean update(Class<T> c, String where, HashMap<String, Object> map) {
        ContentValues values = DBHelper.getInstance().getContentValues(map);
        values.remove("id");
        return DBHelper.getInstance().update(c.getSimpleName(), values, where);
    }

    public <T> ArrayList<T> find(Class<T> c) {
        ArrayList<T> list = DBHelper.getInstance().list(c);
        return list;
    }

    public <T> ArrayList<T> findByWhere(Class<T> c, String where) {
        ArrayList<T> list = DBHelper.getInstance().list(c, where);
        return list;
    }

    public <T> T findOneByWhere(Class<T> c, String where) {
        ArrayList<T> list = DBHelper.getInstance().list(c, where, null, "1");
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        try {
            return c.newInstance();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public DBHelper getDBHelper() {
        return DBHelper.getInstance();
    }

    public boolean clearDataById(Object o, String where) {
        try {
            String table = o.getClass().getSimpleName();
            Column column = DBHelper.getInstance().getTable(table).getId();
            if (column.getType() == Column.Type.Integer) {
                return DBHelper.getInstance().delete(table, column.getName() + "=" + column.getField().get(o) + "");
            } else if (column.getType() == Column.Type.Text) {
                return DBHelper.getInstance().delete(table, column.getName() + "='" + column.getField().get(o) + "'");
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean clearData(String table) {
        return DBHelper.getInstance().delete(table, null);
    }

}
