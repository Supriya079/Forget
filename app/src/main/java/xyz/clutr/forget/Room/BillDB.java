package xyz.clutr.forget.Room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

/*

*/

@Database(entities = BillEntity.class,version = 1,exportSchema = false)
@TypeConverters(DataConverter.class)
public abstract class BillDB extends RoomDatabase {

    private static BillDB billDB = null;

    //access DAO methods
    public abstract BillDAO billDAO();

    public static synchronized BillDB getBillDBInstance(Context context){
        if (billDB == null){
            billDB = Room.databaseBuilder(
                    context.getApplicationContext(),
                    BillDB.class,
                    "Bills")
                    .allowMainThreadQueries()//allow DAO queries
                    .build();
        }
        return billDB;
    }

}
