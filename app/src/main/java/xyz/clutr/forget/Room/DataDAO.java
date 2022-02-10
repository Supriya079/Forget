package xyz.clutr.forget.Room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DataDAO {

    //Bill DAO Methods
    @Query("SELECT * FROM BILLENTITY")
    List<BillEntity> getAllBills();

    @Insert
    void insertBill(BillEntity billEntity);

    @Update
    void updateBill(BillEntity billEntity);

    @Delete
    void deleteBill(BillEntity billEntity);

    //Documents DAO Methods
    @Query("SELECT * FROM DOCUMENTENTITY")
    List<DocumentEntity> getAllDocuments();

    @Insert
    void insertDocument(DocumentEntity documentEntity);

    @Update
    void updateDocument(DocumentEntity documentEntity);

    @Delete
    void deleteDocument(DocumentEntity documentEntity);

}
