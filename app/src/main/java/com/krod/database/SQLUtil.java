package com.krod.database;

public final class SQLUtil {


    /**
     * 检查表是否存在的Sql语句
     *
     * @param tableName
     * @return 检查表是否存在的Sql语句
     */
    public static String checkTableExist(String tableName) {
        return "SELECT COUNT(*) FROM sqlite_master where type='table' and name='" + tableName + "'";
    }

    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

}
