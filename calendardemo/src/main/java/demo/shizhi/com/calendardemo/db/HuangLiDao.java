package demo.shizhi.com.calendardemo.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;



@Dao
public interface HuangLiDao {
    @Query("SELECT * FROM HuangLi WHERE gregoriandate == :dateTime")
    HuangLi queryHuangLi(String dateTime);

    @Query("SELECT * FROM HuangLi WHERE id == :id")
    HuangLi queryHuangLi(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertHuangLi(List<HuangLi> list);
}
