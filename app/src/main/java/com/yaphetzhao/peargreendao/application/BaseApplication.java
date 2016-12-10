package com.yaphetzhao.peargreendao.application;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.yaphetzhao.peargreendao.dao.DaoMaster;
import com.yaphetzhao.peargreendao.dao.DaoSession;

import static com.yaphetzhao.peargreendao.config.Config.DB_NAME;


/**
 * Created by YaphetZhao
 * on 2016/12/10.
 */
@SuppressWarnings("FieldCanBeLocal")
public class BaseApplication extends Application {

    private static BaseApplication instance;

    private DaoMaster.DevOpenHelper dbHelper;
    private SQLiteDatabase sqlDB;
    private DaoMaster daoMaster;
    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        createDataBase();
    }

    public static BaseApplication getInstance() {
        return instance;
    }

    private void createDataBase() {
        dbHelper = new DaoMaster.DevOpenHelper(this, DB_NAME, null);
        sqlDB = dbHelper.getWritableDatabase();
        daoMaster = new DaoMaster(sqlDB);
        daoSession = daoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    public SQLiteDatabase getSqlDb() {
        return sqlDB;
    }

}
