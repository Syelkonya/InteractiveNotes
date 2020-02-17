package su.ternovskiy.interactivenotes.presentation.view;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import su.ternovskiy.interactivenotes.R;


public class MainActivity extends AppCompatActivity {

    private Toolbar mMainToolbar;
    private FloatingActionButton mMainFab;

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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_app_bar_menu, menu);
        return true;
    }
}
