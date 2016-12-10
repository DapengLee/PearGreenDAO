package com.yaphetzhao.peargreendao.dbutil;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.yaphetzhao.peargreendao.application.BaseApplication;
import com.yaphetzhao.peargreendao.bean.PearNote;
import com.yaphetzhao.peargreendao.dao.PearNoteDao;

import java.util.Date;
import java.util.List;

import de.greenrobot.dao.query.Query;
import de.greenrobot.dao.query.QueryBuilder;

/**
 * Created by YaphetZhao
 * on 2016/12/10.
 */

@SuppressWarnings("ALL")
public class PearDBUtil {

    private static PearDBUtil pearDBUtil;

    private Cursor cursor;

    public static PearDBUtil getInstance() {
        if (null == pearDBUtil) {
            pearDBUtil = new PearDBUtil();
        }
        return pearDBUtil;
    }

    public void insert(String key, String value) {
        PearNote note = new PearNote(null, key, value, new Date());
        getPearNoteDao().insert(note);
        getCursor().requery();
    }

    public void insert(PearNote pearNote) {
        getPearNoteDao().insert(pearNote);
        getCursor().requery();
    }

    public void delete(long id) {
        getPearNoteDao().deleteByKey(id);
        getCursor().requery();
    }

    public void delete(PearNote pearNote) {
        getPearNoteDao().delete(pearNote);
        getCursor().requery();
    }

    public void delete(Long... longs) {
        getPearNoteDao().deleteByKeyInTx(longs);
        getCursor().requery();
    }

    public void deleteAll() {
        getPearNoteDao().deleteAll();
        getCursor().requery();
    }

    public void update(String key, String value) {
        PearNote note = new PearNote(null, key, value, new Date());
        getPearNoteDao().insert(note);
        getCursor().requery();
    }

    public void update(PearNote pearNote) {
        getPearNoteDao().insert(pearNote);
        getCursor().requery();
    }

    public List search(long id) {
        Query query = getPearNoteDao().queryBuilder()
                .where(PearNoteDao.Properties.Id.eq(id))
                .orderAsc(PearNoteDao.Properties.PearNoteDAO)
                .build();
        List notes = query.list();
        QueryBuilder.LOG_SQL = true;
        QueryBuilder.LOG_VALUES = true;
        return notes;
    }

    public List search(String key) {
        Query query = getPearNoteDao().queryBuilder()
                .where(PearNoteDao.Properties.Key.eq(key))
                .orderAsc(PearNoteDao.Properties.PearNoteDAO)
                .build();
        List notes = query.list();
        QueryBuilder.LOG_SQL = true;
        QueryBuilder.LOG_VALUES = true;
        return notes;
    }

    public List search(StringBuffer value) {
        Query query = getPearNoteDao().queryBuilder()
                .where(PearNoteDao.Properties.Value.eq(String.valueOf(value)))
                .orderAsc(PearNoteDao.Properties.PearNoteDAO)
                .build();
        List notes = query.list();
        QueryBuilder.LOG_SQL = true;
        QueryBuilder.LOG_VALUES = true;
        return notes;
    }

    public List search(PearNoteDao pearNoteDao) {
        Query query = getPearNoteDao().queryBuilder()
                .where(PearNoteDao.Properties.PearNoteDAO.eq(pearNoteDao))
                .orderAsc(PearNoteDao.Properties.PearNoteDAO)
                .build();
        List notes = query.list();
        QueryBuilder.LOG_SQL = true;
        QueryBuilder.LOG_VALUES = true;
        return notes;
    }

    private Cursor getCursor() {
        if (null == cursor) {
            String textColumn = PearNoteDao.Properties.Key.columnName;
            String orderBy = textColumn + " COLLATE LOCALIZED ASC";
            cursor = getSqlDb().query(getPearNoteDao().getTablename(), getPearNoteDao().getAllColumns(), null, null, null, null, orderBy);
        }
        return cursor;
    }

    private PearNoteDao getPearNoteDao() {
        return BaseApplication.getInstance().getDaoSession().getPearNoteDao();
    }

    private SQLiteDatabase getSqlDb() {
        return BaseApplication.getInstance().getSqlDb();
    }

}
