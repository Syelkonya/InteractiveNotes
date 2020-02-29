package su.ternovskiy.interactivenotes.view.category;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import su.ternovskiy.interactivenotes.R;
import su.ternovskiy.interactivenotes.data.Category;
import su.ternovskiy.interactivenotes.data.Note;
import su.ternovskiy.interactivenotes.view.note.NoteListActivity;


public class MainActivity extends AppCompatActivity
        implements CategoryDialogUpdateOrDelete.CategoryDialogUpdateOrDeleteListener,
        CategoryDialogDelete.CategoryDialogDeleteListener,
        CategoryDialogUpdate.CategoryDialogUpdateListener {

    private String TAG = "MAINACTIVITY TAAAGGG";
    private Toolbar mMainToolbar;
    private FloatingActionButton mMainFab;
    private RecyclerView mCategoriesRecyclerView;
    private CategoryViewModel mCategoryViewModel;
    private DialogFragment mCategoryDialog;
    private DialogFragment mCategoryDialogUpdateOrDelete;
    private DialogFragment mCategoryDialogDelete;
    private DialogFragment mCategoryDialogUpdate;
    private OnCategoryClickListener mOnCategoryClickListener;
    private CategoriesRecyclerAdapter mAdapter;
    private LiveData<List<Category>> mCategoryList;
    private LiveData<List<Note>> mNoteList;
    private Category mCategoryOnItemLongClick;
    private final String mCategoryNameExtra = "CATEGORY_NAME";
    private final String mCategoryIdExtra = "CATEGORY_ID";
    private final int mUPDATE_WAS_PUSHED = 1;
    private final int mDELETE_WAS_PUSHED = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initDialog();
    }


    private void initViews() {

        mMainToolbar = findViewById(R.id.main_menu_toolbar);
        mMainFab = findViewById(R.id.main_activity_fab);
        mCategoriesRecyclerView = findViewById(R.id.main_recycler_view);

        setSupportActionBar(mMainToolbar);

        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        mAdapter = new CategoriesRecyclerAdapter(this);

        mOnCategoryClickListener = new OnCategoryClickListener() {
            @Override
            public void onItemClick(Category category) {
                Log.d(TAG, "onItemClick: SHORT");
                Intent intent = new Intent(getApplicationContext(), NoteListActivity.class);
                intent.putExtra(mCategoryNameExtra, category.getCategoryName());
                intent.putExtra(mCategoryIdExtra, category.getId());
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(Category category) {
                Log.d(TAG, "onItemLongClick: LONG");
                mCategoryDialogUpdateOrDelete = new CategoryDialogUpdateOrDelete();
                mCategoryDialogUpdateOrDelete.show(getSupportFragmentManager(), "UPDATE_OR_DELETE");
                mCategoryOnItemLongClick = category;
            }
        };

        mAdapter.setClickListener(mOnCategoryClickListener);
        mCategoriesRecyclerView.setLayoutManager(layoutManager);
        mCategoriesRecyclerView.setAdapter(mAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getApplicationContext(),
                DividerItemDecoration.VERTICAL);
        mCategoriesRecyclerView.addItemDecoration(dividerItemDecoration);

        mCategoryViewModel = new CategoryViewModel(getApplication());

        mCategoryList = mCategoryViewModel.getAllCategories();

        mCategoryViewModel.getAllCategories().observe(this, mAdapter::setCategoryList);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_app_bar_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mAdapter.getFilter().filter(newText);
                return true;
            }
        });
        return true;
    }


    private void initDialog() {
        mCategoryDialog = new CategoryDialogAdd();
        mMainFab = findViewById(R.id.main_activity_fab);
        mMainFab.setOnClickListener(v -> {
            mCategoryDialog.show(getSupportFragmentManager(), "CATEGORY_DIALOG");
        });
    }


    @Override
    public void whichButtonWasPushed(int pushedButton) {
        switch (pushedButton){
            case mDELETE_WAS_PUSHED:
                mCategoryDialogDelete = new CategoryDialogDelete();
                mCategoryDialogDelete.show(getSupportFragmentManager(), "DELETE_DIALOG");
                break;
            case mUPDATE_WAS_PUSHED:
                mCategoryDialogUpdate = new CategoryDialogUpdate();
                mCategoryDialogUpdate.show(getSupportFragmentManager(), "UPDATE_DIALOG");
                break;
        }
    }

    @Override
    public void isDeleteButtonPushedSecondly(Boolean isDeleteClicked) {
        if (isDeleteClicked)
        mCategoryViewModel.deleteCategory(mCategoryOnItemLongClick);
    }

    @Override
    public void isUpdateButtonClicked(String categoryNameToUpdate) {
        mCategoryOnItemLongClick.setCategoryName(categoryNameToUpdate);
        mCategoryViewModel.updateCategory(mCategoryOnItemLongClick);
    }


}
