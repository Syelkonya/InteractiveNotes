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

    public NoteViewModel(Application application, long categoryId){
        mRepository = new NoteRepository(application);
        mNoteByCategoryId = mRepository.getNotesByCategoryId(categoryId);
    }



    LiveData<List<Note>> getNotesByCategoryId(){

//        if (mNoteByCategoryId == null) {
//            mNoteByCategoryId = new MutableLiveData<List<Note>>();
//        }
        return mNoteByCategoryId;
    }

    public void addNote(Note note){
        mRepository.addNote(note);
    }
}