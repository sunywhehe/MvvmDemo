package com.jidouauto.mvvm.data.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import com.jidouauto.mvvm.constants.DbConstants;
import com.jidouauto.mvvm.data.entity.SearchKey;
import com.jidouauto.mvvm.util.AppUtils;

@Database(entities = {SearchKey.class}, version = 1, exportSchema = false)
@TypeConverters(DbValueConverter.class)
public abstract class SearchDatabase extends RoomDatabase {
    private static SearchDatabase instance;

    public static synchronized SearchDatabase getInstance() {
        if (instance == null) {
            instance = Room
                    .databaseBuilder(AppUtils.getAppContext().getApplicationContext(), SearchDatabase.class, DbConstants.SEARCH_DB_NAME)
                    .build();
        }
        return instance;
    }

    public abstract SearchDao searchDao();
}
