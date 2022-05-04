package com.example.busticketprojectkotlin.di

import android.content.Context
import com.example.busticketprojectkotlin.db.BusTicketDatabase
import com.example.busticketprojectkotlin.db.dao.ICityNameDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context) : BusTicketDatabase {
        return BusTicketDatabase.invoke(context)
    }

    @Singleton
    @Provides
    fun  getDao(appDB: BusTicketDatabase): ICityNameDAO {
        return appDB.cityNameDAO()
    }
}