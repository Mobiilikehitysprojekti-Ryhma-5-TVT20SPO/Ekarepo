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

    fun loadWords():ArrayList<String>{
        var resword = arrayListOf<String>()
        val query="Select*FROM $TABLE_WORDS"
        val db = this.writableDatabase
        val cursor = db.rawQuery(query,null)
        while (cursor.moveToNext()){
            resword.add(cursor.getString(0))
        }
        cursor.close()
        db.close()
        return resword
    }

    fun loadNum():ArrayList<Int>{
        var resnum = arrayListOf<Int>()
        val query="Select*FROM $TABLE_WORDS"
        val db = this.writableDatabase
        val cursor = db.rawQuery(query,null)
        while (cursor.moveToNext()){
            resnum.add(cursor.getInt(1))
        }
        cursor.close()
        db.close()
        return resnum
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

    fun updateTry(word:String,num:Int){
        val db=this.writableDatabase
        val args = ContentValues()
        args.put(COLUMN_NUM, num)
        db.update(TABLE_WORDS, args, "$COLUMN_ID='$word'",null)>0
    }

    companion object{
        private const val DATABASE_VERSION=1
        private const val DATABASE_NAME="wordDB.db"
        private const val TABLE_WORDS="words"
        private const val COLUMN_ID="WordID"
        private const val COLUMN_NUM="WordCount"
    }
}