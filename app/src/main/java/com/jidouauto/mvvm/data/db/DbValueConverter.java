package com.jidouauto.mvvm.data.db;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

/**
 * @author leosun
 */
public class DbValueConverter {
    @TypeConverter
    public static Date toDate(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long fromDate(Date date) {
        return date == null ? null : date.getTime();
    }
}
