package com.example.busticketprojectkotlin.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.busticketprojectkotlin.db.dao.ICityNameDAO
import com.example.busticketprojectkotlin.model.CityModelDto

@Database(entities = [CityModelDto::class], version = 2)
abstract class BusTicketDatabase : RoomDatabase() {
    //Singleton

    abstract fun cityNameDAO() : ICityNameDAO

    companion object{

       @Volatile private var instance :BusTicketDatabase? = null

        private val lock = Any()
        operator fun invoke(context: Context) = instance ?: synchronized(lock){
                instance ?: createDatabase(context).also {
                    instance=it
                }
        }

    private fun createDatabase(context:Context) = Room
        .databaseBuilder(context.applicationContext,BusTicketDatabase::class.java,"bust_ticket_database")
        .fallbackToDestructiveMigration()
        .build()
    }

}