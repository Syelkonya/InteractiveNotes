package su.ternovskiy.interactivenotes.view.category;

import android.view.View;

import androidx.annotation.NonNull;

import su.ternovskiy.interactivenotes.data.Category;

public interface OnCategoryClickListener {

    void onItemClick(Category category);

    void onItemLongClick(Category category);
}
