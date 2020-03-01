package su.ternovskiy.interactivenotes.view.note;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcel;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import su.ternovskiy.interactivenotes.R;
import su.ternovskiy.interactivenotes.data.Note;


public class NoteListActivity extends AppCompatActivity {

    private Toolbar mNoteListToolbar;
    private long mCategoryId;
    private RecyclerView mNoteRecyclerView;
    private NoteViewModel mNoteViewModel;
    private OnNoteClickListener mOnNoteClickListener;
    private FloatingActionButton mAddNoteFab;
    private LiveData<List<Note>> mNoteListLiveData;
    private List<Note> mNoteList;
    private TextView mNoNotesTextView;
    private CoordinatorLayout mCoordinatorLayoutNoteListActivity;
    private NoteRecyclerAdapter mNoteRecyclerAdapter;
    private String TAG = "NoteListActivity";
    private String mCategoryName;
    private final String mCATEGORYName = "CATEGORY_NAME";
    private final String mCATEGORYId = "CATEGORY_ID";
    private final String mIS_FIRST_CREATED = "IS_FIRST_CREATED";
    private final String mNOTE_TO_ADD_ACTIVITY = "NOTE_TO_ADD_ACTIVITY";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);
        mCoordinatorLayoutNoteListActivity = findViewById(R.id.coordinator_activity_note_list);

        initViews();
        initAddNoteActivity();
        enableSwipe();
    }


    private void initViews() {
        mNoteListToolbar = findViewById(R.id.note_list_toolbar);
        mNoteRecyclerView = findViewById(R.id.note_list_recycler_view);
        mNoNotesTextView = findViewById(R.id.note_list_text_view);


        setSupportActionBar(mNoteListToolbar);
        mCategoryName = getIntent().getStringExtra(mCATEGORYName);
        getSupportActionBar().setTitle(mCategoryName);
        setSupportActionBar(mNoteListToolbar);
        mCategoryId = getIntent().getLongExtra(mCATEGORYId, 0);
        Log.d(TAG, mCATEGORYId + mCategoryId);

        mOnNoteClickListener = note -> {
            Intent intent = new Intent(this, AddNoteActivity.class);
            intent.putExtra(mNOTE_TO_ADD_ACTIVITY, note);
            intent.putExtra(mCATEGORYId, mCategoryId);
            intent.putExtra(mCATEGORYName, mCategoryName);
            intent.putExtra(mIS_FIRST_CREATED, false);
            startActivity(intent);
        };

        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        mNoteRecyclerAdapter = new NoteRecyclerAdapter(this);
        mNoteRecyclerAdapter.setClickListener(mOnNoteClickListener);
        mNoteRecyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getApplicationContext(),
                DividerItemDecoration.VERTICAL);
        mNoteRecyclerView.addItemDecoration(dividerItemDecoration);
        mNoteViewModel = new NoteViewModel(getApplication());
        mNoteListLiveData = mNoteViewModel.getNotesByCategoryId(mCategoryId);
        mNoteList = mNoteViewModel.getNotesByCategoryIdList(mCategoryId);
        if (!mNoteList.isEmpty())
            mNoNotesTextView.setVisibility(View.GONE);
        mNoteListLiveData.observe(this, mNoteRecyclerAdapter::setNoteList);
        mNoteRecyclerView.setAdapter(mNoteRecyclerAdapter);
    }


    private void initAddNoteActivity() {
        mAddNoteFab = findViewById(R.id.note_list_fab);
        mAddNoteFab.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddNoteActivity.class);
            intent.putExtra(mCATEGORYId, mCategoryId);
            intent.putExtra(mCATEGORYName, mCategoryName);
            intent.putExtra(mIS_FIRST_CREATED, true);
            startActivity(intent);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.note_list_menu, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<Note> noteList = mNoteViewModel.getNotesByCategoryIdList(mCategoryId);
        if (noteList.isEmpty()) {
            mNoNotesTextView.setVisibility(View.VISIBLE);
        } else {
            mNoNotesTextView.setVisibility(View.GONE);
        }
    }


    private void enableSwipe() {
        NoteItemTouchHelperCallback noteItemTouchHelperCallback = new NoteItemTouchHelperCallback(this) {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                final int position = viewHolder.getAdapterPosition();
                final Note note = mNoteRecyclerAdapter.getNoteList().get(position);
                Parcel p = Parcel.obtain();
                note.writeToParcel(p, 0);
                p.setDataPosition(0);
                Note noteToReborn = Note.CREATOR.createFromParcel(p);
                p.recycle();

                mNoteRecyclerAdapter.removeNoteItem(position);
                mNoteViewModel.deleteNote(note);

                Snackbar snackbar = Snackbar
                        .make(mCoordinatorLayoutNoteListActivity,
                                getString(R.string.note_was_removed), Snackbar.LENGTH_LONG);
                snackbar.setAction(getString(R.string.return_note),view -> {
                    mNoteRecyclerAdapter.restoreItem(noteToReborn, position);
                    mNoteRecyclerView.scrollToPosition(position);
                    mNoteViewModel.addNote(noteToReborn);
                });
                snackbar.setActionTextColor(Color.YELLOW);
                snackbar.show();
            }

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                mNoteRecyclerAdapter.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
                return true;
            }
        };



        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(noteItemTouchHelperCallback);
        itemTouchhelper.attachToRecyclerView(mNoteRecyclerView);
    }
}