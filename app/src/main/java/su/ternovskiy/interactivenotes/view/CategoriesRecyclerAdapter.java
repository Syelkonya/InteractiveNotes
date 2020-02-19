package su.ternovskiy.interactivenotes.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import java.util.List;

import su.ternovskiy.interactivenotes.R;
import su.ternovskiy.interactivenotes.data.Category;

public class CategoriesRecyclerAdapter extends Adapter<CategoriesRecyclerAdapter.CategoriesViewHolder> {

    private List<Category> mCategoryList;
    private final LayoutInflater mLayoutInflater;

    CategoriesRecyclerAdapter(Context context){
        mLayoutInflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public CategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        return new CategoriesViewHolder(
//                LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.categories_view_item, parent, false)
//        );

        View itemView = mLayoutInflater.inflate(R.layout.categories_view_item, parent, false);
        return new CategoriesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesViewHolder holder, int position) {
        holder.bindView(mCategoryList.get(position));
    }

    @Override
    public int getItemCount() {
        if (mCategoryList != null)
            return mCategoryList.size();
        else return 0;
    }

    void setCategoryList(List<Category> categories) {
        mCategoryList = categories;
        notifyDataSetChanged();
    }

    static class CategoriesViewHolder extends RecyclerView.ViewHolder{

        private TextView mCategoryNameTextView;
        private TextView mQuantityOfNotes;
        private ImageView mCategoryIconImageView;

        private CategoriesViewHolder(@NonNull View itemView) {
            super(itemView);

            mCategoryNameTextView = itemView.findViewById(R.id.category_name_text_view);
            mQuantityOfNotes = itemView.findViewById(R.id.quantity_of_notes_text_view);
            mCategoryIconImageView = itemView.findViewById(R.id.category_icon_image_view);
        }


        public void bindView(@NonNull Category category) {
            mCategoryNameTextView.setText(category.getCategoryName());
            mQuantityOfNotes.setText((int)(10*Math.random())+"");
            mCategoryIconImageView.setImageResource(R.drawable.ic_launcher_background);
        }
    }
}


