package su.ternovskiy.interactivenotes.view.note;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.Settings;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.widget.Toolbar;;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Locale;

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
    private FloatingActionButton mRecordVoiceFab;
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

    @SuppressLint({"ClickableViewAccessibility", "ResourceAsColor"})
    private void initViews() {
        mAddNoteToolbar = findViewById(R.id.add_note_toolbar);
        mNameTitleEditText = findViewById(R.id.name_title_edit_text);
        mNoteTextEditText = findViewById(R.id.note_text_edit_text);
        mRecordVoiceFab = findViewById(R.id.add_note_fab);



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


        final SpeechRecognizer mSpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);

        final Intent mSpeechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,
                Locale.getDefault());

        mSpeechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle params) {

            }

            @Override
            public void onBeginningOfSpeech() {

            }

            @Override
            public void onRmsChanged(float rmsdB) {

            }

            @Override
            public void onBufferReceived(byte[] buffer) {

            }

            @Override
            public void onEndOfSpeech() {

            }

            @Override
            public void onError(int error) {

            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onResults(Bundle results) {
                ArrayList<String> matches = results
                        .getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);


                if (matches != null) {
                    String textInEdit = String.valueOf(mNoteTextEditText.getText());
                    mNoteTextEditText.setText(textInEdit + " " +matches.get(0));
                }
            }

            @Override
            public void onPartialResults(Bundle partialResults) {

            }

            @Override
            public void onEvent(int eventType, Bundle params) {

            }
        });

        mRecordVoiceFab.setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    mRecordVoiceFab.setRippleColor(R.color.colorRed);
                    mSpeechRecognizer.startListening(mSpeechRecognizerIntent);
                    break;

                case MotionEvent.ACTION_UP:

                    break;
            }
            return false;
        });
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
//                int length = mNoteViewModel.getNotesByCategoryIdList(mCategoryId).size();
//                Note noteAgain = mNoteViewModel.getNotesByCategoryIdList(mCategoryId).get(length-1);
//                noteAgain.setPosition(noteAgain.getId());
//                mNoteViewModel.updateNote(noteAgain);

            }else{
                mNoteFromNoteListActivity.setTitle(String.valueOf(mNoteFromNoteListActivity.getTitle()));
                mNoteFromNoteListActivity.setText(String.valueOf(mNoteTextEditText.getText()));
                mNoteViewModel.updateNote(mNoteFromNoteListActivity);
            }
        }
    }
}
