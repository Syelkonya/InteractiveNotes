package su.ternovskiy.interactivenotes.view.note;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import androidx.appcompat.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Date;
import java.util.List;

import su.ternovskiy.interactivenotes.R;
import su.ternovskiy.interactivenotes.data.Note;
import su.ternovskiy.interactivenotes.data.NoteRepository;
import su.ternovskiy.interactivenotes.data.NotesDao;

public class NoteListActivity extends AppCompatActivity {

    private Toolbar mNoteListToolbar;
    private long mCategoryId;
    private RecyclerView mNoteRecyclerView;
    private NoteViewModel mNoteViewModel;
    private NoteRepository mNoteRepository;
    private OnNoteClickListener mOnNoteClickListener;
    private FloatingActionButton mAddNoteFab;
    private LiveData<List<Note>> mNoteList;
    private String TAG = "NoteListActivity";
    private String mCategoryName;
    private final String mCATEGORYName = "CATEGORY_NAME";
    private final String mCATEGORYId = "CATEGORY_ID";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);

        initViews();
        initAddNoteActivity();
    }


    private void initViews() {
        mNoteListToolbar = findViewById(R.id.note_list_toolbar);
        mNoteRecyclerView = findViewById(R.id.note_list_recycler_view);


        setSupportActionBar(mNoteListToolbar);
        mCategoryName = getIntent().getStringExtra(mCATEGORYName);
        getSupportActionBar().setTitle(mCategoryName);
        setSupportActionBar(mNoteListToolbar);
        mCategoryId = getIntent().getLongExtra(mCATEGORYId, 0);
        Log.d(TAG, mCATEGORYId + mCategoryId);

        mOnNoteClickListener = note -> {

        };


        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        final NoteRecyclerAdapter adapter = new NoteRecyclerAdapter(this);

        adapter.setClickListener(mOnNoteClickListener);

        mNoteRecyclerView.setLayoutManager(layoutManager);


        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getApplicationContext(),
                DividerItemDecoration.VERTICAL);
        mNoteRecyclerView.addItemDecoration(dividerItemDecoration);


        mNoteViewModel = new NoteViewModel(getApplication(), mCategoryId);


        myMethod();


//        if (mNoteList != null)
//            adapter.setNoteList(mNoteList);
        mNoteList.observe(this, adapter::setNoteList);
        mNoteRecyclerView.setAdapter(adapter);


    }

    public void myMethod() {
        if (Looper.myLooper() == Looper.getMainLooper()) {


            mNoteList = mNoteViewModel.getNotesByCategoryId();
        } else {
        }
    }



    private void initAddNoteActivity() {
        mAddNoteFab = findViewById(R.id.note_list_fab);
        mAddNoteFab.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddNoteActivity.class);
            intent.putExtra(mCATEGORYId, mCategoryId);
            intent.putExtra(mCATEGORYName, mCategoryName);
            startActivity(intent);
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.note_list_menu, menu);
        return true;
    }
}
