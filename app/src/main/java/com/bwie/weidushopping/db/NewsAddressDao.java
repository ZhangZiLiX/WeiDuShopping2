package com.bwie.weidushopping.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.bwie.weidushopping.homepage.fragmentwode.allsubclasses.btnwodedizhi.bean.NewsAddress;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "NEWS_ADDRESS".
*/
public class NewsAddressDao extends AbstractDao<NewsAddress, Long> {

    public static final String TABLENAME = "NEWS_ADDRESS";

    /**
     * Properties of entity NewsAddress.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, long.class, "id", true, "_id");
        public final static Property UserName = new Property(1, String.class, "userName", false, "USER_NAME");
        public final static Property UserPhone = new Property(2, String.class, "userPhone", false, "USER_PHONE");
        public final static Property UserDiQu = new Property(3, String.class, "userDiQu", false, "USER_DI_QU");
        public final static Property UserXiangXiAddress = new Property(4, String.class, "userXiangXiAddress", false, "USER_XIANG_XI_ADDRESS");
        public final static Property UserBianMa = new Property(5, String.class, "userBianMa", false, "USER_BIAN_MA");
    }


    public NewsAddressDao(DaoConfig config) {
        super(config);
    }
    
    public NewsAddressDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"NEWS_ADDRESS\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ," + // 0: id
                "\"USER_NAME\" TEXT," + // 1: userName
                "\"USER_PHONE\" TEXT," + // 2: userPhone
                "\"USER_DI_QU\" TEXT," + // 3: userDiQu
                "\"USER_XIANG_XI_ADDRESS\" TEXT," + // 4: userXiangXiAddress
                "\"USER_BIAN_MA\" TEXT);"); // 5: userBianMa
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"NEWS_ADDRESS\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, NewsAddress entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getId());
 
        String userName = entity.getUserName();
        if (userName != null) {
            stmt.bindString(2, userName);
        }
 
        String userPhone = entity.getUserPhone();
        if (userPhone != null) {
            stmt.bindString(3, userPhone);
        }
 
        String userDiQu = entity.getUserDiQu();
        if (userDiQu != null) {
            stmt.bindString(4, userDiQu);
        }
 
        String userXiangXiAddress = entity.getUserXiangXiAddress();
        if (userXiangXiAddress != null) {
            stmt.bindString(5, userXiangXiAddress);
        }
 
        String userBianMa = entity.getUserBianMa();
        if (userBianMa != null) {
            stmt.bindString(6, userBianMa);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, NewsAddress entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getId());
 
        String userName = entity.getUserName();
        if (userName != null) {
            stmt.bindString(2, userName);
        }
 
        String userPhone = entity.getUserPhone();
        if (userPhone != null) {
            stmt.bindString(3, userPhone);
        }
 
        String userDiQu = entity.getUserDiQu();
        if (userDiQu != null) {
            stmt.bindString(4, userDiQu);
        }
 
        String userXiangXiAddress = entity.getUserXiangXiAddress();
        if (userXiangXiAddress != null) {
            stmt.bindString(5, userXiangXiAddress);
        }
 
        String userBianMa = entity.getUserBianMa();
        if (userBianMa != null) {
            stmt.bindString(6, userBianMa);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.getLong(offset + 0);
    }    

    @Override
    public NewsAddress readEntity(Cursor cursor, int offset) {
        NewsAddress entity = new NewsAddress( //
            cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // userName
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // userPhone
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // userDiQu
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // userXiangXiAddress
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5) // userBianMa
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, NewsAddress entity, int offset) {
        entity.setId(cursor.getLong(offset + 0));
        entity.setUserName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setUserPhone(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setUserDiQu(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setUserXiangXiAddress(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setUserBianMa(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(NewsAddress entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(NewsAddress entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(NewsAddress entity) {
        throw new UnsupportedOperationException("Unsupported for entities with a non-null key");
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
