package com.com.example.testsql;

import com.krod.database.Column;
import com.krod.database.Column.Type;
import com.krod.database.Table;

import java.io.Serializable;


public class GroupMode implements Serializable {

    public int _id;
    /**
     * 群ID
     */
    public int groupId = 0;
    /**
     * 群名称
     */
    public String groupName = "222";
    /**
     * 头像
     */
    public String avatar = "3333";
    /**
     * 创建者
     */
    public String creater = "4444";
    /**
     * 创建时间
     */
    public String createTime = "555";

    public String test = "666";
    /**
     * 群组类型 0:个人群组    1:公共群组
     */
    public int groupType = 0;

    /**
     * 是否被删除：0否，1是
     */
    public int isDel = 0;

    /**
     * 是否被修改：0否，1是
     */
    public int updateState = 0;

    public GroupMode() {
    }

    public GroupMode(int i) {
        groupId += i;
    }

    public static Table createTable() {
        Table table = new Table(GroupMode.class, new Column("_id").setAutoIncrement(true).setType(Type.Integer));
        table.columns.get("groupType").setType(Type.Integer);
        table.columns.get("isDel").setType(Type.Integer);
        table.columns.get("updateState").setType(Type.Integer);
        table.columns.remove("member");
        table.columns.get("test").setmNewInVersion(HermesSqlHelper.SqlVersion - 1);
        return table;
    }

    public enum GroupType {
        personal,
        common,
    }

    public enum DelStatu {
        no,
        yes
    }

    public enum UpdateState {
        no,
        yes
    }
}
