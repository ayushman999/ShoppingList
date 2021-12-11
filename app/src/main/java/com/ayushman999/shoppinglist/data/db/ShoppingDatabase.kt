package com.ayushman999.shoppinglist.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ayushman999.shoppinglist.data.db.entities.ShoppingItem

@Database(
    entities = [ShoppingItem::class],
    version = 1
)
abstract class ShoppingDatabase: RoomDatabase() {

    abstract fun getShoppingDao(): ShoppingDao

    //companion is used to perform singleton pattern
    companion object{
        @Volatile           //Writes to this instance will be visible to other threads(synchronous method of java)
        private var instance: ShoppingDatabase?=null
        private val LOCK=Any()
        /*Whenever we write ShoppingDatabase invoke get's activated
        and if the istance is null then it invokes instance else it locks the thread
         */
        operator fun invoke(context: Context)= instance ?: synchronized(LOCK){
            instance ?: createDatabase(context).also { instance =it }
        }

        private fun createDatabase(context: Context)=
            Room.databaseBuilder(context.applicationContext,
            ShoppingDatabase::class.java,"ShoppingDB.db").build()
    }
}