package su.ternovskiy.interactivenotes.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;


@Database(entities = {Category.class, Note.class}, version = 1,exportSchema = false)
@TypeConverters({DataConverter.class})
public abstract class NoteDatabase extends RoomDatabase {

    public abstract NotesDao getNotesDao();
}
