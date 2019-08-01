package com.jidouauto.mvvm.data.entity;

/**
 * @author leosun
 */
public class SearchKeyUtils {
    public static SearchKey clone(SearchKey from, boolean syncPending) {
        return new SearchKey(from.getId(), from.getPhotoId(), from.getCommentText(),
                from.getTimestamp(), syncPending);
    }

    public static SearchKey clone(SearchKey from, long id) {
        return new SearchKey(id, from.getPhotoId(), from.getCommentText(),
                from.getTimestamp(), from.isSyncPending());
    }
}
