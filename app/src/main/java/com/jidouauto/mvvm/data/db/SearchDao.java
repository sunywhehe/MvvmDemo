package com.jidouauto.mvvm.data.db;

import android.arch.persistence.room.*;
import com.jidouauto.mvvm.data.entity.SearchKey;
import io.reactivex.Flowable;

import java.util.List;

/**
 * @author leosun
 */
@Dao
public interface SearchDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long add(SearchKey searchKey);

    @Update
    void update(SearchKey searchKey);

    @Query("UPDATE tb_search SET sync_pending = :sysPending WHERE  photo_id= :photoId")
    void updateSysPending(long photoId, boolean sysPending);

    @Delete
    void delete(SearchKey searchKey);

    @Query("SELECT * FROM tb_search WHERE photo_id = :photoId ORDER BY timestamp DESC")
    Flowable<List<SearchKey>> getComments(long photoId);
}
