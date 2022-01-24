package xyz.clutr.forget.Room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface BillDAO {

    @Query("SELECT * FROM BILLENTITY")
    List<BillEntity> getAllBills();

    @Insert
    void insertBill(BillEntity billEntity);

    @Update
    void updateBill(BillEntity billEntity);

    @Delete
    void deleteBill(BillEntity billEntity);

}
