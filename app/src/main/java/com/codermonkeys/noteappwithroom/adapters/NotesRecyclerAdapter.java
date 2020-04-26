package com.codermonkeys.noteappwithroom.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codermonkeys.noteappwithroom.R;
import com.codermonkeys.noteappwithroom.models.Notes;

import java.util.ArrayList;

public class NotesRecyclerAdapter extends RecyclerView.Adapter<NotesRecyclerAdapter.ViewHolder> {

    private ArrayList<Notes> mNotes;
    private onNoteListner mOnNoteListner;

    public NotesRecyclerAdapter(ArrayList<Notes> mNotes, onNoteListner onNoteListner) {
        this.mNotes = mNotes;
        mOnNoteListner = onNoteListner;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_note_list_item, parent, false);
       return new ViewHolder(view, mOnNoteListner);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.timestamp.setText(mNotes.get(position).getTimeStamp());
        holder.title.setText(mNotes.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return mNotes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title, timestamp;
        onNoteListner onNoteListner;

        public ViewHolder(@NonNull View itemView, onNoteListner onNoteListner) {
            super(itemView);
            title = itemView.findViewById(R.id.note_title);
            timestamp = itemView.findViewById(R.id.note_timestamp);
            this.onNoteListner = onNoteListner;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            onNoteListner.onNoteClick(getAdapterPosition());
        }
    }

    public interface onNoteListner {
        void onNoteClick(int position);
    }
}
