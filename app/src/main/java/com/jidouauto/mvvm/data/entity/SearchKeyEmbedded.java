package com.jidouauto.mvvm.data.entity;

import android.arch.persistence.room.ColumnInfo;

/**
 * @author leosun
 * Created by Leosun on 2019/1/15 10:46
 */
public class SearchKeyEmbedded {
    @ColumnInfo(name = "embeddedId")
    public int embeddedId;
}
