package com.example.rickandmorty.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.rickandmorty.network.responses.CharacterDetailResponse

@Dao
interface RmDao {
    @Query("select * from databasecharacterdetail")
    fun getCharacters(): LiveData<List<DatabaseCharacterDetail>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg characters: DatabaseCharacterDetail)
}

@Database(entities = [DatabaseCharacterDetail::class], version = 1)
abstract class RmDatabase : RoomDatabase(){
    abstract val rmDao: RmDao
}

private lateinit var INSTANCE: RmDatabase

fun getDatabase(context: Context) : RmDatabase {
    if (!::INSTANCE.isInitialized){
        INSTANCE = Room.databaseBuilder(context.applicationContext,
            RmDatabase::class.java, "RickAndMortyDb").build()
    }

    return INSTANCE
}