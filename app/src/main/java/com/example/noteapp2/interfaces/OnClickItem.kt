package com.example.noteapp2.interfaces

import com.example.noteapp2.data.model.NoteModel

interface OnClickItem {
    fun onLongClick(noteModel: NoteModel)
}