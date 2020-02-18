package com.phonefangdajing.word.utils;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import com.phonefangdajing.word.db.HuangLi;
import com.phonefangdajing.word.db.HuangLiDatabase;

public class DbHelper {

    private static HuangLiDatabase database;
    //获取黄历  传参必须 data 2020-01-01 00:00:00
    public static HuangLi getHuangLi(Context context,String date){
        File file = copyDatabase(context);
        if (database==null) {
            database = openDatabase(context,file);
        }
        return database.huangLiDao().queryHuangLi(date);
    }

    //copy文件到data目录下
    private static File copyDatabase(Context context) {
        File destFile = new File(context.getCacheDir(), "huangli.db");
        if (destFile.exists()) {
            Log.d("Test", "copyDatabase: 已存在");
            return destFile;
        }
        try {
            InputStream inputStream = context.getAssets().open("huangli.db");
            FileUtils.copyInputStreamToFile(inputStream,destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            context=null;
        }
        return destFile;
    }

    //获取数据库操作类
    private static HuangLiDatabase openDatabase(Context context, File databaseFile){
        HuangLiDatabase dataBase = Room.databaseBuilder(
                context, HuangLiDatabase.class, databaseFile.getAbsolutePath())
                .allowMainThreadQueries()
                .addMigrations(new Migration(1,2) {
                    @Override
                    public void migrate(@NonNull SupportSQLiteDatabase database) {
                    }
                })
                .build();
        return dataBase;
    }
}
