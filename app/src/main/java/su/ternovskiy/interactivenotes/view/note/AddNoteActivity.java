package su.ternovskiy.interactivenotes.view.note;

import android.os.Bundle;
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
    private final String mCATEGORYName = "CATEGORY_NAME";
    private final String mCATEGORYId = "CATEGORY_ID";


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

        mCategoryId = getIntent().getLongExtra(mCATEGORYId, 0);

        mNoteViewModel = new NoteViewModel(getApplication(), mCategoryId);
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (!String.valueOf(mNameTitleEditText.getText()).equals("")) {
            Note note = new Note();
            note.setCategoryId(mCategoryId);
            note.setTitle(String.valueOf(mNameTitleEditText.getText()));
            note.setText(String.valueOf(mNoteTextEditText.getText()));
            note.setDate(new Date(System.currentTimeMillis()));
            mNoteViewModel.addNote(note);

        }
    }
}
