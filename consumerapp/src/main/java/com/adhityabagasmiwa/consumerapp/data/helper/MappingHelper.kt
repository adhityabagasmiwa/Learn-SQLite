package com.adhityabagasmiwa.consumerapp.data.helper

import android.database.Cursor
import com.adhityabagasmiwa.consumerapp.data.db.DatabaseContract
import com.adhityabagasmiwa.consumerapp.data.model.Note

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

    fun mapCursorToObject(noteCursor: Cursor?): Note {
        var note = Note()
        noteCursor?.apply {
            moveToFirst()
            val id = getInt(getColumnIndexOrThrow(DatabaseContract.NoteColumns._ID))
            val title = getString(getColumnIndexOrThrow(DatabaseContract.NoteColumns.TITLE))
            val desc = getString(getColumnIndexOrThrow(DatabaseContract.NoteColumns.DESCRIPTION))
            val date = getString(getColumnIndexOrThrow(DatabaseContract.NoteColumns.DATE))
            note = Note(id, title, desc, date)
        }
        return note
    }
}