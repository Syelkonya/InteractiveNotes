package su.ternovskiy.interactivenotes.view.note;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import su.ternovskiy.interactivenotes.data.Note;
import su.ternovskiy.interactivenotes.data.NoteRepository;

public class NoteViewModel {
    private NoteRepository mRepository;
    private LiveData<List<Note>> mNoteByCategoryId;
    private LiveData<List<Note>> mAllNotes;

    public NoteViewModel(Application application){
        mRepository = new NoteRepository(application);
//        mNoteByCategoryId = mRepository.getNotesByCategoryId(categoryId);
    }

    LiveData<List<Note>> getAllNotes(){
        return mAllNotes;
    }

    LiveData<List<Note>> getNotesByCategoryId(){
        return mNoteByCategoryId;
    }

    public void addNote(Note note){
        mRepository.addNote(note);
    }
}
