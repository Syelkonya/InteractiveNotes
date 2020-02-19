package su.ternovskiy.interactivenotes.view;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import su.ternovskiy.interactivenotes.data.Category;
import su.ternovskiy.interactivenotes.data.NoteRepository;

public class CategoryViewModel {
    private NoteRepository mRepository;
    private LiveData<List<Category>> mAllCategories;

    public CategoryViewModel(Application application){
        mRepository = new NoteRepository(application);
        mAllCategories = mRepository.getAllCategories();
    }

    LiveData<List<Category>> getAllCategories(){
        return mAllCategories;
    }

    void addCategory(Category category){
        mRepository.addCategory(category);
    }
}
