package su.ternovskiy.interactivenotes.view.category;

import android.app.Activity;
import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.Collections;
import java.util.List;

import su.ternovskiy.interactivenotes.data.Category;
import su.ternovskiy.interactivenotes.data.Note;
import su.ternovskiy.interactivenotes.data.NoteRepository;

public class CategoryViewModel {
    private NoteRepository mRepository;
    private LiveData<List<Category>> mAllCategories;
    private int mQuantityOfNotesByCategory;


    CategoryViewModel(Application application){
        mRepository = new NoteRepository(application);
        mAllCategories = mRepository.getAllCategories();
    }


    int getQuantityOfNotes(long categoryId){
        return mRepository.getNotesByCategoryIdList(categoryId).size();
    }

    LiveData<List<Category>> getAllCategories(){
        return mAllCategories;
    }


    void addCategory(Category category){
        mRepository.addCategory(category);
    }

    void deleteCategory(Category category){
        mRepository.deleteCategory(category);
    }
    void updateCategory(Category category){
        mRepository.updateCategory(category);
    }



}
