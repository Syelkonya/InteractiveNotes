package su.ternovskiy.interactivenotes.presentation.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import java.util.List;

import su.ternovskiy.interactivenotes.R;
import su.ternovskiy.interactivenotes.data.Category;

public class CategoriesRecyclerAdapter extends Adapter<CategoriesRecyclerAdapter.CategoriesViewHolder> {

    private List<Category> mCategoryList;

    @NonNull
    @Override
    public CategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CategoriesViewHolder(
                LayoutInflater.from(parent.getContext())
                .inflate(R.layout.categories_view_item, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class CategoriesViewHolder extends RecyclerView.ViewHolder{

        private TextView mCategoryNameTextView;
        private TextView mQuantityOfNotes;

        public CategoriesViewHolder(@NonNull View itemView) {
            super(itemView);

            mCategoryNameTextView = itemView.findViewById(R.id.category_name_text_view);
            mQuantityOfNotes = itemView.findViewById(R.id.quantity_of_notes_text_view);
        }


    }
}


