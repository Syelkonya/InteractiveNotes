package su.ternovskiy.interactivenotes.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NotesDao {

    @Query("SELECT * FROM categories ORDER BY position ASC")
    LiveData<List<Category>> getAllCategories();

    @Insert
    void addCategory(Category category);

    @Query("DELETE FROM categories")
    void deleteAll();

    @Query("SELECT * FROM notes WHERE category_id = :categoryId")
    LiveData<List<Note>> getNotesByCategoryId(long categoryId);

    @Insert
    void addNote(Note note);
}
