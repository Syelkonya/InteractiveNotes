package su.ternovskiy.interactivenotes.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NotesDao {

    @Query("SELECT * FROM categories")
    LiveData<List<Category>> getAllCategories();

//    @Query("SELECT position FROM categories")
//    List<Long> getAllCategoryPositions();

    @Insert
    void addCategory(Category category);

    @Delete
    void deleteCategory(Category category);

    @Update
    void updateCategory(Category category);

    @Query("SELECT * FROM notes")
    LiveData<List<Note>> getAllNotes();

    @Query("SELECT * FROM notes WHERE category_id = :categoryId")
    LiveData<List<Note>> getNotesByCategoryId(long categoryId);

    @Insert
    void addNote(Note note);
}
