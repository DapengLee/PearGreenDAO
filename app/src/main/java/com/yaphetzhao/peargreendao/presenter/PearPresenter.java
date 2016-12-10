package com.yaphetzhao.peargreendao.presenter;

import com.yaphetzhao.peargreendao.bean.PearNote;
import com.yaphetzhao.peargreendao.dao.PearNoteDao;
import com.yaphetzhao.peargreendao.util.PearDBUtil;

import java.util.List;

/**
 * Created by YaphetZhao
 * on 2016/12/10.
 */

public class PearPresenter implements IPearPresenter {

    @Override
    public void insert(String key, String value) {
        PearDBUtil.getInstance().insert(key, value);
    }

    @Override
    public void insert(PearNote pearNote) {
        PearDBUtil.getInstance().insert(pearNote);
    }

    @Override
    public void delete(long id) {
        PearDBUtil.getInstance().delete(id);
    }

    @Override
    public void delete(PearNote pearNote) {
        PearDBUtil.getInstance().delete(pearNote);
    }

    @Override
    public void delete(Long... longs) {
        PearDBUtil.getInstance().delete(longs);
    }

    @Override
    public void deleteAll() {
        PearDBUtil.getInstance().deleteAll();
    }

    @Override
    public void update(PearNote pearNote) {
        PearDBUtil.getInstance().update(pearNote);
    }

    @Override
    public void update(PearNote... pearNotes) {
        PearDBUtil.getInstance().update(pearNotes);
    }

    @Override
    public void update(Iterable<PearNote> iterable) {
        PearDBUtil.getInstance().update(iterable);
    }

    @Override
    public List search(long id) {
        return PearDBUtil.getInstance().search(id);
    }

    @Override
    public List search(String key) {
        return PearDBUtil.getInstance().search(key);
    }

    @Override
    public List search(StringBuffer value) {
        return PearDBUtil.getInstance().search(value);
    }

    @Override
    public List search(PearNoteDao pearNoteDao) {
        return PearDBUtil.getInstance().search(pearNoteDao);
    }

    @Override
    public List searchAll() {
        return PearDBUtil.getInstance().searchAll();
    }

}
