package com.yaphetzhao.peardaogenerator.generator;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

/**
 * Created by YaphetZhao
 * on 2016/12/9.
 */

public class PearDaoGenerator {

    // 数据库版本号
    // The database version number)
    private static final int DAO_VERSION_CODE = 1;

    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(DAO_VERSION_CODE, "com.yaphetzhao.peargreendao.bean");
        schema.setDefaultJavaPackageDao("com.yaphetzhao.peargreendao.dao");
        // schema.enableActiveEntitiesByDefault();
        // schema.enableKeepSectionsByDefault();
        addNote(schema);

        //生成模板的绝对目录，根据自己的情况修改
        //Windows系统："C:"
        new DaoGenerator().generateAll(schema,
                "/Users/yaphetzhao/Projects/AndroidStudioProjects/PearGreenDAO/app/src/main/java-gen");
    }

    private static void addNote(Schema schema) {
        Entity entity = schema.addEntity("PearNote");
        entity.addIdProperty();//主键
        entity.addStringProperty("key").notNull();
        entity.addStringProperty("value");
        entity.addStringProperty("createtime");
        entity.addDateProperty("PearNoteDAO");
    }

}
