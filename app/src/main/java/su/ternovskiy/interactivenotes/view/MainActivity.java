package su.ternovskiy.interactivenotes.view;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import su.ternovskiy.interactivenotes.R;
import su.ternovskiy.interactivenotes.data.Category;


public class MainActivity extends AppCompatActivity{

    private Toolbar mMainToolbar;
    private FloatingActionButton mMainFab;
    private RecyclerView mCategoriesRecyclerView;
    private CategoryViewModel mCategoryViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }


    private void initViews() {

        mMainToolbar = findViewById(R.id.main_menu_toolbar);
        setSupportActionBar(mMainToolbar);

        mMainFab = findViewById(R.id.main_activity_fab);

        mCategoriesRecyclerView = findViewById(R.id.main_recycler_view);
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        final CategoriesRecyclerAdapter adapter = new CategoriesRecyclerAdapter(this);
        mCategoriesRecyclerView.setLayoutManager(layoutManager);
        mCategoriesRecyclerView.setAdapter(adapter);

        mCategoryViewModel = new CategoryViewModel(getApplication());

        mCategoryViewModel.getAllCategories().observe(this, adapter::setCategoryList);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_app_bar_menu, menu);
        return true;
    }

}
