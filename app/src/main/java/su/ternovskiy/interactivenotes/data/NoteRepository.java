package su.ternovskiy.interactivenotes.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteRepository {

    private NotesDao mNotesDao;
    private LiveData<List<Category>> mAllCategories;
    private LiveData<List<Note>> mNotesByCategoryId;
    private List<Long> mAllCategoryPositions;


    public NoteRepository(Application application) {
        NoteDatabase noteDatabase = NoteDatabase.getDatabase(application);
        mNotesDao = noteDatabase.getNotesDao();
        mAllCategories = mNotesDao.getAllCategories();
//        mAllCategoryPositions = mNotesDao.getAllCategoryPositions();
    }

    public LiveData<List<Category>> getAllCategories() {
        return mAllCategories;
    }

    public void addCategory(Category category){
        NoteDatabase.databaseWriteExecutor.execute(() -> {
            mNotesDao.addCategory(category);
        } );
    }

    public LiveData<List<Note>> getNotesByCategoryId(long categoryId){
        NoteDatabase.databaseWriteExecutor.execute(() ->{
            mNotesByCategoryId = mNotesDao.getNotesByCategoryId(categoryId);
        });
        return mNotesByCategoryId;
    }

    public void addNote(Note note){
        NoteDatabase.databaseWriteExecutor.execute(() -> {
          mNotesDao.addNote(note);
        });
    }

//    public List<Long> getAllCategoryPositions() {
//        return mAllCategoryPositions;
//    }
}
