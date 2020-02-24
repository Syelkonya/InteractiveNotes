package su.ternovskiy.interactivenotes.data;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Database(entities = {Category.class, Note.class}, version = 1, exportSchema = false)
@TypeConverters({DataConverter.class})
public abstract class NoteDatabase extends RoomDatabase {

    public abstract NotesDao getNotesDao();

    private static volatile NoteDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static NoteDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (NoteDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            NoteDatabase.class, "note_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }


    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            databaseWriteExecutor.execute(() -> {
                NotesDao dao = INSTANCE.getNotesDao();
                dao.deleteAll();

                Category categoryS = new Category("SUPER", 2);
                dao.addCategory(categoryS);

                Category categoryFood = new Category("STAR", 1);
                dao.addCategory(categoryFood);

                Category categoryq = new Category("STA13R", 12);
                dao.addCategory(categoryq);
                Category categoryw = new Category("ST1231AR", 15);
                dao.addCategory(categoryw);
                Category categorye = new Category("ST12313AR", 41);
                dao.addCategory(categorye);
                Category categoryr = new Category("ST123AR", 18);
                dao.addCategory(categoryr);
                Category categoryt = new Category("ST123AR", 11);
                dao.addCategory(categoryt);



//                Note note = new Note();
//                note.setCategoryId(1);
//                note.setTitle("ZHOPA");
//                note.setText("V OGNE");
//                note.setDate(new Date(System.currentTimeMillis()));
//                dao.addNote(note);

            });

        }


        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecutor.execute(() -> {
                NotesDao dao = INSTANCE.getNotesDao();


            });
        }
    };
}
