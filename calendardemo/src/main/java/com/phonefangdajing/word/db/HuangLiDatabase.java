package com.phonefangdajing.word.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = HuangLi.class, version = 1)
public abstract class HuangLiDatabase extends RoomDatabase {
    public abstract HuangLiDao huangLiDao();
}
