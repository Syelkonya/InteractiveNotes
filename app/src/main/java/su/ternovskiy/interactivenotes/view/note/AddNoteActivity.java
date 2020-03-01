package su.ternovskiy.interactivenotes.view.note;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.EditText;

import androidx.appcompat.widget.Toolbar;;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.sql.Date;

import su.ternovskiy.interactivenotes.R;
import su.ternovskiy.interactivenotes.data.Note;

public class AddNoteActivity extends AppCompatActivity {

    private Toolbar mAddNoteToolbar;
    private EditText mNameTitleEditText;
    private EditText mNoteTextEditText;
    private NoteViewModel mNoteViewModel;
    private long mCategoryId;
    private boolean mIsFirstCreated;
    private Note mNoteFromNoteListActivity;
    private final String mCATEGORYName = "CATEGORY_NAME";
    private final String mCATEGORYId = "CATEGORY_ID";
    private final String mIS_FIRST_CREATED = "IS_FIRST_CREATED";
    private final String mNOTE_TO_ADD_ACTIVITY = "NOTE_TO_ADD_ACTIVITY";



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        initViews();

    }

    private void initViews() {
        mAddNoteToolbar = findViewById(R.id.add_note_toolbar);
        mNameTitleEditText = findViewById(R.id.name_title_edit_text);
        mNoteTextEditText = findViewById(R.id.note_text_edit_text);


        setSupportActionBar(mAddNoteToolbar);
        getSupportActionBar().setTitle(getIntent().getStringExtra(mCATEGORYName));

        Intent intent = getIntent();
        mCategoryId = intent.getLongExtra(mCATEGORYId, 0);
        mIsFirstCreated = getIntent().getBooleanExtra(mIS_FIRST_CREATED, false);

        if (!mIsFirstCreated) {
            mNoteFromNoteListActivity = intent.getParcelableExtra(mNOTE_TO_ADD_ACTIVITY);
            mNameTitleEditText.setText(String.valueOf(mNoteFromNoteListActivity.getTitle()));
            mNoteTextEditText.setText(String.valueOf(mNoteFromNoteListActivity.getText()));
        }
        mNoteViewModel = new NoteViewModel(getApplication());
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (!String.valueOf(mNameTitleEditText.getText()).trim().equals("")) {
            if (mIsFirstCreated) {
                Note note = new Note(String.valueOf(mNameTitleEditText.getText()),
                        String.valueOf(mNoteTextEditText.getText()),
                        mCategoryId,
                        new Date (System.currentTimeMillis()));
                mNoteViewModel.addNote(note);
            }else{
                mNoteFromNoteListActivity.setTitle(String.valueOf(mNoteFromNoteListActivity.getTitle()));
                mNoteFromNoteListActivity.setText(String.valueOf(mNoteTextEditText.getText()));
                mNoteViewModel.updateNote(mNoteFromNoteListActivity);
            }
        }
    }
}
