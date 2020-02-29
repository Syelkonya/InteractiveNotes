package su.ternovskiy.interactivenotes.view.category;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import su.ternovskiy.interactivenotes.R;
import su.ternovskiy.interactivenotes.data.Category;

public class CategoriesRecyclerAdapter extends Adapter<CategoriesRecyclerAdapter.BaseViewHolder> implements Filterable {

    private List<Category> mCategoryList;
    private final LayoutInflater mLayoutInflater;
    private OnCategoryClickListener mCategoryClickListener;
    private List<Category> mCategoryListFull;


    CategoriesRecyclerAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }

    void setClickListener(@Nullable OnCategoryClickListener onCategoryClickListener) {
        mCategoryClickListener = onCategoryClickListener;
    }

    @NonNull
    @Override
    public CategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.categories_view_item, parent, false);
        return new CategoriesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        Category category = mCategoryList.get(position);
        ((CategoriesViewHolder) holder).bindView(category);
    }


    @Override
    public int getItemCount() {
        if (mCategoryList != null)
            return mCategoryList.size();
        else return 0;
    }

    void setCategoryList(List<Category> categories) {
        mCategoryList = categories;
        mCategoryListFull = new ArrayList<>(mCategoryList);
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        return mFilter;
    }


    static abstract class BaseViewHolder extends RecyclerView.ViewHolder {
        BaseViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    private class CategoriesViewHolder extends BaseViewHolder{

        private TextView mCategoryNameTextView;
        private TextView mQuantityOfNotes;
        private ImageView mCategoryIconImageView;

        private CategoriesViewHolder(@NonNull View itemView) {
            super(itemView);

            mCategoryNameTextView = itemView.findViewById(R.id.category_name_text_view);
            mQuantityOfNotes = itemView.findViewById(R.id.quantity_of_notes_text_view);
            mCategoryIconImageView = itemView.findViewById(R.id.category_icon_image_view);


        }


        void bindView(@NonNull Category category) {
            mCategoryNameTextView.setText(category.getCategoryName());
            mQuantityOfNotes.setText(Math.PI + "");
            mCategoryIconImageView.setImageResource(R.drawable.ic_launcher_background);
            itemView.setOnClickListener(v -> {
                if (mCategoryClickListener != null) {
                    mCategoryClickListener.onItemClick(category);
                }
            });
            itemView.setOnLongClickListener(v -> {
                if (mCategoryClickListener != null){
                    mCategoryClickListener.onItemLongClick(category);
                    return true;
                }else
                    return false;
            });
        }
    }


    private Filter mFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Category> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(mCategoryListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Category category : mCategoryListFull) {
                    if (category.getCategoryName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(category);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mCategoryList.clear();
            mCategoryList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}


