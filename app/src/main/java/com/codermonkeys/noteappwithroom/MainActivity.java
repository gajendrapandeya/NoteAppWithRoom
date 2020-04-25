package com.codermonkeys.noteappwithroom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.codermonkeys.noteappwithroom.adapters.NotesRecyclerAdapter;
import com.codermonkeys.noteappwithroom.models.Notes;
import com.codermonkeys.noteappwithroom.util.VerticalSpacingItemDecorator;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NotesRecyclerAdapter.onNoteListner {

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
        mNotesRecyclerAdapter = new NotesRecyclerAdapter(mNotes, this);
        mRecyclerView.setAdapter(mNotesRecyclerAdapter);
    }

    @Override
    public void onNoteClick(int position) {

        Intent intent = new Intent(MainActivity.this, NotesActivity.class);
        intent.putExtra("selected_note", mNotes.get(position));
        startActivity(intent);
    }
}
