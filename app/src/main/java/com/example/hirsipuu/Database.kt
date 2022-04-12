package com.example.hirsipuu

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Database internal constructor(context: Context?):
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_WORD_TABLE=("CREATE TABLE "+ TABLE_WORDS + "(" + COLUMN_ID + " TEXT PRIMARY KEY,"+ COLUMN_NUM + " INTEGER " + ")")
        db.execSQL(CREATE_WORD_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_WORDS")
        onCreate(db)
    }

    fun load():String{
        var result=""
        val query="Select*FROM $TABLE_WORDS"
        val db = this.writableDatabase
        val cursor = db.rawQuery(query,null)
        while (cursor.moveToNext()){
            val result_0= cursor.getString(0)
            val result_1= cursor.getInt(1)
            result+= result_0+" "+result_1.toString()+System.getProperty("line.separator")
        }
        cursor.close()
        db.close()
        if(result=="") result="empty"
        return result
    }

    fun add(word: String):Long{
        val confirm : Long
        val values = ContentValues()
        values.put(COLUMN_ID, word)
        values.put(COLUMN_NUM, 0)
        val db=this.writableDatabase
        confirm = db.insert(TABLE_WORDS, null, values)
        db.close()
        return confirm
    }

    companion object{
        private const val DATABASE_VERSION=1
        private const val DATABASE_NAME="wordDB.db"
        private const val TABLE_WORDS="words"
        private const val COLUMN_ID="WordID"
        private const val COLUMN_NUM="WordCount"
    }
}
