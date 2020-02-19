package su.ternovskiy.interactivenotes.data;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteRepository {

    private NotesDao mNotesDao;
    private LiveData<List<Category>> mAllCategories;

    public NoteRepository(Application application) {
        NoteDatabase noteDatabase = NoteDatabase.getDatabase(application);
        mNotesDao = noteDatabase.getNotesDao();
        mAllCategories = mNotesDao.getAllCategories();
    }

    public LiveData<List<Category>> getAllCategories() {
        return mAllCategories;
    }

    public void addCategory(Category category){
        NoteDatabase.databaseWriteExecutor.execute(() -> {
            mNotesDao.addCategory(category);
        } );
    }
}
