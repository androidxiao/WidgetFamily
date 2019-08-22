package com.widget.family.API.net;


import android.text.TextUtils;

/**
 * Created by wei.
 * Date: 2019-04-25 23:56
 * Description:
 */
public class RepositoryFactory {

    public static final String DEFAULT = "DEFAULT";

    /**
     * 可以创建多个 IRepository
     * @param repository
     * @return
     */
    public static IRepository createRepository(String repository) {
        if (TextUtils.equals(repository, DEFAULT)) {
            return Repository.getInstance();
        }
        return null;
    }

    public static IRepository createRepository() {
        return createRepository(DEFAULT);
    }
}
