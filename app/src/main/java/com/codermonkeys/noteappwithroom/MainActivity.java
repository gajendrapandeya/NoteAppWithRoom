package com.codermonkeys.noteappwithroom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.codermonkeys.noteappwithroom.adapters.NotesRecyclerAdapter;
import com.codermonkeys.noteappwithroom.models.Notes;
import com.codermonkeys.noteappwithroom.util.VerticalSpacingItemDecorator;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NotesRecyclerAdapter.onNoteListner, View.OnClickListener {

    //Ui components
    private RecyclerView mRecyclerView;
    private Toolbar mToolbar;

    //vars
    private ArrayList<Notes> mNotes = new ArrayList<>();
    private NotesRecyclerAdapter mNotesRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recycler_view);
        mToolbar = findViewById(R.id.notes_toolbar);
        findViewById(R.id.fab).setOnClickListener(this);

        setSupportActionBar(mToolbar);
        setTitle("Notes");

        initRecyclerView();
        insertFakeNotes();
    }

    private void insertFakeNotes() {

        for(int i = 0; i < 100; i++) {
            Notes notes = new Notes();
            notes.setTitle("Title is good one" + i);
            notes.setContent("Content #" + i);
            notes.setTimeStamp("Apr 24");
            mNotes.add(notes);
        }
        mNotesRecyclerAdapter.notifyDataSetChanged();
    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        VerticalSpacingItemDecorator itemDecorator = new VerticalSpacingItemDecorator(10);
        mRecyclerView.addItemDecoration(itemDecorator);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(mRecyclerView);
        mNotesRecyclerAdapter = new NotesRecyclerAdapter(mNotes, this);
        mRecyclerView.setAdapter(mNotesRecyclerAdapter);
    }

    @Override
    public void onNoteClick(int position) {

        Intent intent = new Intent(MainActivity.this, NotesActivity.class);
        intent.putExtra("selected_note", mNotes.get(position));
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, NotesActivity.class);
        startActivity(intent);
    }

    private void deleteNote(Notes notes) {

        mNotes.remove(notes);
        mNotesRecyclerAdapter.notifyDataSetChanged();
    }

    private ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

            deleteNote(mNotes.get(viewHolder.getAdapterPosition()));
        }
    };
}
