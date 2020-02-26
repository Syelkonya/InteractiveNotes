package su.ternovskiy.interactivenotes.view.category;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.Collections;
import java.util.List;

import su.ternovskiy.interactivenotes.data.Category;
import su.ternovskiy.interactivenotes.data.NoteRepository;

public class CategoryViewModel {
    private NoteRepository mRepository;
    private LiveData<List<Category>> mAllCategories;
//    private List<Long> mAllCategoryPositions;

    public CategoryViewModel(Application application){
        mRepository = new NoteRepository(application);
        mAllCategories = mRepository.getAllCategories();
//        mAllCategoryPositions = mRepository.getAllCategoryPositions();
    }

    LiveData<List<Category>> getAllCategories(){
        return mAllCategories;
    }



    void addCategory(Category category){
        mRepository.addCategory(category);
    }



}
