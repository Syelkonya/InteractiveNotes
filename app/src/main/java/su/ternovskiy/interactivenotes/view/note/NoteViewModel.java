package su.ternovskiy.interactivenotes.view.note;

import android.app.Application;

import androidx.lifecycle.LiveData;


import java.util.List;

import su.ternovskiy.interactivenotes.data.Note;
import su.ternovskiy.interactivenotes.data.NoteRepository;

class NoteViewModel {
    private NoteRepository mRepository;


    NoteViewModel(Application application){
        mRepository = new NoteRepository(application);
    }


    LiveData<List<Note>> getNotesByCategoryId(long categoryId){
        return mRepository.getNotesByCategoryId(categoryId);
    }

    List<Note> getNotesByCategoryIdList(long categoryId){
        return mRepository.getNotesByCategoryIdList(categoryId);
    }

    void addNote(Note note){
        mRepository.addNote(note);
    }

    void updateNote(Note note){
        mRepository.updateNote(note);
    }

    public void deleteNote(Note note){
       mRepository.deleteNote(note);
    }
}
