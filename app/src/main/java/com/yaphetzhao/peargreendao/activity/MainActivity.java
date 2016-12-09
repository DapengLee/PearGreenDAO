package com.yaphetzhao.peargreendao.activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.yaphetzhao.peargreendao.R;
import com.yaphetzhao.peargreendao.application.BaseApplication;
import com.yaphetzhao.peargreendao.bean.PearNote;
import com.yaphetzhao.peargreendao.dao.PearNoteDao;

import java.util.Date;
import java.util.List;

import de.greenrobot.dao.query.Query;
import de.greenrobot.dao.query.QueryBuilder;

import static com.yaphetzhao.peargreendao.config.Config.TAG;

public class MainActivity extends AppCompatActivity {

    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // 增
    // Insert
    private void insertDB() {
        if (null == cursor) {
            createCursor();
        }

        String noteKey = String.valueOf("key" + System.currentTimeMillis());
        String noteValue = String.valueOf("Value" + System.currentTimeMillis());
        String noteCreateTime = String.valueOf("CreateTIme" + System.currentTimeMillis());

        PearNote note = new PearNote(null, noteKey, noteValue, noteCreateTime, new Date());
        getPearNoteDao().insert(note);
        Log.d(TAG, "Inserted new note, ID: " + note.getId());
        getCursor().requery();

    }

    // 删
    // delete
    private void deleteDB(long id) {
        getPearNoteDao().deleteAll();
        getPearNoteDao().deleteByKey(id);
        getPearNoteDao().delete(new PearNote());
        getPearNoteDao().deleteByKeyInTx((long) 1, (long) 2);
        getCursor().requery();
    }

    // 改:同增
    // update:like insert

    // 查
    // search
    private void search() {
        String noteKey = String.valueOf("key" + System.currentTimeMillis());

        Query query = getPearNoteDao().queryBuilder()
                .where(PearNoteDao.Properties.Key.eq(noteKey))
                .orderAsc(PearNoteDao.Properties.Createtime)
                .build();
        List notes = query.list();
        Log.i("YaphetZhao-notes.size:", String.valueOf(notes.size()));

        QueryBuilder.LOG_SQL = true;
        QueryBuilder.LOG_VALUES = true;
    }

    private Cursor getCursor() {
        if (null == cursor) {
            createCursor();
        }
        return cursor;
    }

    private void createCursor() {
        String textColumn = PearNoteDao.Properties.Key.columnName;
        String orderBy = textColumn + " COLLATE LOCALIZED ASC";
        cursor = getSqlDb().query(getPearNoteDao().getTablename(), getPearNoteDao().getAllColumns(), null, null, null, null, orderBy);
    }

    private PearNoteDao getPearNoteDao() {
        return ((BaseApplication) this.getApplicationContext()).getDaoSession().getPearNoteDao();
    }

    private SQLiteDatabase getSqlDb() {
        return ((BaseApplication) this.getApplicationContext()).getSqlDb();
    }

}
