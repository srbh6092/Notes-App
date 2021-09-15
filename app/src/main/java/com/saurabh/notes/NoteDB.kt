package com.saurabh.notes

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities = arrayOf(Note::class), version=1, exportSchema=false)
abstract class NoteDB : RoomDatabase(){

    abstract fun getNoteDAO(): NoteDAO

    //To implement singleton in order to avoid multiple instance
    companion object{

        @Volatile
        private  var INSTANCE: NoteDB?=null //initialize instance

        @InternalCoroutinesApi
        fun getDB(context: Context):NoteDB{


            return  INSTANCE //if instance is not null then return it
                ?: synchronized(this) {//if instance is null
                val instance = Room.databaseBuilder(
                    context.applicationContext,//context
                    NoteDB::class.java,//type
                    "notes_DB"//name
                ).build()
                INSTANCE =instance

                //return instance
                instance
            }
        }
    }
}