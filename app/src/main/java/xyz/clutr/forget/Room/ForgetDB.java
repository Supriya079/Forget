package xyz.clutr.forget.Room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {BillEntity.class,DocumentEntity.class},version = 1,exportSchema = false)
@TypeConverters(DataConverter.class)
public abstract class ForgetDB extends RoomDatabase {

    private static ForgetDB forgetDB = null;

    //access DAO methods
    public abstract DataDAO dataDAO();

    public static synchronized ForgetDB getBillDBInstance(Context context){
        if (forgetDB == null){
            forgetDB = Room.databaseBuilder(
                    context.getApplicationContext(),
                    ForgetDB.class,
                    "Forget")//database name
                    .allowMainThreadQueries()//allow DAO queries
                    .build();
        }
        return forgetDB;
    }

}
