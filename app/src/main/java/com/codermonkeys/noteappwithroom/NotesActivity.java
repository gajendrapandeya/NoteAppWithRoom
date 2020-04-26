package com.codermonkeys.noteappwithroom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.codermonkeys.noteappwithroom.models.Notes;

public class NotesActivity extends AppCompatActivity implements View.OnTouchListener, GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    //ui components
    private LineEditText mLineEditText;
    private EditText mEditTitle;
    private TextView mViewTitle;


    //vars
    private boolean mIsNewNote;
    private Notes mInitialNote;
    private GestureDetector mGestureDetector;

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

        setListners();
    }

    private void setListners() {

        mLineEditText.setOnTouchListener(this);
        mGestureDetector = new GestureDetector(this, this);
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

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        return mGestureDetector.onTouchEvent(event);
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }
}
