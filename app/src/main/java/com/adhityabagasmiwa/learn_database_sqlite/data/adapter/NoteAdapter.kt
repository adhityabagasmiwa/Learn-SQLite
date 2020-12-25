package com.adhityabagasmiwa.learn_database_sqlite.data.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adhityabagasmiwa.learn_database_sqlite.R
import com.adhityabagasmiwa.learn_database_sqlite.data.model.Note
import com.adhityabagasmiwa.learn_database_sqlite.databinding.ItemNoteBinding
import com.adhityabagasmiwa.learn_database_sqlite.ui.NoteAddUpdateActivity
import com.adhityabagasmiwa.learn_database_sqlite.util.CustomOnItemClickListener

class NoteAdapter(private val activity: Activity) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    var listNotes = ArrayList<Note>()
        set(listNotes) {
            if (listNotes.size > 0) {
                this.listNotes.clear()
            }
            this.listNotes.addAll(listNotes)
            notifyDataSetChanged()
        }

    fun addItem(note: Note) {
        this.listNotes.add(note)
        notifyItemInserted(this.listNotes.size - 1)
    }

    fun updateItem(position: Int, note: Note) {
        this.listNotes[position] = note
        notifyItemChanged(position, note)
    }

    fun removeItem(position: Int) {
        this.listNotes.removeAt(position)
        notifyItemRemoved(position)
        notifyItemChanged(position, this.listNotes.size)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(mView)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(listNotes[position])
    }

    override fun getItemCount(): Int = this.listNotes.size

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemNoteBinding.bind(itemView)
        fun bind(note: Note) {
            binding.tvItemTitle.text = note.title
            binding.tvItemDesc.text = note.description
            binding.tvItemDate.text = note.date
            binding.cvItemNote.setOnClickListener(CustomOnItemClickListener(adapterPosition, object : CustomOnItemClickListener.OnItemClickCallback {
                override fun onItemClicked(v: View, position: Int) {
                    val mIntent = Intent(activity, NoteAddUpdateActivity::class.java)
                    mIntent.putExtra(NoteAddUpdateActivity.EXTRA_POSITION, position)
                    mIntent.putExtra(NoteAddUpdateActivity.EXTRA_NOTE, note)
                    activity.startActivityForResult(mIntent, NoteAddUpdateActivity.REQUEST_UPDATE)
                }
            }))
        }
    }
}