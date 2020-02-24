package su.ternovskiy.interactivenotes.view.category;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import su.ternovskiy.interactivenotes.R;
import su.ternovskiy.interactivenotes.data.Category;
import su.ternovskiy.interactivenotes.view.note.NoteListActivity;


public class MainActivity extends AppCompatActivity {

    private String TAG = "MAINACTIVITY TAAAGGG";
    private Toolbar mMainToolbar;
    private FloatingActionButton mMainFab;
    private RecyclerView mCategoriesRecyclerView;
    private CategoryViewModel mCategoryViewModel;
    private DialogFragment mCategoryDialog;
    private OnCategoryClickListener mOnCategoryClickListener;


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
        final CategoriesRecyclerAdapter adapter = new CategoriesRecyclerAdapter(this);

        mOnCategoryClickListener = new OnCategoryClickListener() {
            @Override
            public void onItemClick(Category category) {
                Log.d(TAG, "onItemClick: SHORT");
                Intent intent = new Intent(getApplicationContext(), NoteListActivity.class);
                intent.putExtra("CATEGORY_NAME", category.getCategoryName());
                intent.putExtra("CATEGORY_ID",category.getId());
                startActivity(intent);

            }

            @Override
            public void onItemLongClick(Category category) {
                Log.d(TAG, "onItemLongClick: LONG");
            }
        };


        adapter.setClickListener(mOnCategoryClickListener);
        mCategoriesRecyclerView.setLayoutManager(layoutManager);
        mCategoriesRecyclerView.setAdapter(adapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getApplicationContext(),
                DividerItemDecoration.VERTICAL);
        mCategoriesRecyclerView.addItemDecoration(dividerItemDecoration);

        mCategoryViewModel = new CategoryViewModel(getApplication());

        mCategoryViewModel.getAllCategories().observe(this, adapter::setCategoryList);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_app_bar_menu, menu);
        return true;
    }


    private void initDialog() {
        mCategoryDialog = new CategoryDialog();
        mMainFab = findViewById(R.id.main_activity_fab);
        mMainFab.setOnClickListener(v -> {
            mCategoryDialog.show(getSupportFragmentManager(), "");
        });
    }


}
