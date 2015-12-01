package com.krod.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.HashMap;
import java.util.Map.Entry;

public class DataFrameworkCore {
    private HashMap<String, Table> tables = new HashMap<String, Table>();
    private Context context;
    private String dataName;
    private int dataBaseVersion = 1;
    private int dataBaseOldVersion = 1;
    private SQLiteDatabase db;
    private MySqliteOpenHelper myHelper;

    public DataFrameworkCore() {

    }

    public void open(Context context, String databaseName, HashMap<String, Table> table, int version) {
        this.context = context;
        this.dataName = databaseName;
        this.tables = table;
        this.dataBaseVersion = version;
        this.myHelper = new MySqliteOpenHelper();
        this.db = myHelper.getReadableDatabase();
    }

    public void close() {
        db.close();
        myHelper.close();
    }

    class MySqliteOpenHelper extends SQLiteOpenHelper {

        public MySqliteOpenHelper() {
            super(context, dataName, null, dataBaseVersion);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            int len = tables.size();
            if (len > 0) {
                for (Entry<String, Table> e : tables.entrySet()) {
                    db.execSQL(e.getValue().createTable());
                }
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            dataBaseOldVersion = oldVersion;
            if (tables != null && tables.size() > 0) {
                for (Entry<String, Table> e : tables.entrySet()) {
                    Table t = e.getValue();
                    if (t.getmNewInVersion() > dataBaseOldVersion) {
                        db.execSQL(t.deleteTable());
                        db.execSQL(t.createTable());
                    } else {
                        for (Entry<String, Column> entry : t.columns.entrySet()) {
                            Column f = entry.getValue();
                            if (f.getmNewInVersion() > dataBaseOldVersion) {
                                String sql = t.getSQLAddField(f);
                                if (sql != null) {
                                    db.execSQL(sql);
                                }
                            }
                        }
                    }
                }
            }
        }

        private boolean compare(int version, int oldVersion, int newVersion) {
            if (version > oldVersion && version < newVersion) {
                return true;
            } else {
                return false;
            }
        }
    }

    public SQLiteDatabase getmDb() {
        return db;
    }

}
