package com.yaphetzhao.peardaoutil.util;

import android.database.Cursor;

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

}
