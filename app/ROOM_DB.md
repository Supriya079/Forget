ROOM DataBase:
Room Database is a part of the Android Architecture components which provides an abstraction layer over SQLite 
    which allows for more robust database access while still providing the full power of SQLite.
There Are Basically 3 Major Components In Room.
1. @Entity
   : Entity Representation of table and columns become very easy. you have to annotate “@Entity” to a class and 
   the name of the class becomes table name and, data members become the name of the columns. 
   “@Entity” class represents an entity in a table.

2. @Dao — Data Access Object
   An Interface where we put all our SQL queries. We don’t require to write whole queries now; 
   we need to make a method and annotate with specific annotations like
   @Insert — Used to insert record into Room database.
   @Delete — Used to delete record from Room database.
   @Update — Used to update record in Room Database.
   @Query — Used to enter the Query like (SELECT * FROM TABLE_NAME)”

3. @Database
   This is an abstract class that extends RoomDatabase, this is where you define the entities (tables)and 
   the version number of your database. It contains the database holder and serves as the main access point for the 
   underlying connection.

References Used:
Official Website room db -
https://developer.android.com/jetpack/androidx/releases/room#declaring_dependencies
YouTube websites referred - 
https://www.youtube.com/playlist?list=PLdHg5T0SNpN3CMNtsd5KGaiBtzhTGIwtC
https://www.youtube.com/playlist?list=PL85VhPtoVCZJglHtOY25mBovae9zw9diF
