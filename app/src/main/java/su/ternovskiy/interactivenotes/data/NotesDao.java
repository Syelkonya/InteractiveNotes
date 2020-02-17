package su.ternovskiy.interactivenotes.data;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NotesDao {

    @Query("SELECT * FROM categories")
    List<Category> getCategories();
}
