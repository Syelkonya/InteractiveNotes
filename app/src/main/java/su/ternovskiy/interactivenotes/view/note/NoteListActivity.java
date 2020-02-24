package su.ternovskiy.interactivenotes.view.note;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;

import androidx.appcompat.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Date;

import su.ternovskiy.interactivenotes.R;
import su.ternovskiy.interactivenotes.data.Note;

public class NoteListActivity extends AppCompatActivity {

    private Toolbar mNoteListToolbar;
    private long mCategoryId;
    private RecyclerView mNoteRecyclerView;
    private NoteViewModel mNoteViewModel;
    private OnNoteClickListener mOnNoteClickListener;
    private String TAG = "NoteListActivity";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);

        initViews();

    }

    private void initViews() {
        mNoteListToolbar = findViewById(R.id.note_list_toolbar);
        mNoteRecyclerView = findViewById(R.id.note_list_recycler_view);



        setSupportActionBar(mNoteListToolbar);
        getSupportActionBar().setTitle(getIntent().getStringExtra("CATEGORY_NAME"));
        setSupportActionBar(mNoteListToolbar);
        mCategoryId = getIntent().getLongExtra("CATEGORY_ID",0);
        Log.d(TAG, "CATEGORY_ID " + mCategoryId);

//        mOnNoteClickListener = note -> {
//
//        };
//
//
//        LinearLayoutManager layoutManager =
//                new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
//        final NoteRecyclerAdapter adapter = new NoteRecyclerAdapter(this);
//
//        adapter.setClickListener(mOnNoteClickListener);
//
//        mNoteRecyclerView.setLayoutManager(layoutManager);
//
//
//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getApplicationContext(),
//                DividerItemDecoration.VERTICAL);
//        mNoteRecyclerView.addItemDecoration(dividerItemDecoration);
//
//        mNoteViewModel = new NoteViewModel(getApplication(), mCategoryId);
//
//        mNoteViewModel.getNotesByCategoryId().observe(this, adapter::setNoteList);
//        mNoteRecyclerView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.note_list_menu,menu);
        return true;
    }
}
