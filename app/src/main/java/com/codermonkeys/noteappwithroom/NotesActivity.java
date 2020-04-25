package com.codermonkeys.noteappwithroom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.codermonkeys.noteappwithroom.models.Notes;

public class NotesActivity extends AppCompatActivity {

    //ui components
    private LineEditText mLineEditText;
    private EditText mEditTitle;
    private TextView mViewTitle;

    //vars
    private boolean mIsNewNote;
    private Notes mInitialNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        mLineEditText = findViewById(R.id.note_text);
        mEditTitle = findViewById(R.id.note_edit_title);
        mViewTitle = findViewById(R.id.note_text_title);


        if(getIncomingIntent()) {

            //this is a new note, (Edit Note)
            setNewNoteProperties();
        } else {

            //this is not a new note, (View Note)
            setNoteProperties();
        }

    }

    private boolean getIncomingIntent() {
        if(getIntent().hasExtra("selected_note")) {

            mInitialNote = getIntent().getParcelableExtra("selected_note");

            mIsNewNote = false;
            return false;
        }
       mIsNewNote = true;
        return true;
    }

    private  void setNewNoteProperties() {
        mViewTitle.setText("New Note");
        mEditTitle.setError("New Note");
    }

    private void setNoteProperties() {
        mViewTitle.setText(mInitialNote.getTitle());
        mEditTitle.setText(mInitialNote.getTitle());
        mLineEditText.setText(mInitialNote.getContent());
    }
}
