package com.jidouauto.mvvm.data.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import com.jidouauto.mvvm.constants.DbConstants;
import com.jidouauto.mvvm.data.entity.SearchKey;
import com.jidouauto.mvvm.util.AppUtils;

/**
 * @author leosun
 */
@Database(entities = {SearchKey.class}, version = 1, exportSchema = false)
@TypeConverters(DbValueConverter.class)
public abstract class BaseSearchDatabase extends RoomDatabase {
    private static BaseSearchDatabase instance;

    public static synchronized BaseSearchDatabase getInstance() {
        if (instance == null) {
            instance = Room
                    .databaseBuilder(AppUtils.getAppContext().getApplicationContext(), BaseSearchDatabase.class, DbConstants.SEARCH_DB_NAME)
                    .build();
        }
        return instance;
    }

    public abstract SearchDao searchDao();
}
