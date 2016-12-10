package com.yaphetzhao.peargreendao.presenter;

import com.yaphetzhao.peargreendao.bean.PearNote;
import com.yaphetzhao.peargreendao.dao.PearNoteDao;

import java.util.List;

/**
 * Created by YaphetZhao
 * on 2016/12/10.
 */

public interface IPearPresenter {

    void insert(String key, String value);

    void insert(PearNote pearNote);

    void delete(long id);

    void delete(PearNote pearNote);

    void delete(Long... longs);

    void deleteAll();

    void update(PearNote pearNote);

    void update(PearNote... pearNotes);

    void update(Iterable<PearNote> iterable);

    List search(long id);

    List search(String key);

    List search(StringBuffer value);

    List search(PearNoteDao pearNoteDao);

    List searchAll();

}
