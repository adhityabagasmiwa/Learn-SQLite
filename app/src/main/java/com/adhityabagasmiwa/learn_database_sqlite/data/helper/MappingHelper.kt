package com.adhityabagasmiwa.learn_database_sqlite.data.helper

import android.database.Cursor
import com.adhityabagasmiwa.learn_database_sqlite.data.db.DatabaseContract
import com.adhityabagasmiwa.learn_database_sqlite.data.model.Note

object MappingHelper {

    fun mapCursorToArrayList(noteCursor: Cursor?): ArrayList<Note> {
        val noteList = ArrayList<Note>()

        noteCursor?.apply {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow(DatabaseContract.NoteColumns._ID))
                val title = getString(getColumnIndexOrThrow(DatabaseContract.NoteColumns.TITLE))
                val desc = getString(getColumnIndexOrThrow(DatabaseContract.NoteColumns.DESCRIPTION))
                val date = getString(getColumnIndexOrThrow(DatabaseContract.NoteColumns.DATE))
                noteList.add(Note(id, title, desc, date))
            }
        }
        return noteList
    }

}